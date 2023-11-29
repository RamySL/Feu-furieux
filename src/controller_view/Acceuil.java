package controller_view;

import model.Joueur;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Acceuil extends JPanel implements MouseListener, KeyListener, FocusListener {
    private JTextField pseudo;
    private JButton start;
    private Image image;
    private JPanel container;
    private BufferedImage myPicture;
    private JLabel picLabel;
    private String pseudoName;
    JFrame frame;

    public Acceuil() {
        pseudo = new JTextField();
        pseudo.setText("Pseudo");
        pseudo.setPreferredSize(new Dimension(270, 60));
        start = new JButton("Se connecter");
        start.setBackground(new Color(0x14213d));
        start.setPreferredSize(new Dimension(270, 60));
        container = new JPanel();
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
        frame = new JFrame("model.Furfeux");
        frame.setSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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
        //Il faut rechercher le joueur dans le fichier;
        if(e.getSource() == start){
            System.out.println("Nom joueur" + pseudoName);
            Menu menu = new Menu(pseudoName);
            this.frame.getContentPane().remove(this);
            this.frame.getContentPane().add(menu);
            this.frame.validate();
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
}

class Main {
    public static void main(String args[]){
        Acceuil ac = new Acceuil();
    }
}

