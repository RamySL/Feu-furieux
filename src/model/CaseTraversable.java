package model;

import java.awt.*;
import java.util.ArrayList;

public abstract class CaseTraversable extends Case {
    protected int chaleur;

    public CaseTraversable (int l, int c, int chaleur){
        super(l,c);
        this.chaleur = chaleur;
    }

    public void incrementeChaleur (){
        // 10 c'est le maximum que peut prendre chaleur

        if (this.chaleur < 10){
            this.chaleur ++;
        }
    }
    public void decrementeChaleur (){
        // 0 c'est le minimum
        if (this.chaleur > 0){
            this.chaleur ++;
        }
    }

    public int getChaleur(){
        return this.chaleur;
    }


    public void entre (Joueur j){
        this.joueur = j;
    }

    public Joueur getJoueur (){
        // retourne le joueur si il yen a sinon retourne null
        return this.joueur;
    }

    public int rougeIntensite (){
        return 25 * this.chaleur;

    }
}
