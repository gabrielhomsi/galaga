package galaga.shared.gameobjects;

import java.awt.*;
import java.io.Serializable;

public interface GameObject extends Serializable {
    //Craft move
    void update(double dt);

    void setRushPoint(Craft player);

    //EnemyMove
    //void updateX(double dt);

    // void updateY(double dt);
//    int getClosestPlayerConnectionId(int positionX, int positionY);

    public String getBulletImagePath();

    String getImagePath();//novo, selecionar n image paths

    Point getPosition();
}
