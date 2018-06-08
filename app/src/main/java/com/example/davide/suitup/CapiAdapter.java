package com.example.davide.suitup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import java.util.List;

public class CapiAdapter extends RecyclerView.Adapter<CapiAdapter.CapiViewHolder> {

    private Context context;
    private List<Capo> elencoCapi;
    private StorageReference imagePath;
    private FirebaseUser user;
    private int position;

    public CapiAdapter(Context context, List<Capo> elencoCapi) {
        this.context = context;
        this.elencoCapi = elencoCapi;
        user = FirebaseAuth.getInstance().getCurrentUser();
        imagePath = FirebaseStorage.getInstance().getReference().child(user.getUid());
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onBindViewHolder(final CapiViewHolder holder, int i) {
        Capo capo = elencoCapi.get(i);
        holder.progressBar.setVisibility(View.VISIBLE);
        GlideApp.with(context).load(imagePath.child(capo.getID()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).listener(new RequestListener<Drawable>() {
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
        }).into(holder.vImage);
        holder.vTipo.setText(capo.getTipo().toString());
    }

    @NonNull
    @Override
    public CapiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.riga_capo, parent, false);
        CapiAdapter.CapiViewHolder holder = new CapiAdapter.CapiViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return elencoCapi.size();
    }

    public Capo getItem (int i){
        return elencoCapi.get(i);
    }

    public void setElencoCapi (List<Capo> elencoCapi)
    {
        this.elencoCapi = elencoCapi;
        notifyDataSetChanged();
    }

    public class CapiViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnCreateContextMenuListener{

        //riferimenti
        ImageView vImage;
        ProgressBar progressBar;
        TextView vTipo;


        public CapiViewHolder(View itemView){
            super(itemView);
            vImage = itemView.findViewById(R.id.imageCapo);
            progressBar = itemView.findViewById(R.id.progress);
            vTipo = itemView.findViewById(R.id.textTipo);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add( Menu.NONE, R.id.itemDelete, Menu.NONE, "Rimuovi Capo");
            menu.add(Menu.NONE, R.id.itemEdit,Menu.NONE ,"Modifica capo");
        }
    }

}
