package model;

import javax.sound.sampled.*;
import java.io.*;
import java.util.TreeSet;

public class DataBase {
    public static final int move_sound = 0;
    private final String paramsFile= "C:\\Users\\Oualid_CHABANE\\IdeaProjects\\projet_feu_furieux\\src\\files\\parametresJeu.bin", filePath = "C:\\Users\\Oualid_CHABANE\\IdeaProjects\\projet_feu_furieux\\src\\files\\joueurs.bin";
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

    }

    public void playSound(int type) {
        String soundName;
        switch (type) {
            case DataBase.move_sound:
                soundName = "path";
                break;
            default:
                soundName = "anotherPath";
                break;
        }
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clip.start();
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

