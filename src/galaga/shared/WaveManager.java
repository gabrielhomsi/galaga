package galaga.shared;


import galaga.shared.gameobjects.Craft;
import galaga.shared.gameobjects.Enemy;
import galaga.shared.gameobjects.GameObject;
import galaga.shared.stages.GameStage;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class WaveManager implements Serializable {
    private static final int TIME_TO_NEW_WAVE = 5;

    private final GameStage currentStage;
    private double timePassed = 0;
    private double timePassed2 = 0;
    private boolean canDestroy = false;

    public WaveManager(GameStage gameStage) {
        this.currentStage = gameStage;
    }

    private void createEnemy(int index) {
        int positionX = (this.currentStage.getFrameWidth() / 4) + (index % 10) * 30;
        int positionY = (index / 10) * 30;

        Point position = new Point(positionX, positionY);

        Random random = new Random(); // tempo para mover
        double timeToMove = (random.nextInt(30) + 1) * 0.3;

        //System.out.println("Time to Move enemy[ " + index + "]:" + timeToMove);

        Enemy enemy = new Enemy(position, this.currentStage.getFrameWidth(), this.currentStage.getFrameHeight(), timeToMove);

        //Setando Rally Point
        enemy.setRushPoint(/*this.currentStage.getClosestCraftByConnectionId(enemy.getPosition())*/this.currentStage.getCraftByConnectionId(0));
        this.currentStage.getGameObjects().add(enemy);
    }

    private void newWave() {
        System.out.println("newWave");

        LinkedList<GameObject> gameObjects = this.currentStage.getGameObjects();

        if (gameObjects.size() == 2) {
            System.out.println("Nenhum inimigo na tela. Criando 100 inimigos...");

            for (int i = 0; i < 100; i++) {
                this.createEnemy(i);
            }
        } else {
            if (gameObjects.size() < 100) {
                for (int i = 0; i < 10; i++) {
                    this.createEnemy(i);
                }
            }
        }
    }

    public int destroyEnemy(int index) {
        //throws IndexOutOfBoundsException
        int score = 0;
        try {
            GameObject gameObject = this.currentStage.getGameObjects().get(index);

            if (gameObject != null && !(gameObject instanceof Craft)) {
                System.out.println("Destroying gameObject with index = " + index);

                score = ((Enemy) this.currentStage.getGameObjects().get(index)).getEnemyType() * 100;
                this.currentStage.getGameObjects().remove(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Acessou posicao invalida");
        }
        return score;
    }

    public void notifyTime(double dt) {
        this.timePassed += dt;
        this.timePassed2 += dt;

        if (this.timePassed > TIME_TO_NEW_WAVE) {
            this.timePassed -= TIME_TO_NEW_WAVE;
            this.newWave();
            this.canDestroy = !this.canDestroy;
        }

//        if (this.timePassed2 > (TIME_TO_NEW_WAVE * 0.2)) {
//            int numberOfGameObjects = this.currentStage.getGameObjects().size();
//
//            Random random = new Random();
//            int indexToRemove = random.nextInt(numberOfGameObjects);
//
//            this.destroyEnemy(indexToRemove);
//        }
    }
}
