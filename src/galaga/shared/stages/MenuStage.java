package galaga.shared.stages;

import galaga.server.Main;
import galaga.shared.gameobjects.GameObject;

import java.util.LinkedList;

public class MenuStage implements Stage {
    @Override
    public LinkedList<GameObject> getGameObjects() {
        LinkedList<GameObject> gameObjects = new LinkedList<>();

        return gameObjects;
    }

    @Override
    public int getFrameWidth() {
        return 800;
    }

    @Override
    public int getFrameHeight() {
        return 600;
    }

    @Override
    public void notifyTime(double dt) {

    }

    @Override
    public boolean canGoToNextStage(Main main) {
        return main.getNumberOfConnections() >= 2;
    }

    @Override
    public Stage getNextStage(Main main) {
        return new GameStage(main.getNumberOfConnections());
    }
}
