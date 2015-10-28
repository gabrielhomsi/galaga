package galaga.client;

import galaga.shared.gameobjects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

//Ricardo-------------------------------------------------
import galaga.shared.gameobjects.Craft;
import galaga.shared.gameobjects.Enemy;
import java.rmi.RemoteException;
//Ricardo-------------------------------------------------


class Panel extends JPanel /*implements ActionListener*/ {
    private final int objectSize = 25;
    private Main main;
    private ImageCache imageCache;

    public Panel(Main main) {
        this.main = main;
        this.imageCache = new ImageCache();

        this.configure();
    }

//    @Override
//    public void paint(Graphics graphics) {
//        Graphics2D graphics2D = (Graphics2D) graphics;
//
//        LinkedList<GameObject> gameObjects = this.main.getCurrentStage().getGameObjects();
//
//        for (GameObject gameObject : gameObjects) {
//            Image image = this.imageCache.getImage(gameObject.getImagePath());
//
//            Point position = gameObject.getPosition();
//
//            graphics2D.drawImage(image, position.x, position.y, this.objectSize, this.objectSize, this);
//        }
//    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        LinkedList<GameObject> gameObjects = this.main.getCurrentStage().getGameObjects();
        for (GameObject gameObject : gameObjects) {


            if (gameObject instanceof Enemy) {
            //    if (((Enemy) gameObject).isAlive()) {
                    Image image = this.imageCache.getImage(gameObject.getImagePath());

                    Point position = gameObject.getPosition();

                    graphics2D.drawImage(image, position.x, position.y, this.objectSize, this.objectSize, this);
            //    }

            }else if(gameObject instanceof Craft){
                Image image = this.imageCache.getImage(gameObject.getImagePath());
                Point position = gameObject.getPosition();

                graphics2D.drawImage(image, position.x, position.y, this.objectSize, this.objectSize, this);

                int playerId = ((Craft) gameObject).getConnectionId() + 1;

                graphics2D.drawString("Player " + playerId, (10 + (playerId) * 60), 20);
                graphics2D.drawString("Score:", (10 + (playerId) * 60), 40);
                graphics2D.drawString(((Craft) gameObject).getScore() + "", (10 + (playerId) * 60), 60);

                LinkedList<Point> shots = ((Craft) gameObject).getBullets();
                for(Point shot : shots){
                    Image i = this.imageCache.getImage(gameObject.getBulletImagePath());
                    Point p = new Point();
                    p.x = shot.x;
                    p.y = shot.y;

                    graphics2D.drawImage(i, p.x, p.y, this.objectSize/4, this.objectSize/2, this);
                }

            }else{
                Image image = this.imageCache.getImage(gameObject.getImagePath());

                Point position = gameObject.getPosition();

                graphics2D.drawImage(image, position.x, position.y, this.objectSize, this.objectSize, this);
            }
        }

    }

    private void configure() {
        this.addKeyListener(new KeyListener(this.main));
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
    }
}

