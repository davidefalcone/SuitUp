package com.example.davide.suitup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.davide.suitup.DataModel.Capo;

import java.util.List;

public class CapiAdapter extends BaseAdapter {

    private Context context;
    private List<Capo> elencoCapi;

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

        Capo capo = elencoCapi.get(i);
        vNomecapo.setText(capo.getNomeCapo());
        return view;
    }

    public void setElencoCapi (List<Capo> elencoCapi)
    {
        this.elencoCapi = elencoCapi;
        notifyDataSetChanged();
    }

}
