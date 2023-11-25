package controller_view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FenetreJeu extends JPanel implements KeyListener {
    private Terrain terrain;
    private int tailleCase = 36;
    private int hauteur, largeur;
    private JFrame frame;

    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;

        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(9 * tailleCase, 9 * tailleCase));

        JFrame frame = new JFrame("model.Furfeux");
        this.frame = frame;

        frame.addKeyListener(this);

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
        // La méthode va desssiner le champs du joueur
        //
        ArrayList<Case> vision = this.terrain.getVisionJoueur();
        for (Case c : vision){
            c.paint(g);
            if (c.possedeJoueur()) {
                c.getJoueur().paint(g);
            }
        }
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

    @Override
    public void keyTyped(KeyEvent e) {

        //System.out.println("Code : "  + e.getKeyLocation());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Joueur j = this.terrain.getJoueur();
        Case cc = j.getCase();

        if (e.getKeyCode() == 38){
            Case cible = this.terrain.getCarte()[cc.getLigne() - 1][cc.getColone()];
            j.bouge(cible);
        }else if (e.getKeyCode() == 39){
            Case cible = this.terrain.getCarte()[cc.getLigne()][cc.getColone() + 1];
            j.bouge(cible);
        }else if (e.getKeyCode() == 40){
            Case cible = this.terrain.getCarte()[cc.getLigne() + 1][cc.getColone()];
            j.bouge(cible);
        }else if (e.getKeyCode() == 37){
            Case cible = this.terrain.getCarte()[cc.getLigne()][cc.getColone() - 1];
            j.bouge(cible);}
        System.out.println(this.terrain.getVisionJoueur().size());
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        FenetreJeu f = new FenetreJeu(new Terrain("src/model/manoir.txt"));

    }
}
