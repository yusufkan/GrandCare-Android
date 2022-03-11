package com.example.deneme2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Intro extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    private Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        //HOOKS
        image = findViewById(R.id.logointro);
        logo = findViewById(R.id.brandintro);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);

    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent delayintent = new Intent(Intro.this, DelayPage.class);
            startActivity(delayintent);
            finish();

            Intent intent = new Intent(Intro.this, Login.class);
            startActivity(intent);

            }
        },SPLASH_SCREEN);
    }
}