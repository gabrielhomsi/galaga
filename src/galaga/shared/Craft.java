package galaga.shared;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class Craft implements Serializable, GameObject {
    private int x;
    private int y;

    private int dx;
    private int dy;

    public Craft() {
        this.x = 40;
        this.y = 60;
    }

    public void move() {
        this.x += this.dx;
        this.y += this.dy;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void keyCodePressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.dx = -1;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.dx = 1;
        } else if (keyCode == KeyEvent.VK_UP) {
            this.dy = -1;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.dy = 1;
        }
    }

    public void keyCodeReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.dx = 0;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.dx = 0;
        } else if (keyCode == KeyEvent.VK_UP) {
            this.dy = 0;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.dy = 0;
        }
    }

    @Override
    public String getImagePath() {
        return "assets/playerShip1_Blue.png";
    }
}