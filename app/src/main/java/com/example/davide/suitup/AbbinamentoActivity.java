package com.example.davide.suitup;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
    private String pantaloniSelectedID;
    private String maglieSelectedID;
    private String scarpeSelectedID;
    private String giaccheSelectedID;
    private String gonneSelectedID;
    private String felpeSelectedID;
    private String abitiSelectedID;
    private String camicieSelectedID;

    private final String EXTRA_OUTFIT = "outfit";

    //riferimenti alle view
    private Button vOk;
    private TextView vTextTips;
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
    private AbbinamentiAdapter abitiAdapter;


    //chiave per il passaggio parametri dall'altra activity
    private final String EXTRA_CAPO = "capo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abbinamento);

        vOk = findViewById(R.id.btnOk);
        vTextTips = findViewById(R.id.textTips);
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
        abitiAdapter = new AbbinamentiAdapter(AbbinamentoActivity.this);
        final String[] tips = { getResources().getString(R.string.tip1), getResources().getString(R.string.tip2), getResources().getString(R.string.tip3), getResources().getString(R.string.tip4), getResources().getString(R.string.tip5) };




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
            switch (capo.getTipo()){
                case Maglia:
                    outfit.setMaglia(capo);
                    break;
                case Pantalone:
                    outfit.setPantalone(capo);
                    break;
                case Camicia:
                    outfit.setCamicia(capo);
                case Scarpe:
                    outfit.setScarpe(capo);
                    break;
                case Giacca:
                    outfit.setGiacca(capo);
                    break;
                case Gonna:
                    outfit.setGiacca(capo);
                    break;
                case Felpa:
                    outfit.setFelpa(capo);
                    break;
                case Abito:
                    outfit.setAbito(capo);
                    break;
            }
        }

        setRecyclerViewLists();
        vTextTips.setText(tips[0]);

        final Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(5000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vTextTips.setText(tips[(int)(Math.random()*(tips.length))]);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();

        vOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbbinamentoActivity.this, OutfitActivity.class);
                intent.putExtra(EXTRA_OUTFIT, outfit);
                startActivity(intent);
            }
        });

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
                    switch (((AbbinamentiAdapter) recyclerView.getAdapter()).getItem(position).getTipo()){
                        case Maglia:
                            outfit.setMaglia(((AbbinamentiAdapter) recyclerView.getAdapter()).getItem(position));
                            break;
                        case Pantalone:
                            outfit.setPantalone(((AbbinamentiAdapter) recyclerView.getAdapter()).getItem(position));
                            break;
                        case Camicia:
                            outfit.setCamicia(((AbbinamentiAdapter) recyclerView.getAdapter()).getItem(position));
                            break;
                        case Scarpe:
                            outfit.setScarpe(((AbbinamentiAdapter) recyclerView.getAdapter()).getItem(position));
                            break;
                        case Giacca:
                            outfit.setGiacca(((AbbinamentiAdapter) recyclerView.getAdapter()).getItem(position));
                            break;
                        case Gonna:
                            outfit.setGonna(((AbbinamentiAdapter) recyclerView.getAdapter()).getItem(position));
                            break;
                        case Felpa:
                            outfit.setFelpa(((AbbinamentiAdapter) recyclerView.getAdapter()).getItem(position));
                            break;
                        case Abito:
                            outfit.setAbito(((AbbinamentiAdapter) recyclerView.getAdapter()).getItem(position));
                            break;
                    }
                    ((AbbinamentiAdapter) recyclerView.getAdapter()).setSelected_position(position);
                    ((AbbinamentiAdapter) recyclerView.getAdapter()).notifyDataSetChanged();
                    saveSelectedPosition(recyclerView);
                }else{
                    switch (((AbbinamentiAdapter) recyclerView.getAdapter()).getItem(position).getTipo()){
                        case Maglia:
                            outfit.setMaglia(null);
                            break;
                        case Pantalone:
                            outfit.setPantalone(null);
                            break;
                        case Camicia:
                            outfit.setCamicia(null);
                            break;
                        case Scarpe:
                            outfit.setScarpe(null);
                            break;
                        case Giacca:
                            outfit.setGiacca(null);
                            break;
                        case Gonna:
                            outfit.setGonna(null);
                            break;
                        case Felpa:
                            outfit.setFelpa(null);
                            break;
                        case Abito:
                            outfit.setAbito(null);
                            break;
                    }
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
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, outfit.getlistaColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), scarpeAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, outfit.getlistaColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), giaccheAdapter, null);
                break;
            case Felpa:
                textFelpa.setVisibility(View.GONE);
                textCamicie.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaFelpe.setVisibility(View.GONE);
                listaCamicie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), pantaloniAdapter, null);
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), maglieAdapter, null);
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), scarpeAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), gonneAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), giaccheAdapter, null);
                break;
            case Gonna:
                textPantalone.setVisibility(View.GONE);
                textGonna.setVisibility(View.GONE);
                listaPantaloni.setVisibility(View.GONE);
                listaGonne.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), scarpeAdapter, null);
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), maglieAdapter, null);
                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), felpeAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), giaccheAdapter, null);
                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), camicieAdapter, null);
                break;
            case Giacca:
                textGiacca.setVisibility(View.GONE);
                listaGiacche.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), scarpeAdapter, null);
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), maglieAdapter, null);
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), pantaloniAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), gonneAdapter, null);
                dataSource.cercaCapiAbbinabili(listaAbiti, textAbito, Capo.Tipo.Abito, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), abitiAdapter, null);
                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), camicieAdapter, null);
                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), felpeAdapter, null);
                break;
            case Maglia:
                textMaglia.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaMaglie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), pantaloniAdapter, null);
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(),scarpeAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), gonneAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), giaccheAdapter, null);
                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), camicieAdapter, null);
                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(),felpeAdapter, null);

                break;
            case Scarpe:
                textScarpe.setVisibility(View.GONE);
                listaScarpe.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), maglieAdapter, null);
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), pantaloniAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), gonneAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), giaccheAdapter, null);
                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), camicieAdapter, null);
                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), felpeAdapter, null);
                dataSource.cercaCapiAbbinabili(listaAbiti, textAbito, Capo.Tipo.Abito, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), abitiAdapter, null);
                break;
            case Camicia:
                textFelpa.setVisibility(View.GONE);
                textCamicie.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaFelpe.setVisibility(View.GONE);
                listaCamicie.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), maglieAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), giaccheAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), gonneAdapter, null);
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), scarpeAdapter, null);
                dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), pantaloniAdapter, null);
                break;
            case Pantalone:
                textPantalone.setVisibility(View.GONE);
                textGonna.setVisibility(View.GONE);
                textAbito.setVisibility(View.GONE);
                listaPantaloni.setVisibility(View.GONE);
                listaGonne.setVisibility(View.GONE);
                listaAbiti.setVisibility(View.GONE);
                dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), scarpeAdapter, null);
                dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), maglieAdapter, null);
                dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), camicieAdapter, null);
                dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), giaccheAdapter, null);
                dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, capo.getColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), felpeAdapter, null);
                break;
        }
    }

    private void updateAdapter(RecyclerView recyclerView){
        if(recyclerView != listaCamicie && listaCamicie.getVisibility() == View.VISIBLE)
            dataSource.cercaCapiAbbinabili(listaCamicie, textCamicie, Capo.Tipo.Camicia, outfit.getlistaColori(),AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), camicieAdapter, camicieSelectedID);
        if(recyclerView != listaGiacche && listaGiacche.getVisibility() == View.VISIBLE)
            dataSource.cercaCapiAbbinabili(listaGiacche, textGiacca, Capo.Tipo.Giacca, outfit.getlistaColori(),AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), giaccheAdapter, giaccheSelectedID);
        if(recyclerView != listaGonne && listaGonne.getVisibility() == View.VISIBLE)
            dataSource.cercaCapiAbbinabili(listaGonne, textGonna, Capo.Tipo.Gonna, outfit.getlistaColori(),AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), gonneAdapter, gonneSelectedID);
        if(recyclerView != listaAbiti && listaAbiti.getVisibility() == View.VISIBLE)
            dataSource.cercaCapiAbbinabili(listaAbiti, textAbito, Capo.Tipo.Abito, outfit.getlistaColori(),AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), abitiAdapter, abitiSelectedID);
        if(recyclerView != listaFelpe && listaFelpe.getVisibility() == View.VISIBLE)
            dataSource.cercaCapiAbbinabili(listaFelpe, textFelpa, Capo.Tipo.Felpa, outfit.getlistaColori(),AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), felpeAdapter, felpeSelectedID);
        if(recyclerView != listaMaglie && listaMaglie.getVisibility() == View.VISIBLE)
            dataSource.cercaCapiAbbinabili(listaMaglie, textMaglia, Capo.Tipo.Maglia, outfit.getlistaColori(),AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), maglieAdapter, maglieSelectedID);
        if(recyclerView != listaScarpe && listaScarpe.getVisibility() == View.VISIBLE)
            dataSource.cercaCapiAbbinabili(listaScarpe, textScarpe, Capo.Tipo.Scarpe, outfit.getlistaColori(),AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), scarpeAdapter, scarpeSelectedID);
        if(recyclerView != listaPantaloni && listaPantaloni.getVisibility() == View.VISIBLE) {
            dataSource.cercaCapiAbbinabili(listaPantaloni, textPantalone, Capo.Tipo.Pantalone, outfit.getlistaColori(), AbbinamentoActivity.this, capo.getStagione(), capo.getOccasione(), pantaloniAdapter, pantaloniSelectedID);
        }
    }

    private void saveSelectedPosition(RecyclerView recyclerView){
        if(recyclerView != listaPantaloni && listaPantaloni.getVisibility() == View.VISIBLE && (((AbbinamentiAdapter)listaPantaloni.getAdapter()).getSelected_position()) != -1)
            pantaloniSelectedID = ((AbbinamentiAdapter)listaPantaloni.getAdapter()).getItem(((AbbinamentiAdapter)listaPantaloni.getAdapter()).getSelected_position()).getID();

        if(recyclerView != listaScarpe && listaScarpe.getVisibility() == View.VISIBLE && (((AbbinamentiAdapter)listaScarpe.getAdapter()).getSelected_position()) != -1)
            scarpeSelectedID = ((AbbinamentiAdapter)listaScarpe.getAdapter()).getItem(((AbbinamentiAdapter)listaScarpe.getAdapter()).getSelected_position()).getID();

        if(recyclerView != listaMaglie && listaMaglie.getVisibility() == View.VISIBLE && (((AbbinamentiAdapter)listaMaglie.getAdapter()).getSelected_position()) != -1)
            maglieSelectedID = ((AbbinamentiAdapter)listaMaglie.getAdapter()).getItem(((AbbinamentiAdapter)listaPantaloni.getAdapter()).getSelected_position()).getID();

        if(recyclerView != listaFelpe && listaFelpe.getVisibility() == View.VISIBLE && (((AbbinamentiAdapter)listaFelpe.getAdapter()).getSelected_position()) != -1)
            felpeSelectedID = ((AbbinamentiAdapter)listaFelpe.getAdapter()).getItem(((AbbinamentiAdapter)listaFelpe.getAdapter()).getSelected_position()).getID();

        if(recyclerView != listaGonne && listaGonne.getVisibility() == View.VISIBLE && (((AbbinamentiAdapter)listaGonne.getAdapter()).getSelected_position()) != -1)
            gonneSelectedID = ((AbbinamentiAdapter)listaGonne.getAdapter()).getItem(((AbbinamentiAdapter)listaGonne.getAdapter()).getSelected_position()).getID();

        if(recyclerView != listaGiacche && listaGiacche.getVisibility() == View.VISIBLE && (((AbbinamentiAdapter)listaGiacche.getAdapter()).getSelected_position()) != -1)
            giaccheSelectedID = ((AbbinamentiAdapter)listaGiacche.getAdapter()).getItem(((AbbinamentiAdapter)listaGiacche.getAdapter()).getSelected_position()).getID();

        if(recyclerView != listaAbiti && listaAbiti.getVisibility() == View.VISIBLE && (((AbbinamentiAdapter)listaAbiti.getAdapter()).getSelected_position()) != -1)
            abitiSelectedID = ((AbbinamentiAdapter)listaAbiti.getAdapter()).getItem(((AbbinamentiAdapter)listaAbiti.getAdapter()).getSelected_position()).getID();

        if(recyclerView != listaCamicie && listaCamicie.getVisibility() == View.VISIBLE && (((AbbinamentiAdapter)listaCamicie.getAdapter()).getSelected_position()) != -1)
            camicieSelectedID = ((AbbinamentiAdapter)listaCamicie.getAdapter()).getItem(((AbbinamentiAdapter)listaCamicie.getAdapter()).getSelected_position()).getID();

    }

}
