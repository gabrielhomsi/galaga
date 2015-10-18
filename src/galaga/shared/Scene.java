package galaga.shared;

import java.io.Serializable;
import java.util.LinkedList;

public class Scene implements Serializable {
    private int frameWidth = 800;
    private int frameHeight = 600;

    private Craft craft;
    private LinkedList<GameObject> gameObjects;

    public Scene() {
        this.craft = new Craft();
        this.gameObjects = new LinkedList<>();
        this.gameObjects.add(this.craft);
    }

    public int getFrameWidth() {
        return this.frameWidth;
    }

    public int getFrameHeight() {
        return this.frameHeight;
    }

    public Craft getCraft() {
        return craft;
    }

    public LinkedList<GameObject> getGameObjects() {
        return this.gameObjects;
    }
}
