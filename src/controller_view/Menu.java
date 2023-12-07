package controller_view;

import com.sun.source.tree.Tree;
import model.*;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.io.*;
import java.util.TreeSet;

public class Menu extends JSplitPane implements MouseListener, KeyListener{
    //La class menu pour afficher tout les joueurs classées selon le niveau et le score
    //Le niveau s'incrémente en incrémentant le score (le niveau n+1 demande plus de score que le niveau n, donc le niveau s'incrémente d'ine maniere logarithmique
    JFrame frame;//le meme frame dans l'acceuil est dans menu, qui sera aussi dans les autres class d'affichage
    pageJeu pagejeu;//la class qui se charge d'afficher le jeu avec les animations
    DataPane dataPane;//le panel qui contient les informations du joueur actuel, il se trouve sur la droite.
    EnsembleJoueurs listPlayer;//le tableau des joueurs dans base de données
    String terrain;// le chemin vers le fichier qui contient le terrain

    public static int leftSize = 200; // la taille du coté gauche (qui va contenir les coord du joueur)
    public Menu(String name, JFrame frame, String terrain){
        //initalisations...
        this.terrain = terrain;
        this.frame = frame;
        dataPane = new DataPane(name, this);
        listPlayer = new EnsembleJoueurs();
//        JPanel rightPanel  = new JPanel();
//        rightPanel.setBackground(new Color(0,0,0));
//        rightPanel.add(listPlayer);
        this.setDividerLocation(Menu.leftSize);
        this.setResizeWeight(0.0);
        //this.setBackground(new Color(0x290025));
        this.setLeftComponent(dataPane);

        this.setRightComponent(listPlayer);
        this.setBackground(new Color(0x290025));
        this.frame.getContentPane().removeAll();//on supprime tous qui est dans le frame poue afficher le menu, on réutilise le meme frame dans tout le jeu
        this.setPreferredSize(new Dimension(1000, 600));
        this.frame.add(this);
        this.frame.validate();//pour actualiser(equivalent de repaint)
    }
    @Override
    public void mouseClicked(MouseEvent event) {
        //en clicquant sur le bouton pour commencer, on crée un instance de la page d'affichage du jeu, c'est elle qui se charge de la mise à jour du contenu du frame
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
        //Pour actualiser les données des joueurs dans le menu, en cas d'affichage plusieurs fois dans la meme partie
        listPlayer.update(dataPane.getJoueur());// le tableau
        dataPane.update();// les données du joueurs dans le dataPane
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}

class DataPane extends JPanel  {
    //Conteneur des données du joueur actuel.
    private Joueur jr;//Le joueur actuel
    private DataBase db;//l'intérmidiaire entre le view et le model
    //parametres d'affichage
    private JButton buttonPlay;
    private JPanel userData;
    private JLabel title, name, score, level;
    public DataPane(String nameJr, MouseListener listner){
        this.db = new DataBase();
        //1.On cherche le joueurs saisi dans l'acceuil
        this.jr = db.searchInFile(nameJr);
        if(this.jr == null){
            //2.Si il n'existe pas dans la base de données, on crée un avec son pseudo, et sa case initial dans le jeu
            this.jr = new Joueur((CaseTraversable) Terrain.caseParDefaut, 300, 1, nameJr);
            //3.Apres la créatin du joueur, on l'ajoute à la base de données
            db.insertIntoFile(this.jr);
        }
        this.setBackground(new Color(0x290025));
        buttonPlay = new JButton();
        //buttonPlay.setPreferredSize(new Dimension(100, 50));
        buttonPlay.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPlay.setText("Jouer");
        buttonPlay.setForeground(new Color(255,255,255));
        buttonPlay.setBackground(new Color(0x3a015c));
        buttonPlay.setFocusPainted(false);
        buttonPlay.addMouseListener(listner);
        buttonPlay.setBounds((int)(Menu.leftSize * 0.1),20,(int)(Menu.leftSize * 0.8),50);

        JPanel coordPanel = new JPanel();
        coordPanel.setLayout(new BorderLayout());
        //coordPanel.setBorder(new LineBorder(Color.GREEN,2));

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0x3a015c));

        userData = new JPanel();
        this.title = new JLabel("Cordonnees");
        this.title.setForeground(Color.WHITE    );
        //this.title.setBackground(Color.WHITE);
        this.title.setFont(new Font( "MV Boli", Font.BOLD,20));
        //this.title.setBorder(new LineBorder(Color.GREEN,2));
        //this.title.setPreferredSize(new Dimension( 100,50));
        titlePanel.add(this.title);
        coordPanel.add(titlePanel, BorderLayout.NORTH);

        this.name = new JLabel("Pseudo: " + jr.getNom());
        this.name.setForeground(Color.WHITE);
        this.score = new JLabel("Score: " + jr.getScore());
        this.score.setForeground(Color.WHITE);
        this.level = new JLabel("Niveau: " + jr.getNiveau());
        this.level.setForeground(Color.WHITE);
        this.userData.setLayout(new BoxLayout(userData, BoxLayout.Y_AXIS));
        //this.userData.add(title);
        this.userData.add(name);
        this.userData.add(score);
        this.userData.add(level);
        //this.userData.setBorder(new LineBorder(Color.GREEN,2));
        this.userData.setBackground(new Color(0x220135));
        //this.userData.setForeground(Color.WHITE);
        this.setLayout(null);
        this.add(buttonPlay);
        coordPanel.add(this.userData, BorderLayout.CENTER);
        coordPanel.setBackground(new Color(0x290025, true));
        coordPanel.setBounds((int)(Menu.leftSize * 0.1),130,(int)(Menu.leftSize * 0.8),150);
        this.add(coordPanel);

        //this.setBorder(new LineBorder(Color.RED, 2));
    }
    public void update(){
        //la mise à jour des informations du joueur affichés sur l'écrane
        this.name.setText("Pseudo: " + jr.getNom());
        this.score.setText("Score: " + jr.getScore());
        this.level.setText("Niveau: " + jr.getNiveau());
    }
    public Joueur getJoueur(){
        return this.jr;
    }

}
 class EnsembleJoueurs extends JScrollPane {
    //Le tableau de tout les joueurs enregistré dans la base de données
     TreeSet<Joueur> listeJoueurs;//Ordonnées par le score et le niveau
     JTable tableJoueurs;

     public EnsembleJoueurs() {
         DataBase db = new DataBase();
         listeJoueurs = db.getAllPlayers();
         //this.setBorder(new LineBorder(Color.GREEN,2));
         init();//pour la création du tableau graphique avec son contenu
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
             i++;
         }
         tableJoueurs = new JTable(data, columnsName);
         setViewportView(tableJoueurs);
         this.tableJoueurs.setBackground(new Color(0x190028));
         this.tableJoueurs.setForeground(Color.WHITE);
         viewport.setBackground(new Color(0x190028));
     }

     public void update(Joueur j){
         //mise à jour des informations des joueurs dans la table
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
