package com.example.davide.suitup;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.davide.suitup.DataModel.Capo;
import com.example.davide.suitup.DataModel.Colore;

import java.lang.reflect.Array;

public class EditCapoActivity extends AppCompatActivity {

    //riferimenti alle view
    private Button vOk;
    private Button vAnnulla;
    private EditText vNome;
    private Spinner vTipo;
    private Spinner vOccasione;
    private Spinner vStagione;
    private FragmentManager fm;

    private final String EXTRA_CAPO = "capo";
    private final String EXTRA_COLORI = "colori";
    private final String ACTIVITY_CHIAMANTE = "activity";
    private final int ACTIVITY_EDIT_CAPO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_capo);


        //ottengo i riferimenti alle view
        vOk = findViewById(R.id.btnOk);
        vAnnulla = findViewById(R.id.btnAnnulla);
        vNome = findViewById(R.id.editNome);
        vTipo = findViewById(R.id.spnTipo);
        vOccasione = findViewById(R.id.spnOccasione);
        vStagione = findViewById(R.id.spnStagione);
        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        //imposto il fragment per la lista colori
        if (fragment == null ) {
            fragment = new HorizontalListViewFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }

        //imposto gli spinner
        ArrayAdapter<Capo.Tipo> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,Capo.Tipo.values());
        vTipo.setAdapter(adapter);
        ArrayAdapter<Capo.Occasione> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,Capo.Occasione.values());
        vOccasione.setAdapter(adapter1);
        ArrayAdapter<Capo.Stagione> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,Capo.Stagione.values());
        vStagione.setAdapter(adapter2);

        //ottengo i dati passati da lista capi activity
        final Intent intent = getIntent();
        Capo capo = (Capo) intent.getSerializableExtra(EXTRA_CAPO);

        if (capo !=  null) {
            vNome.setText(capo.getNomeCapo());
            vTipo.setSelection(capo.getTipo().ordinal());
            vStagione.setSelection(capo.getStagione().ordinal());
            vOccasione.setSelection(capo.getOccasione().ordinal());
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(EXTRA_COLORI, capo.getColori());
            bundle.putInt(ACTIVITY_CHIAMANTE, ACTIVITY_EDIT_CAPO);
            fragment.setArguments(bundle);
        }

        //imposto il pulsante ok
        vOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Capo capo = leggiDatiCapo();
                if (capo != null) {
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_CAPO, capo);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),R.string.dati_obbligatori, Toast.LENGTH_LONG).show();
                    }
            }
        });

        //imposto il pulsante annulla
        vAnnulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private Capo leggiDatiCapo() {
        Capo capo = new Capo();
        capo.setNomeCapo(vNome.getText().toString());
        capo.setTipo(Capo.Tipo.values()[vTipo.getSelectedItemPosition()]);
        capo.setStagione(Capo.Stagione.values()[vStagione.getSelectedItemPosition()]);
        capo.setOccasione(Capo.Occasione.values()[vOccasione.getSelectedItemPosition()]);
        capo.setColori(ColoriAdapter.getInstance().getListaColori());
        return capo;
    }
}
