package model;

public class Mur extends CaseNonTraversable{
    public Mur(int l, int c){
        super(l, c);
    }

    public boolean estTraversable(){
        return false;
    }
}
