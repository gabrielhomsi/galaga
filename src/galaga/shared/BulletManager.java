package galaga.shared;

import galaga.shared.gameobjects.Bullet;
import galaga.shared.gameobjects.Craft;
import galaga.shared.stages.GameStage;

import java.awt.*;

public class BulletManager {
    private final GameStage gameStage;

    public BulletManager(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    public void beginFire(int connectionId) {
        Craft craft = this.gameStage.getCraftByConnectionId(connectionId);
        Point craftPosition = craft.getPosition();
        Bullet bullet = new Bullet((Point) craftPosition.clone());

        this.gameStage.getGameObjects().add(bullet);
    }

    public void endFire(int connectionId) {

    }

    private void shoot(){
        Point playerActualPosition = new Point();


    }
}
