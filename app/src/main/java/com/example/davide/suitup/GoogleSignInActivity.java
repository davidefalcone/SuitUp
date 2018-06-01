package com.example.davide.suitup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleSignInActivity extends AppCompatActivity {

    //attributi
    private GoogleSignInOptions vGoogleSignInOptions;
    private GoogleSignInClient vGoogleSignInClient;
    private FirebaseAuth vAuth;


    private static final int RC_SIGN_IN = 9001;
    private static final String default_web_client_id = "9306961171-1mmq9proh040c7kll9df5pau13409p7j.apps.googleusercontent.com";

    //riferimenti alle view
    private VideoView videoView;
    private SignInButton vSignIn;
    public ProgressDialog vProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        //recupero i riferimenti alle view
        videoView = findViewById(R.id.videoView);
        vSignIn = findViewById(R.id.sign_in_button);

        //imposto il video in background
        playVideo();

        //chiedo un sign in di defaul che include userid e informazioni basilari sull'user
        vGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(default_web_client_id).build();
        //imposto il googlesigninclient con le impostazioni da me richieste
        vGoogleSignInClient = GoogleSignIn.getClient(this, vGoogleSignInOptions);

        vAuth = FirebaseAuth.getInstance();

        if(vAuth.getCurrentUser() != null)
            mostraLogo();

        vSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //mi autentico con firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                FirebaseSignIn(account);
            } catch (ApiException e) {
                // Google Sign In fallita

            }
        }
    }

    private void FirebaseSignIn(GoogleSignInAccount acct) {

        showProgressDialog();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        vAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in avvenuta con successo
                            mostraArmadio();

                        } else {
                            // se la sign in fallisce mostro un messaggio
                            Toast.makeText(getApplicationContext(), R.string.failedAuth, Toast.LENGTH_SHORT).show();
                        }


                        hideProgressDialog();

                    }
                });
    }
    private void playVideo(){
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //tolgo l'audio del video e lo metto in loop
                mp.setVolume(0f, 0f);
                mp.setLooping(true);
            }
        });
        videoView.start();
    }

    private void signIn() {
        //intent specifico per sign in con google
        Intent signInIntent = vGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void showProgressDialog() {
        if (vProgressDialog == null) {
            vProgressDialog = new ProgressDialog(this);
            vProgressDialog.setMessage(getString(R.string.loading));
            vProgressDialog.setIndeterminate(true);
        }

        vProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (vProgressDialog != null && vProgressDialog.isShowing()) {
            vProgressDialog.dismiss();
        }
    }

    private void mostraLogo() {
        Intent intent = new Intent(getApplicationContext(), LogoActivity.class);
        startActivity(intent);
    }

    private void mostraArmadio() {
        Intent intent = new Intent(getApplicationContext(), ArmadioActivity.class);
        startActivity(intent);
    }


}
