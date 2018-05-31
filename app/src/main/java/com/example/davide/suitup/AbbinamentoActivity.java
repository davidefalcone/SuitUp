package com.example.davide.suitup;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.davide.suitup.DataModel.Capo;
import com.example.davide.suitup.DataModel.DataSource;

public class AbbinamentoActivity extends AppCompatActivity {


    private Capo capo;
    private DataSource dataSource;
    private RecyclerView.LayoutManager mLayoutManager;

    //riferimenti alle view
    private TextView textMaglia;
    private TextView textPantalone;
    private TextView textScarpe;
    private TextView textGiacca;
    private TextView textGonna;
    private TextView textAbito;
    private TextView textFelpa;
    private TextView textCamicie;
    private RecyclerView listaMaglie;
    private RecyclerView listaPantaloni;
    private RecyclerView listaScarpe;
    private RecyclerView listaGiacche;
    private RecyclerView listaGonne;
    private RecyclerView listaAbiti;
    private RecyclerView listaFelpe;
    private RecyclerView listaCamicie;

    //chiave per il passaggio parametri dall'altra activity
    private final String EXTRA_CAPO = "capo";
    private final String EXTRA_TIPO = "tipo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abbinamento);

        textAbito = findViewById(R.id.textAbiti);
        textFelpa = findViewById(R.id.textFelpa);
        textMaglia = findViewById(R.id.textMaglie);
        textPantalone = findViewById(R.id.textPantaloni);
        textGiacca = findViewById(R.id.textGiacche);
        textGonna = findViewById(R.id.textGonne);
        textCamicie = findViewById(R.id.textCamicie);
        listaMaglie = findViewById(R.id.listaMaglie);
        listaPantaloni = findViewById(R.id.listaPantaloni);
        listaGiacche = findViewById(R.id.listaGiacche);
        listaGonne = findViewById(R.id.listaGonne);
        listaScarpe = findViewById(R.id.listaScarpe);
        listaCamicie = findViewById(R.id.listaCamicie);
        listaFelpe = findViewById(R.id.listaFelpe);
        listaAbiti = findViewById(R.id.listaAbiti);

        setRecyclerViews();

        dataSource = DataSource.getInstance();


        if (capo == null)
            capo = (Capo) getIntent().getSerializableExtra(EXTRA_CAPO);

        switch (capo.getTipo()) {
            case Abito:
                //nascondo i testo e lista dei tipi che non mi servono: un abito non verrà mai abinato ad un pantalone
                textMaglia.setVisibility(View.GONE);
                textPantalone.setVisibility(View.GONE);
                textGonna.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                textCamicie.setVisibility(View.GONE);
                listaMaglie.setVisibility(View.GONE);
                listaPantaloni.setVisibility(View.GONE);
                listaGonne.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                listaCamicie.setVisibility(View.GONE);
                //le liste che sono rimaste devono essere riempite
                dataSource.cercaCapiAbbinabili(listaScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Felpa:
                textFelpa.setVisibility(View.GONE);
                textCamicie.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaFelpe.setVisibility(View.GONE);
                listaCamicie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaMaglie, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGonne, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Gonna:
                textPantalone.setVisibility(View.GONE);
                textGonna.setVisibility(View.GONE);
                listaPantaloni.setVisibility(View.GONE);
                listaGonne.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaMaglie, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaFelpe, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Giacca:
                textGiacca.setVisibility(View.GONE);
                listaGiacche.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaMaglie, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaPantaloni, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGonne, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaAbiti, Capo.Tipo.Abito, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Maglia:
                textMaglia.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaMaglie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaPantaloni, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGonne, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Scarpe:
                textScarpe.setVisibility(View.GONE);
                listaScarpe.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaMaglie, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaPantaloni, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGonne, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Camicia:
                textFelpa.setVisibility(View.GONE);
                textCamicie.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaFelpe.setVisibility(View.GONE);
                listaCamicie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaMaglie, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGonne, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Pantalone:
                textPantalone.setVisibility(View.GONE);
                textGonna.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaPantaloni.setVisibility(View.GONE);
                listaGonne.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaMaglie, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaFelpe, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
        }


    }

    private void setRecyclerViews(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaPantaloni.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        listaPantaloni.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        listaGiacche.setLayoutManager(linearLayoutManager1);
        listaGiacche.setHasFixedSize(true);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        listaGonne.setLayoutManager(linearLayoutManager2);
        listaGonne.setHasFixedSize(true);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this);
        listaScarpe.setLayoutManager(linearLayoutManager3);
        listaScarpe.setHasFixedSize(true);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(this);
        listaCamicie.setLayoutManager(linearLayoutManager4);
        listaCamicie.setHasFixedSize(true);
        linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager5 = new LinearLayoutManager(this);
        listaFelpe.setLayoutManager(linearLayoutManager5);
        listaFelpe.setHasFixedSize(true);
        linearLayoutManager5.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager6 = new LinearLayoutManager(this);
        listaAbiti.setLayoutManager(linearLayoutManager6);
        listaAbiti.setHasFixedSize(true);
        linearLayoutManager6.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager7 = new LinearLayoutManager(this);
        listaMaglie.setLayoutManager(linearLayoutManager7);
        linearLayoutManager7.setOrientation(LinearLayoutManager.HORIZONTAL);
        listaMaglie.setHasFixedSize(true);
    }



}
