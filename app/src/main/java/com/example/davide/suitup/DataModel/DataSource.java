package com.example.davide.suitup.DataModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.davide.suitup.AbbinamentiAdapter;
import com.example.davide.suitup.CapiAdapter;
import com.example.davide.suitup.Filtro;
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
import static com.example.davide.suitup.DataModel.Colore.coloriAbbinabili;

public class DataSource {
    //riferimento all'user corrente
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    //riferiento al database
    private DatabaseReference userStorage = FirebaseDatabase.getInstance().getReference().child(user.getUid());

    //TAG
    private final String EMPTY = "empty";

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


    public void deleteCapo(final String nomeCapo) {
        userStorage.child(nomeCapo).removeValue();
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

    public void filtraRicerca(final  ListView listView,final CapiAdapter adapter){
        final Filtro filtro = Filtro.getInstance();
        final boolean checked[] = filtro.getChecked();
        int n = (checked[0] ? 4 : 0) + (checked[1] ? 2 : 0) + (checked[2] ? 1 : 0);
        switch (n) {
            case 0:
                //l'utente non ha settato nessun flag
                userStorage.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        elencoCapi.clear();
                        for (DataSnapshot capo : dataSnapshot.getChildren()) {
                            elencoCapi.add(capo.getValue(Capo.class));
                        }
                        adapter.setElencoCapi(elencoCapi);
                        listView.setAdapter(adapter);
                        filtro.setTipo(null);
                        filtro.setOccasione(null);
                        filtro.setStagione(null);
                        for (int i = 0;i<3;i++)
                            checked[i] = false;
                        filtro.setChecked(checked);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
                break;

            case 1:
                //solo stagione
                userStorage.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        elencoCapi.clear();
                        for (DataSnapshot capo : dataSnapshot.getChildren()) {
                            if(capo.getValue(Capo.class).getStagione() == filtro.getStagione())
                            elencoCapi.add(capo.getValue(Capo.class));
                        }
                        adapter.setElencoCapi(elencoCapi);
                        listView.setAdapter(adapter);
                        filtro.setTipo(null);
                        filtro.setOccasione(null);
                        filtro.setStagione(null);
                        for (int i = 0;i<3;i++)
                            checked[i] = false;
                        filtro.setChecked(checked);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
                break;
            case 2:
                //solo occasione
                userStorage.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        elencoCapi.clear();
                        for (DataSnapshot capo : dataSnapshot.getChildren()) {
                            if(capo.getValue(Capo.class).getOccasione() == filtro.getOccasione())
                            elencoCapi.add(capo.getValue(Capo.class));
                        }
                        adapter.setElencoCapi(elencoCapi);
                        listView.setAdapter(adapter);
                        filtro.setTipo(null);
                        filtro.setOccasione(null);
                        filtro.setStagione(null);
                        for (int i = 0;i<3;i++)
                            checked[i] = false;
                        filtro.setChecked(checked);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
                break;
            case 3:
                //occasione e stagione
                userStorage.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        elencoCapi.clear();
                        for (DataSnapshot capo : dataSnapshot.getChildren()) {
                            if (capo.getValue(Capo.class).getOccasione() == filtro.getOccasione() && capo.getValue(Capo.class).getStagione() == filtro.getStagione())
                            elencoCapi.add(capo.getValue(Capo.class));
                        }
                        adapter.setElencoCapi(elencoCapi);
                        listView.setAdapter(adapter);
                        filtro.setTipo(null);
                        filtro.setOccasione(null);
                        filtro.setStagione(null);
                        for (int i = 0;i<3;i++)
                            checked[i] = false;
                        filtro.setChecked(checked);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
                break;
            case 4:
                //solo tipo
                userStorage.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        elencoCapi.clear();
                        for (DataSnapshot capo : dataSnapshot.getChildren()) {
                            if(capo.getValue(Capo.class).getTipo() == filtro.getTipo())
                            elencoCapi.add(capo.getValue(Capo.class));
                        }
                        adapter.setElencoCapi(elencoCapi);
                        listView.setAdapter(adapter);
                        filtro.setTipo(null);
                        filtro.setOccasione(null);
                        filtro.setStagione(null);
                        for (int i = 0;i<3;i++)
                            checked[i] = false;
                        filtro.setChecked(checked);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
                break;
            case 5:
                //tipo e stagione
                userStorage.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        elencoCapi.clear();
                        for (DataSnapshot capo : dataSnapshot.getChildren()) {
                            if(capo.getValue(Capo.class).getTipo() == filtro.getTipo() && capo.getValue(Capo.class).getStagione() == filtro.getStagione())
                            elencoCapi.add(capo.getValue(Capo.class));
                        }
                        adapter.setElencoCapi(elencoCapi);
                        listView.setAdapter(adapter);
                        filtro.setTipo(null);
                        filtro.setOccasione(null);
                        filtro.setStagione(null);
                        for (int i = 0;i<3;i++)
                            checked[i] = false;
                        filtro.setChecked(checked);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
                break;
            case 6:
                //tipo e occasione
                userStorage.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        elencoCapi.clear();
                        for (DataSnapshot capo : dataSnapshot.getChildren()) {
                            if(capo.getValue(Capo.class).getTipo() == filtro.getTipo() && capo.getValue(Capo.class).getOccasione() == filtro.getOccasione())
                            elencoCapi.add(capo.getValue(Capo.class));
                        }
                        adapter.setElencoCapi(elencoCapi);
                        listView.setAdapter(adapter);
                        filtro.setTipo(null);
                        filtro.setOccasione(null);
                        filtro.setStagione(null);
                        for (int i = 0;i<3;i++)
                            checked[i] = false;
                        filtro.setChecked(checked);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
                break;
                case 7 :
                //tutti flaggati
                    userStorage.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            elencoCapi.clear();
                            for (DataSnapshot capo : dataSnapshot.getChildren()) {
                                if(capo.getValue(Capo.class).getTipo() == filtro.getTipo() && capo.getValue(Capo.class).getOccasione() == filtro.getOccasione() && capo.getValue(Capo.class).getStagione() == filtro.getStagione())
                                elencoCapi.add(capo.getValue(Capo.class));
                            }
                            adapter.setElencoCapi(elencoCapi);
                            listView.setAdapter(adapter);
                            filtro.setTipo(null);
                            filtro.setOccasione(null);
                            filtro.setStagione(null);
                            for (int i = 0;i<3;i++)
                                checked[i] = false;
                            filtro.setChecked(checked);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                    break;
        }

       }

    public void popolaDataSource(final ListView listView,final CapiAdapter adapter) {
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

    public void cercaCapiAbbinabili (final RecyclerView recyclerView, final TextView textView, final Capo.Tipo tipo, final ArrayList<Colore> colori, final Context context, final Capo.Stagione stagione, final Capo.Occasione occasione, final AbbinamentiAdapter adapter) {
        userStorage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                elencoCapi = new ArrayList<Capo>();
                for (DataSnapshot capo : dataSnapshot.getChildren()) {
                    if (capo.getValue(Capo.class).getTipo() == tipo && coloriAbbinabili(colori, capo.getValue(Capo.class).getColori()) && occasione == capo.getValue(Capo.class).getOccasione() && stagione == capo.getValue(Capo.class).getStagione()) {
                        elencoCapi.add(capo.getValue(Capo.class));
                        }
                }
                if (elencoCapi.size()>0) {
                    adapter.setListaAbbinamenti(elencoCapi);
                    recyclerView.setAdapter(adapter);
                }else
                    textView.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}