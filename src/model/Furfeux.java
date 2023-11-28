package model;

import controller_view.FenetreJeu;

import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;

public class Furfeux {

    Terrain terrain;
    Joueur joueur;

    public Furfeux(String f) {
        this.terrain = new Terrain(f);
        this.joueur = terrain.getJoueur();
    }

    public void tour() {
        /* À compléter */

        // ici il faut infliger les degat au joueur quand il est sur
        // une case avec chaleur parceque c'es liée Timer
        CaseTraversable caseJoueur =(CaseTraversable) this.joueur.getCase();
        this.joueur.subisDegat(caseJoueur.getChaleur());

        // ici il faut prpagé les flemmes aussi

        // La somme de la case et de ses 8 vosines, on tire au hazard entre 0 et 199
        // si sum > random alors chaleur += 1
        // sinon si random > 190 alors chaleur -= 1
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

    public static void main(String[] args) {
        int tempo = 100;
        Furfeux jeu = new Furfeux("src/model/manoir.txt");
        FenetreJeu graphic = new FenetreJeu(jeu.terrain);
        Timer timer = new Timer(tempo, e -> {
            jeu.tour();
            graphic.actuVie();
            graphic.actuCles();
            graphic.repaint();
            if (jeu.partieFinie()) {
                graphic.ecranFinal(Math.max(0, jeu.joueur.getResistance()));
                ((Timer)e.getSource()).stop();
            }
        });
        timer.start();
    }
}
