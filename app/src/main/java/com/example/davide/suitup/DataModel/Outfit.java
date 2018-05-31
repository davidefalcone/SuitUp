package com.example.davide.suitup.DataModel;

public class Outfit {
    //attributi
    private Capo maglia;
    private Capo pantalone;
    private Capo scarpe;
    private Capo felpa;
    private Capo giacca;
    private Capo abito;
    private Capo camicia;
    private Capo gonna;
    private Capo jeans;

    //costruttore
    public Outfit () {
        maglia = null;
        pantalone = null;
        scarpe = null;
        felpa = null;
        giacca = null;
        abito = null;
        camicia = null;
        jeans = null;
        gonna = null;
        }

    //getters e setters

    public Capo getGonna() {
        return gonna;
    }

    public void setGonna(Capo gonna) {
        this.gonna = gonna;
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

    public Capo getScarpe() {
        return scarpe;
    }

    public void setScarpe(Capo scarpe) {
        this.scarpe = scarpe;
    }

    public Capo getFelpa() {
        return felpa;
    }

    public void setFelpa(Capo felpa) {
        this.felpa = felpa;
    }

    public Capo getGiacca() {
        return giacca;
    }

    public void setGiacca(Capo giacca) {
        this.giacca = giacca;
    }

    public Capo getAbito() {
        return abito;
    }

    public void setAbito(Capo abito) {
        this.abito = abito;
    }

    public Capo getCamicia() {
        return camicia;
    }

    public void setCamicia(Capo camicia) {
        this.camicia = camicia;
    }

    public Capo getJeans() {
        return jeans;
    }

    public void setJeans(Capo jeans) {
        this.jeans = jeans;
    }
}
