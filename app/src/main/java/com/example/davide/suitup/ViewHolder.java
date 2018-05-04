package com.example.davide.suitup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;



public class ViewHolder extends RecyclerView.ViewHolder {

    //attributi
    String nomeColore;
    //riferimento all imageview
    ImageView vColoreImage;

    //tag che tengono conto dell'acitvity chiamante
    private final int ACTIVITY_CAPO = 0;
    private final int ACTIVITY_EDIT_CAPO = 1;

    //costruttore
    public ViewHolder(View v, int activity) {
        super(v);
        vColoreImage = v.findViewById(R.id.coloreImage);
//        if (activity == ACTIVITY_EDIT_CAPO) {
//            //se sto editando il capo, i colori potranno essere eliminati premendoli
//            vColoreImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //imposto il popup per l'eliminazione del colore
//                    AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
//                    alert.setTitle(R.string.elimina);
//                    alert.setView(R.layout.fragment_elimina_capo);
//
//                    //imposto i pulsanti ok e annulla
//                    alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//                    alert.setNegativeButton(R.string.annulla, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//                    //mostro il popup
//                    AlertDialog alertDialog = alert.create();
//                    alertDialog.show();
//                }
//            });
//        }
    }
}
