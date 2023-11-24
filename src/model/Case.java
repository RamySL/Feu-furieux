package model;

import java.awt.*;
import java.util.ArrayList;

public abstract class Case {
        protected final int lig, col;

        protected Joueur joueur = null;
        protected int tailleCase = 36;
        public Case(int l, int c) {
            this.lig = l;
            this.col = c;
        }
        public abstract boolean estTraversable();

        public int getLigne (){return this.lig;}
        public int getColone (){return this.col;}

        public abstract boolean possedeJoueur ();

        public abstract void paint(Graphics g);


}
