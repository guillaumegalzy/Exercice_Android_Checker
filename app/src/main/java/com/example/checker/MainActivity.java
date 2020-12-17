package com.example.checker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.checker.Tools.CalculationsTools;

public class MainActivity extends AppCompatActivity {

    EditText prenom1Field;
    EditText prenom2Field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prenom1Field = findViewById(R.id.prenom1);
        prenom2Field = findViewById(R.id.prenom2);
    }

    public void TestMatch(View view) {
        if (prenom1Field.getText().length() == 0 || prenom2Field.getText().length()==0){
            // Créé et affiche une alert si nécessaire
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setMessage("Saisir les deux prénoms avant de lancer le test");

                AlertDialog alert = builder.create();
                alert.show();
        }else{
            CalculationsTools tools = new CalculationsTools();
            double pourcentage = Math.ceil(tools.TestMatch(prenom1Field,prenom2Field)*100);
            System.out.println("Pourcentage " + pourcentage);
            Intent intent = new Intent(this,SecondActivity.class);
            intent.putExtra("Pourcentage",pourcentage);
            startActivity(intent);
        }
    }
}