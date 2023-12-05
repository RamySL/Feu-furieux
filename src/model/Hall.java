package model;

import model.CaseTraversable;

import javax.swing.*;
import java.awt.*;

public class Hall extends CaseTraversable {

    private boolean cle;
    public Hall (int l, int c){
        this (l,c,0,false);
    }

    public Hall (int l, int c, boolean cle){
        // par défaut si on ne fournie pas de chaleur c'est 0
        this (l, c, 0,cle);
    }
    public Hall (int l, int c, int chaleur){
        this (l, c, chaleur,false);
    }

    public Hall (int l, int c,int chaleur, boolean cle ){
        super (l,c, chaleur);
        this.cle = cle;
    }

    public boolean possedeCle(){
        return this.cle;
    }
    public void supprimerCle(){
        this.cle = false;
    }
    public boolean estTraversable (){
        return true;
        //return this.getJoueur() == null; le hall est toujours traversable, tu veux dire ici estLibre()
    }



    public boolean possedeJoueur (){
        // pour l'instant elle n'a pas vraiment de sens mais si on ajoute
        // d'autres créatures le hall elle peut ne pas etre traversable
        // mais ne pas avoir un joueur

        return (! estTraversable());
    }

    public void paint(Graphics g, int translationX, int translationY){
            if (this.rougeIntensite() == 0)
                g.setColor(new Color(255, 255, 255));
             else
                g.setColor(new Color(255, 0, 0, this.rougeIntensite()));

            super.paint(g, translationX, translationY);
            if (this.possedeCle()) {
                //g.setColor(Color.LIGHT_GRAY);
                //g.setColor(new Color(150, 150, 150));

                // sans le getImage on a un objet ImageIcone
                Image cleImage = (new ImageIcon("src/assets/key2.png")).getImage() ;
                g.drawImage(cleImage
                        , (this.getColone() - translationX) * this.tailleCase,
                        (this.getLigne() - translationY) * this.tailleCase, Case.tailleCle, Case.tailleCle, Color.WHITE, null);

                //g.fillRect((this.getColone() - translationX) * this.tailleCase, (this.getLigne() - translationY) * this.tailleCase, Case.tailleCle, Case.tailleCle);
            }
        }
}
