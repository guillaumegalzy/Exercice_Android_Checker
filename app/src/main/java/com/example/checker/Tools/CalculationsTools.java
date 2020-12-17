package com.example.checker.Tools;

import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

public class CalculationsTools {
    /**
     * Fonction recursive pour obtenir un seul digit à partir d'un nombre donné
     * @param nombre (int) Nombre entré en paramètre
     * @return integer issu de la réduction du nombre
     */
    public Integer sum_of_digit(int nombre){
        if(nombre < 10){
            return nombre;
        } else{
            return sum_of_digit(nombre % 10 + sum_of_digit(nombre / 10));
        }
    }

    /**
     * Calcul du score du prénom
     * @param prenom String prénom
     * @return int score issu du prénom donné en paramètre
     */
    public Integer CalcScore(String prenom){
        char[] prenomTab = prenom.toCharArray();
        int scoreNonReduit = 0;

        for (Character character: prenomTab) {
            scoreNonReduit += character - 96; // Valeur ASCII du caractère que l'on ramène à 1 pour la lettre 'a'
        }

        System.out.println("Score non réduit " + prenom + " : " + scoreNonReduit);
        int scoreReduit = sum_of_digit(scoreNonReduit);
        System.out.println("Score réduit " + prenom + " : " + scoreReduit);
        return scoreReduit;
    }

    public Float Pourcentage(int scoreReduit1, int scoreReduit2){
        return (float) (9-Math.abs(scoreReduit1-scoreReduit2))/9;
    }

    public Float TestMatch(TextView prenom1, TextView prenom2) {
        // Récupère les prénoms et les normalises + suppression des caractères spéciaux et accents
            String prenom1Text = String.valueOf(prenom1.getText());
            prenom1Text = StringUtils.stripAccents(prenom1Text);
            prenom1Text = prenom1Text.toLowerCase();
            prenom1Text = prenom1Text.replaceAll("[\\W_]","");

            String prenom2Text = String.valueOf(prenom2.getText());
            prenom2Text = StringUtils.stripAccents(prenom2Text);
            prenom2Text = prenom2Text.toLowerCase();
            prenom2Text = prenom2Text.replaceAll("[\\W_]","");


        int scoreReduitPrenom1 = CalcScore(prenom1Text);
        int scoreReduitPrenom2 = CalcScore(prenom2Text);
        return Pourcentage(scoreReduitPrenom1,scoreReduitPrenom2);
    }

    public String CompatibleTest(int nombre){
        if (nombre <25){
            return "Pas copain";
        } else if (nombre <50){
            return "Pote";
        }else if (nombre <75){
            return "Amitié possible";
        }else if (nombre <99){
            return "BFF";
        }else {
            return "Match parfait!";
        }
    }
}
