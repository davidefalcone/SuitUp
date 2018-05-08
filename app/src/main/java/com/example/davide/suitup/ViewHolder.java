package com.example.davide.suitup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;


public class ViewHolder extends RecyclerView.ViewHolder {

    //riferimento all imageview
    CircleImageView vColoreImage;

    //costruttore
    public ViewHolder(View v) {
        super(v);
        vColoreImage = v.findViewById(R.id.coloreImage);

    }
}
