package galaga.shared.stages;

import galaga.server.Main;
import galaga.shared.WaveManager;
import galaga.shared.gameobjects.Craft;
import galaga.shared.gameobjects.GameObject;

import java.io.Serializable;
import java.util.LinkedList;

public class GameStage implements Stage {
    private int lastNewCraftId;

    private LinkedList<GameObject> gameObjects;
    private WaveManager waveManager;

    public GameStage() {
        this.lastNewCraftId = 0;
        this.gameObjects = new LinkedList<>();
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
    public Stage getNextStage() {
        return null;
    }

    public Craft getCraftById(int craftId) {
        for (GameObject gameObject : this.gameObjects) {
            if (gameObject instanceof Craft) {
                Craft craft = (Craft) gameObject;

                if (craftId == craft.getId()) {
                    return craft;
                }
            }
        }

        return null;
    }

    public LinkedList<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    public Craft makeNewCraft() {
        Craft newCraft = new Craft(this.lastNewCraftId + 1);
        this.lastNewCraftId++;
        this.gameObjects.add(newCraft);
        return newCraft;
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
                    closestPlayer = gameObject.getId();
                }
            } else {
                System.out.println("Enemy");
            }
        }

        return closestPlayer;
    }
}
