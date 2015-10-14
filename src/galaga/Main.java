package galaga;

import javax.swing.*;
import java.awt.*;

class Teste extends JPanel {

    private void doDrawing(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawString("Galaga", 100, 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

}

public class Main extends JFrame {

    Main() {
        add(new Teste());
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