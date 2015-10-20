package galaga.shared;

import java.io.Serializable;

public class Enemy implements Serializable, GameObject {
    private int x;
    private int y;

    private int frameWidth = 800;//Scene variable

    private int objectSize = 100;//120 ótimo valor

    private int xSpeed;

    public Enemy() {
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

    @Override
    public void update(double dt) {
        if (((this.x + (int) (this.xSpeed * dt)) > 0) && ((this.x + (int) (this.xSpeed * dt)) < (frameWidth - objectSize))) {
            this.x += (int) (this.xSpeed * dt);
        }

    }

    @Override
    public String getImagePath() {
        return "assets/enemies/enemyBlack1.png";
    }
}