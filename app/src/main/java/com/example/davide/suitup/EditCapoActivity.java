package com.example.davide.suitup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.davide.suitup.DataModel.Capo;
import com.example.davide.suitup.DataModel.Colore;
import java.io.IOException;
import java.util.ArrayList;


public class EditCapoActivity extends AppCompatActivity {

    //attributi
    private EditColoriAdapter coloriAdapter = new EditColoriAdapter();
    private ArrayList<Colore> listaColori = Colore.Tuttiicolori();
    private Capo capo;

    //TAG
    private final String EMPTY = "empty";
    private final String NO_EMPTY = "no_empty";


    //riferimenti alle view
    private Button vOk;
    private Button vAnnulla;
    private EditText vNome;
    private Spinner vTipo;
    private Spinner vOccasione;
    private Spinner vStagione;
    private FragmentManager fm;
    private Button vAggiungi;
    private Fragment fragment;
    private ImageView vImage;

    private final String EXTRA_CAPO = "capo";
    private final String EXTRA_COLORI = "colori";
    private final int GALLERY = 0;
    private final int CAMERA = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_capo);


        //ottengo i riferimenti alle view
        vOk = findViewById(R.id.btnOk);
        vAnnulla = findViewById(R.id.btnAnnulla);
        vNome = findViewById(R.id.editNome);
        vTipo = findViewById(R.id.spnTipo);
        vOccasione = findViewById(R.id.spnOccasione);
        vStagione = findViewById(R.id.spnStagione);
        vAggiungi = findViewById(R.id.btnAggiungi);
        vImage = findViewById(R.id.imageCapo);

        vImage.setImageResource(R.drawable.emptyimage);
        vImage.setTag(EMPTY);

        //riferimento al fragment
        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.fragmentContainer);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        //inizializzo il fragment con la classe Horizontal list view
        if (fragment == null ) {
            fragment = new HorizontalListViewFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }

        //imposto la posizione del pulsante aggiungi colore


        //imposto gli spinner
        final ArrayAdapter<Capo.Tipo> tipoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Capo.Tipo.values());
        vTipo.setAdapter(tipoAdapter);
        final ArrayAdapter<Capo.Occasione> occasioneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Capo.Occasione.values());
        vOccasione.setAdapter(occasioneAdapter);
        final ArrayAdapter<Capo.Stagione> stagioneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Capo.Stagione.values());
        vStagione.setAdapter(stagioneAdapter);

        //ottengo i dati passati da lista capi activity
        final Intent intent = getIntent();
        capo = (Capo) intent.getSerializableExtra(EXTRA_CAPO);

        final Bundle bundle = new Bundle();

        if (capo !=  null) {
            vNome.setText(capo.getNomeCapo());
            vTipo.setSelection(capo.getTipo().ordinal());
            vStagione.setSelection(capo.getStagione().ordinal());
            vOccasione.setSelection(capo.getOccasione().ordinal());
            vImage.setImageResource(capo.getImage());
            vImage.setTag(NO_EMPTY);
            coloriAdapter = new EditColoriAdapter(capo.getColori());
            listaColori.clear();
            listaColori = Colore.ColoriRimanenti(capo);
        }else
        coloriAdapter = new EditColoriAdapter();
        bundle.putSerializable(EXTRA_COLORI, coloriAdapter);
        fragment.setArguments(bundle);


        vAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle(R.string.seleziona_colore);
                alert.setView(R.layout.fragment_scegli_colore);
                final ColoriAdapter adapter = new ColoriAdapter(listaColori, getApplicationContext());
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
                final AlertDialog alertDialog = alert.create();
                alertDialog.show();
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
        capo.setNomeCapo(vNome.getText().toString());
        capo.setTipo(Capo.Tipo.values()[vTipo.getSelectedItemPosition()]);
        capo.setStagione(Capo.Stagione.values()[vStagione.getSelectedItemPosition()]);
        capo.setOccasione(Capo.Occasione.values()[vOccasione.getSelectedItemPosition()]);
        capo.setColori(coloriAdapter.getListaColori());
        capo.setImage(vImage.getId());
        if(capo.getNomeCapo().length()>=1 && capo.getColori().size()>=1 && vImage.getTag() != EMPTY){
            return capo;
        }else return null;
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
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    vImage.setImageBitmap(bitmap);
                    vImage.setTag(NO_EMPTY);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(EditCapoActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            vImage.setImageBitmap(bitmap);
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

}
