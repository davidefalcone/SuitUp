package com.example.davide.suitup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
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

public class CapiAdapter extends BaseAdapter {

    private Context context;
    private List<Capo> elencoCapi;
    private StorageReference imagePath;
    private FirebaseUser user;

    public CapiAdapter(Context context, List<Capo> elencoCapi) {
        this.context = context;
        this.elencoCapi = elencoCapi;
        user = FirebaseAuth.getInstance().getCurrentUser();
        imagePath = FirebaseStorage.getInstance().getReference().child(user.getUid());
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
        final ProgressBar progressBar = view.findViewById(R.id.progress);

        Capo capo = elencoCapi.get(i);

        vNomecapo.setText(capo.getNomeCapo());
        vTipo.setText(capo.getTipo().toString());
        progressBar.setVisibility(view.VISIBLE);
        GlideApp.with(context).load(imagePath.child(capo.getNomeCapo()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(vImage);
        return view;
    }

    public void setElencoCapi (List<Capo> elencoCapi)
    {
        this.elencoCapi = elencoCapi;
        notifyDataSetChanged();
    }

}
