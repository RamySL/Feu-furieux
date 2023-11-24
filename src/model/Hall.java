package model;

import model.CaseTraversable;

import java.awt.*;

public class Hall extends CaseTraversable {

    private boolean cle;


    public Hall (int l, int c){
        this (l,c,0,false);
    }

    public Hall (int l, int c, boolean cle){
        // par défaut si on ne fournie pas de chaleur c'est 0
        this (l, c, 0,cle);
    }
    public Hall (int l, int c, int chaleur){
        this (l, c, chaleur,false);
    }

    public Hall (int l, int c,int chaleur, boolean cle ){
        super (l,c, chaleur);
        this.cle = cle;
    }

    public boolean possedeCle(){
        return this.cle;
    }

    public boolean estTraversable (){
        return this.getJoueur() == null;
    }



    public boolean possedeJoueur (){
        // pour l'instant elle n'a pas vraiment de sens mais si on ajoute
        // d'autres créatures le hall elle peut ne pas etre traversable
        // mais ne pas avoir un joueur

        return (! estTraversable());
    }

    public void paint(Graphics g){

        if (this.rougeIntensite() == 0){
            g.setColor(new Color(255,255,255));
            g.fillRect(this.getColone() * this.tailleCase, this.getLigne() * this.tailleCase,this.tailleCase,this.tailleCase );
        }else{
            g.setColor(new Color(255,0,0,this.rougeIntensite()));
            g.fillRect(this.getColone() * this.tailleCase, this.getLigne() * this.tailleCase,this.tailleCase,this.tailleCase );
        }

        if (this.possedeCle()){
            //g.setColor(Color.LIGHT_GRAY);
            g.setColor(new Color(150,150,150));
            g.fillRect(this.getColone() * this.tailleCase , this.getLigne() * this.tailleCase ,(int) (this.tailleCase * 0.5),(int) (this.tailleCase * 0.5));
        }
    }




}
