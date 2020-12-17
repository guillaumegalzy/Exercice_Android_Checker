package com.example.checker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {
    //Variables
        private final static int SPLASH_SCREEN_DURATION = 5000; // Durée de l'animation programmée en ms
        Animation topAnim, bottomAnim;
        AnimatedVectorDrawable logoAnimation;
        ImageView imgView;
        TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        // Enlevons la barre en haut
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Animations des élément sur la page
            topAnim = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
            bottomAnim = AnimationUtils.loadAnimation(this,R.anim.desc_animation);

        //Hooks
            imgView = findViewById(R.id.LogoImg);
            desc = findViewById(R.id.AppName);

        // Animation du contenu du logo SVG
            imgView.setBackgroundResource(R.drawable.animatorvectordrawable_logo_checker_app);
            logoAnimation = (AnimatedVectorDrawable) imgView.getBackground();

        //Lancement des animations des éléments
            imgView.setAnimation(topAnim);
            desc.setAnimation(bottomAnim);

        // Lancement de l'animation du logo coeur du SVG
            logoAnimation.start();

        // Création d'un Handler pour navigation vers la MainActivity suite à l'écoulement de la durée programmée de l'animation
        new Handler(Looper.myLooper()).postDelayed(() -> {
            Intent intent = new Intent(Splashscreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        },SPLASH_SCREEN_DURATION);
    }
}