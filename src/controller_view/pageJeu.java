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
    FenetreJeu ff;

    JButton quit;
    Menu menu;
    JFrame frame;
    String tr;
    Furfeux feuFurieux;
    Timer timer;

    final int tempo = 100;
    public pageJeu(Menu menu, JFrame frame, String tr){
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
        this.add(ff, BorderLayout.CENTER);
        this.addContainerListener(this);
        this.add(quit, BorderLayout.WEST);
        this.frame.getContentPane().removeAll();
        this.frame.add(this);
        this.frame.validate();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        this.frame.getContentPane().removeAll();
        menu.update();
        this.frame.getContentPane().add(menu);
        this.frame.validate();
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
        if(e.getSource() == this.ff){
            //System.out.println("bbbbbbbbbbbbbbbbbbbbbb");
            // timer.start();
        }
    }

    @Override
    public void componentRemoved(ContainerEvent e) {
        //timer.stop();
    }
}
