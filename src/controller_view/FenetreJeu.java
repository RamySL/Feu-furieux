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
    }

    public void paintAll (Graphics g) {
        // La méthode va desssiner tout les element du terrain
        Case[][] carte = this.terrain.getCarte();
        for (Case[] ligne : carte) {
            for (Case c : ligne) {
                c.paint(g);
                if (c.possedeJoueur()){
                    this.paintJoueur(g,c.getLigne(), c.getColone());
                }


            }
        }
    }

    public void paintJoueur (Graphics g,int l, int c){
        // pour ne pas recopier encore une fois la methode getJoueur dans Porte
        // paintJoueur ne prends pas d'instance de Joueur

        g.setColor(new Color(190,100,50));
        g.fillOval(c * this.tailleCase, l * this.tailleCase,this.tailleCase,this.tailleCase);

    }



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
