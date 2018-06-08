package com.example.davide.suitup;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.davide.suitup.DataModel.Capo;
import com.example.davide.suitup.DataModel.Colore;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class EditCapoActivity extends AppCompatActivity {

    //attributi
    private EditColoriAdapter coloriAdapter = new EditColoriAdapter();
    private ArrayList<Colore> listaColori = Colore.Tuttiicolori();
    private Capo capo;
    private FirebaseUser user;
    private StorageReference imagePath;
    private DatabaseReference myref;
    //TAG
    private final String EMPTY = "empty";
    private final String NO_EMPTY = "no_empty";


    //riferimenti alle view
    private Button vOk;
    private Button vAnnulla;
    private Spinner vTipo;
    private Spinner vOccasione;
    private Spinner vStagione;
    private RecyclerView vListaColori;
    private Button vAggiungi;
    private ImageView vImage;
    private ProgressBar progressBar;

    private final String EXTRA_CAPO = "capo";
    private final int GALLERY = 0;
    private final int CAMERA = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_capo);


        //ottengo i riferimenti alle view
        vOk = findViewById(R.id.btnOk);
        vAnnulla = findViewById(R.id.btnAnnulla);
        vTipo = findViewById(R.id.spnTipo);
        vOccasione = findViewById(R.id.spnOccasione);
        vStagione = findViewById(R.id.spnStagione);
        vAggiungi = findViewById(R.id.btnAggiungi);
        vListaColori = findViewById(R.id.listaColori);
        vImage = findViewById(R.id.imageCapo);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);

        user = FirebaseAuth.getInstance().getCurrentUser();
        imagePath = FirebaseStorage.getInstance().getReference().child(user.getUid());

        myref = FirebaseDatabase.getInstance().getReference();

        //metto nell'imageview una foto vuota
        vImage.setImageResource(R.drawable.emptyimage);
        vImage.setTag(EMPTY);

        //imposto gli spinner
        setSpinner();

        //imposto la recyclerview
        setRecyclerView();

        //ottengo i dati passati da lista capi activity
        final Intent intent = getIntent();
        capo = (Capo) intent.getSerializableExtra(EXTRA_CAPO);


        if (capo !=  null) {
            vTipo.setSelection(capo.getTipo().ordinal());
            vStagione.setSelection(capo.getStagione().ordinal());
            vOccasione.setSelection(capo.getOccasione().ordinal());
            GlideApp.with(this).load(imagePath.child(capo.getID()+".jpg")).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(vImage);
            vImage.setTag(NO_EMPTY);
            coloriAdapter = new EditColoriAdapter(capo.getColori());
            listaColori.clear();
            listaColori = Colore.ColoriRimanenti(capo);
        }else
        coloriAdapter = new EditColoriAdapter();
        vListaColori.setAdapter(coloriAdapter);


        vAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aggiungiColore();
            }
        });

        //imposto il pulsante ok
        vOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capo = leggiDatiCapo();
                if (capo != null) {
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_CAPO, capo);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),R.string.dati_obbligatori, Toast.LENGTH_LONG).show();
                    }
            }
        });

        //imposto il pulsante annulla
        vAnnulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        vImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDialog();
            }
        });
    }

    private Capo leggiDatiCapo() {
        if(capo == null)
            capo = new Capo();
        capo.setTipo(Capo.Tipo.values()[vTipo.getSelectedItemPosition()]);
        capo.setStagione(Capo.Stagione.values()[vStagione.getSelectedItemPosition()]);
        capo.setOccasione(Capo.Occasione.values()[vOccasione.getSelectedItemPosition()]);
        capo.setColori(coloriAdapter.getListaColori());
        if(capo.getColori().size() >= 1 && vImage.getTag() == NO_EMPTY){
                if(capo.getID() == null)
                capo.setID(myref.push().getKey());
                uploadImage();
                return capo;
            }
        else return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    vImage.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI));
                    vImage.setTag(NO_EMPTY);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(EditCapoActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            vImage.setImageBitmap((Bitmap) data.getExtras().get("data"));
            vImage.setTag(NO_EMPTY);
        }
    }

    private void startDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        String[] pictureDialogItems = {
                "Dalla galleria",
                "Dalla fotocamera",
                "Rimuovi"};
        if(vImage.getTag() == EMPTY){
             String[] copy =  {
                     "Dalla galleria",
                     "Dalla fotocamera"};
             pictureDialogItems = null;
             pictureDialogItems = copy;
        }

        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                scegliDallaGalleria();
                                break;
                            case 1:
                                scegliDallaFotocamera();
                                break;
                            case 2:
                                rimuovi();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void scegliDallaGalleria() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void scegliDallaFotocamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    private void rimuovi(){
        vImage.setImageResource(R.drawable.emptyimage);
        vImage.setTag(EMPTY);
    }

    private void aggiungiColore(){
        AlertDialog.Builder alert = new AlertDialog.Builder(EditCapoActivity.this);
        alert.setTitle(R.string.seleziona_colore);
        alert.setView(R.layout.fragment_scegli_colore);
        ColoriAdapter adapter = new ColoriAdapter(listaColori, getApplicationContext());
        alert.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if ( capo == null ){
                    capo = new Capo();
                }
                capo.getColori().add(listaColori.get(i));
                coloriAdapter.setListaColori(capo.getColori());
                listaColori.remove(i);
            }
        });

        //mostro il popup
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    private void uploadImage(){
        imagePath.child(capo.getID()+".jpg").delete();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable)vImage.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = imagePath.child(capo.getID()+".jpg").putBytes(data);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.GONE);
            }
        });
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setSpinner(){
        final ArrayAdapter<Capo.Tipo> tipoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Capo.Tipo.values());
        vTipo.setAdapter(tipoAdapter);
        final ArrayAdapter<Capo.Occasione> occasioneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Capo.Occasione.values());
        vOccasione.setAdapter(occasioneAdapter);
        final ArrayAdapter<Capo.Stagione> stagioneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Capo.Stagione.values());
        vStagione.setAdapter(stagioneAdapter);
    }

    private void setRecyclerView(){
        vListaColori.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(EditCapoActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        vListaColori.setLayoutManager(linearLayoutManager);
    }

}

//serve a far convivere il metodo settag e glide
class App extends Application {
    @Override public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);
    }
}