package com.example.davide.suitup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.davide.suitup.DataModel.Outfit;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class OutfitActivity extends AppCompatActivity {

    private final String EXTRA_OUTFIT = "outfit";
    private Outfit outfit;
    private StorageReference imagePath;
    private FirebaseUser user;


    //riferimenti alle view
    private Button vHome;
    private ImageView vImageGiacca;
    private ImageView vImageFelpa;
    private ImageView vImageCamicia;
    private ImageView vImageMaglia;
    private ImageView vImageAbito;
    private ImageView vImagePantalone;
    private ImageView vImageGonna;
    private ImageView vImageScarpe;
    private ImageView[] images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit);

        vHome = findViewById(R.id.btnHome);
        vImageAbito = findViewById(R.id.imageAbito);
        vImageCamicia = findViewById(R.id.imageCamicia);
        vImageFelpa = findViewById(R.id.imageFelpa);
        vImageMaglia = findViewById(R.id.imageMaglia);
        vImageGiacca = findViewById(R.id.imageGiacca);
        vImagePantalone = findViewById(R.id.imagePantalone);
        vImageGonna = findViewById(R.id.imageGonna);
        vImageScarpe = findViewById(R.id.imageScarpa);

        user = FirebaseAuth.getInstance().getCurrentUser();
        imagePath = FirebaseStorage.getInstance().getReference().child(user.getUid());

        outfit = (Outfit) getIntent().getSerializableExtra(EXTRA_OUTFIT);

        setImageViews();


        vHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OutfitActivity.this, ArmadioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setImageViews(){
        if(outfit.getAbito() != null)
            GlideApp.with(this).load(imagePath.child(outfit.getAbito().getID()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(vImageAbito);
        else vImageAbito.setVisibility(View.GONE);

        if(outfit.getMaglia() != null)
            GlideApp.with(this).load(imagePath.child(outfit.getMaglia().getID()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(vImageMaglia);
        else vImageMaglia.setVisibility(View.GONE);

        if(outfit.getCamicia() != null)
            GlideApp.with(this).load(imagePath.child(outfit.getCamicia().getID()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(vImageCamicia);
        else vImageCamicia.setVisibility(View.GONE);

        if(outfit.getPantalone() != null)
            GlideApp.with(this).load(imagePath.child(outfit.getPantalone().getID()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(vImagePantalone);
        else vImagePantalone.setVisibility(View.GONE);

        if(outfit.getScarpe() != null)
            GlideApp.with(this).load(imagePath.child(outfit.getScarpe().getID()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(vImageScarpe);
        else vImageScarpe.setVisibility(View.GONE);

        if(outfit.getGiacca() != null)
            GlideApp.with(this).load(imagePath.child(outfit.getGiacca().getID()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(vImageGiacca);
        else vImageGiacca.setVisibility(View.GONE);

        if(outfit.getGonna() != null)
            GlideApp.with(this).load(imagePath.child(outfit.getGonna().getID()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(vImageGonna);
        else vImageGonna.setVisibility(View.GONE);

        if(outfit.getFelpa() != null)
            GlideApp.with(this).load(imagePath.child(outfit.getFelpa().getID()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(vImageFelpa);
        else vImageFelpa.setVisibility(View.GONE);
    }


}
