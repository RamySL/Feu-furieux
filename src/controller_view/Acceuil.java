package controller_view;

import model.DataBase;
import model.Joueur;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
        // vont definire la taille de back image
        int hauteurfen = 400;
        int largeurfen = 700;

        this.terrain = terrain;
        this.frame = frame;

        this.pseudo = new JTextField();
        this.pseudo.setText("Pseudo");
        // le -50 c'est par rapport à la hauteur pour qu'il soit bien centré
        // wifth : 200 normalment c'est pour couvrir tout la largeur de container
        this.pseudo.setBounds(0,hauteurfen/2 - 50,210,50);
        this.pseudo.setBorder(null);
        this.pseudo.addKeyListener(this); // il entend pour la touche Entré

        this.start = new JButton("Se connecter");
        //Border startBorder = new LineBorder(new Color(0x801421),2);
        this.start.setBorder(null);
        // j'ai pas simplifier pour montrer la logique
        this.start.setBounds(50,(hauteurfen/2 - 50) + 50,100,50);
        this.start.setFont(new Font("Arial", Font.BOLD, 10));
        this.start.setBackground(new Color(0xCC14213D, true));
        this.start.setForeground(Color.WHITE);

        this.container = new JPanel();
        this.container.setBackground(new Color(0x014213D, true));
        this.container.setLayout(null);
        //Box layout pour afficher verticalement
        //container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        //container.setOpaque(false);
        this.container.setPreferredSize(new Dimension(200, 200));
        this.container.add(this.pseudo);
        this.container.add(this.start);
        try {
            this.myPicture = ImageIO.read(new File("src/assets/acceuil.jpg"));
            this.image = myPicture.getScaledInstance(largeurfen, hauteurfen, Image.SCALE_DEFAULT);
            //.getScaledInstance(1000, 600, Image.SCALE_DEFAULT);
            this.picLabel = new JLabel(new ImageIcon(this.image));
            this.add(this.picLabel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.picLabel.setLayout(new BorderLayout());
        this.picLabel.add(this.container, BorderLayout.EAST);
        //container.setBackground(new Color(222, 232, 133));
        //container.setSize(new Dimension(400, 400));
        //this.add(superPanel);
        this.start.addMouseListener(this);
        this.pseudo.addFocusListener(this);
        this.frame.getContentPane().add(this);
        this.frame.validate();

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // c'est pour commencer le jeu si on valide son pseudo par la tuche entré
        //System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 10){
            this.menu = new Menu(this.pseudo.getText(), this.frame, this.terrain);
        }

    }



    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == this.start){
            //commencer le jeu si _clic bouton_
            //l'instanciation de menu change le contenu de frame passé en arguments qui est l'écran
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
        if (menu != null){
            // le if c'est pour gerer le cas où on veut fermer l'app sans avoir passer l'écran de pseudo
            DataBase db = new DataBase();
            db.writeGameParams(Joueur.id_courant);
            db.updatePlayer(menu.getJoueur());
        }

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

        //frame.setSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Acceuil ac = new Acceuil(frame, "src/model/manoir.txt");
        frame.addWindowListener(ac);
        frame.pack();
        frame.setVisible(true);
    }
}

