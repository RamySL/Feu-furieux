package controller_view;

import model.DataBase;
import model.Joueur;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Acceuil extends JPanel implements MouseListener, KeyListener, FocusListener, WindowListener {
    private JTextField pseudo;//Nom de l'utilisateur, s'il n'éxiste pas, il va etre crrée et ajouté à la base de données
    private JButton start;//Acceder au menu
    private Image image;//background image
    private JPanel container;
    private BufferedImage myPicture;
    private JLabel picLabel;
    private String pseudoName;
    private JFrame frame;//le frame qui contiendera le jeu
    private String terrain;//le chemin vers fichier terrain.txt
    private Menu menu;

    public Acceuil(JFrame frame, String terrain) {
        //l'écrane d'acceuil oprend en arguments le frame et le nom du terrain pour créer e tapis du jeu
        //initialisations...
        this.terrain = terrain;
        this.frame = frame;
        pseudo = new JTextField();
        pseudo.setText("Pseudo");
        pseudo.setPreferredSize(new Dimension(270, 60));
        start = new JButton("Se connecter");
        start.setBackground(new Color(0x14213d));
        start.setPreferredSize(new Dimension(270, 60));
        container = new JPanel();
        //Box layout pour afficher verticalement
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);
        container.setPreferredSize(new Dimension(300, 600));
        container.add(pseudo);
        container.add(start);
        try {
            myPicture = ImageIO.read(new File("C:\\Users\\Oualid_CHABANE\\IdeaProjects\\projet_feu_furieux\\src\\assets\\acceuil.jpg"));
            image = myPicture.getScaledInstance(1000, 600, Image.SCALE_DEFAULT);
            //.getScaledInstance(1000, 600, Image.SCALE_DEFAULT);
            picLabel = new JLabel(new ImageIcon(image));
            this.add(picLabel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        picLabel.setLayout(new BorderLayout());
        picLabel.add(container, BorderLayout.EAST);
        //container.setBackground(new Color(222, 232, 133));
        //container.setSize(new Dimension(400, 400));
        //this.add(superPanel);
        start.addMouseListener(this);
        pseudo.addFocusListener(this);
        this.frame.getContentPane().add(this);
        this.frame.validate();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == start){
            //commencer le jeu si _clic bouton_
            //l'instanciation de menu change le contenu de frame passé en arguments qui est l'écrane
           this.menu = new Menu(pseudoName, this.frame, this.terrain);
        }
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
    public void focusGained(FocusEvent e) {
        //pseudo.setBackground(new Color(255, 255, 255, 100));


    }

    @Override
    public void focusLost(FocusEvent e) {
        //pseudo.setBackground(new Color(255, 255, 255, 150));
        this.pseudoName = pseudo.getText();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //Quand on commence le jeu(frame est ouvert), on récupére l'id qu'on va utiliser en cas de création d'un nouveau joueur
        DataBase db = new DataBase();//Base de données qui se charge de ça
        int param = db.getNumberOfPlayers();
        Joueur.id_courant = param;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //En fermant le jeu, on sauvgarde les données du joueur qui joue, et l'id actuelle, en cas de modification
        //l'id est modifié si et seulement si on a ajouté un joueur à la base de données, c'est pointeir général vers le nombre des joueurs dans le jeu
        DataBase db = new DataBase();
        db.writeGameParams(Joueur.id_courant);
        db.updatePlayer(menu.getJoueur());
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

class Main{


    public static void main(String args[]){
        //Ici ou on commence le jeu
        JFrame frame;
        frame = new JFrame("model.Furfeux");

        frame.setSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Acceuil ac = new Acceuil(frame, "src/model/manoir.txt");
        frame.addWindowListener(ac);
        frame.pack();
        frame.setVisible(true);
    }
}

