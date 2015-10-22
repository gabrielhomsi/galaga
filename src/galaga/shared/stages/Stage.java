package galaga.shared.stages;

import galaga.server.Main;
import galaga.shared.gameobjects.GameObject;

import java.io.Serializable;
import java.util.LinkedList;

public interface Stage extends Serializable {
    LinkedList<GameObject> getGameObjects();

    int getFrameWidth();

    int getFrameHeight();

    void notifyTime(double dt);

    boolean canGoToNextStage(Main main);

    Stage getNextStage(Main main);
}
