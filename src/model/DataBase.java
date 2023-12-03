package model;

import java.io.*;

public class DataBase {
    private final String filePath = "C:\\Users\\Oualid_CHABANE\\IdeaProjects\\projet_feu_furieux\\src\\files\\joueurs.bin";
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

    public void insertIntoFile(Joueur jr){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(jr);
        } catch (Exception e) {
            e.printStackTrace();
        }  finally{
            try{
                if (oos != null) oos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
