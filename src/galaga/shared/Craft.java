package galaga.shared;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class Craft implements Serializable, GameObject {
    private int x;
    private int y;

    private int xSpeed;
    private int ySpeed;

    public Craft() {
        this.x = 40;
        this.y = 60;

        this.xSpeed = 0;
        this.ySpeed = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void keyCodePressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.xSpeed = -100;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.xSpeed = 100;
        } else if (keyCode == KeyEvent.VK_UP) {
            this.ySpeed = -100;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.ySpeed = 100;
        }

        System.out.println("keyCodePressed, this.xSpeed = " + this.xSpeed + ", this.ySpeed = " + this.ySpeed);
    }

    public void keyCodeReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.xSpeed = 0;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.xSpeed = 0;
        } else if (keyCode == KeyEvent.VK_UP) {
            this.ySpeed = 0;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.ySpeed = 0;
        }

        System.out.println("keyCodeReleased");
    }

    @Override
    public void update(double dt) {
        this.x += (int) (this.xSpeed * dt);
        this.y += (int) (this.ySpeed * dt);

        System.out.println("Craft.update, this.x = " + this.x + ", this.y = " + this.y + ", this.xSpeed = " + this.xSpeed + ", this.ySpeed = " + this.ySpeed + ", dt = " + dt);
    }

    @Override
    public String getImagePath() {
        return "assets/playerShip1_Blue.png";
    }
}