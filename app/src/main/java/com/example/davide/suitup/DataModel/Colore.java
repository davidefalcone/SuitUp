package com.example.davide.suitup.DataModel;

import com.example.davide.suitup.R;
import java.io.Serializable;
import java.util.ArrayList;

public class Colore implements Serializable{

    //attributi

    private String nomeColore;
    private int imageResourceId;

    //costruttore
    public Colore() {};

    public Colore(String nomeColore, int imageResourceId) {
        this.nomeColore = nomeColore;
        this.imageResourceId = imageResourceId;
    }
//getters e setters


    public String getNomeColore() {
        return nomeColore;
    }

    public void setNomeColore(String nomeColore) {
        this.nomeColore = nomeColore;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    //metodo che restituisce una lista ddi tutti i colori che non sono contenuti nel Capo
    public static ArrayList<Colore> ColoriRimanenti (Capo capo) {
        ArrayList<Colore> coloriRimanenti = Colore.Tuttiicolori();
        ArrayList<Colore> colori = capo.getColori();
        for (int i = 0; i<colori.size(); i++){
            for (int j = 0; j<coloriRimanenti.size(); j++) {
                if (colori.get(i).getNomeColore().matches(coloriRimanenti.get(j).getNomeColore()))
                    coloriRimanenti.remove(j);

            }
        }return coloriRimanenti;
    }

    public static ArrayList<Colore> Tuttiicolori (){
        ArrayList<Colore> lista = new ArrayList<Colore>();
        lista.add(new Colore("Rosso", R.color.rosso));
        lista.add(new Colore("Giallo", R.color.giallo));
        lista.add(new Colore("Verde", R.color.verde));
        lista.add(new Colore("Blu", R.color.blu));
        lista.add(new Colore("Nero", R.color.black));
        lista.add(new Colore("Grigio",R.color.grigio));
        lista.add(new Colore("Bianco", R.color.bianco));
        lista.add(new Colore("Bordeaux", R.color.bordeaux));
        lista.add(new Colore("Beige", R.color.beige));
        lista.add(new Colore("Marrone", R.color.marrone));
        return lista;
    }
}

