package controller_view;

import com.sun.source.tree.Tree;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.io.*;
import java.util.TreeSet;

public class Menu extends JSplitPane implements MouseListener{
    private String ramy = "Ramy SAIL", oualid = "Oualid CHABANE";
    JFrame frame;
    pageJeu pagejeu;
    DataPane dataPane;
    EnsembleJoueurs listPlayer;
    String terrain;
    public Menu(String name, JFrame frame, String terrain){
        this.terrain = terrain;
        this.frame = frame;
        dataPane = new DataPane(name, this);
        listPlayer = new EnsembleJoueurs();
        this.setDividerLocation(250);
        this.setResizeWeight(0.0);
        this.setLeftComponent(dataPane);
        this.setRightComponent(listPlayer);
        this.frame.getContentPane().removeAll();
        this.setPreferredSize(new Dimension(1000, 600));
        this.frame.add(this);
        this.frame.validate();
    }
    @Override
    public void mouseClicked(MouseEvent event) {
        this.pagejeu = new pageJeu(this, this.frame, this.terrain);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ((JButton) e.getSource()).setBackground(new Color(0x2980b9));
        ((JButton) e.getSource()).setPreferredSize(new Dimension(220, 60));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ((JButton) e.getSource()).setBackground(new Color(0xf72585));
        ((JButton) e.getSource()).setPreferredSize(new Dimension(200, 50));
    }

    public Joueur getJoueur(){
        return dataPane.getJoueur();
    }

    public void update(){
        listPlayer.update(dataPane.getJoueur());
    }
}

class DataPane extends JPanel {
    private Joueur jr;
    private DataBase db;
    private JButton buttonPlay;
    private JPanel userData;
    private JLabel title, name, score, level;
    public DataPane(String nameJr, MouseListener listner){
        this.db = new DataBase();
        this.jr = db.searchInFile(nameJr);
        if(this.jr == null){
            this.jr = new Joueur((CaseTraversable) Terrain.caseParDefaut, 300, 1, nameJr);
            db.insertIntoFile(this.jr);
        }
        buttonPlay = new JButton();
        buttonPlay.setPreferredSize(new Dimension(200, 50));
        buttonPlay.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPlay.setText("Jouer");
        buttonPlay.setForeground(new Color(0x463f3a));
        buttonPlay.setBackground(new Color(0xf72585));
        buttonPlay.setFocusPainted(false);
        buttonPlay.addMouseListener(listner);

        userData = new JPanel();
        this.title = new JLabel("Cordonnees");
        this.name = new JLabel("Pseudo: " + jr.getNom());
        this.score = new JLabel("Score: " + jr.getScore());
        this.level = new JLabel("Niveau: " + jr.getNiveau());
        this.userData.setLayout(new BoxLayout(userData, BoxLayout.Y_AXIS));
        this.userData.add(title);
        this.userData.add(name);
        this.userData.add(score);
        this.userData.add(level);
        this.setLayout(new BorderLayout());
        this.add(buttonPlay, BorderLayout.NORTH);
        this.add(userData, BorderLayout.CENTER);

    }

    public Joueur getJoueur(){
        return this.jr;
    }
}
 class EnsembleJoueurs extends JScrollPane {
     TreeSet<Joueur> listeJoueurs;
     JTable tableJoueurs;

     public EnsembleJoueurs() {
         DataBase db = new DataBase();
         listeJoueurs = db.getAllPlayers();
         init();
     }

     public void init() {
         String[] columnsName = new String[]{"Classement", "Nom", "Niveau", "Score"};
         Object[][] data = new Object[listeJoueurs.size()][4];
         int i = 0;
         for (Joueur jr : listeJoueurs) {
             data[i][0] = i + 1;
             data[i][1] = jr.getNom();
             data[i][2] = jr.getNiveau();
             data[i][3] = jr.getScore();
             System.out.println("Score angaru :" + jr.getScore() + "ref: " + jr);
             i++;
         }
         tableJoueurs = new JTable(data, columnsName);
         setViewportView(tableJoueurs);
     }

     public void update(Joueur j){
         for(Joueur jr: this.listeJoueurs){
             if(jr.getId() == j.getId()){
                 this.listeJoueurs.remove(jr);
                 this.listeJoueurs.add(j);
                 break;
             }
         }
         init();
     }
 }
