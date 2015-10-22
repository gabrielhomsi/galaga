package galaga.shared;


import galaga.shared.gameobjects.Enemy;
import galaga.shared.gameobjects.GameObject;
import galaga.shared.stages.GameStage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class WaveManager {
    private final GameStage currentStage;
    private ArrayList<LinkedList<GameObject>> enemyMatrix = new ArrayList<>(10);

    public WaveManager(GameStage gameStage) {
        this.currentStage = gameStage;
    }

    private void createEnemy(int index, int i) {
        Random random = new Random();
//        int positionX = random.nextInt(this.scene.getFrameWidth() - 70);
//        int positionY = random.nextInt(this.scene.getFrameHeight() - 70);

        int positionX = (this.currentStage.getFrameWidth() / 4) + i * 30;
        int positionY = index * 30;


        Enemy enemy = new Enemy(positionX, positionY, this.currentStage.getFrameWidth(), this.currentStage.getFrameHeight());
        this.currentStage.getGameObjects().add(enemy);

        this.enemyMatrix.get(index).add(enemy);
    }
    //GameObject[][] enemyMatrix = new GameObject[10][10];

    protected void newWave() {
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
                for (int i = 0; i < 10 - enemyRow.size(); i++) {//preenche posi�oes vazias das linhas
                    createEnemy(index, i);
                    System.out.println("'Buraco' preenchido");
                }
                index++;
            }
        }

        //createEnemy();
    }
}
