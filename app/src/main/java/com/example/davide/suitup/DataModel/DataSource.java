package com.example.davide.suitup.DataModel;

import com.example.davide.suitup.R;

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

public class DataSource{

    // Lista locale per simulare una ipotetica sorgente dati
    private Hashtable<String, Capo> elencoCapi;

    // Unica instanza
    private static DataSource instance = null;

    // Costruttore privato
    private DataSource() {
            elencoCapi = new Hashtable<>();
            popolaDataSource();
            }


    public static DataSource getInstance() {
            if (instance == null)
            instance = new DataSource();
            return instance;
            }


    public void addCapo(Capo capo) {
            elencoCapi.put(capo.getNomeCapo(), capo);
            }


    public void deleteCapo(String nomeCapo) {
            elencoCapi.remove(nomeCapo);
            }


    public Capo getCapo(String nomeCapo) {
            return elencoCapi.get(nomeCapo);
            }


    public List<Capo> getElencoCapi(Capo.Tipo tipo) {

            ArrayList<Capo> risultato = new ArrayList<Capo>();

            // Itero tutti gli elementi per cercare quelli che soddisfano il requisito richiesto
            for (Map.Entry<String, Capo> elemento: elencoCapi.entrySet()) {
            if (elemento.getValue().getTipo() == tipo)
            risultato.add(elemento.getValue());
            }
            return risultato;
        }

        public List<Capo> getElencoCapi() {

        ArrayList<Capo> risultato = new ArrayList<Capo>();

        // Itero tutti gli elementi per cercare quelli che soddisfano il requisito richiesto
        for (Map.Entry<String, Capo> elemento: elencoCapi.entrySet()) {

                risultato.add(elemento.getValue());
        }
        return risultato;
    }

// Popolo il data source con dati di prova
private void popolaDataSource() {

        ArrayList<Colore> listaprova = new ArrayList<>();
        listaprova.add(new Colore("Nero", R.color.nero));
        listaprova.add(new Colore("Bianco", R.color.bianco));

        ArrayList<Colore> listaprova1 = new ArrayList<>();
        listaprova1.add(new Colore ("Grigio",R.color.grigio));
        listaprova1.add(new Colore("Bianco", R.color.bianco));

        ArrayList<Colore> listaprova2 = new ArrayList<>();
        listaprova2.add(new Colore("Bianco", R.color.bianco));

        elencoCapi.put("Gucci limited edition", new Capo("Gucci limited edition", Maglia, AutunnoInverno, Elegante, listaprova, R.drawable.samplemaglia));
        elencoCapi.put("H&M divided", new Capo("H&M divided", Pantalone, PrimaveraEstate, Sportivo, listaprova1, R.drawable.pantalone));
        elencoCapi.put("Cortez", new Capo("Cortez", Scarpe, PrimaveraEstate, Elegante, listaprova2, R.drawable.scarpe));
        elencoCapi.put("Adidas SuperStar", new Capo("Adidas SuperStar", Scarpe, PrimaveraEstate, Casual, listaprova , R.drawable.scarpe));
        elencoCapi.put("Converse All Star", new Capo("Converse All Star", Scarpe, PrimaveraEstate, Casual, listaprova, R.drawable.scarpe));
        elencoCapi.put("Nike Blazer", new Capo("Nike Blazer", Scarpe, PrimaveraEstate, Sportivo, listaprova1, R.drawable.scarpe));
        elencoCapi.put("Superga", new Capo("Superga", Scarpe, PrimaveraEstate, Casual));
        elencoCapi.put("Adidas", new Capo("Adidas", Pantalone, PrimaveraEstate, Sportivo));
        }
}