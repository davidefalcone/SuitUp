package com.example.davide.suitup;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.davide.suitup.DataModel.Capo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

public class AbbinamentiAdapter extends RecyclerView.Adapter<AbbinamentiAdapter.AbbinamentiViewHolder> {
    //attributi
    private ArrayList<Capo> listaAbbinamenti;
    private Context context;
    private StorageReference imagePath;
    private FirebaseUser user;
    static int selected_position = -1;


    //costruttore

    public AbbinamentiAdapter(ArrayList<Capo> listaAbbinamenti, Context context) {
        this.listaAbbinamenti = listaAbbinamenti;
        this.context = context;
        user = FirebaseAuth.getInstance().getCurrentUser();
        imagePath = FirebaseStorage.getInstance().getReference().child(user.getUid());
    }
    public AbbinamentiAdapter(Context context) {
        this.context = context;
        user = FirebaseAuth.getInstance().getCurrentUser();
        imagePath = FirebaseStorage.getInstance().getReference().child(user.getUid());
    }


    //getters e setters

    public ArrayList<Capo> getListaAbbinamenti() {
        return listaAbbinamenti;
    }

    public void setListaAbbinamenti(ArrayList<Capo> listaAbbinamenti) {
        this.listaAbbinamenti = listaAbbinamenti;
        notifyDataSetChanged();
    }
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    public AbbinamentiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.abbinamento_item, parent, false);
        AbbinamentiViewHolder holder = new AbbinamentiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final AbbinamentiViewHolder holder,final int i) {

        //se l'elemento è stato cliccato allora viene evidenziato
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == RecyclerView.NO_POSITION) return;
                    selected_position = i;
                    notifyDataSetChanged();
            }
        });
        holder.itemView.setBackgroundColor(selected_position == i ? getContext().getResources().getColor(R.color.colorAccent ) : Color.TRANSPARENT);

        Capo capo = listaAbbinamenti.get(i);
        holder.progressBar.setVisibility(View.VISIBLE);
        GlideApp.with(context).load(imagePath.child(capo.getNomeCapo()+".jpg")).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.vAbbinamentoImage);

    }

    @Override
    public int getItemCount() {
        return listaAbbinamenti.size();
    }

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
}
