package com.subhasmith.loadshare.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.subhasmith.loadshare.R;
import com.subhasmith.loadshare.model.RealmUser;

import io.realm.Realm;

public class SplashActivity extends AppCompatActivity {

    Realm realm;
    ImageView splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash = findViewById(R.id.splash);
        realm = Realm.getDefaultInstance();
        RealmUser realmUser = realm.where(RealmUser.class).findFirst();

        Glide.with(SplashActivity.this)
                .load(getResources().getDrawable(R.drawable.loadshare))
                .into(splash);
        new Handler().postDelayed(() -> {
            if (realmUser != null){
                startActivity(new Intent(SplashActivity.this, SearchIFSCActivity.class));
            }else {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

        },3000);


    }
}