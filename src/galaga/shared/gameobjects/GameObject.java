package galaga.shared.gameobjects;

import java.io.Serializable;

public interface GameObject extends Serializable {
    //Craft move
    void update(double dt);

    //EnemyMove
    void updateX(double dt);

    void updateY(double dt);
//    int getClosestPlayerId(int positionX, int positionY);


    String getImagePath();//novo, selecionar n image paths

    int getX();

    int getY();

    boolean isDrawn(int craftId);

    void draw(int craftId);

    int getConnectionId();
}
