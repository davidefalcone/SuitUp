package com.example.davide.suitup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davide.suitup.DataModel.Colore;
import com.example.davide.suitup.DataModel.DataSource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class CapoColoriAdapter extends RecyclerView.Adapter<ColoriViewHolder> implements Serializable {

    //attributi
    protected ArrayList<Colore> listaColori;

    //costruttore
    public CapoColoriAdapter(ArrayList<Colore> data){
        listaColori = data;
    }

    public CapoColoriAdapter(){}

    @NonNull
    @Override
    public ColoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_items, parent, false);
        ColoriViewHolder holder = new ColoriViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ColoriViewHolder holder, int i) {

        holder.vColoreImage.setImageResource(listaColori.get(i).getImageResourceId());
    }


    @Override
    public int getItemCount() {
        return listaColori.size();
    }

    public ArrayList<Colore> getListaColori() {
        return listaColori;
    }

    public void setListaColori(ArrayList<Colore> listaColori) {
        this.listaColori = listaColori;
        notifyDataSetChanged();
    }
}
