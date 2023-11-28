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
        return true;
    }


    public boolean possedeJoueur (){
        return !(estTraversable ());
    }

    public void paint (Graphics g, int translationX, int translationY){
        g.setColor(new Color(0,0,255));
        super.paint(g, translationX, translationY);
      }
    }



