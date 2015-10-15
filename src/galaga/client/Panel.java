package galaga.client;

import galaga.shared.Scene;

import javax.swing.*;
import java.awt.*;

class Panel extends JPanel {
    private Scene scene;

    public Panel(Scene scene) {
        this.scene = scene;
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawString(this.scene.getTextMessage(), this.scene.getTextPositionX(), this.scene.getTextPositionY());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);

    }

}