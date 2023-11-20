package model;

public class Porte extends Case{
    private boolean ouverte;

    public Porte(int l, int c, boolean ouverte){
        super(l, c);
        this.ouverte = ouverte;
    }

    public boolean estTraversable(){
        return ouverte;
    }
}
