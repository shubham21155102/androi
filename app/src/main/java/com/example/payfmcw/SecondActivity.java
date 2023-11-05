package com.example.payfmcw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SecondActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ProgressBar loader;
    TextView name,email,idToken,allData;
    ImageView profilePic;
    Button signOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ImageView imageView = findViewById(R.id.imageView);
        loader=findViewById(R.id.loader);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        idToken=findViewById(R.id.idToken);
        allData=findViewById(R.id.alldatas);
        signOutBtn=findViewById(R.id.signout);
//        profilePic=findViewById(R.id.imageView);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount!=null){
            name.setText(signInAccount.getDisplayName());
            email.setText(signInAccount.getEmail());
//            profilePic.setImageURI(signInAccount.getPhotoUrl());
//            String imageUrl = "https://media.istockphoto.com/id/636379014/photo/hands-forming-a-heart-shape-with-sunset-silhouette.jpg?s=612x612&w=0&k=20&c=CgjWWGEasjgwia2VT7ufXa10azba2HXmUDe96wZG8F0=";
             String imageUrl = signInAccount.getPhotoUrl().toString();
            Glide.with(this).load(imageUrl).into(imageView);
          idToken.setText(signInAccount.getIdToken());
            idToken.setText("ID Token: "+signInAccount.getIdToken());
            allData.setText("all"+signInAccount.getDisplayName()+"\n"+signInAccount.getEmail()+"\n"+signInAccount.getIdToken()+"\n"+signInAccount.getId()+"\n"+signInAccount.getPhotoUrl()+"\n"+signInAccount);


        }
        signOutBtn.setOnClickListener(v -> {
            gsc.signOut().addOnCompleteListener(task -> {
                Toast.makeText(getApplicationContext(),"Sign Out Successfully", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            });
        });
    }
}