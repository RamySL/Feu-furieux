public class Hall extends CaseTraversable{

    private boolean cle,aJoueur = false;

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
        return this.aJoueur;
    }

    public void entre (Joueur j){
        this.aJoueur = true;
    }


}
