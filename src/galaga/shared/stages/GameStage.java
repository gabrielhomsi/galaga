package galaga.shared.stages;

import galaga.server.Main;
import galaga.shared.BulletManager;
import galaga.shared.WaveManager;
import galaga.shared.gameobjects.Craft;
import galaga.shared.gameobjects.GameObject;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

//Ricardo------------------------------------
import galaga.shared.gameobjects.Enemy;
//Ricardo------------------------------------


public class GameStage implements Stage {
    private LinkedList<GameObject> gameObjects;
    private WaveManager waveManager;
    private boolean canDestroy = true;
    private int contador = 0;
    public BulletManager bulletManager;

    public GameStage(int numberOfConnections) {
        this.gameObjects = new LinkedList<>();

        for (int connectionId = 0; connectionId < numberOfConnections; connectionId++) {
            Craft craft = new Craft(connectionId, this.getFrameWidth());

            this.gameObjects.add(craft);
        }

        this.waveManager = new WaveManager(this);
        this.bulletManager = new BulletManager(this);
    }

    public int getFrameWidth() {
        return 800;
    }

    public int getFrameHeight() {
        return 600;
    }

    @Override
    public void notifyTime(double dt) {
        contador++;
        if (contador > 20) {
            if (canDestroy) {
                checkColision();
            }
            contador = 0;
        }

        this.waveManager.notifyTime(dt);
    }
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

    //mudar esse loop quando der tempo, dá para simplificar
    //acho que Adiciona a lista mais de uma vez um inimigo
    //Ricardo---------------------------------
    public void checkColision() {
        if (this.gameObjects == null) return;
        this.canDestroy = false;//nao permite chamar novamente a funçao até terminar isto

        LinkedList<Integer> enemiesToDestroy = new LinkedList<Integer>();
        LinkedList<Integer> bulletsToDestroy = new LinkedList<Integer>();
        LinkedList<Integer> playerList = new LinkedList<Integer>();
        //Posicao 0 enemy indice
        //posicao 1 bullet indice
        //posicao 2 player number

        try {
            for (GameObject gObjectC : this.gameObjects) {
                if (gObjectC instanceof Craft) {
                    int i = 0;
//
//                    ArrayList<Integer> bulletToDestroy = ((Craft) gObjectC).getBulletsToDestroy();
//                    for(Integer bullet : bulletToDestroy){
//                        ((Craft) gObjectC).destroyBullet(bullet);
//                    }
//                    ((Craft) gObjectC).setBulletsToDestroyEmpty();

                    for (Point bullet : ((Craft) gObjectC).getBullets()) {
//                        System.out.println("Numero de Balas na tela");
                        int j = 0;
                        boolean hit = false;//hit acertou inimigo
                        //System.out.println("bullet[" + bullet.x + ", " + bullet.y + "]");
                        for (GameObject gObjectE : this.gameObjects) {

                            if (!hit) {
//                            System.out.println("GameObjects " + j);
                                if (gObjectE instanceof Enemy) {
//                                System.out.println("GameObjects " + j);
//                                System.out.println("Enemy");
                                    //System.out.println("Enemy[" + gObjectE.getPosition().x + ", " + gObjectE.getPosition().y + "]");
                                    if (Math.abs(bullet.x - gObjectE.getPosition().x) <= /*15*/15) {
                                        if (Math.abs(bullet.y - gObjectE.getPosition().y) <= /*15*/15) {
//                                    listToDestroy.add(new LinkedList<Integer>());
//                                    ((Enemy) gObjectE).Die();
//                                        ((Craft) gObjectC).getBullets().remove(i);//destroy bala ao colidir
//                                        this.waveManager.destroyEnemy(j);
                                            enemiesToDestroy.add(j);
//                                        System.out.println(enemiesToDestroy.getLast());
                                            bulletsToDestroy.add(i);
                                            playerList.add(((Craft) gObjectC).getConnectionId());

                                            //((Craft) gObjectC).deSpawnBullet(i);
                                            System.out.println(" Add inimigo to deletion");
                                            hit = true;
                                        }
                                    }
                                } else{
//                                System.out.println("Player");
                                }

                            }
                            j++;
                        }
                        i++;
                    }
                }
            }
        } catch (ConcurrentModificationException e){
            e.printStackTrace();
            System.out.println("Elemento que está sendo utilizado está sendo modificado ao mesmo tempo exception");
        }



        if (enemiesToDestroy.size() == bulletsToDestroy.size() && enemiesToDestroy.size() == playerList.size()) {
            deletingLoop(enemiesToDestroy, bulletsToDestroy, playerList, playerList.size());
        } else {
            System.out.println("Algo deu muito errado");
        }


    }
    //Ricardo-----------------------------------

    private void destroyOnlyBullets(int bulletIndex, int playerId){

    }

    private void deletingLoop(LinkedList<Integer> enemyToDelete, LinkedList<Integer> bulletToDelete, LinkedList<Integer> playerList, int size) {
//        //Destroy inimigos
//        for (int i = 0; i < enemyToDelete.size(); i++) {
//            this.waveManager.destroyEnemy(enemyToDelete.get(i));
//        }
//
//        //destroy balas
//        for (int i = 0; i < bulletToDelete.size(); i++) {
//            this.getCraftByConnectionId(player.get(i)).getBullets().remove(bulletToDelete.get(i));//destroy bala ao colidir
//        }
        for (int i = 0; i < size; i++) {

            //destroy balas
            System.out.println("Craft ID: " + playerList.get(i));
            Craft player = this.getCraftByConnectionId(playerList.get(i));
            player.destroyBullet(bulletToDelete.get(i));
//            player.getBullets().remove();//destroy bala ao colidir

            //Destroy inimigos
            int score = this.waveManager.destroyEnemy(enemyToDelete.get(i));

            //Add Score
            player.addScore(score);

        }


        this.canDestroy = true;
    }

}
