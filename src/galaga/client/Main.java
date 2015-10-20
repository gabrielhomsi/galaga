package galaga.client;

import galaga.shared.GameObject;
import galaga.shared.RemoteInterface;
import galaga.shared.Scene;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

public class Main extends JFrame {
    private RemoteInterface remoteInterface;
    private boolean isGameRunning = false;
    private Scene scene;

    public Main() {
        try {
            this.initializeRemoteInterface();
            this.retrieveFreshScene();
            this.buildPanel();
            this.startGameLoop();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    protected RemoteInterface getRemoteInterface() {
        return this.remoteInterface;
    }

    protected Scene getScene() {
        return this.scene;
    }

    private void buildPanel() {
        this.add(new Panel(this));
        this.setTitle("Galaga");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(scene.getFrameWidth(), scene.getFrameHeight());
        this.setVisible(true);
    }

    private void startGameLoop() {
        Main main = this;

        new Thread("RefreshScreen") {
            public void run() {
                main.setIsGameRunning(true);

                long start = System.currentTimeMillis();

                //teste
                int count = 0;
                //fim Teste
                while (main.getIsGameRunning()) {
                    long now = System.currentTimeMillis();

                    if (now - start >= 33) {
                        main.retrieveFreshScene();
                        main.repaint();
                        start = System.currentTimeMillis();

                        //teste
                        count++;
                    }
                    //teste
                    if (count > 100) {
                        count = 0;
                        printGameObjects();
                    }

                }
            }
        }.start();
    }

    protected void retrieveFreshScene() {
        try {
            this.scene = this.remoteInterface.getScene();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void initializeRemoteInterface() {
        try {
            String host = "127.0.0.1";
            Registry registry = LocateRegistry.getRegistry(host);
            this.remoteInterface = (RemoteInterface) registry.lookup("RemoteInterface");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printGameObjects() {
        LinkedList<GameObject> gameObjects = this.scene.getGameObjects();
        System.out.println("N objects: " + gameObjects.size());
        for (int i = 0; i < gameObjects.size(); i++) {
            System.out.println(gameObjects.get(i));
        }

        Panel p = new Panel(this);
        //p.paintAllGameObjects();

    }


    public boolean getIsGameRunning() {
        return isGameRunning;
    }

    public void setIsGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }
}