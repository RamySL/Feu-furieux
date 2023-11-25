package controller_view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FenetreJeu extends JPanel implements KeyListener {
    private Terrain terrain;
    private final int tailleCase = 36;
    private final int hauteur, largeur, fenetreHaut, fenetreLarg, rayon2;
    private JFrame frame;

    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        fenetreHaut = 9;
        fenetreLarg = 9;
        rayon2 = 10;
        this.terrain = t;

        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(this.fenetreLarg * tailleCase, this.fenetreHaut * tailleCase));

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
        // La méthode va dessiner tout les element du terrain
        int centrey = terrain.getJoueur().getCase().getLigne(),
        centrex = terrain.getJoueur().getCase().getColone();

        for(int i = 0; i < terrain.getHauteur(); i++){
            for(int j = 0; j < terrain.getLargeur(); j++){
                if((i - centrey) * (i - centrey) + (j - centrex) * (j - centrex) <= this.rayon2) {

                    terrain.getCarte()[i][j].paint(g, centrex - 4, centrey - 4);
                }
            }
        }
        terrain.getJoueur().paint(g, centrex - 4, centrey - 4);

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
    public void keyPressed(KeyEvent e){
        Joueur j = this.terrain.getJoueur();
        Case cc = j.getCase(), cible = null;

        if (e.getKeyCode() == 38 && cc.getLigne() - 1 >= 0)
            cible = this.terrain.getCarte()[cc.getLigne() - 1][cc.getColone()];


        else if (e.getKeyCode() == 39 && cc.getColone() + 1 <= this.largeur)
            cible = this.terrain.getCarte()[cc.getLigne()][cc.getColone() + 1];

        else if (e.getKeyCode() == 40 && cc.getLigne() + 1 <= this.hauteur)
            cible = this.terrain.getCarte()[cc.getLigne() + 1][cc.getColone()];

        else if (e.getKeyCode() == 37 && cc.getColone() - 1 >= 0)
            cible = this.terrain.getCarte()[cc.getLigne()][cc.getColone() - 1];

        if(cible != null && cible.estTraversable())
            j.deplacer((CaseTraversable) cible);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        FenetreJeu f = new FenetreJeu(new Terrain("src/model/manoir.txt"));

    }
}
