package model;

import model.CaseTraversable;

import java.awt.*;

public class Sortie extends CaseTraversable {


    public Sortie (int l, int c){
        this (l,c,0);
    }
    public Sortie (int l, int c, int chaleur) {
        super(l, c, chaleur);
    }
    public boolean estTraversable (){
        return this.getJoueur() == null;
    }

    public boolean possedeJoueur (){
        return !(estTraversable ());
    }

    public void paint (Graphics g){
        g.setColor(new Color(0,0,255));
        g.fillRect(this.getColone() * this.tailleCase, this.getLigne() * this.tailleCase,this.tailleCase,this.tailleCase );
    }



    }



