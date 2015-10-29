package galaga.shared.gameobjects;

import java.awt.*;
import java.io.Serializable;

public class Bullet implements GameObject {
    private Point position;
    private int speed = 5;

    public Bullet(Point position) {
        this.position = position;
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void setRushPoint(Craft player) {

    }

    @Override
    public String getImagePath() {
        return "assets/lasers/laserBlue.png";
    }

    public Point getPosition() {
        return position;
    }
}
