package galaga.shared.gameobjects;

import java.awt.*;
import java.io.Serializable;

public class Bullet implements Serializable {
    private Point position;
    private int speed = 5;

    public Bullet(Point bullet) {
        this.position = position;
    }

    public String getBulletImagePath() {
        return "assets/lasers/laserBlue.png";
    }

}
