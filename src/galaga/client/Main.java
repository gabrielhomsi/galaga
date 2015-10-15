package galaga.client;

import galaga.shared.RemoteInterface;
import galaga.shared.Scene;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main extends JFrame {
    private RemoteInterface stub;

    Main() {
        try {
            this.initializeRemoteInterface();

            Scene scene = this.stub.getScene();

            this.add(new Panel(scene));
            this.setTitle("Galaga");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setSize(scene.getFrameWidth(), scene.getFrameHeight());
            this.setVisible(true);

            // public void run(){
            running = true;
            long initialTime = System.currentTimeMillis();
            long lastTime = initialTime;

            while (running){
                lastTime = System.currentTimeMillis();
                if((lastTime - initialTime)/1000 >= 1){
                    scene.updateTextMessage();
                    this.paintComponents(this.getGraphics());
                    //System.out.println(scene.getTextMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Main();
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

    private boolean running = false;


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