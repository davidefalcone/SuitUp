package com.example.davide.suitup.DataModel;

import java.util.ArrayList;

public class Outfit {
    //attributi
    private ArrayList<Capo> listaCapi;

    public Outfit(){
        listaCapi = new ArrayList<>();
    }

    public void aggiungiCapo(Capo capo){
        listaCapi.add(capo);
    }

    public void rimuoviUltimo (){
        if(listaCapi.size()!=1)
        listaCapi.remove(listaCapi.size()-1);
    }

    public ArrayList<Colore> getlistaColori (){
        ArrayList<Colore> listaColori = new ArrayList<>();
        for (int i= 0; i<listaCapi.size(); i++){
            for (int j=0; j<listaCapi.get(i).getColori().size(); j++)
                if(!listaColori.contains(listaCapi.get(i).getColori().get(j)))
                listaColori.add(listaCapi.get(i).getColori().get(j));
        }return listaColori;
    }
}
