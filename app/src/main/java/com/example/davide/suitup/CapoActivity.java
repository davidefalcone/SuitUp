package com.example.davide.suitup;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.davide.suitup.DataModel.Capo;
import android.support.v4.app.Fragment;

import java.util.ArrayList;


public class CapoActivity extends AppCompatActivity {

    //adapter
    private CapoColoriAdapter adapter;

    //creo i riferimenti alle view
    private ImageView vImageview;
    private Button vElimina;
    private Button vAbbina;
    private TextView vNomeCapo;
    private TextView vOccasioneStagione;
    private FragmentManager fm;


    //chiave per il passaggio parametri dall'altra activity
    private final String EXTRA_CAPO = "capo";
    private final String EXTRA_COLORI = "colori";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capo);

        //imposto i riferimenti alle view
        vImageview = findViewById(R.id.imageCapo);
        vElimina = findViewById(R.id.eliminabtn);
        vAbbina = findViewById(R.id.btnOk);
        vNomeCapo = findViewById(R.id.textNome);
        vOccasioneStagione = findViewById(R.id.textOccasioneStagione);
        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null ) {
            fragment = new HorizontalListViewFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }

        //ottengo i dati forniti dall'activity precedente
        Intent intent = getIntent();
        Capo capo = (Capo)intent.getSerializableExtra(EXTRA_CAPO);

        if(capo != null){
            vNomeCapo.setText(capo.getNomeCapo());
            vOccasioneStagione.setText(capo.getOccasione()+" • "+capo.getStagione());
            Bundle bundle = new Bundle();
            adapter = new CapoColoriAdapter(capo.getColori());
            if(adapter!=null)
            bundle.putSerializable(EXTRA_COLORI, adapter);
            fragment.setArguments(bundle);
        }




    }
}
