package galaga.shared;

import java.io.Serializable;

public class Scene implements Serializable {
    private int frameWidth = 800;
    private int frameHeight = 600;

    public int getFrameWidth() {
        return this.frameWidth;
    }

    public int getFrameHeight() {
        return this.frameHeight;
    }
}
