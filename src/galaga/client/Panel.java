package galaga.client;

import galaga.shared.gameobjects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


class Panel extends JPanel /*implements ActionListener*/ {
    private final int objectSize = 25;
    private Main main;
    private ImageCache imageCache;

    public Panel(Main main) {
        this.main = main;
        this.imageCache = new ImageCache();

        this.configure();
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        LinkedList<GameObject> gameObjects = this.main.getCurrentStage().getGameObjects();

        for (GameObject gameObject : gameObjects) {
            Image image = this.imageCache.getImage(gameObject.getImagePath());

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