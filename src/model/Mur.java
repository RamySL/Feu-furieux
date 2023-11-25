package model;

import java.awt.*;

public class Mur extends CaseNonTraversable{
    public Mur(int l, int c){
        super(l, c);
    }

    public boolean estTraversable(){
        return false;
    }
    public boolean possedeJoueur(){return false;}

    public void paint(Graphics g, int translationX, int translationY){

        g.setColor(new Color(0,0,0));
        super.paint(g, translationX, translationY);
     }




}
