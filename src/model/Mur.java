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

    public void paint(Graphics g){

        g.setColor(new Color(0,0,0));
        g.fillRect(this.getColone() * this.tailleCase , this.getLigne() * this.tailleCase,this.tailleCase,this.tailleCase );
    }




}
