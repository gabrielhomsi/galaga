package galaga.shared.stages;

import galaga.server.Main;
import galaga.shared.WaveManager;
import galaga.shared.gameobjects.Craft;
import galaga.shared.gameobjects.GameObject;

import java.awt.*;
import java.util.LinkedList;

public class GameStage implements Stage {
    private LinkedList<GameObject> gameObjects;
    private WaveManager waveManager;

    public GameStage(int numberOfConnections) {
        this.gameObjects = new LinkedList<>();

        for (int connectionId = 0; connectionId < numberOfConnections; connectionId++) {
            Craft craft = new Craft(connectionId, this.getFrameWidth());

            this.gameObjects.add(craft);
        }

        this.waveManager = new WaveManager(this);
    }

    public int getFrameWidth() {
        return 800;
    }

    public int getFrameHeight() {
        return 600;
    }

    @Override
    public void notifyTime(double dt) {
        //            if ((t1 - startWave) > this.timeToNewWave) {
        //                startWave += this.timeToNewWave;
        //                System.out.println("New Wave after 60secs!");
        //                //createEnemy();
        //                this.main.newWave();
        //            }
    }

    @Override
    public boolean canGoToNextStage(Main main) {
        return false;
    }

    @Override
    public Stage getNextStage(Main main) {
        return null;
    }

    public Craft getCraftByConnectionId(int connectionId) {
        for (GameObject gameObject : this.gameObjects) {
            if (gameObject instanceof Craft) {
                Craft craft = (Craft) gameObject;

                if (connectionId == craft.getConnectionId()) {
                    return craft;
                }
            }
        }

        return null;
    }

    public LinkedList<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    public int getClosestPlayerConnectionId(Point position) {
        Craft closestPlayer = null;
        int distance = Integer.MAX_VALUE;//Distancia setada para o "infinito"

        for (GameObject gameObject : this.gameObjects) {
            if (gameObject instanceof Craft) {
                System.out.printf("Player");

                Point position1 = gameObject.getPosition();
                int aux = (int) position.distance(position1);

                if (aux < distance) {
                    closestPlayer = (Craft) gameObject;
                }
            } else {
                System.out.println("Enemy");
            }
        }

        if (closestPlayer != null) {
            return closestPlayer.getConnectionId();
        } else {
            return -1;
        }
    }
}
