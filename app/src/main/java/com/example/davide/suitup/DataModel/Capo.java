package com.example.davide.suitup.DataModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Capo implements Serializable{


    public enum Tipo  {Maglia, Pantalone, Scarpe, Felpa, Giacca, Abito, Camicia, Gonna };
    public enum Stagione  {
        PrimaveraEstate, AutunnoInverno;

        @Override
        public String toString() {
            if ( this == PrimaveraEstate )
                return "Primavera/Estate";
            else return "Autunno/Inverno";
        }
    };
    public enum Occasione  {Elegante, Sportivo, Casual};
    private String ID;
    private Tipo tipo;
    private Stagione stagione;
    private Occasione occasione;
    private ArrayList<Colore> colori= new ArrayList<Colore>();

    public String getID() {
        return ID;
    }

    public void setID(String ID) { this.ID = ID; }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Stagione getStagione() {
        return stagione;
    }

    public void setStagione(Stagione stagione) {
        this.stagione = stagione;
    }

    public Occasione getOccasione() {
        return occasione;
    }

    public void setOccasione(Occasione occasione) {
        this.occasione = occasione;
    }

    public void AggiungiColore (Colore colore)
    {
        colori.add(colore);
    }

    public void RimuoviColore (Colore colore)
    {
        colori.remove(colore);
    }

    public ArrayList<Colore> getColori() {
        return colori;
    }

    public void setColori(ArrayList<Colore> colori) {
        this.colori = colori;
    }

}
