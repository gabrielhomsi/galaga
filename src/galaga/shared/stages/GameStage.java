package galaga.shared.stages;

import galaga.server.Main;
import galaga.shared.WaveManager;
import galaga.shared.gameobjects.Craft;
import galaga.shared.gameobjects.GameObject;

import java.awt.*;
import java.util.LinkedList;

//Ricardo------------------------------------
import galaga.shared.gameobjects.Enemy;
//Ricardo------------------------------------


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
        this.waveManager.notifyTime(dt);
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
        //Ricardo--------------------
        //checkColision();
        //Ricardo--------------------
        return this.gameObjects;
    }

    public Craft getClosestCraftByConnectionId(Point enemyPosition) {
        Craft closestCraft = null;

        for (GameObject gameObject : this.gameObjects) {
            if (gameObject instanceof Craft) {
                System.out.printf("Player");

                Craft currentCraft = (Craft) gameObject;

                if (closestCraft == null) {
                    closestCraft = currentCraft;
                } else {
                    int distanceClosestCraft = (int) enemyPosition.distance(closestCraft.getPosition());
                    int distanceCurrentCraft = (int) enemyPosition.distance(currentCraft.getPosition());

                    if (distanceCurrentCraft < distanceClosestCraft) {
                        closestCraft = currentCraft;
                    }
                }
            } else {
                System.out.println("Enemy");
            }
        }

        if (closestCraft != null) {
            return closestCraft;
        } else {
            return null;
        }
    }

    //Ricardo---------------------------------
    private void checkColision() {
        if (this.gameObjects == null) return;
        for (GameObject gObjectC : this.gameObjects) {
            if (gObjectC instanceof Craft) {
                int i = 0;
                for (int[] b : ((Craft) gObjectC).getBullets()) {
                    for (GameObject gObjectE : this.gameObjects) {
                        if (gObjectE instanceof Enemy) {
                            if (Math.abs(b[0] - gObjectE.getPosition().x) <= 15) {
                                if (Math.abs(b[1] - gObjectE.getPosition().y) <= 15) {
                                    ((Enemy) gObjectE).Die();
                                    ((Craft) gObjectC).deSpawnBullet(i);
                                    System.out.println("inimigo Down");
                                }
                            }
                        }
                    }
                    i++;
                }
            }
        }
    }
    //Ricardo-----------------------------------

}
