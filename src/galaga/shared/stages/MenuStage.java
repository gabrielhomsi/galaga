package galaga.shared.stages;

import galaga.server.Main;
import galaga.shared.gameobjects.GameObject;

import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.*;

public class MenuStage implements Stage {
    @Override
    public LinkedList<GameObject> getGameObjects() {
        LinkedList<GameObject> gameObjects = new LinkedList<>();

        return gameObjects;
    }

    public Rectangle button1 = new Rectangle(350, 300, 100, 50);
    public Rectangle button2 = new Rectangle(350, 400, 100, 50);

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
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("GALAGA - THE RETURN", 100, 100);

        Font fnt1 = new Font ("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Jogar", button1.x + 10, button1.y + 30);
        g2d.draw(button1);
        g.drawString("Sair", button2.x + 15, button2.y + 30);
        g2d.draw(button2);

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
