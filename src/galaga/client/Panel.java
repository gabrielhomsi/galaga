package galaga.client;

import galaga.shared.gameobjects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


class Panel extends JPanel /*implements ActionListener*/ {
    private final Main main;
    private final int objectSize = 25;

    public Panel(Main main) {
        this.main = main;

        this.configure();
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        LinkedList<GameObject> gameObjects = this.main.getCurrentStage().getGameObjects();

        for (GameObject gameObject : gameObjects) {
            ImageIcon imageIcon = new ImageIcon(gameObject.getImagePath());
            Image image = imageIcon.getImage();

            Point position = gameObject.getPosition();

            graphics2D.drawImage(image, position.x, position.y, this.objectSize, this.objectSize, this);
        }
    }

    private void configure() {
        this.addKeyListener(new KeyListener(this.main));
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
    }
}