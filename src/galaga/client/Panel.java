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
            if (!gameObject.isDrawn(gameObject.getConnectionId())) {
                ImageIcon gameObjectImageIcon = new ImageIcon(gameObject.getImagePath());
                Image gameObjectImage = gameObjectImageIcon.getImage();

                graphics2D.drawImage(gameObjectImage, gameObject.getX(), gameObject.getY(), this.objectSize, this.objectSize, this);

                gameObject.draw(gameObject.getConnectionId());
            }

        }
    }

    private void configure() {
        this.addKeyListener(new KeyListener(this.main));
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
    }
}