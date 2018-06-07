package com.example.davide.suitup;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.davide.suitup.DataModel.Capo;
import com.example.davide.suitup.DataModel.Colore;
import com.example.davide.suitup.DataModel.DataSource;
import com.example.davide.suitup.DataModel.Outfit;

import java.util.ArrayList;

public class AbbinamentoActivity extends AppCompatActivity {


    private Capo capo;
    private DataSource dataSource;
    private Outfit outfit;

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
    private AbbinamentiAdapter maglieAdapter;
    private AbbinamentiAdapter pantaloniAdapter;
    private AbbinamentiAdapter scarpeAdapter;
    private AbbinamentiAdapter gonneAdapter;
    private AbbinamentiAdapter giaccheAdapter;
    private AbbinamentiAdapter felpeAdapter;
    private AbbinamentiAdapter camicieAdapter;
    private AbbinamentiAdapter[] adapters;


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
        maglieAdapter = new AbbinamentiAdapter(AbbinamentoActivity.this);
        scarpeAdapter = new AbbinamentiAdapter(AbbinamentoActivity.this);
        pantaloniAdapter = new AbbinamentiAdapter(AbbinamentoActivity.this);
        felpeAdapter = new AbbinamentiAdapter(AbbinamentoActivity.this);
        camicieAdapter = new AbbinamentiAdapter(AbbinamentoActivity.this);
        gonneAdapter = new AbbinamentiAdapter(AbbinamentoActivity.this);
        giaccheAdapter = new AbbinamentiAdapter(AbbinamentoActivity.this);




        setRecyclerView(listaMaglie);
        setRecyclerView(listaPantaloni);
        setRecyclerView(listaScarpe);
        setRecyclerView(listaGiacche);
        setRecyclerView(listaGonne);
        setRecyclerView(listaCamicie);
        setRecyclerView(listaAbiti);
        setRecyclerView(listaFelpe);


        dataSource = DataSource.getInstance();


        if (capo == null) {
            capo = (Capo) getIntent().getSerializableExtra(EXTRA_CAPO);
            outfit = new Outfit();
            outfit.aggiungiCapo(capo);
        }

        setRecyclerViewLists();

    }
    private void setRecyclerView(final RecyclerView recyclerView){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AbbinamentoActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(AbbinamentoActivity.this, listaMaglie, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position != ((AbbinamentiAdapter)recyclerView.getAdapter()).getSelected_position()) {
                    outfit.rimuoviUltimo();
                    outfit.aggiungiCapo(((AbbinamentiAdapter) recyclerView.getAdapter()).getItem(position));
                    ((AbbinamentiAdapter) recyclerView.getAdapter()).setSelected_position(position);
                    ((AbbinamentiAdapter) recyclerView.getAdapter()).notifyDataSetChanged();
                    removeSelectedPosition(recyclerView);
                }else{
                    outfit.rimuoviUltimo();
                    ((AbbinamentiAdapter) recyclerView.getAdapter()).setSelected_position(-1);
                    ((AbbinamentiAdapter) recyclerView.getAdapter()).notifyDataSetChanged();
                }updateAdapter(recyclerView);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void setRecyclerViewLists() {
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
//                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, outfit.listaColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, outfit.listaColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Felpa:
                textFelpa.setVisibility(View.GONE);
                textCamicie.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaFelpe.setVisibility(View.GONE);
                listaCamicie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
//                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Gonna:
                textPantalone.setVisibility(View.GONE);
                textGonna.setVisibility(View.GONE);
                listaPantaloni.setVisibility(View.GONE);
                listaGonne.setVisibility(View.GONE);
//                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Giacca:
                textGiacca.setVisibility(View.GONE);
                listaGiacche.setVisibility(View.GONE);
//                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaAbiti, textAbito, Capo.Tipo.Abito, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Maglia:
                textMaglia.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaMaglie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), pantaloniAdapter);
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(),scarpeAdapter);
//                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());

                break;
            case Scarpe:
                textScarpe.setVisibility(View.GONE);
                listaScarpe.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), maglieAdapter);
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), pantaloniAdapter);
//                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaAbiti, textAbito, Capo.Tipo.Abito, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Camicia:
                textFelpa.setVisibility(View.GONE);
                textCamicie.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaFelpe.setVisibility(View.GONE);
                listaCamicie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
//                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
            case Pantalone:
                textPantalone.setVisibility(View.GONE);
                textGonna.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaPantaloni.setVisibility(View.GONE);
                listaGonne.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), scarpeAdapter);
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), maglieAdapter);
//                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
//                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione());
                break;
        }
    }

    private void updateAdapter(RecyclerView recyclerView){
        if(recyclerView != listaScarpe && listaScarpe.getVisibility() == View.VISIBLE)
            dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, outfit.getlistaColori(),AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), scarpeAdapter);
        if(recyclerView != listaPantaloni && listaPantaloni.getVisibility() == View.VISIBLE)
            dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, outfit.getlistaColori(),AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), pantaloniAdapter);
        if(recyclerView != listaMaglie && listaMaglie.getVisibility() == View.VISIBLE)
            dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, outfit.getlistaColori(),AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), maglieAdapter);

    }
    private void removeSelectedPosition(RecyclerView recyclerView){
        if(recyclerView != listaScarpe && listaScarpe.getVisibility() == View.VISIBLE)
            ((AbbinamentiAdapter)listaScarpe.getAdapter()).setSelected_position(-1);
        if(recyclerView != listaPantaloni && listaPantaloni.getVisibility() == View.VISIBLE)
            ((AbbinamentiAdapter)listaPantaloni.getAdapter()).setSelected_position(-1);
        if(recyclerView != listaMaglie && listaMaglie.getVisibility() == View.VISIBLE)
            ((AbbinamentiAdapter)listaMaglie.getAdapter()).setSelected_position(-1);
    }

}
