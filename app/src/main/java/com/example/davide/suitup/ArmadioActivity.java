package com.example.davide.suitup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davide.suitup.DataModel.Capo;
import com.example.davide.suitup.DataModel.DataSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ArmadioActivity extends AppCompatActivity {

    //Riferimenti alle view
    private ListView vListaCapi;
    private Toolbar vToolbar;
    private ImageView vUserImage;

    //Adapter e data source
    private DataSource dataSource;
    private CapiAdapter adapter;

    private FirebaseUser user;

    //chiave per il passaggio di parametri alla nuova activity
    private final String EXTRA_CAPO = "capo";


    // Costanti con i result code
    private final int REQ_ADD_CAPO = 1;
    private final int REQ_EDIT_CAPO = 2;

    private String nomeCorrente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armadio);

        user = FirebaseAuth.getInstance().getCurrentUser();

        //recupero i riferimenti alle view
        vListaCapi = findViewById(R.id.listaCapi);
        vToolbar = findViewById(R.id.my_toolbar);

        // Riferimento al data source
        dataSource = DataSource.getInstance();
        adapter = new CapiAdapter(this, dataSource.getElencoCapi());
        dataSource.popolaDataSource(vListaCapi, this, adapter);

        registerForContextMenu(vListaCapi);

        //imposto la toolbar
        setToolbar();

        //Imposto il listner per il click sulla listview

        vListaCapi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Capo capo = (Capo)adapter.getItem(i);

                Intent intent = new Intent(view.getContext(), CapoActivity.class);

                intent.putExtra(EXTRA_CAPO, capo);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //faccio inflate per creare gli item della tollbar
        getMenuInflater().inflate(R.menu.action_bar_items, menu);
        //cerco la vieo account setting
        final MenuItem alertMenuItem = menu.findItem(R.id.account_settings);
        //ottengo il riferimento a tale view
        ConstraintLayout v = (ConstraintLayout) alertMenuItem.getActionView();
        //ottengo il riferimento all'immagine dentro la view
        vUserImage = v.findViewById(R.id.imageUser);
        //inserisco la fto dell'utente dentro l'imageview
        GlideApp.with(this).load(user.getPhotoUrl()).into(vUserImage);
        vUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filtra:
                // l'utente ha scelto filtra
                createFilter();
                return true;

            case R.id.aggiungi_capo:
                // l'utente intende aggiungere un nuovo capo
                Intent intent = new Intent(getApplicationContext(), EditCapoActivity.class);
                startActivityForResult(intent, REQ_ADD_CAPO);
                return true;

                default:
                //se ne occupa la superclasse (l'ho letto sulla documentazione)
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQ_ADD_CAPO :
                if (resultCode == RESULT_OK){

                    Capo capo = (Capo) data.getSerializableExtra(EXTRA_CAPO);

                    if(capo != null){
                        dataSource.addCapo(capo);
                        adapter.setElencoCapi(dataSource.getElencoCapi());
                        }
                }
                break;

            case REQ_EDIT_CAPO :
                if (resultCode ==RESULT_OK) {

                    Capo capo = (Capo) data.getSerializableExtra(EXTRA_CAPO);

                    if (capo != null){
                        dataSource.deleteCapo(nomeCorrente);
                        dataSource.addCapo(capo);
                        adapter.setElencoCapi(dataSource.getElencoCapi());
                    }
                }
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // All'interno di info.position posso leggere la posizione dell'elemento selezionato

        switch (item.getItemId()) {

            case R.id.itemDelete:
                // Eliminazione capo
                dataSource.deleteCapo(adapter.getItem(info.position).getNomeCapo());
                adapter.setElencoCapi(dataSource.getElencoCapi());
                //quando rimuovo un capo, elimino la relativa foto nello storage
                ImageDelete(adapter.getItem(info.position).getNomeCapo());
                return true;

            case R.id.itemEdit:
                // Modifica capo
                Capo capo = adapter.getItem(info.position);
                nomeCorrente = capo.getNomeCapo();
                Intent intent = new Intent(getApplicationContext(), EditCapoActivity.class);
                intent.putExtra(EXTRA_CAPO, capo);
                // Faccio partire l'activiy in modalità edit
                startActivityForResult(intent, REQ_EDIT_CAPO);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    //creazione del context menu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_capi, menu);
    }

    public void ImageDelete (String nomeCapo) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        StorageReference imagePath = FirebaseStorage.getInstance().getReference().child(user.getUid());
        imagePath.child(nomeCapo+".jpg").delete();
}

    public void setToolbar(){
        setSupportActionBar(vToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void createDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(ArmadioActivity.this);
        alert.setTitle(R.string.effettuare_logout);
        alert.setView(R.layout.empty_fragment);

        //imposto i pulsanti ok e annulla
        alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ArmadioActivity.this, GoogleSignInActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton(R.string.annulla, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //mostro il popup
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    private void createFilter() {
        AlertDialog.Builder alert = new AlertDialog.Builder(ArmadioActivity.this);
        alert.setTitle(R.string.filtra_ricerca);
        alert.setMessage(R.string.ricerca_per);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.filtro_layout, null);
        alert.setView(view);

        //riferimenti alle view nel dialog
        final CheckBox vCheckTipo = view.findViewById(R.id.checkTipo);
        final CheckBox vCheckOccasione = view.findViewById(R.id.checkOccasione);
        final CheckBox vCheckStagione = view.findViewById(R.id.checkStagione);
        final Spinner vSpinnerTipo = view.findViewById(R.id.spinnerTipo);
        final Spinner vSpinnerOccasione = view.findViewById(R.id.spinnerOccasione);
        final Spinner vSpinnerStagione = view.findViewById(R.id.spinnerStagione);
        final boolean checked[] = new boolean[3];

        //oscuro gli spinner
        vSpinnerTipo.setVisibility(View.INVISIBLE);
        vSpinnerOccasione.setVisibility(View.INVISIBLE);
        vSpinnerStagione.setVisibility(View.INVISIBLE);

        //imposto gli spinner
        final ArrayAdapter<Capo.Tipo> tipoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Capo.Tipo.values());
        vSpinnerTipo.setAdapter(tipoAdapter);
        final ArrayAdapter<Capo.Occasione> occasioneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Capo.Occasione.values());
        vSpinnerOccasione.setAdapter(occasioneAdapter);
        final ArrayAdapter<Capo.Stagione> stagioneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Capo.Stagione.values());
        vSpinnerStagione.setAdapter(stagioneAdapter);

        vCheckTipo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    vSpinnerTipo.setVisibility(View.VISIBLE);
                else vSpinnerTipo.setVisibility(View.INVISIBLE);
            }
        });


        vCheckOccasione.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    vSpinnerOccasione.setVisibility(View.VISIBLE);
                else vSpinnerOccasione.setVisibility(View.INVISIBLE);
            }
        });

        vCheckStagione.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    vSpinnerStagione.setVisibility(View.VISIBLE);
                else vSpinnerStagione.setVisibility(View.INVISIBLE);
            }
        });

        //imposto i pulsanti applica e annulla
        alert.setPositiveButton(R.string.applica, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Filtro filtro = Filtro.getInstance();
                if(checked[0] = vCheckTipo.isChecked())
                    filtro.setTipo((Capo.Tipo)vSpinnerTipo.getSelectedItem());
                else filtro.setTipo(null);

                if(checked[1] = vCheckOccasione.isChecked())
                    filtro.setOccasione((Capo.Occasione)vSpinnerOccasione.getSelectedItem());
                else filtro.setOccasione(null);

                if(checked[2] = vCheckStagione.isChecked())
                    filtro.setStagione((Capo.Stagione)vSpinnerStagione.getSelectedItem());
                else filtro.setStagione(null);

                filtro.setChecked(checked);
                dataSource.filtraRicerca(vListaCapi,ArmadioActivity.this, adapter);
            }
        });
        alert.setNegativeButton(R.string.annulla, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //mostro il popup
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

}

