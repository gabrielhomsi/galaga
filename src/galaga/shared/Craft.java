package galaga.shared;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class Craft implements Serializable, GameObject {
    private int x;
    private int y;

    private int xSpeed;

    public Craft() {
        this.x = 40;
        this.y = 470;

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
            this.xSpeed = -100;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.xSpeed = 100;
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
        this.x += (int) (this.xSpeed * dt);
    }

    @Override
    public String getImagePath() {
        return "assets/playerShip1_Blue.png";
    }
}