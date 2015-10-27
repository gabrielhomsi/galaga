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
//        contador++;
//        if(contador > 20){
//            checkColisionTime();
//            contador=0;
//        }

        this.waveManager.notifyTime(dt);
    }

//    private int contador = 0;
//
//    private void checkColisionTime(){
//        checkColision();
//    }

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
//        checkColision();
        //Ricardo--------------------
        return this.gameObjects;
    }

    private Craft getClosestCraftByConnectionId(Point enemyPosition) {
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
    public void checkColision() {
        if (this.gameObjects == null) return;
        for (GameObject gObjectC : this.gameObjects) {
            if (gObjectC instanceof Craft) {
                int i = 0;
                for (Point bullet : ((Craft) gObjectC).getBullets()) {
                    int j = 0;
                    //System.out.println("bullet[" + bullet.x + ", " + bullet.y + "]");
                    for (GameObject gObjectE : this.gameObjects) {
                        if (gObjectE instanceof Enemy) {
                            //System.out.println("Enemy[" + gObjectE.getPosition().x + ", " + gObjectE.getPosition().y + "]");
                            if (Math.abs(bullet.x - gObjectE.getPosition().x) <= /*15*/15) {
                                if (Math.abs(bullet.y - gObjectE.getPosition().y) <= /*15*/15) {
//                                    ((Enemy) gObjectE).Die();
                                    ((Craft) gObjectC).getBullets().remove(i);//destroy bala ao colidir
                                    this.waveManager.destroyEnemy(j);
                                    ((Craft) gObjectC).deSpawnBullet(i);
                                    System.out.println("inimigo Down");
                                }
                            }
                            j++;
                        }
                    }
                    i++;
                }
            }
        }
    }
    //Ricardo-----------------------------------

}
