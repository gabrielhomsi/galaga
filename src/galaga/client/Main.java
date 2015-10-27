package galaga.client;

import galaga.shared.RemoteInterface;
import galaga.shared.stages.Stage;

import javax.swing.*;
import java.rmi.RemoteException;

public class Main extends JFrame {
    private RemoteInterface remoteInterface;
    private boolean isGameRunning = false;
    private Stage currentStage;
    private int connectionId;

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
        this.setTitle("Galaga");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(currentStage.getFrameWidth(), currentStage.getFrameHeight());
        this.setVisible(true);
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
    }

    public RemoteInterface getRemoteInterface() {
        return remoteInterface;
    }

    public int getConnectionId() {
        return connectionId;
    }
}