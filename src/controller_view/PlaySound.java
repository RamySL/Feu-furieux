package controller_view;

import javax.sound.sampled.*;
import java.io.File;

public class PlaySound {
    private Clip clip;

    public PlaySound(String cheminAudio) {
        try {
            File audPath = new File(cheminAudio);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audPath);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jouer(boolean rejouer) {
        // le bool rejouer c'est pour relancer la musique après sa fin
        if (clip != null && !clip.isRunning()){
            clip.setFramePosition(0);
            clip.start();
        }
        if (rejouer) {
            //JOptionPane.showMessageDialog(null,"Presse OK");
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void arreter() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }


//    public static void main(String[] args) {
//        MusiqueDeFond m =new MusiqueDeFond("src/assets/audio/MyNounSound.wav");
//        m.jouer();
//        //JOptionPane.showMessageDialog(null,"Presse OK");
//
//    }
}

