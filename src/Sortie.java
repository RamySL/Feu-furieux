public class Sortie extends CaseTraversable{


    public Sortie (int l, int c){
        this (l,c,0);
    }
    public Sortie (int l, int c, int chaleur) {
        super(l, c, chaleur);
    }
    public boolean estTraversable (){
        return true;
    }

    }



