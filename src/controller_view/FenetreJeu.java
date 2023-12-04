package controller_view;

import model.*;

import javax.sound.sampled.*;
import javax.swing.*;
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

    JLabel nbCles =new JLabel();

    JProgressBar barVie = new JProgressBar();
    public FenetreJeu(Terrain t, JFrame frame) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        fenetreHaut = 9;
        fenetreLarg = 9;
        rayon2 = 10;
        this.terrain = t;

        ////////////////////////////////////////////////////////////////////////////////////////////////
        // largeur et heuteur du menu de partie

        int largeurMenu = this.fenetreLarg * tailleCase;
        int hauteurMenu = 40;

        Joueur joueur = this.terrain.getJoueur();

        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(this.fenetreLarg * tailleCase,  this.fenetreHaut * tailleCase));

        //JFrame frame = new JFrame("FurFeux Oualid & Ramy");
        this.frame = frame;

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
        ImageIcon iconeCle = new ImageIcon("src/assets/key2.png");
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
        barVie.setBounds((int)(largeurMenu * 0.2),0,(int)(largeurMenu * 0.6),hauteurMenu);
        barVie.setFont(new Font("MV Boli",Font.BOLD,20));
        barVie.setValue(resistance);
        barVie.setBackground(Color.BLACK);
        barVie.setForeground(Color.RED);
        barVie.setString("" + resistance);
        barVie.setStringPainted(true);
        infoJoueur.add(barVie);//,BorderLayout.WEST);

        //JLabel joueurPseudo = new JLabel();

//        // Menu du jeu
//        JButton bouttonPause = new JButton("P");
//        bouttonPause.setBounds((int)(largeurMenu * 0.8), 0,(int)(largeurMenu * 0.2) , hauteurMenu);
//        infoJoueur.add(bouttonPause);//, BorderLayout.EAST);


        frame.addKeyListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(infoJoueur,BorderLayout.NORTH);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);


        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //setBackground(Color.LIGHT_GRAY);
        //setPreferredSize(new Dimension(this.fenetreLarg * tailleCase, this.fenetreHaut * tailleCase));

        //this.frame = frame;

        this.frame.addKeyListener(this);
        this.frame.setFocusable(true);
        this.frame.requestFocusInWindow();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintAll (g);
    }

    public void paintAll (Graphics g) {
        // La méthode va dessiner tout les element du terrain
        int centrey = terrain.getJoueur().getCase().getLigne(),
        centrex = terrain.getJoueur().getCase().getColone();
        for (int i = 0; i < terrain.getHauteur(); i++) {
            for (int j = 0; j < terrain.getLargeur(); j++) {
                if ((i - centrey) * (i - centrey) + (j - centrex) * (j - centrex) <= this.rayon2) {
                    terrain.getCarte()[i][j].paint(g, centrex - 4, centrey - 4);
                }
            }
        }
        terrain.getJoueur().paint(g, centrex - 4, centrey - 4);
    }

    public void ecranFinal(int n) {
        /* l'écran quand la partie s'est terminé */
        //.remove(this);
        Component parent = this.getParent();
        ((JPanel) parent).remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
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
            //(new DataBase()).playSound(DataBase.move_sound);
            //playSound("C:\\Users\\Oualid_CHABANE\\IdeaProjects\\projet_feu_furieux\\src\\assets\\marche.wav");
        }
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
