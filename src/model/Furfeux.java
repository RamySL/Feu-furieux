package model;

import java.util.ArrayList;
import java.util.Random;

public class Furfeux {

    Terrain terrain;
    Joueur joueur;

    public Furfeux(String f) {
        this(f,(new Terrain(f)).getJoueur());
    }
    public Furfeux(String f, Joueur jr) {
        this.terrain = new Terrain(f, jr);
        this.joueur = jr;
    }


    public void tour() {

        // ici il faut infliger les dégâts au joueur quand il est sur
        // une case avec chaleur et propager les flemmes, parce que c'est lié au Timer
        CaseTraversable caseJoueur = (CaseTraversable) this.joueur.getCase();
        this.joueur.subisDegat(caseJoueur.getChaleur());

        // La somme de la case et de ses 8 voisines, on tire au hazard entre 0 et 199
        // si sum > random alors chaleur += 1
        // sinon si random > 190, alors chaleur -= 1
        // sinon rien ne change

        for (Case[] ligne : this.terrain.getCarte()) {
            for (Case cc : ligne) {
                if (cc instanceof CaseTraversable) {
                    int sumChaleurs = 0;
                    Random rnd = new Random();
                    int r = rnd.nextInt(200);
                    ArrayList<CaseTraversable> voisineChaleur = this.terrain.getVoisinesTraversables(cc.getLigne(), cc.getColone());
                    for (CaseTraversable v : voisineChaleur) {
                        sumChaleurs += v.getChaleur();
                    }
                    if (sumChaleurs > r) {
                        ((CaseTraversable) cc).incrementeChaleur();
                    }else if (r > 190){
                        ((CaseTraversable) cc).decrementeChaleur();
                    }

                }
            }

        }
    }



    public boolean partieFinie() {
        /* À compléter */
        return (this.joueur.getResistance() == 0) || (this.joueur.getCase() instanceof Sortie);
    }

    public Joueur getJoueur(){
        return joueur;
    }

    public Terrain getTerrain(){
        return this.terrain;
    }
}
