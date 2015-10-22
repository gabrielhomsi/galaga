package galaga.shared.gameobjects;

import java.awt.event.KeyEvent;
import java.awt.Point;

public class Craft implements GameObject {
    private int connectionId;
    private int frameWidth;
    private Point position;

    private int xSpeed;

    private int objectSize = 30;//120 �timo valor

    public Craft(int connectionId, int frameWidth) {
        this.connectionId = connectionId;
        this.frameWidth = frameWidth;
        this.position = new Point(0, 530);
        this.xSpeed = 0;
    }

    @Override
    public Point getPosition() {
        return this.position;
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
        if (((this.position.x + (int) (this.xSpeed * dt)) > 0) && ((this.position.x + (int) (this.xSpeed * dt)) < (frameWidth - objectSize))) {
            this.position.x += (int) (this.xSpeed * dt);
        }
    }

//    //Enemy move, n�o necess�rio
//    @Override
//    public void updateX(double dt) {
//    }
//
//    @Override
//    public void updateY(double dt) {
//    }

    @Override
    public String getImagePath() {
        return "assets/playerShip1_Blue.png";
    }

    public int getConnectionId() {
        return this.connectionId;
    }
}