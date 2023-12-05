package model;

import model.CaseTraversable;

import javax.swing.*;
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

        // sans le getImage on a un objet ImageIcone
        Image sortieImage = (new ImageIcon("src/assets/sortie512.png")).getImage() ;
        g.drawImage(sortieImage
                , (this.getColone() - translationX) * this.tailleCase,
                (this.getLigne() - translationY) * this.tailleCase, this.getTailleCase() ,this.getTailleCase() , Color.WHITE, null);

    }
    }



