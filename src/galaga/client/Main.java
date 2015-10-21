package galaga.client;

import galaga.shared.RemoteInterface;
import galaga.shared.Scene;

import javax.swing.*;
import java.rmi.RemoteException;

public class Main extends JFrame {
    private RemoteInterface remoteInterface;
    private boolean isGameRunning = false;
    private Scene scene;

    public Main() {
        this.remoteInterface = new RemoteInterfaceManager().getRemoteInterface();
        this.retrieveFreshScene();
        this.configure();
        new GameLoop(this).start();
    }

    public static void main(String[] args) {
        new Main();
    }

    protected Scene getScene() {
        return this.scene;
    }

    private void configure() {
        this.add(new Panel(this));
        this.setTitle("Galaga");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(scene.getFrameWidth(), scene.getFrameHeight());
        this.setVisible(true);
    }

    protected void retrieveFreshScene() {
        try {
            this.scene = this.remoteInterface.getScene();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getIsGameRunning() {
        return isGameRunning;
    }

    public void setIsGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }
}