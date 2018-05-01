package com.example.davide.suitup;

import android.content.Intent;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davide.suitup.DataModel.Capo;
import com.example.davide.suitup.DataModel.DataSource;


public class ArmadioActivity extends AppCompatActivity {

    //sfondo prova


    //Riferimenti alle view
    private ListView vListaCapi;
    private Button vFiltra;
    private Button vAggiungi;

    //Adapter e data source
    private CapiAdapter adapter;
    private DataSource dataSource;

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

        //recupero i riferimenti alle view
        vListaCapi = findViewById(R.id.listaCapi);
        vFiltra = findViewById(R.id.btnFiltra);
        vAggiungi = findViewById(R.id.btnAggiungi);

        // Riferimento al data source
        dataSource = DataSource.getInstance();

        //Creo adapter
        adapter = new CapiAdapter(this, dataSource.getElencoCapi());

        //Associo l'adapter alla list view
        vListaCapi.setAdapter(adapter);

        vAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditCapoActivity.class);
                startActivityForResult(intent, REQ_ADD_CAPO);
            }
        });

        registerForContextMenu(vListaCapi);


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
    // Selezione di un elemento nel context menu
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // All'interno di info.position posso leggere la posizione dell'elemento selezionato

        switch (item.getItemId()) {

            case R.id.itemDelete:
                // Eliminazione studente
                dataSource.deleteCapo(adapter.getItem(info.position).getNomeCapo());
                adapter.setElencoCapi(dataSource.getElencoCapi());
                return true;

            case R.id.itemEdit:
                // Modifica studente. Chiedo lo studente all'adapter e lo passo all'altra activiy
                Capo capo = adapter.getItem(info.position);
                nomeCorrente = capo.getNomeCapo();    // Salvo la matricola per poterla eventualmente modificare
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




}

