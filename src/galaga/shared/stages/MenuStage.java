package galaga.shared.stages;

import galaga.server.Main;
import galaga.shared.gameobjects.GameObject;
import galaga.shared.SoundHandler;

import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.*;

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

    //Bruno -> render do Menu --------------------------

    public void render(Graphics g) {
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("GALAGA - THE RETURN", 400, 100);
        SoundHandler.get().play("twoTone");
        SoundHandler.get().loadSoundFX();
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
