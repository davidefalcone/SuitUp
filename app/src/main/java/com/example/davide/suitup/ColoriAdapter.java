package com.example.davide.suitup;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davide.suitup.DataModel.Colore;

import java.util.ArrayList;

public class ColoriAdapter extends RecyclerView.Adapter<ViewHolder> {

    //attributi
    private ArrayList<Colore> listaColori;

    //costruttore
    public ColoriAdapter(ArrayList<Colore> data){
        listaColori = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.vColoreImage.setImageResource(listaColori.get(i).getImageResourceId());

    }

    @Override
    public int getItemCount() {
        return listaColori.size();
    }
}
