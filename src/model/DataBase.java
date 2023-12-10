package model;

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class DataBase {
    //class qui nous permet au view d'intéragir avec la base de données
    //Joueur.bin la base de données des joueurs
    //paraametresJeu, contient l'id du prochain joueur à ajouter à la base de données
    private final String paramsFile= "src/files/parametresJeu.bin", filePath = "src/files/joueurs.bin";
    public Joueur searchInFile(String name){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            boolean stop = false;
            while(!stop) {
                Joueur j = (Joueur) objectInputStream.readObject();
                if (j == null)
                    stop = true;
                else if(j.compareName(name))
                    return j;

            }
            return null;
        } catch (IOException | ClassNotFoundException e) {
            //Eof
            return null;
        }
    }

    public void insertIntoFile(Joueur jr) {
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
             ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(raf.getFD()))) {
            raf.seek(raf.length()); // Move the file pointer to the end
            out.writeObject(jr);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public TreeSet<Joueur> getAllPlayers(){
        TreeSet<Joueur> tr = new TreeSet<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while(true) {
                Joueur j = (Joueur) objectInputStream.readObject();
                tr.add(j);
            }
        } catch (IOException | ClassNotFoundException e) {
            //Eof
            return tr;
        }
    }
    public void updatePlayer(Joueur jr){
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        ArrayList<Object> dataBase = new ArrayList<>();
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.filePath)));
            while (true) {
                Joueur jrRed = (Joueur) in.readObject();
                if (jrRed.getId() ==  jr.getId())
                    dataBase.add(jr);
                else
                    dataBase.add(jrRed);
            }
        }
         catch(IOException | ClassNotFoundException e){
             //EoF
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        try{
             out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(this.filePath)));
             while(dataBase.size()>0){
                out.writeObject(dataBase.remove(0));
             }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



    public void writeGameParams(int params){
        DataOutputStream oos = null;
        try {
            oos = new DataOutputStream(new BufferedOutputStream (new FileOutputStream(paramsFile)));
            System.out.println("Id wrote is " + params);
            oos.writeInt(params);
        } catch (Exception e) {
            e.printStackTrace();
        }  finally{
            try{
                if (oos != null) oos.close ();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public int getNumberOfPlayers(){
        DataInputStream in=null;
        try {
            in = new DataInputStream(new BufferedInputStream (new FileInputStream(paramsFile)));
            try {
                int j = in.readInt();
                return j;
            } catch (EOFException eof) {
                // EOF reached, file is empty
                return 0;
            }
        } catch (IOException e) {
            //Eof
            return 0;
        }finally{
            try{
                if (in != null) in.close ();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

