package controller_view;

import model.CaseTraversable;
import model.DataBase;
import model.Joueur;
import model.Terrain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.io.*;
import java.util.TreeSet;

public class Menu extends JSplitPane {
    private String ramy = "Ramy SAIL", oualid = "Oualid CHABANE";
    public Menu(String name){
        JScrollPane listPlayer = new EnsembleJoueurs();
        JPanel dataPane = new DataPane(name);
        this.setDividerLocation(250);
        this.setResizeWeight(0.0);
        this.setLeftComponent(dataPane);
        this.setRightComponent(listPlayer);
 /*       JFrame frame = new JFrame("model.Furfeux");
        frame.setSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setVisible(true);

  */
    }

}

class DataPane extends JPanel implements MouseListener {
    private Joueur jr;

    private DataBase db;
    private JButton buttonPlay;
    private JPanel userData;
    private JLabel title, name, score, level;
    public DataPane(String nameJr){
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
        buttonPlay.addMouseListener(this);
        this.add(buttonPlay);
        userData = new JPanel();
        this.title = new JLabel("Cordonnees");
        this.name = new JLabel("Pseudo: " + jr.getNom());
        this.score = new JLabel("Score: " + jr.getScore());
        this.level = new JLabel("Niveau: " + jr.getNiveau());
        this.userData.add(title);
        this.userData.add(name);
        this.userData.add(score);
        this.userData.add(level);
        this.add(userData);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        buttonPlay.setBackground(new Color(0x2980b9));
        buttonPlay.setPreferredSize(new Dimension(220, 60));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        buttonPlay.setBackground(new Color(0xf72585));
        buttonPlay.setPreferredSize(new Dimension(200, 50));
    }
}
 class EnsembleJoueurs extends JScrollPane {
    TreeSet<Joueur> listeJoueurs;
    final String filePath = "C:\\Users\\Oualid_CHABANE\\IdeaProjects\\projet_feu_furieux\\src\\files\\joueurs.bin";
    JTable tableJoueurs;
    public EnsembleJoueurs(){
        listeJoueurs = new TreeSet<>();
        init();
    }

    private void init(){
        String [] columnsName = new String[]{"Classement", "Nom", "Niveau", "Score"};
        Object [][] data= new Object[listeJoueurs.size()][4];
        int i = 0;
        for(Joueur jr: listeJoueurs){
            data[i][0] = i + 1;
            data[i][1] = jr.getNom();
            data[i][2] = jr.getNiveau();
            data[i][3] = jr.getScore();
            i++;
        }
        tableJoueurs = new JTable(data, columnsName);
        setViewportView(tableJoueurs);
    }
}

class Main_Ac {
    public static void main(String args[]){
        //Joueur jr = new Joueur(null, 1, 1, "oualid");
        Menu menu = new Menu("Oualid");
    }
}
