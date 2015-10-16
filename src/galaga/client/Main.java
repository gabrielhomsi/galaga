package galaga.client;

import galaga.shared.RemoteInterface;
import galaga.shared.Scene;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main extends JFrame {
    public boolean running = false;
    Scene scene;
    private RemoteInterface stub;
    Main() {
        try {
            this.initializeRemoteInterface();
            scene = this.stub.getScene();
            this.add(new Panel(scene));
            this.setTitle("Galaga");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setSize(scene.getFrameWidth(), scene.getFrameHeight());
            this.setVisible(true);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Main main = new Main();
        //Thread para rodar o temporizador
        new Thread("RefreshScreen") {
            public void run() {
                main.running = true;
                long initialTime = System.currentTimeMillis();
                long lastTime;
                while (main.running) {
                    lastTime = System.currentTimeMillis();
                    if ((lastTime - initialTime) / 1000 >= 1) {
                        initialTime = lastTime;
                        main.scene.updateTextMessage();
                        main.repaint();
                        //System.out.println(main.scene.getTextMessage());
                        try {
                            Thread.sleep(800);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    public void initializeRemoteInterface() {
        try {
            String host = "127.0.0.1";
            Registry registry = LocateRegistry.getRegistry(host);
            this.stub = (RemoteInterface) registry.lookup("RemoteInterface");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

/*

public void run() {
    running = true;
    initialize();
    long curTime = System.nanoTime();
    long lastTime = curTime;
    double nsPerFrame;
    while( running ) {
        curTime = System.nanoTime();
        nsPerFrame = curTime - lastTime;
        gameLoop( nsPerFrame / 1.0E9 );
        lastTime = curTime;
    }
}

 */