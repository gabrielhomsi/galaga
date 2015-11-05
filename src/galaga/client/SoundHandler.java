package galaga.client;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundHandler {

    private static final int BUFFER_SIZE = 128000;
    private static File soundFile;
    private static AudioInputStream audioStream;
    private static AudioFormat audioFormat;
    private static SourceDataLine sourceLine;

    public static void playSound(String soundPath, boolean loop){
        try {
            soundFile = new File(soundPath);
            audioStream = AudioSystem.getAudioInputStream(soundFile);
            audioFormat = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!loop){
            sourceLine.start();

            int nBytesRead = 0;
            byte[] abData = new byte[BUFFER_SIZE];
            while (nBytesRead != -1) {//enquanto tiver dados preenchendo o buffer
                try {
                    nBytesRead = audioStream.read(abData, 0, abData.length);
                    sourceLine.write(abData, 0, nBytesRead);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            sourceLine.drain();
            sourceLine.close();
        } else{
            while(true){
                sourceLine.start();

                int nBytesRead = 0;
                byte[] abData = new byte[BUFFER_SIZE];
                while (nBytesRead != -1) {//enquanto tiver dados preenchendo o buffer
                    try {
                        nBytesRead = audioStream.read(abData, 0, abData.length);
                        sourceLine.write(abData, 0, nBytesRead);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                sourceLine.drain();
                sourceLine.close();
            }
        }

    }
}

//import java.util.HashMap;
//
//import java.io.File;
//import java.io.IOException;
//
//import javax.sound.sampled.*;
//
//public class SoundHandler implements LineListener {
//    private static final int BUFFER = 128000;
//    private static SoundHandler instance = new SoundHandler();
//    private HashMap soundFiles;
//
//    //teste - Marcos
//     private File soundFile;
//    private AudioInputStream audioStream;
//    private AudioFormat audioFormat;
//    private SourceDataLine sourceLine;
//    //fim teste
//
//    public static SoundHandler get() {
//        return instance;
//    }
//
//
//    public void playSound(String filename){
//
//        try {
//            soundFile = new File(filename);
//            audioStream = AudioSystem.getAudioInputStream(soundFile);
//            audioFormat = audioStream.getFormat();
//            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
//            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
//            sourceLine.open(audioFormat);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        sourceLine.start();
//
//        int nBytesRead = 0;
//        byte[] abData = new byte[BUFFER];
//        while (nBytesRead != -1) {
//            try {
//                nBytesRead = audioStream.read(abData, 0, abData.length);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (nBytesRead >= 0) {
//                @SuppressWarnings("unused")
//                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
//            }
//        }
//
//        sourceLine.drain();
//        sourceLine.close();
//    }
//
//
//
//    public void loadSoundFX() {
//        soundFiles = new HashMap();
//        File dir = new File("assets/Sounds");
//        File[] files = dir.listFiles();
//        for (File i : files) {
//            if (i.isFile()) {
//                //Nao acha o caminho pq nao existe arquivo com esse caminho, falta a extensao
//                String path = "assets/Sounds/sfx_bgm";//File Not Exists - FileNotFoundException
//                File theFile = new File(path);
//                int dot = path.indexOf(".wav");//Isso retorna o indice da primeira aparencia da string passada por parametro
//
//                String ref = path.substring(0, dot);
//                AudioInputStream audioInputStream = null;
//                Clip clip = null;
//
//                try {
//                    audioInputStream = AudioSystem.getAudioInputStream(theFile);
//                    if (audioInputStream != null) {
//                        AudioFormat format = audioInputStream.getFormat();
//                        DataLine.Info info = new DataLine.Info(Clip.class, format);
//                        try {
//                            clip = (Clip)AudioSystem.getLine(info);
//                            //clip.addLineListener(this);
//                            clip.open(audioInputStream);
//                            soundFiles.put(ref, clip);
//                        }
//                        catch (LineUnavailableException e)
//                        {
//                            e.printStackTrace();
//                        }
//                        catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void update (LineEvent event) {
//        if (event.getType().equals(LineEvent.Type.STOP)) {
//            Clip clip = (Clip)event.getLine();
//            clip.setFramePosition(1);
//        }
//        else if (event.getType().equals(LineEvent.Type.CLOSE)) {
//            System.exit(0);
//        }
//    }
//
//    public void play (String ref) {
//        Clip clip = (Clip) soundFiles.get(ref);
//        if (clip.isRunning()) {
//            clip.stop();
//            clip.setFramePosition(1);
//        }
//        clip.addLineListener(this);
//        clip.start();
//    }
//
//}
