package model;

import javax.swing.*;
import java.awt.*;


public class Porte extends CaseTraversable{
    private boolean ouverte;

    public Porte(int l, int c, boolean ouverte){
        super(l, c, 0);//initialement les chaleurs sont à 0
        this.ouverte = ouverte;
    }

    public boolean estTraversable(){
        return true;
    }


    public void ouvrire(){
        this.ouverte = true;
    }

    public boolean estOuverte(){
        return ouverte;
    }


    public void paint (Graphics g, int translationX, int translationY){


            if (ouverte){
                // sans le getImage on a un objet ImageIcone
                Image porteouverteImage = (new ImageIcon("src/assets/images/double-door-opened.png")).getImage() ;
                g.drawImage(porteouverteImage
                        , (this.getColone() - translationX) * this.tailleCase,
                        (this.getLigne() - translationY) * this.tailleCase, this.getTailleCase() ,this.getTailleCase() , Color.WHITE, null);}
            else{
                // sans le getImage on a un objet ImageIcone
                Image porteFermeeImage = (new ImageIcon("src/assets/images/porte-fermee_noire.png")).getImage() ;
                g.drawImage(porteFermeeImage
                        , (this.getColone() - translationX) * this.tailleCase,
                        (this.getLigne() - translationY) * this.tailleCase, this.getTailleCase() ,this.getTailleCase() , Color.WHITE, null);

            }



    }
}
