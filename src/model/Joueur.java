package model;

import java.awt.*;
import java.io.Serializable;

public class Joueur implements Serializable {
    private static int id_courant = 0;
    private CaseTraversable c;
    private int resistance;
    private int cles;
    private String nom;

    private int id, score, niveau;
    public Joueur(CaseTraversable c, int r, int k) {
        this.c = c;
        this.resistance = r;
        this.cles = k;
        this.id = id_courant;
        id_courant++;
    }

    public Joueur(CaseTraversable c, int r, int k, String nom){
        this(c, r, k);
        this.nom = nom;
        this.score = 0;
        this.niveau = 1;
    }


    public int getResistance() {
        return this.resistance;
    }

    public void bouge(Case cible) {
        /* À compléter */
        if (cible instanceof CaseTraversable ){
            ((CaseTraversable) cible).entre(this);
            this.c = ((CaseTraversable) cible);
        }

    }

    public String getNom(){
        return this.nom;
    }

    public int getId() {
        return id;
    }

    public int getCles(){return this.cles;}

    public Case getCase (){return this.c;}

    public void paint (Graphics g, int translationX, int translationY){
        int tailleCase = this.c.getTailleCase();
        g.setColor(new Color(190,100,50));
        g.fillOval((this.c.getColone() - translationX) * tailleCase, (this.c.getLigne() - translationY) * tailleCase,tailleCase,tailleCase);
    }

    public void deplacer(CaseTraversable c) {
        if(c == null)
            return;

        if (c instanceof Sortie){
            c.entre(this);
            this.c = c;
        }

        if(c instanceof Porte){
            if(!((Porte) c).estOuverte() && cles > 0){
                ((Porte) c).ouvrire();
                cles--;
                c.entre(this);
                this.c = c;
            } else if(((Porte) c).estOuverte()){
                c.entre(this);
                this.c = c;
            }

        } else if (c instanceof  Hall){
            if(((Hall) c).possedeCle()){
                cles++;
                ((Hall) c).supprimerCle();
            }
            c.entre(this);
            this.c = c;
        }
    }

    public void subisDegat (int degat){
        this.resistance -= degat;
        if (this.resistance < 0){
            this.resistance = 0;
        }
    }
}
