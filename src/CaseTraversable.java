public abstract class CaseTraversable extends Case {
    protected int chaleur;

    public CaseTraversable (int l, int c, int chaleur){
        super(l,c);
        this.chaleur = chaleur;
    }

    public void incrementeChaleur (){
        // 10 c'est le maximum que peut prendre chaleur

        if (this.chaleur < 10){
            this.chaleur ++;
        }
    }

    // 0 c'est le minimum

    public void decrementeChaleur (){
        // 0 c'est le minimum
        if (this.chaleur > 0){
            this.chaleur ++;
        }
    }
}
