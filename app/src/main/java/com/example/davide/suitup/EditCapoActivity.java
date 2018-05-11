package com.example.davide.suitup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.davide.suitup.DataModel.Capo;
import com.example.davide.suitup.DataModel.Colore;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EditCapoActivity extends AppCompatActivity {

    //attributi
    private EditColoriAdapter coloriAdapter = new EditColoriAdapter();
    private ArrayList<Colore> listaColori = Colore.Tuttiicolori();

    private Capo capo;

    //riferimenti alle view
    private Button vOk;
    private Button vAnnulla;
    private EditText vNome;
    private Spinner vTipo;
    private Spinner vOccasione;
    private Spinner vStagione;
    private FragmentManager fm;
    private Button vAggiungi;
    private Fragment fragment;
    private ListView vListacolori;

    private final String EXTRA_CAPO = "capo";
    private final String EXTRA_COLORI = "colori";
    private final String ACTIVITY_CHIAMANTE = "activity";
    private final int ACTIVITY_EDIT_CAPO = 1;
    private final int ACTIVITY_ADD_CAPO = 2;

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
        vAggiungi = findViewById(R.id.btnAggiungi);

        //riferimento al fragment
        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.fragmentContainer);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        //inizializzo il fragment con la classe Horizontal list view
        if (fragment == null ) {
            fragment = new HorizontalListViewFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }

        //imposto la posizione del pulsante aggiungi colore


        //imposto gli spinner
        final ArrayAdapter<Capo.Tipo> tipoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,Capo.Tipo.values());
        vTipo.setAdapter(tipoAdapter);
        final ArrayAdapter<Capo.Occasione> occasioneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,Capo.Occasione.values());
        vOccasione.setAdapter(occasioneAdapter);
        final ArrayAdapter<Capo.Stagione> stagioneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,Capo.Stagione.values());
        vStagione.setAdapter(stagioneAdapter);

        //ottengo i dati passati da lista capi activity
        final Intent intent = getIntent();
        capo = (Capo) intent.getSerializableExtra(EXTRA_CAPO);

        final Bundle bundle = new Bundle();

        if (capo !=  null) {
            vNome.setText(capo.getNomeCapo());
            vTipo.setSelection(capo.getTipo().ordinal());
            vStagione.setSelection(capo.getStagione().ordinal());
            vOccasione.setSelection(capo.getOccasione().ordinal());
            coloriAdapter = new EditColoriAdapter(capo.getColori());
            listaColori.clear();
            listaColori = Colore.ColoriRimanenti(capo);
            bundle.putSerializable(EXTRA_COLORI, coloriAdapter);
            fragment.setArguments(bundle);
        }
        coloriAdapter = new EditColoriAdapter();
        bundle.putSerializable(EXTRA_COLORI, coloriAdapter);
        fragment.setArguments(bundle);


        vAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle(R.string.seleziona_colore);
                alert.setView(R.layout.fragment_scegli_colore);
                final ColoriAdapter adapter = new ColoriAdapter(listaColori, getApplicationContext());
                alert.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if ( capo == null ){
                            capo = new Capo();
                            }
                            capo.getColori().add(listaColori.get(i));
                            coloriAdapter.setListaColori(capo.getColori());
                            listaColori.remove(i);
                    }
                });

                //mostro il popup
                final AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        //imposto il pulsante ok
        vOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capo = leggiDatiCapo();
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
        if(capo == null)
            capo = new Capo();
        capo.setNomeCapo(vNome.getText().toString());
        capo.setTipo(Capo.Tipo.values()[vTipo.getSelectedItemPosition()]);
        capo.setStagione(Capo.Stagione.values()[vStagione.getSelectedItemPosition()]);
        capo.setOccasione(Capo.Occasione.values()[vOccasione.getSelectedItemPosition()]);
        capo.setColori(coloriAdapter.getListaColori());
        if(capo.getNomeCapo().length()>=1 && capo.getColori().size()>=1){
            return capo;
        }else return null;
    }

}
