package galaga.shared.stages;

import galaga.server.Main;
import galaga.shared.WaveManager;
import galaga.shared.gameobjects.Craft;
import galaga.shared.gameobjects.GameObject;

import java.util.LinkedList;

public class GameStage implements Stage {
    private LinkedList<GameObject> gameObjects;
    private WaveManager waveManager;

    public GameStage(int numberOfConnections) {
        this.gameObjects = new LinkedList<>();

        for (int connectionId = 0; connectionId < numberOfConnections; connectionId++) {
            Craft craft = new Craft(connectionId);

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

    public int getDistanceBetween2Points(int pointAX, int pointAY, int pointBX, int pointBY) {
        int distance;
        distance = (int) Math.hypot((pointAX - pointBX), (pointAY - pointBY));
        return distance;
    }

    public int getClosestPlayerId(int positionX, int positionY) {
        int closestPlayer = 0;//jogador mais pr?ximo
        int distance = Integer.MAX_VALUE;//Distancia setada para o "infinito"
        int aux;

        for (GameObject gameObject : this.gameObjects) {
            if (gameObject.getClass().isInstance(Craft.class)) {
                System.out.printf("Player");
                aux = getDistanceBetween2Points(positionX, positionY, gameObject.getX(), gameObject.getY());
                if (aux < distance) {
                    closestPlayer = gameObject.getConnectionId();
                }
            } else {
                System.out.println("Enemy");
            }
        }

        return closestPlayer;
    }
}
