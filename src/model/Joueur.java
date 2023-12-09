package model;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Joueur implements Serializable, Comparable{
    public static int id_courant;
    private transient CaseTraversable c;
    private int resistance;
    private transient int cles;
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
        // on a utiliser une calsse deolacer à la place
    }

    public int getCles(){
        return this.cles;
    }

    public String getNom(){
        return this.nom;
    }

    public int getId() {
        return id;
    }

    public int getScore(){
        return this.score;
    }
    public int getNiveau(){
        return this.niveau;
    }

    public void incScore(int offset){
        this.score+=offset;
    }
    public void incNiveau(){
        this.niveau++;
    }
    public Case getCase (){return this.c;}

    public void paint (Graphics g, int translationX, int translationY){
        int tailleCase = this.c.getTailleCase();
        // sans le getImage on a un objet ImageIcone
        Image joueurImage = (new ImageIcon("src/assets/images/joueur.png")).getImage() ;
        g.drawImage(joueurImage
                , (this.c.getColone() - translationX) * tailleCase,
                (this.c.getLigne() - translationY) * tailleCase, tailleCase ,tailleCase , new Color(255, true), null);}


    public void deplacer(CaseTraversable c) {
        if(c == null)
            return;
        if(c instanceof Porte){

            if(!((Porte) c).estOuverte() && cles > 0){

                PlaySound porteOuverteSound = new PlaySound("src/assets/audio/porte_ouverte2.wav");
                porteOuverteSound.jouer(false);
                ((Porte) c).ouvrire();
                cles--;
                // on met le joueur dans sa nouvelle case c (la case elle sait qu'il ya le joueur)
                c.entre(this);
                // mais le joueur ne sait pas encore su'il a changé de case
                // on le fait ici
                this.c = c;
                return;
            } else if (!((Porte) c).estOuverte()){
                return;
            }

            else if(((Porte) c).estOuverte()){
                c.entre(this);
                this.c = c;
                return;
            }

        } else if (c instanceof  Hall && ((Hall) c).possedeCle()){
                PlaySound ramassageCleSound = new PlaySound("src/assets/audio/cle.wav");
                ramassageCleSound.jouer(false);
                cles++;
                ((Hall) c).supprimerCle();
        }
        c.entre(this);
        this.c = c;

    }

    public void subisDegat (int degat){
        this.resistance -= degat;
        if (this.resistance < 0){
            this.resistance = 0;
        }
    }

    public boolean compareName(String str){
        return this.nom.compareTo(str)==0;
    }
    public int compareTo(Object o){
        if ((this.niveau == ((Joueur) o).niveau))
            if(this.score == ((Joueur) o).score) return this.id - ((Joueur) o).id;
            else return - this.score + ((Joueur) o).score;//trier selon ordre décroissant
        else return - this.niveau + ((Joueur) o).niveau;

    }
    public void setCase(CaseTraversable cc){
        this.c = cc;
    }
    public void setResistance(int r){
        this.resistance = r;
    }
    public void setCles(int cles){
        this.cles = cles;
    }
    public void setScore(int sc){
        //méthode qui gére l'incrémentation du niveau et score d'une manière logarithmique
        this.score += sc;
        if(this.score >= (this.niveau + 1) * 1000){
            this.niveau += this.score / ((this.niveau + 1) * 1000);
            this.score -= this.niveau * 1000;
        }
    }
}
