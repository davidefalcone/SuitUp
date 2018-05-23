package com.example.davide.suitup.DataModel;

import android.content.Context;
import android.widget.ListView;

import com.example.davide.suitup.CapiAdapter;
import com.example.davide.suitup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.OnProgressListener;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static com.example.davide.suitup.DataModel.Capo.Occasione.Casual;
import static com.example.davide.suitup.DataModel.Capo.Occasione.Elegante;
import static com.example.davide.suitup.DataModel.Capo.Occasione.Sportivo;
import static com.example.davide.suitup.DataModel.Capo.Stagione.PrimaveraEstate;
import static com.example.davide.suitup.DataModel.Capo.Stagione.AutunnoInverno;
import static com.example.davide.suitup.DataModel.Capo.Tipo.Felpa;
import static com.example.davide.suitup.DataModel.Capo.Tipo.Maglia;
import static com.example.davide.suitup.DataModel.Capo.Tipo.Pantalone;
import static com.example.davide.suitup.DataModel.Capo.Tipo.Scarpe;

public class DataSource {
    //riferimento all'user corrente
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    //riferiento al database
    private DatabaseReference userStorage = FirebaseDatabase.getInstance().getReference().child(user.getUid());

    // Lista locale
    private ArrayList<Capo> elencoCapi;

    // Unica instanza
    private static DataSource instance = null;

    // Costruttore privato
    private DataSource() {
        elencoCapi = new ArrayList<>();
    }


    public static DataSource getInstance() {
        if (instance == null)
            instance = new DataSource();
        return instance;
    }


    public void addCapo(Capo capo) {
        userStorage.child(capo.getNomeCapo()).setValue(capo);
    }


    public void deleteCapo(String nomeCapo) {
        for (int i = 0; i<elencoCapi.size(); i++){
            if(nomeCapo == elencoCapi.get(i).getNomeCapo()) {
                userStorage.child(nomeCapo).removeValue();
                break;
            }
        }
    }


    public Capo getCapo(String nomeCapo) {
        Capo capo = null;
        for (int i = 0; i<elencoCapi.size(); i++){
            if(nomeCapo == elencoCapi.get(i).getNomeCapo()) {
                capo = elencoCapi.get(i);
            }
        }return capo;
    }


        public List<Capo> getElencoCapi() {
        return elencoCapi;
    }

    public void popolaDataSource(final ListView listView,final Context context,final CapiAdapter adapter) {
        userStorage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                elencoCapi.clear();
                for (DataSnapshot capo : dataSnapshot.getChildren()) {
                    elencoCapi.add(capo.getValue(Capo.class));
                }
                adapter.setElencoCapi(elencoCapi);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}