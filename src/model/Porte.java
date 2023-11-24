package model;

import java.awt.*;

public class Porte extends Case{
    private boolean ouverte;

    public Porte(int l, int c, boolean ouverte){
        super(l, c);
        this.ouverte = ouverte;
    }

    public boolean estTraversable(){
        return ouverte;
    }

    public void entre (Joueur j){
        this.joueur = j;
    }

    public boolean possedeJoueur (){
         if (! ouverte){
             return false;
         }else {
             return this.joueur != null;
         }
    }

    public void paint (Graphics g){

        if (this.estTraversable()){
            // porte ouverte
            g.setColor(new Color(255,255,255));
            g.fillRect(this.getColone() * this.tailleCase, this.getLigne() * this.tailleCase,this.tailleCase,this.tailleCase );
        }else{
            g.setColor(new Color(0,255,0));
            g.fillRect(this.getColone() * this.tailleCase, this.getLigne() * this.tailleCase,this.tailleCase,this.tailleCase );
        }

    }
}
