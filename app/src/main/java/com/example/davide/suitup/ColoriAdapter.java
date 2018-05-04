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

import java.util.ArrayList;
import java.util.Hashtable;

public class ColoriAdapter extends RecyclerView.Adapter<ViewHolder> {

    //attributi
    ArrayList<Colore> listaColori;
    int activity;

    //tag che tengono conto dell'acitvity chiamante
    private final int ACTIVITY_CAPO = 0;
    private final int ACTIVITY_EDIT_CAPO = 1;

    // Unica instanza
    private static ColoriAdapter instance = null;


    public static ColoriAdapter getInstance(ArrayList<Colore> data, int activity) {
        if (instance == null)
            instance = new ColoriAdapter(data, activity);
        return instance;
        }

    public static ColoriAdapter getInstance() {
        if (instance == null)
            instance = new ColoriAdapter();
        return instance;
    }
    private ColoriAdapter(){};
    //costruttore
    private ColoriAdapter(ArrayList<Colore> data, int activity){
        listaColori = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_items, parent, false);
        ViewHolder holder = new ViewHolder(view,activity);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        //todo: forse la prossima istrzione la devi togliere
        holder.nomeColore = listaColori.get(i).getNomeColore();
        holder.vColoreImage.setImageResource(listaColori.get(i).getImageResourceId());
        if (activity == ACTIVITY_EDIT_CAPO) {
            //se sto editando il capo, i colori potranno essere eliminati premendoli
            holder.vColoreImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //imposto il popup per l'eliminazione del colore
                    AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                    alert.setTitle(R.string.elimina);
                    alert.setView(R.layout.fragment_elimina_capo);

                    //imposto i pulsanti ok e annulla
                    alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        listaColori.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                        }
                    });
                    alert.setNegativeButton(R.string.annulla, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    //mostro il popup
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return listaColori.size();
    }

    public ArrayList<Colore> getListaColori() {
        return listaColori;
    }
}
