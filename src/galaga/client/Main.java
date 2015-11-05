package galaga.client;

import galaga.shared.RemoteInterface;
import galaga.shared.stages.Stage;

import galaga.client.SoundHandler;

import javax.swing.*;
import java.rmi.RemoteException;

public class Main extends JFrame {
    private RemoteInterface remoteInterface;
    private boolean isGameRunning = false;
    private Stage currentStage;
    private int connectionId;

//    private SoundHandler soundHandler;

    public Main() {
        this.remoteInterface = new RemoteInterfaceManager().getRemoteInterface();

        try {
            this.connectionId = this.remoteInterface.getNewConnectionId();
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        this.retrieveFreshStage();
        this.configure();
        new GameLoop(this).start();
    }

    public static void main(String[] args) {
        new Main();
    }

    protected Stage getCurrentStage() {
        return this.currentStage;
    }

    private void configure() {
        this.add(new Panel(this));
        this.setTitle("Galaga - The Return");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(currentStage.getFrameWidth(), currentStage.getFrameHeight());
        this.setVisible(true);
//        SoundHandler.get().loadSoundFX();
//        SoundHandler.get().play("bgm");
        String soundPath = "assets/Sounds/sfx_bgm.wav";
        boolean loop = true;
        SoundHandler.playSound(soundPath, loop);
    }

    protected void retrieveFreshStage() {
        try {
            this.currentStage = this.remoteInterface.getCurrentStage();
        } catch (RemoteException e) {
//            e.printStackTrace();
//            System.exit(-1);
        }
    }


    public boolean getIsGameRunning() {
        return isGameRunning;
    }

    public void setIsGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
//        SoundHandler.get().loadSoundFX();
//        String soundPath = "assets/Sounds/sfx_bgm.wav";
//        SoundHandler.playSound(soundPath);
    }

    public RemoteInterface getRemoteInterface() {
        return remoteInterface;
    }

    public int getConnectionId() {
        return connectionId;
    }
}