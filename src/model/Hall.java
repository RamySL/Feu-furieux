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
    }


    public void paint(Graphics g, int translationX, int translationY){

            if (this.rougeIntensite() == 0)
                g.setColor(new Color(255, 255, 255));
             else
                g.setColor(new Color(255, 0, 0, this.rougeIntensite()));
            super.paint(g, translationX, translationY);

            if (this.possedeCle()) {
                // sans le getImage on a un objet ImageIcone
                Image cleImage = (new ImageIcon("src/assets/images/key.png")).getImage() ;
                g.drawImage(cleImage
                        , (this.getColone() - translationX) * this.tailleCase,
                        (this.getLigne() - translationY) * this.tailleCase, Case.tailleCle, Case.tailleCle, new Color(255, true), null);

            }
        }
}
