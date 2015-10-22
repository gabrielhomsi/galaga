package galaga.shared.gameobjects;

import java.awt.event.KeyEvent;

public class Craft implements GameObject {
    private int connectionId;

    private boolean isDrawn = false;

    private int x;
    private int y;

    private int frameWidth = 800;//Scene variable

    private int objectSize = 30;//120 �timo valor

    private int xSpeed;

    public Craft(int connectionId) {
        this.connectionId = connectionId;

        this.x = 0;
        this.y = 530;

        this.xSpeed = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void keyCodePressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.xSpeed = -200;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.xSpeed = 200;
        }
    }

    public void keyCodeReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.xSpeed = 0;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.xSpeed = 0;
        }
    }

    @Override
    public void update(double dt) {
        if (((this.x + (int) (this.xSpeed * dt)) > 0) && ((this.x + (int) (this.xSpeed * dt)) < (frameWidth - objectSize))) {
            this.x += (int) (this.xSpeed * dt);
        }
    }

    //Enemy move, n�o necess�rio
    @Override
    public void updateX(double dt) {
    }

    @Override
    public void updateY(double dt) {
    }

//    @Override
//    public int getClosestPlayerId(int positionX, int positionY){
//        return 0;
//    }

    @Override
    public String getImagePath() {
        return "assets/playerShip1_Blue.png";
    }

    public int getConnectionId() {
        return this.connectionId;
    }

    public boolean isDrawn(int craftId) {
        return isDrawn;
    }

    public void draw(int craftId) {
        this.isDrawn = true;
    }
}