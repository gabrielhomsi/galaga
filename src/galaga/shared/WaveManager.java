package galaga.shared;


import galaga.shared.gameobjects.Enemy;
import galaga.shared.gameobjects.GameObject;
import galaga.shared.stages.GameStage;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class WaveManager implements Serializable {
    private static final int TIME_TO_NEW_WAVE = 1;

    private final GameStage currentStage;
    private ArrayList<LinkedList<GameObject>> enemyMatrix = new ArrayList<>(10);
    private double timePassed = 0;
    private double timePassed2 = 0;
    private boolean canDestroy = false;

    public WaveManager(GameStage gameStage) {
        this.currentStage = gameStage;
    }

    private void createEnemy(int index, int i) {
        int positionX = (this.currentStage.getFrameWidth() / 4) + i * 30;
        int positionY = index * 30;

        Point position = new Point(positionX, positionY);

        Enemy enemy = new Enemy(position, this.currentStage.getFrameWidth(), this.currentStage.getFrameHeight());
        this.currentStage.getGameObjects().add(enemy);

        this.enemyMatrix.get(index).add(enemy);
    }
    //GameObject[][] enemyMatrix = new GameObject[10][10];

    private void newWave() {
        int index = 0;
        boolean rowEmpty = false;
        System.out.println("WAVE");
        if (this.enemyMatrix.isEmpty()) {
            System.out.println("VAZIO");
            for (int j = 0; j < 10; j++) {
                this.enemyMatrix.add(new LinkedList<GameObject>());
                for (int i = 0; i < 10; i++) {
//                    this.enemyMatrix
                    createEnemy(j, i);
                    System.out.println("Enemy Row Created");
                }
            }
        } else {
            for (LinkedList<GameObject> enemyRow : this.enemyMatrix) {
//                System.out.println("FOra 1");
//                System.out.println(enemyRow.size());
                if (enemyRow.isEmpty()) {
                    rowEmpty = true;
                    for (int i = 0; i < 10; i++) {
                        createEnemy(index, i);
                        System.out.println("Enemy Row Created");
                    }
                    break;
                }
                index++;
            }
        }

        index = 0;
        if (!rowEmpty) {
            for (LinkedList<GameObject> enemyRow : enemyMatrix) {
                for (int i = 0; i < 10 - enemyRow.size(); i++) {//preenche posicoes vazias das linhas
                    createEnemy(index, i);
                    System.out.println("'Buraco' preenchido");
                }
                index++;
            }
        }
        //createEnemy();
    }

    public void destroy(int index){

        index+=2;//eliminando os players
        System.out.println("Destroyed " + index);

        try{
            //debuggando
            //Dando erro
            //this.currentStage.getGameObjects().remove(index);
//            this.currentStage.getGameObjects().remove(this.currentStage.getGameObjects().get(index));
            System.out.println(this.currentStage.getGameObjects().get(index));
            if(this.currentStage.getGameObjects().get(index) != null){
               // this.currentStage.getGameObjects().remove(index);
                //this.enemyMatrix.get(index / 10).remove();
            }

            System.out.println(this.currentStage.getGameObjects().get(index).getPosition());
//            System.out.println(this.currentStage.getGameObjects().get(index).toString());
            //dando erro
            //Fim do debugging


        } catch (/*IndexOutOfBoundsException*/Exception e){
            System.out.println("Elemento nao existe");
        }
    //alter

    }

    public void notifyTime(double dt) {
        this.timePassed += dt;
        this.timePassed2 += dt;

        if (this.timePassed > TIME_TO_NEW_WAVE){
            this.timePassed -= TIME_TO_NEW_WAVE;
            this.newWave();
            this.canDestroy = !this.canDestroy;
        }

        if (this.timePassed2 > (TIME_TO_NEW_WAVE * 0.2)){
            Random random = new Random();
            this.destroy(random.nextInt(90));
        }
    }
}
