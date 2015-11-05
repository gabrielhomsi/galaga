package galaga.client;

import galaga.shared.gameobjects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        //background
        Image background = this.imageCache.getImage("assets/Backgrounds/purple.png");
        graphics2D.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

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

//                graphics2D.drawString("Player " + playerId, (10 + (playerId) * 60), 20);
//                graphics2D.drawString("Score:", (10 + (playerId) * 60), 40);
//                graphics2D.drawString(((Craft) gameObject).getScore() + "", (10 + (playerId) * 60), 60);

                Point scoreNumberInitialPosition = new Point();
                scoreNumberInitialPosition.x = 10;
                scoreNumberInitialPosition.y = 60;
                int numberPosition = (playerId - 1) * 10;

                ArrayList<String> scoreImageList = getScoreImages(((Craft) gameObject).getScore());
                for (String imagePath : scoreImageList) {
                    Image i = this.imageCache.getImage(imagePath);
                    graphics2D.drawImage(i, scoreNumberInitialPosition.x + 15 * numberPosition, scoreNumberInitialPosition.y, 10, 10, this);
                    numberPosition++;
                }

                numberPosition = (playerId - 1) * 10;
                for (int i = 0; i < ((Craft) gameObject).getLivesNumber(); i++) {
                    Image lifeImage = this.imageCache.getImage(gameObject.getImagePath());
                    graphics2D.drawImage(lifeImage, scoreNumberInitialPosition.x + 20 * numberPosition, scoreNumberInitialPosition.y - 50, 15, 15, this);
                    numberPosition++;
                }



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

    private ArrayList<String> getScoreImages(int score) {
        int numberDigits = 9;
        int i = 0;
        ArrayList<String> imageList = new ArrayList<String>();
        while (i < numberDigits) {
            int number = score % 10;
            imageList.add(0, getNumberImage(number));
            score /= 10;
            i++;
        }
        return imageList;
    }

    private String getNumberImage(int number) {
        switch (number) {
            case 0:
                return "assets/UI/numeral0.png";
            case 1:
                return "assets/UI/numeral1.png";
            case 2:
                return "assets/UI/numeral2.png";
            case 3:
                return "assets/UI/numeral3.png";
            case 4:
                return "assets/UI/numeral4.png";
            case 5:
                return "assets/UI/numeral5.png";
            case 6:
                return "assets/UI/numeral6.png";
            case 7:
                return "assets/UI/numeral7.png";
            case 8:
                return "assets/UI/numeral8.png";
//            case 9:
            default:
                return "assets/UI/numeral9.png";
        }
    }


    private void configure() {
        this.addKeyListener(new KeyListener(this.main));
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
    }
}

