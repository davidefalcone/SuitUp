package com.example.davide.suitup;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import android.support.v4.app.Fragment;




public class CapoActivity extends AppCompatActivity {

    //adapter
    private CapoColoriAdapter adapter;
    private StorageReference imagePath;
    private FirebaseUser user;

    //creo i riferimenti alle view
    private ImageView vImageview;
    private Button vElimina;
    private Button vAbbina;
    private TextView vNomeCapo;
    private TextView vOccasioneStagione;
    private FragmentManager fm;
    private ProgressBar progressBar;


    //chiave per il passaggio parametri dall'altra activity
    private final String EXTRA_CAPO = "capo";
    private final String EXTRA_COLORI = "colori";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capo);

        user = FirebaseAuth.getInstance().getCurrentUser();
        imagePath = FirebaseStorage.getInstance().getReference().child(user.getUid());

        //imposto i riferimenti alle view
        vImageview = findViewById(R.id.imageCapo);
        vElimina = findViewById(R.id.eliminabtn);
        vAbbina = findViewById(R.id.btnOk);
        vNomeCapo = findViewById(R.id.textNome);
        vOccasioneStagione = findViewById(R.id.textOccasioneStagione);
        progressBar = findViewById(R.id.progress);
        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new HorizontalListViewFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }

        //ottengo i dati forniti dall'activity precedente
        final Intent intent = getIntent();
        Capo capo = (Capo) intent.getSerializableExtra(EXTRA_CAPO);

        if (capo != null) {
            vNomeCapo.setText(capo.getNomeCapo());
            vOccasioneStagione.setText(capo.getOccasione() + " • " + capo.getStagione());
            progressBar.setVisibility(View.VISIBLE);
            GlideApp.with(this).load(imagePath.child(capo.getNomeCapo()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
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
            }).into(vImageview);
            Bundle bundle = new Bundle();
            adapter = new CapoColoriAdapter(capo.getColori());
            if (adapter != null)
                bundle.putSerializable(EXTRA_COLORI, adapter);
            fragment.setArguments(bundle);
        }

    }
}

