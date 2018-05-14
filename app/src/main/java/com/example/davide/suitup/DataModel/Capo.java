package com.example.davide.suitup.DataModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Capo implements Serializable{


    public enum Tipo  {Maglia, Pantalone, Scarpe, Felpa, Giacca, Abito, Camicia, Gonna, Jeans };
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
    private String nomeCapo;
    private Tipo tipo;
    private Stagione stagione;
    private Occasione occasione;
    private int image;
    private ArrayList<Colore> colori= new ArrayList<Colore>();

    public Capo() {
    }

    public Capo(String nomeCapo, Tipo tipo, Stagione stagione, Occasione occasione, ArrayList<Colore> colori, int image) {
        this.nomeCapo = nomeCapo;
        this.tipo = tipo;
        this.stagione = stagione;
        this.occasione = occasione;
        this.colori = colori;
        this.image = image;
    }
    public Capo(String nomeCapo, Tipo tipo, Stagione stagione, Occasione occasione) {
        this.nomeCapo = nomeCapo;
        this.tipo = tipo;
        this.stagione = stagione;
        this.occasione = occasione;
    }



    public String getNomeCapo() {
        return nomeCapo;
    }

    public void setNomeCapo(String nomeCapo) {
        this.nomeCapo = nomeCapo;
    }

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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
