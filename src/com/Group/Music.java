package com.Group;
import java.io.*;
import javax.sound.sampled.*;

public class Music{
    Clip clip;
    public Music(String audioFile) {
        play(audioFile);
       
    }
    
    public void play(String audioFile){
        try {
            File audio = new File(audioFile);
            AudioInputStream ais = AudioSystem.getAudioInputStream(audio);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public void stop() {
        try{
            if(clip != null){
                clip.stop();
            }
        }
        catch(Exception e){}
    }

}