package com.example.davide.suitup;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davide.suitup.DataModel.Capo;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CapiAdapter extends BaseAdapter {

    private Context context;
    private List<Capo> elencoCapi;
    Bundle bundle = new Bundle();

    //adapter
    private CapoColoriAdapter adapter;

    private final String NUMERO_CAPI = "numerocapi";


    public CapiAdapter(Context context, List<Capo> elencoCapi) {
        this.context = context;
        this.elencoCapi = elencoCapi;
    }


    @Override
    public int getCount() {
        return elencoCapi.size();
    }

    @Override
    public Capo getItem(int i) {
        return elencoCapi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.riga_capo, null);

        TextView vNomecapo = view.findViewById(R.id.textNomecapo);
        ImageView vImage = view.findViewById(R.id.imageCapo);
        TextView vTipo = view.findViewById(R.id.textTipo);

        Capo capo = elencoCapi.get(i);
        vImage.setImageResource(capo.getImage());
        vNomecapo.setText(capo.getNomeCapo());
        vTipo.setText(capo.getTipo().toString());
        return view;
    }

    public void setElencoCapi (List<Capo> elencoCapi)
    {
        this.elencoCapi = elencoCapi;
        notifyDataSetChanged();
    }

}
