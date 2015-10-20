package galaga.shared;

import java.io.Serializable;

public class Enemy implements Serializable, GameObject {
    private int x;
    private int y;

    private int id;

    private boolean isDrawn = false;

    private int frameWidth = 800;//Scene variable

    private int objectSize = 100;//120 ótimo valor

    private int xSpeed;

    public Enemy() {
        this.x = 40;
        this.y = 100;

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

    public int getId() {
        return this.id;
    }

    public boolean isDrawn(int craftId){
        return isDrawn;
    }

    public void draw(int craftId){
        this.isDrawn = true;
    }
}