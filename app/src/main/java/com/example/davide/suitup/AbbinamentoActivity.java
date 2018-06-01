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

    private final String EMPTY = "empty";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abbinamento);

        textAbito = findViewById(R.id.textAbiti);
        textFelpa = findViewById(R.id.textFelpa);
        textMaglia = findViewById(R.id.textMaglie);
        textScarpe = findViewById(R.id.textScarpe);
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

        setRecyclerView(listaMaglie);
        setRecyclerView(listaPantaloni);
        setRecyclerView(listaScarpe);
        setRecyclerView(listaGiacche);
        setRecyclerView(listaGonne);
        setRecyclerView(listaCamicie);
        setRecyclerView(listaAbiti);
        setRecyclerView(listaFelpe);


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
                textFelpa.setVisibility(View.GONE);
                listaFelpe.setVisibility(View.GONE);
                listaMaglie.setVisibility(View.GONE);
                listaPantaloni.setVisibility(View.GONE);
                listaGonne.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                listaCamicie.setVisibility(View.GONE);
                //le liste che sono rimaste devono essere riempite
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Felpa:
                textFelpa.setVisibility(View.GONE);
                textCamicie.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaFelpe.setVisibility(View.GONE);
                listaCamicie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Gonna:
                textPantalone.setVisibility(View.GONE);
                textGonna.setVisibility(View.GONE);
                listaPantaloni.setVisibility(View.GONE);
                listaGonne.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Giacca:
                textGiacca.setVisibility(View.GONE);
                listaGiacche.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaAbiti, textAbito, Capo.Tipo.Abito, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Maglia:
                textMaglia.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaMaglie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Scarpe:
                textScarpe.setVisibility(View.GONE);
                listaScarpe.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaAbiti, textAbito, Capo.Tipo.Abito, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Camicia:
                textFelpa.setVisibility(View.GONE);
                textCamicie.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaFelpe.setVisibility(View.GONE);
                listaCamicie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Pantalone:
                textPantalone.setVisibility(View.GONE);
                textGonna.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaPantaloni.setVisibility(View.GONE);
                listaGonne.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
        }


    }
    private void setRecyclerView(RecyclerView recyclerView){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AbbinamentoActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }





}
