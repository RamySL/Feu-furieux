package model;

import java.awt.*;

public class Joueur {
    private static int id_courant = 0;
    private CaseTraversable c;
    private int resistance;
    private int cles;
    private String nom, pswd;

    private int id;
    public Joueur(CaseTraversable c, int r, int k) {
        this.c = c;
        this.resistance = r;
        this.cles = k;
    }

    public Joueur(CaseTraversable c, int r, int k, String nom, String pswd){
        this.c = c;
        this.resistance = r;
        this.cles = k;
        this.nom = nom;
        this.pswd = pswd;
        this.id = Joueur.id_courant;
        Joueur.id_courant++;
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

    public String getPswd() {
        return pswd;
    }

    public int getId() {
        return id;
    }

    public Case getCase (){return this.c;}

    public void paint (Graphics g){
        int tailleCase = this.c.getTailleCase();
        g.setColor(new Color(190,100,50));
        g.fillOval(this.c.getColone() * tailleCase, this.c.getLigne() * tailleCase,tailleCase,tailleCase);
    }


}
