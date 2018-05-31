package com.example.davide.suitup;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import de.hdodenhof.circleimageview.CircleImageView;


public class ColoriViewHolder extends RecyclerView.ViewHolder {

    //riferimento all imageview
    CircleImageView vColoreImage;

    //costruttore
    public ColoriViewHolder(View v) {
        super(v);
        vColoreImage = v.findViewById(R.id.coloreImage);

    }
}
