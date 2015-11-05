package galaga.client;

import java.util.HashMap;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

/**
 * Created by brunodg on 04/11/15.
 */
public class SoundHandler implements LineListener {
    private static final int BUFFER = 128000;
    private static SoundHandler instance = new SoundHandler();
    private HashMap soundFiles;

    public static SoundHandler get() {
        return instance;
    }


    public void loadSoundFX() {
        soundFiles = new HashMap();
        File dir = new File("assets/Sounds");
        File[] files = dir.listFiles();
        for (File i : files) {
            if (i.isFile()) {
                //Nao acha o caminho pq nao existe arquivo com esse caminho, falta a extensao
                String path = "assets/Sounds/sfx_bgm";//File Not Exists - FileNotFoundException
                File theFile = new File(path);
                int dot = path.indexOf(".wav");//Isso retorna o indice da primeira aparencia da string passada por parametro

                String ref = path.substring(0, dot);
                AudioInputStream audioInputStream = null;
                Clip clip = null;

                try {
                    audioInputStream = AudioSystem.getAudioInputStream(theFile);
                    if (audioInputStream != null) {
                        AudioFormat format = audioInputStream.getFormat();
                        DataLine.Info info = new DataLine.Info(Clip.class, format);
                        try {
                            clip = (Clip)AudioSystem.getLine(info);
                            //clip.addLineListener(this);
                            clip.open(audioInputStream);
                            soundFiles.put(ref, clip);
                        }
                        catch (LineUnavailableException e)
                        {
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void update (LineEvent event) {
        if (event.getType().equals(LineEvent.Type.STOP)) {
            Clip clip = (Clip)event.getLine();
            clip.setFramePosition(1);
        }
        else if (event.getType().equals(LineEvent.Type.CLOSE)) {
            System.exit(0);
        }
    }

    public void play (String ref) {
        Clip clip = (Clip) soundFiles.get(ref);
        if (clip.isRunning()) {
            clip.stop();
            clip.setFramePosition(1);
        }
        clip.addLineListener(this);
        clip.start();
    }

}
