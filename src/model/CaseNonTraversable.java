package model;
import java.awt.*;
abstract class CaseNonTraversable extends Case{
    //Une classe qui peut servir à utilisations future de l'applicaiton

    public CaseNonTraversable(int l, int c){
        super(l, c);
    }
    public abstract boolean estTraversable();
    public void paint(Graphics g, int translationX, int translationY){
        super.paint(g, translationX, translationY);
    }
}
