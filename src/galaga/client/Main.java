package galaga.client;

import galaga.shared.RemoteInterface;
import galaga.shared.Scene;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main extends JFrame {
    private boolean isGameRunning = false;

    private RemoteInterface stub;
    private Scene scene;

    public Main() {
        try {
            this.initializeRemoteInterface();

            scene = this.stub.getScene();

            this.add(new Panel(scene));
            this.setTitle("Galaga");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setSize(scene.getFrameWidth(), scene.getFrameHeight());
            this.setVisible(true);

            this.startGameLoop();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    private void startGameLoop() {
        Main main = this;

        new Thread("RefreshScreen") {
            public void run() {
                main.setIsGameRunning(true);

                long start = System.currentTimeMillis();

                while (main.getIsGameRunning()) {
                    long now = System.currentTimeMillis();

                    if (now - start >= 100) {
                        main.repaint();
                        start = System.currentTimeMillis();
                    }
                }
            }
        }.start();
    }

    private void initializeRemoteInterface() {
        try {
            String host = "127.0.0.1";
            Registry registry = LocateRegistry.getRegistry(host);
            this.stub = (RemoteInterface) registry.lookup("RemoteInterface");
        } catch (Exception e) {
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