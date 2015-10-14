package galaga.client;

import javax.swing.*;

public class Main extends JFrame {
    Main() {
        this.add(new Teste());
        this.setTitle("Galaga");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800, 600);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
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