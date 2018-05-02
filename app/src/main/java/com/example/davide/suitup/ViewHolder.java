package com.example.davide.suitup;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


public class ViewHolder extends RecyclerView.ViewHolder {

    //riferimento all imageview
    ImageView vColoreImage;

    //costruttore
    public ViewHolder(View v) {
        super(v);
        vColoreImage = v.findViewById(R.id.coloreImage);

    }
}
