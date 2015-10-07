package galaga;

import javax.swing.*;

public class Main extends JFrame {
    Main() {
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
