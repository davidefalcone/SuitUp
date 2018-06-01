package com.example.davide.suitup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davide.suitup.DataModel.Colore;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ColoriAdapter extends BaseAdapter {

    //attributi
    private ArrayList<Colore> lista;
    private Context context;

    public ColoriAdapter(ArrayList<Colore> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }


    public ArrayList<Colore> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Colore> lista) {
        this.lista = lista;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Colore getItem(int i) {
        return lista.get(i);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if ( view == null )
            view = LayoutInflater.from(context).inflate(R.layout.riga_colore,null);

            TextView vNome = view.findViewById(R.id.textNomecolore);
            vNome.setText(lista.get(i).getNomeColore());
            ImageView vImage = view.findViewById(R.id.imageColore);
            vImage.setImageResource(lista.get(i).getImageResourceId());
            return view;

    }

    public static class ColoriViewHolder extends RecyclerView.ViewHolder {

        //riferimento all imageview
        CircleImageView vColoreImage;

        //costruttore
        public ColoriViewHolder(View v) {
            super(v);
            vColoreImage = v.findViewById(R.id.coloreImage);

        }
    }
}
