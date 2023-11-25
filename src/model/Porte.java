package model;

import java.awt.*;

// POrte doit etendre CaseLibre

public class Porte extends CaseTraversable{
    private boolean ouverte;

    public Porte(int l, int c, boolean ouverte){
        super(l, c, 0);//initialement les chaleurs sont à 0
        this.ouverte = ouverte;
    }

    public boolean estTraversable(){
        return true;
    }

    public void entre (Joueur j){
        this.joueur = j;
    }

    public void ouvrire(){
        this.ouverte = true;
    }

    public boolean estOuverte(){
        return ouverte;
    }

 /*   public boolean possedeJoueur (){
         if (! ouverte){
             return false;
         }else {
             return this.joueur != null;
         }
    }

  */

    public void paint (Graphics g, int translationX, int translationY){


            if (ouverte)
                // porte ouverte
                g.setColor(new Color(255, 255, 255));
            else
                g.setColor(new Color(0, 255, 0));

            super.paint(g, translationX, translationY);
    }
}
