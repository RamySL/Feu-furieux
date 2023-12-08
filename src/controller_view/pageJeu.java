package controller_view;

import model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class pageJeu extends JPanel implements MouseListener, ContainerListener {
    //Contenaire du jeu avec le bouton de retour vers le menu
    FenetreJeu ff;//la fenetre qdonnée dans le cahier de chargees
    JButton quit;
    Menu menu;//le menu précédent
    JFrame frame;// le frame globale réutilisé
    String tr;
    Furfeux feuFurieux;//feufurieux, qui permet de bouger le jeu
    Timer timer;

    final int tempo = 100;
    public pageJeu(Menu menu, JFrame frame, String tr){
        //On aura besoin d'un menu, parce que cette class dispose d'un bouton retour, donc on doit récupérer le menu précedent quelque part
        this.menu = menu;
        this.tr = tr;
        this.frame = frame;
        //création de la fenetre avec le meme terrain dans feufurieux
        this.feuFurieux = new Furfeux(this.tr, menu.getJoueur());
        this.ff = new FenetreJeu(this.feuFurieux.getTerrain(), this.frame);

        this.setPreferredSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout());



        quit = new JButton("Quitter");
        quit.addMouseListener(this);
        quit.setPreferredSize(new Dimension(100, 50));
        quit.setFont(new Font("Arial", Font.BOLD, 16));
        quit.setForeground(new Color(0x463f3a));
        quit.setBackground(new Color(0xFFD5D5DA, true));
        quit.setFocusPainted(false);

        // coté gauche de la fen
        JPanel westSide = new JPanel();
        westSide.setPreferredSize(new Dimension(150,100));
        westSide.setBackground(new Color(0x290025));
        westSide.add (quit);

        // le haut de la fen
        JPanel northSide = new JPanel();
        northSide.setBackground(new Color(0x290025));
        northSide.setPreferredSize(new Dimension(100,70));

        // le bas de la fenetre
        JPanel southSide = new JPanel();
        southSide.setBackground(new Color(0x290025));
        southSide.setPreferredSize(new Dimension(100,50));
        // coté droit de la fenetre
        JPanel eastSide = new JPanel();
        eastSide.setBackground(new Color(0x290025));
        eastSide.setPreferredSize(new Dimension(150,100));
        // le centre de la fenetre
        JPanel center = new JPanel();
        center.setBackground(new Color(0,0,0));
        center.add(ff);

        //Image de fond (on va la mettre sur un JLabel
        ImageIcon fondImage = new ImageIcon("src/assets/jeu_back_pic.jpg");
        JLabel fondLabel = new JLabel(fondImage);
        fondLabel.setPreferredSize(new Dimension(800,500));
        fondLabel.setLayout(new BorderLayout());


        timer = new Timer(tempo, e -> {
            feuFurieux.tour();
            ff.repaint();
            // c'est pour actualiser l'affichage de la barre de vie et nb de clés
            ff.actuVie();
            ff.actuCles();
            if (feuFurieux.partieFinie()) {
                if(feuFurieux.getJoueur().getCase() instanceof Sortie){
                    // si le joueur gagne
                    PlaySound winSound = new PlaySound("src/assets/audio/winning.wav");
                    winSound.jouer(false);
                }else {
                    // si il perd
                    //System.out.println("je suis dans le else de perdu");
                    PlaySound looseSound = new PlaySound("src/assets/audio/loosingSound.wav");
                    looseSound.jouer(false);
                }
                // Pour eviter le dcalage qu'il ya entre l'affichage et l'apparition du sond
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                feuFurieux.getJoueur().setScore(feuFurieux.getJoueur().getResistance());
                ff.ecranFinal(Math.max(0, feuFurieux.getJoueur().getScore()));
                ((Timer)e.getSource()).stop();
                System.out.println("Ref init: " + this.menu.getJoueur());
            }
        });

        //Les bordures pour le débuggage
//        westSide.setBorder(new LineBorder(Color.GREEN,2));
//        eastSide.setBorder(new LineBorder(Color.GREEN,2));
//        southSide.setBorder(new LineBorder(Color.GREEN,2));
//        center.setBorder(new LineBorder(Color.GREEN,2));
//        northSide.setBorder(new LineBorder(Color.GREEN,2));

        //régulation de l'affichage

        this.addContainerListener(this);
        fondLabel.add(westSide, BorderLayout.WEST);
        fondLabel.add(northSide,BorderLayout.NORTH);
        fondLabel.add(southSide,BorderLayout.SOUTH);
        fondLabel.add(eastSide,BorderLayout.EAST);
        fondLabel.add(center,BorderLayout.CENTER);
        this.add(fondLabel);

        this.frame.getContentPane().removeAll();
        this.frame.add(this);
        this.frame.revalidate();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // en cas de retour, on enleve la fenetre dans les écouteurs du frame, si on ne fait pas ça, ça va causer un problème quand on reviens une deuxieme fois sur le jeu, les méthodes de déplacement vont etre invoqué deux fois, 3fois, ...
        this.frame.getContentPane().removeAll();
        this.frame.removeKeyListener(this.ff);
        menu.update();//On met à jour les données des utilisateurs
        this.frame.getContentPane().add(menu);
        this.frame.revalidate();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void componentAdded(ContainerEvent e) {
        timer.start();
        //On déclenche le timer, quand la fenetre apparait
    }

    @Override
    public void componentRemoved(ContainerEvent e) {
        timer.stop();
        //On arret le timer quand on sort du jeu vers le menu
    }

}
