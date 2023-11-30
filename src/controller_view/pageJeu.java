package controller_view;

import model.DataBase;
import model.Furfeux;
import model.Joueur;
import model.Terrain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class pageJeu extends JPanel implements MouseListener {
    FenetreJeu ff;

    JButton quit;
    Menu menu;
    JFrame frame;
    String tr;
    Furfeux feuFurieux;
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
        this.frame.getContentPane().removeAll();
        this.setPreferredSize(new Dimension(1000, 600));
        this.frame.add(this);
        this.setLayout(new BorderLayout());
        this.ff = new FenetreJeu(new Terrain(this.tr, menu.getJoueur()), this.frame);
        this.add(ff, BorderLayout.CENTER);
        this.ff.setAlignmentX(CENTER_ALIGNMENT);
        this.add(quit, BorderLayout.WEST);
        this.frame.validate();
        launch();
    }
    public FenetreJeu getFenetre(){
        return this.ff;
    }

    public void launch(){
        int tempo = 100;
        this.feuFurieux = new Furfeux(this.tr, menu.getJoueur());
        Timer timer = new Timer(tempo, e -> {
            feuFurieux.tour();
            ff.repaint();
            if (feuFurieux.partieFinie()) {
                ff.ecranFinal(Math.max(0, feuFurieux.getJoueur().getResistance()));
                ((Timer)e.getSource()).stop();
            }

        });
        timer.start();
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
}
