package model;

import javax.swing.*;
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

//        g.setColor(new Color(0,0,0));
//        super.paint(g, translationX, translationY);
        Image murImage = (new ImageIcon("src/assets/images/mur.png")).getImage() ;
        g.drawImage(murImage
                , (this.getColone() - translationX) * this.tailleCase,
                (this.getLigne() - translationY) * this.tailleCase,  this.getTailleCase(),  this.getTailleCase(), null);

    }




}
