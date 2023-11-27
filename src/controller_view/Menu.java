package controller_view;

import model.Joueur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
public class Menu extends JSplitPane {
    private String ramy = "Ramy SAIL", oualid = "Oualid CHABANE";
    public Menu(){
        JScrollPane listPlayer = new ListeJoueurs();
        JPanel dataPane = new DataPane();
        this.setDividerLocation(250);
        this.setResizeWeight(0.0);
        this.setLeftComponent(dataPane);
        this.setRightComponent(listPlayer);
        JFrame frame = new JFrame("model.Furfeux");
        frame.setSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);

        frame.setVisible(true);
    }

}

class DataPane extends JPanel{

    public DataPane(){

    }
}
 class ListeJoueurs extends JScrollPane {
    ArrayList<Joueur> listeJoueurs;
    final String filePath = " C:\\Users\\Oualid_CHABANE\\IdeaProjects\\projet_feu_furieux\\src\\files\\joueurs.bin"; // Replace with the actual path;
    JTable tableJoueurs;
    public ListeJoueurs(){
/*        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            boolean stop = false;
            while(!stop) {
                Joueur j = (Joueur) objectInputStream.readObject();
                if (j == null)
                    stop = true;
                else
                    listeJoueurs.add(j);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
  */
        init();
    }

    private void init(){
        String [] columnsName = {"Nom", "Niveau", "Score"};
        Object [][] data= {{1, 1, 1}, {1, 1, 1}};
        JTable tbJoueurs = new JTable(data, columnsName);
        this.add(tbJoueurs);
    }
}

class Main {
    public static void main(String args[]){
        Menu menu = new Menu();
    }
}
