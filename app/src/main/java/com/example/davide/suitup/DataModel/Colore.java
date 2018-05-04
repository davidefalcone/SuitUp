package com.example.davide.suitup.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Vector;


public class Colore implements Parcelable, Serializable{

    //attributi
    String nomeColore;
    int imageResourceId;

    //costruttore
    public Colore() {};

    public Colore (Parcel in) {
        String data[] = {nomeColore};
        in.readStringArray(data);
        this.nomeColore = data[0];
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.nomeColore});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Colore createFromParcel(Parcel in) {
            return new Colore(in);
        }
        public Colore[] newArray(int size) {
            return new Colore[size];
        }
};
}

