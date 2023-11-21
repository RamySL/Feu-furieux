package model;

import java.util.ArrayList;

public abstract class Case {
        public final int lig, col;
        public Case(int l, int c) {
            this.lig = l;
            this.col = c;
        }
        public abstract boolean estTraversable();

        public int getLigne (){return this.lig;}
        public int getColone (){return this.col;}
}
