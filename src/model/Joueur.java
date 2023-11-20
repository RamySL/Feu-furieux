package model;

public class Joueur {
    private CaseTraversable c;
    private int resistance;
    private int cles;
    public Joueur(CaseTraversable c, int r, int k) {
        this.c = c;
        this.resistance = r;
        this.cles = k;
    }

    public int getResistance() {
        return this.resistance;
    }

    public void bouge(Case cible) {
        /* À compléter */
    }
}
