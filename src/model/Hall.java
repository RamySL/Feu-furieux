package model;

import model.CaseTraversable;

public class Hall extends CaseTraversable {

    private boolean cle;
    private Joueur  joueur = null;

    public Hall (int l, int c){
        this (l,c,0,false);
    }

    public Hall (int l, int c, boolean cle){
        // par défaut si on ne fournie pas de chaleur c'est 0
        this (l, c, 0,cle);
    }
    public Hall (int l, int c, int chaleur){
        this (l, c, chaleur,false);
    }

    public Hall (int l, int c,int chaleur, boolean cle ){
        super (l,c, chaleur);
        this.cle = cle;
    }

    public boolean possedeCle(){
        return this.cle;
    }

    public boolean estTraversable (){
        return this.joueur == null;
    }

    public Joueur getJoueur (){
        // retourne le joueur si il yen a sinon retourne null

        if ( ! this.estTraversable()){
            return this.joueur;
        }else {
            return null;
        }
    }

    public void entre (Joueur j){
        this.joueur = j;
    }


}
