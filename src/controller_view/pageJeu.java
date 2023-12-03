package controller_view;

import model.DataBase;
import model.Furfeux;
import model.Joueur;
import model.Terrain;

import javax.swing.*;
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
        quit = new JButton("Quitter");
        quit.addMouseListener(this);
        quit.setPreferredSize(new Dimension(200, 50));
        quit.setFont(new Font("Arial", Font.BOLD, 16));
        quit.setForeground(new Color(0x463f3a));
        quit.setBackground(new Color(0xf72585));
        quit.setFocusPainted(false);
        this.setPreferredSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout());
        //création de la fenetre avec le meme terrain dans feufurieux
        this.feuFurieux = new Furfeux(this.tr, menu.getJoueur());
        this.ff = new FenetreJeu(this.feuFurieux.getTerrain(), this.frame);

        timer = new Timer(tempo, e -> {
            feuFurieux.tour();
            ff.repaint();
            if (feuFurieux.partieFinie()) {
                feuFurieux.getJoueur().setScore(feuFurieux.getJoueur().getResistance());
                ff.ecranFinal(Math.max(0, feuFurieux.getJoueur().getScore()));
                ((Timer)e.getSource()).stop();
                System.out.println("Ref init: " + this.menu.getJoueur());
            }
        });
        //régulation de l'affichage
        this.add(ff, BorderLayout.CENTER);
        this.addContainerListener(this);
        this.add(quit, BorderLayout.WEST);
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
