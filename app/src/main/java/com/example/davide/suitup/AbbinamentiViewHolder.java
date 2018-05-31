package com.example.davide.suitup;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class AbbinamentiViewHolder extends RecyclerView.ViewHolder {
    //riferimento all'imageview
    ImageView vAbbinamentoImage;
    ProgressBar progressBar;

    public AbbinamentiViewHolder(View itemView) {
        super(itemView);
        vAbbinamentoImage = itemView.findViewById(R.id.imageAbbinamento);
        progressBar = itemView.findViewById(R.id.progress);

    }
}
