package com.example.davide.suitup.DataModel;


import java.io.Serializable;
import java.util.ArrayList;

public class Capo implements Serializable{


    public final int MAGLIA = 0;
    public final int PANTALONE = 1;
    public final int SCARPE = 2;
    public final int ELEGANTE = 0;
    public final int SPORTIVO = 0;
    public final int ESTIVO = 0;
    public final int ROSSO = 0;
    public final int GIALLO = 1;
    public final int VERDE = 2;
    public final int JEANS = 3;
    public final int BLU = 4;
    public final int NERO = 5;
    public final int BEIGE = 6;
    public final int GRIGIO = 7;
    public final int BORDEAUX = 8;
    public final int MARRONE = 9;
    public final int INVERNALE = 0;
    public enum Tipo = {MAGLIA, PANTALONE, SCARPE};
    public enum Stagione = {ESTIVO, INVERNALE};
    public enum Occasione = {ELEGANTE, SPORTIVO};
    public enum Colore = {ROSSO, GIALLO, VERDE, JEANS, BLU, NERO ,BEIGE, GRIGIO, BORDEAUX, MARRONE};
    private String nomeCapo;
    private Tipo tipo;
    private Stagione stagione;
    private Occasione occasione;
    private ArrayList<Integer> colori= new ArrayList<Integer>();

    public Capo() {
    }

    public Capo(String nomeCapo, Tipo tipo, Stagione stagione, Occasione occasione) {
        this.nomeCapo = nomeCapo;
        this.tipo = tipo;
        this.stagione = stagione;
        this.occasione = occasione;
    }

    public int getMAGLIA() {
        return MAGLIA;
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


}
