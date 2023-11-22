package controller_view;

import model.*;

import javax.swing.*;
import java.awt.*;

public class FenetreJeu extends JPanel {
    private Terrain terrain;
    private int tailleCase = 36;
    private int hauteur, largeur;
    private JFrame frame;

    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;

        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(this.largeur * tailleCase, this.hauteur * tailleCase));

        JFrame frame = new JFrame("model.Furfeux");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintAll (g);
        /* À compléter */    }

    public void paintAll (Graphics g) {
        // La méthode va desssiner tout les element du terrain
        Case[][] carte = this.terrain.getCarte();

        for (int l = 0; l < carte.length; l++) {
            Case[] ligne = carte[l];
            for (int c = 0; c < ligne.length; c++) {
                Case cc = ligne[c];

                if (cc instanceof Mur) {
                    this.paintMurs(g, ((Mur) cc));
                }

                if (cc instanceof Hall) {
                    this.paintHalls(g, ((Hall) cc));
                }

                if (cc instanceof Sortie) {
                    this.paintSortie(g, ((Sortie) cc));
                }

                if (cc instanceof Porte){
                    this.paintPorte(g, (Porte) cc);
                }

            }
        }

    }
    public void paintMurs(Graphics g, Mur mur){

        g.setColor(new Color(0,0,0));
        g.fillRect(mur.getColone() * this.tailleCase, mur.getLigne() * this.tailleCase,this.tailleCase,this.tailleCase );
    }

    public void paintHalls (Graphics g, Hall hall){

        g.setColor(new Color(255,0,0,hall.rougeIntensite()));
        g.fillRect(hall.getColone() * this.tailleCase, hall.getLigne() * this.tailleCase,this.tailleCase,this.tailleCase );

        if (hall.possedeCle()){
            //g.setColor(Color.LIGHT_GRAY);
            g.setColor(new Color(150,150,150));
            g.fillRect(hall.getColone() * this.tailleCase , hall.getLigne() * this.tailleCase ,(int) (this.tailleCase * 0.5),(int) (this.tailleCase * 0.5));
        }

    }

    public void paintSortie (Graphics g, Sortie sortie){
        g.setColor(new Color(0,0,255));
        g.fillRect(sortie.getColone() * this.tailleCase, sortie.getLigne() * this.tailleCase,this.tailleCase,this.tailleCase );
    }

    public void paintPorte (Graphics g, Porte porte){

        if (porte.estTraversable()){
            // porte ouverte
            g.setColor(new Color(255,255,255));
            g.fillRect(porte.getColone() * this.tailleCase, porte.getLigne() * this.tailleCase,this.tailleCase,this.tailleCase );
        }else{
            g.setColor(new Color(0,255,0));
            g.fillRect(porte.getColone() * this.tailleCase, porte.getLigne() * this.tailleCase,this.tailleCase,this.tailleCase );
        }

    }

    //public void paintJoueur (Graphics g, Joueur j){}



    public void ecranFinal(int n) {
        /* l'écran quand la partie s'est terminé */
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }

    public static void main(String[] args) {
        FenetreJeu f = new FenetreJeu(new Terrain("src/model/manoir.txt"));

    }
}
