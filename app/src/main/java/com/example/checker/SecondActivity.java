package com.example.checker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.TransitionValues;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.checker.Tools.CalculationsTools;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    int compteur = 0; // Chiffre utilisé pour l'affichage du compteur

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Récupération des données de l'activité principale
            Intent intent = getIntent();
            double resultatTest = intent.getDoubleExtra("Pourcentage",0);

            List<Integer> nombre = new ArrayList<>();

        // Change le text pour reprendre le résultat envoyé par l'activité principale
            TextView result = findViewById(R.id.pourcentageResult);
            String textResult =  (int) resultatTest + " %"; // résultat final

        // Définit selon le résultat obtenu le texte afficher en dessous
            TextView compatibleText = findViewById(R.id.compatibleText);
            CalculationsTools tools = new CalculationsTools();
            String compatible = tools.CompatibleTest((int) resultatTest);

        // Création d'un décompte pour afficher graduellement la hausse du pourcentage afficher dans le TextView
            // Paramétrage du décompte
                int countDownTotalTime = 3000; // En secondes
                int countdownInterval = (countDownTotalTime/ (int) resultatTest) -1;  // On effectue un intervalle par unité de pourcentage

                CountDownTimer countDown = new CountDownTimer(countDownTotalTime,countdownInterval) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String text = compteur + " %";
                        result.setText(text); // résultat intermédiaire
                        compteur++;
                    }

                    @Override
                    public void onFinish() {
                        result.setText(textResult);
                        result.setTextSize(64);
                        result.setTextColor(ContextCompat.getColor(SecondActivity.this,R.color.teal_700));
                        result.setShadowLayer(0,0,0,Color.rgb(35,44,63));

                        compatibleText.setText(compatible);
                    }
                };

        // On déclenche le compteur
            countDown.start();
    }
}