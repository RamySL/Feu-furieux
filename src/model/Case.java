package model;

import java.awt.*;
import java.util.ArrayList;

public abstract class Case {
        static final int tailleCle = 15;
        protected final int lig, col;

        protected int tailleCase = 36;
        public Case(int l, int c) {
            this.lig = l;
            this.col = c;
        }
        public abstract boolean estTraversable();

        public int getLigne (){return this.lig;}
        public int getColone (){return this.col;}

        public int getTailleCase(){return this.tailleCase;}

        public void paint(Graphics g, int translationX, int translationY){
                // le if est tjr satisfait vu qu'on utilise paint que sur les cases visivle du joueur
                if(this.getColone() - translationX >= 0 && this.getLigne() - translationY >= 0) {
                        //System.out.println("this.getColone() - translationX : " + (this.getColone() - translationX) + " this.getLigne() - translationY " + (this.getLigne() - translationY ));
                        //System.out.println("TransX " + translationX + " TransY " + translationY);
                        g.fillRect((this.getColone() - translationX) * this.tailleCase, (this.getLigne() - translationY) * this.tailleCase, this.tailleCase, this.tailleCase);
                }
        }


}
