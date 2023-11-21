package controller_view;

import model.Joueur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Menu extends JSplitPane {
    private String ramy = "Ramy SAIL", oualid = "Oualid CHABANE";


}
 class ListeJoueurs extends JScrollPane {
    ArrayList<Joueur> listeJoueurs;
    JTable tableJoueurs;

   /* public ListeJoueurs(ArrayList<Joueur> list, double lon, double larg, Component parent){
        this.listeJoueurs = list;
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setPreferredSize(new Dimension(parent.getWidth(), parent.getHeight()));
        actualiserTableJoueurs();
        this.add(tableJoueurs);
    }

    private void actualiserTableJoueurs(){
        tableJoueurs.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
    }*/
}
