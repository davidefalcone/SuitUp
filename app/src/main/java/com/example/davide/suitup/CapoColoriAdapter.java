package com.example.davide.suitup;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davide.suitup.DataModel.Colore;

import java.io.Serializable;
import java.util.ArrayList;

public class CapoColoriAdapter extends RecyclerView.Adapter<ColoriAdapter.ColoriViewHolder> implements Serializable {

    //attributi
    protected ArrayList<Colore> listaColori;

    //costruttore
    public CapoColoriAdapter(ArrayList<Colore> data){
        listaColori = data;
    }

    public CapoColoriAdapter(){}

    @NonNull
    @Override
    public ColoriAdapter.ColoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.colore_item, parent, false);
        ColoriAdapter.ColoriViewHolder holder = new ColoriAdapter.ColoriViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ColoriAdapter.ColoriViewHolder holder, int i) {

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
