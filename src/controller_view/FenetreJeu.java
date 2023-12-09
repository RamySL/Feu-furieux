package controller_view;

import model.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class FenetreJeu extends JPanel implements KeyListener {
    private Terrain terrain;
    private final int tailleCase = 36;
    private final int hauteur, largeur, fenetreHaut, fenetreLarg, rayon2;
    private JFrame frame;

    private JLabel nbCles =new JLabel();

    private JProgressBar barVie;
    public FenetreJeu(Terrain t, JFrame frame) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        fenetreHaut = 14;
        fenetreLarg = 14;
        rayon2 = 10;
        this.terrain = t;
        this.frame = frame;
        Joueur joueur = this.terrain.getJoueur();
        ////////////////////////////////////////////////////////////////////////////////////////////////
        // largeur et heuteur du menu de partie

        int largeurMenu = this.fenetreLarg * tailleCase;
        int hauteurMenu = 40;
        //this.setBackground(new Color(0x80ffdb));
        this.setBackground(new Color(143, 143, 143, 255));
        setPreferredSize(new Dimension(this.fenetreLarg * tailleCase,  this.fenetreHaut * tailleCase));

        this.setLayout(new BorderLayout());
        //JFrame frame = new JFrame("FurFeux Oualid & Ramy");

        /* Le panel qui va contenir les infos du joueur pendant la partie
        comme son nombre de clés son niveau de vie son pseudo (l'actualisation de l'affichage est faite dans
        FurFeux)
         */
        JPanel infoJoueur = new JPanel();
        infoJoueur.setLayout(null);
        infoJoueur.setBackground(Color.BLACK);
        infoJoueur.setPreferredSize(new Dimension(largeurMenu,hauteurMenu));

        // Le panel qui va contenir le nombre de cle et l'icone representant la clé
        JPanel infoCles = new JPanel();
        infoCles.setBounds(0,0,(int)(largeurMenu * 0.2),hauteurMenu);
        infoCles.setBackground(Color.CYAN);
        // Le label qui va contenir l'icone de la clé
        JLabel labelCle = new JLabel();
        ImageIcon iconeCle = new ImageIcon("src/assets/key.png");
        labelCle.setIcon(iconeCle);
        infoCles.add(labelCle);
        // Le label qui indique le nombre de clés
        this.nbCles.setText(String.valueOf(joueur.getCles()));
        this.nbCles.setForeground(Color.RED);
        //this.nbCles.setBounds(this.fenetreLarg * tailleCase - 50,20,20,20);
        infoCles.add(nbCles);
        infoJoueur.add(infoCles);


        // La bare de vie du joueur
        int resistance = joueur.getResistance();
        barVie = new JProgressBar(0,resistance);
        barVie.setBounds((int)(largeurMenu * 0.2),0,(int)(largeurMenu * 0.8),hauteurMenu);
        barVie.setFont(new Font("MV Boli",Font.BOLD,20));
        barVie.setValue(resistance);
        barVie.setBackground(Color.BLACK);
        barVie.setForeground(Color.RED);
        barVie.setString("" + resistance);
        barVie.setStringPainted(true);
        infoJoueur.add(barVie);//,BorderLayout.WEST);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(infoJoueur,BorderLayout.NORTH);

        this.frame.addKeyListener(this);
        this.frame.setFocusable(true);
        this.frame.requestFocusInWindow();
    }

    public void actuVie(){
        int resistance = this.terrain.getJoueur().getResistance();
        this.barVie.setValue(resistance);
        this.barVie.setString("" + resistance);}

    public void actuCles(){
        this.nbCles.setText(String.valueOf(this.terrain.getJoueur().getCles()));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintAll (g);
    }

    public void paintAll (Graphics g) {
        // La méthode va dessiner tous les elements du terrain
        // centrex et centrey c'est les coord x et y du joueur (qui est le centre de la vue)
        int centrey = terrain.getJoueur().getCase().getLigne(),
        centrex = terrain.getJoueur().getCase().getColone();
        for (int i = 0; i < terrain.getHauteur(); i++) {
            for (int j = 0; j < terrain.getLargeur(); j++) {
                if ((i - centrey) * (i - centrey) + (j - centrex) * (j - centrex) <= this.rayon2) {
                    terrain.getCarte()[i][j].paint(g, centrex - 7, centrey - 5);
                }
            }
        }
        terrain.getJoueur().paint(g, centrex - 7, centrey - 5);
    }

    public void ecranFinal(int n) {
        /* l'écran quand la partie s'est terminé */
        //.remove(this);
        Component parent = this.getParent();
        ((JPanel) parent).remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Verdana", 1, 20));
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        ((JPanel) parent).add(label, BorderLayout.CENTER);
        parent.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

        //System.out.println("Code : "  + e.getKeyLocation());
    }

    @Override
    public void keyPressed(KeyEvent e) {

        Joueur j = this.terrain.getJoueur();
        Case cc = j.getCase(), cible = null;
        if (e.getKeyCode() == 38 && cc.getLigne() - 1 >= 0) {
            cible = this.terrain.getCarte()[cc.getLigne() - 1][cc.getColone()];
        } else if (e.getKeyCode() == 39 && cc.getColone() + 1 <= this.largeur){
            cible = this.terrain.getCarte()[cc.getLigne()][cc.getColone() + 1];
        }
        else if (e.getKeyCode() == 40 && cc.getLigne() + 1 <= this.hauteur){
            cible = this.terrain.getCarte()[cc.getLigne() + 1][cc.getColone()];
        }
        else if (e.getKeyCode() == 37 && cc.getColone() - 1 >= 0) {
            cible = this.terrain.getCarte()[cc.getLigne()][cc.getColone() - 1];
        }

        if(cible != null && cible.estTraversable()){
            j.deplacer((CaseTraversable) cible);
        }
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
