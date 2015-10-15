package galaga.shared;

import java.io.Serializable;

public class Scene implements Serializable {
    private int frameWidth = 800;
    private int frameHeight = 600;

    private int textPositionX = 100;
    private int textPositionY = 200;
    private String textMessage = "Hello, Galaga (texto provieniente de um RMI)";

    public Scene() {

    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public int getTextPositionX() {
        return textPositionX;
    }

    public int getTextPositionY() {
        return textPositionY;
    }

    public String getTextMessage() {
        return textMessage;
    }
}
