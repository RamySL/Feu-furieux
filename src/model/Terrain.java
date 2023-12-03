package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Terrain {
    public static Case caseParDefaut;
    private int hauteur, largeur;
    private Case[][] carte;
    private Joueur joueur;

    public Terrain(String file) {
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();
            int resistanceJoueur = sc.nextInt();
            int cles = sc.nextInt();
            sc.nextLine();
            this.carte = new Case[hauteur][largeur];
            for (int l=0; l<hauteur; l++) {
                String line = sc.nextLine();
                for (int c=0; c<largeur; c++) {
                    Case cc;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#': cc = new Mur(l, c); break;
                        case ' ': cc = new Hall(l, c); break;
                        case '+': cc = new Hall(l, c, true); break;
                        case '1': case '2': case '3': case '4':
                            cc = new Hall(l, c, (int)ch-(int)'0'); break;
                        case 'O': cc = new Sortie(l, c, 0); break;
                        case '@': cc = new Porte(l, c, false); break;
                        case '.': cc = new Porte(l, c, true); break;
                        case 'H':
                            if (this.joueur != null) throw new IllegalArgumentException("carte avec deux joueurs");
                            cc = new Hall(l, c);
                            this.joueur = new Joueur((CaseTraversable) cc, resistanceJoueur, cles);
                            ((Hall) cc).entre(joueur);
                            break;
                        default:  cc = null; break;
                    }
                    carte[l][c] = cc;
                }
            }
            sc.close();
        }
        catch (IOException e) { e.printStackTrace(); System.exit(1); }
        caseParDefaut = carte[10][19];//case par default pour creer des joueurs, il sera executé une seule fois car c'est pas logique d'avoire plusieurs terrains dans ce cas
    }

    public Terrain(String file, Joueur joueur) {

        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();
            int resistanceJoueur = sc.nextInt();
            int cles = sc.nextInt();
            sc.nextLine();
            this.carte = new Case[hauteur][largeur];
            for (int l=0; l<hauteur; l++) {
                String line = sc.nextLine();
                for (int c=0; c<largeur; c++) {
                    Case cc;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#': cc = new Mur(l, c); break;
                        case ' ': cc = new Hall(l, c); break;
                        case '+': cc = new Hall(l, c, true); break;
                        case '1': case '2': case '3': case '4':
                            cc = new Hall(l, c, (int)ch-(int)'0'); break;
                        case 'O': cc = new Sortie(l, c, 0); break;
                        case '@': cc = new Porte(l, c, false); break;
                        case '.': cc = new Porte(l, c, true); break;
                        case 'H':
                            if (this.joueur != null) throw new IllegalArgumentException("carte avec deux joueurs");
                            cc = new Hall(l, c);
                            this.joueur = joueur;
                            this.joueur.setCase((CaseTraversable) cc);
                            this.joueur.setResistance(resistanceJoueur);
                            this.joueur.setCles(cles);
                            ((Hall) cc).entre(joueur);
                            break;
                        default:  cc = null; break;
                    }
                    carte[l][c] = cc;
                }
            }
            sc.close();
        }
        catch (IOException e) { e.printStackTrace(); System.exit(1); }
        caseParDefaut = carte[10][19];//case par default pour creer des joueurs, il sera executé une seule fois car c'est pas logique d'avoire plusieurs terrains dans ce cas
    }
    public Joueur getJoueur() { return this.joueur; }

    public int getHauteur (){
        return this.hauteur;
    }

    public int getLargeur (){
        return this.largeur;
    }

    public Case[][] getCarte (){
        return this.carte;
    }


    public ArrayList<CaseTraversable> getVoisinesTraversables(int lig, int col) {
        /* À compléter */
        //un voisin c'est une case qui son num de ligne et de cologne ne depassent pas 1
        // en distance avec la case courante

        // dans ce code la case courante est inclus dans la liste (card = 9)
        ArrayList<CaseTraversable> res = new ArrayList<>();

        for (int l = 0; l< this.hauteur; l++){
            Case [] ligne = this.carte[l];
            for (int c = 0; c< this.largeur;c++){
                if ( (lig - l == 0 || lig - l == 1 || lig - l == -1) && ((col - c == 0 || col - c == 1 || col - c == -1))){
                    // c'est une vosine
                    Case cc = ligne[c];
                    if (cc instanceof CaseTraversable){
                        res.add((CaseTraversable) cc);
                    }
                }
            }
        }
        return res;
    }
}
