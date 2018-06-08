package com.example.davide.suitup.DataModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Outfit implements Serializable{
    //attributi
    private Capo maglia;
    private Capo pantalone;
    private Capo gonna;
    private Capo scarpe;
    private Capo giacca;
    private Capo camicia;
    private Capo felpa;
    private Capo abito;

    public Outfit(){
    }

    public void aggiungiCapo(Capo capo, Capo.Tipo tipo){
        switch (tipo){
            case Abito:
                abito = capo;
                break;
            case Felpa:
                felpa = capo;
                break;
            case Gonna:
                gonna = capo;
                break;
            case Giacca:
                giacca = capo;
                break;
            case Scarpe:
                scarpe = capo;
                break;
            case Camicia:
                camicia = capo;
                break;
            case Pantalone:
                pantalone = capo;
                break;
            case Maglia:
                maglia = capo;
                break;
        }
    }

    public Capo getMaglia() {
        return maglia;
    }

    public void setMaglia(Capo maglia) {
        this.maglia = maglia;
    }

    public Capo getPantalone() {
        return pantalone;
    }

    public void setPantalone(Capo pantalone) {
        this.pantalone = pantalone;
    }

    public Capo getGonna() {
        return gonna;
    }

    public void setGonna(Capo gonna) {
        this.gonna = gonna;
    }

    public Capo getScarpe() {
        return scarpe;
    }

    public void setScarpe(Capo scarpe) {
        this.scarpe = scarpe;
    }

    public Capo getGiacca() {
        return giacca;
    }

    public void setGiacca(Capo giacca) {
        this.giacca = giacca;
    }

    public Capo getCamicia() {
        return camicia;
    }

    public void setCamicia(Capo camicia) {
        this.camicia = camicia;
    }

    public Capo getFelpa() {
        return felpa;
    }

    public void setFelpa(Capo felpa) {
        this.felpa = felpa;
    }

    public Capo getAbito() {
        return abito;
    }

    public void setAbito(Capo abito) {
        this.abito = abito;
    }

    public ArrayList<Colore> getlistaColori (){
        ArrayList<Colore> listaColori = new ArrayList<>();
        if(maglia != null){
            for (int i=0; i<maglia.getColori().size(); i++){
                if(!listaColori.contains(maglia.getColori().get(i)))
                    listaColori.add(maglia.getColori().get(i));
            }
        }
        if(pantalone != null){
            for (int i=0; i<pantalone.getColori().size(); i++){
                if(!listaColori.contains(pantalone.getColori().get(i)))
                    listaColori.add(pantalone.getColori().get(i));
            }
        }
        if(giacca != null){
            for (int i=0; i<giacca.getColori().size(); i++){
                if(!listaColori.contains(giacca.getColori().get(i)))
                    listaColori.add(giacca.getColori().get(i));
            }
        }
        if(scarpe != null){
            for (int i=0; i<scarpe.getColori().size(); i++){
                if(!listaColori.contains(scarpe.getColori().get(i)))
                    listaColori.add(scarpe.getColori().get(i));
            }
        }
        if(gonna != null){
            for (int i=0; i<gonna.getColori().size(); i++){
                if(!listaColori.contains(gonna.getColori().get(i)))
                    listaColori.add(gonna.getColori().get(i));
            }
        }
        if(camicia != null){
            for (int i=0; i<camicia.getColori().size(); i++){
                if(!listaColori.contains(camicia.getColori().get(i)))
                    listaColori.add(camicia.getColori().get(i));
            }
        }
        if(abito != null){
            for (int i=0; i<abito.getColori().size(); i++){
                if(!listaColori.contains(abito.getColori().get(i)))
                    listaColori.add(abito.getColori().get(i));
            }
        }
        if(felpa != null){
            for (int i=0; i<felpa.getColori().size(); i++){
                if(!listaColori.contains(felpa.getColori().get(i)))
                    listaColori.add(felpa.getColori().get(i));
            }
        }return listaColori;

    }

}
