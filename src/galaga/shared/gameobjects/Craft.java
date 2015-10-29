package galaga.shared.gameobjects;

import galaga.shared.stages.GameStage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

//-------------------------------------
//ricardo
//ricardo

//-------------------------------------

public class Craft implements GameObject {
    private int bulletSpeed;

    private int score;
    private int lives;

    private int connectionId;
    private int frameWidth;
    private Point position;

    private int xSpeed;

    private int objectSize = 30;//120 otimo valor

    public Craft(int connectionId, int frameWidth) {
        this.score = 0;
        this.lives = 3;

        this.connectionId = connectionId;
        this.frameWidth = frameWidth;
        this.position = new Point(0, 530);
        this.xSpeed = 0;
    }

    public int getLivesNumber() {
        return this.lives;
    }

    public void lifeUp() {
        this.lives++;
    }

    public void lifeDown(){this.lives--;}

    public void addScore(int score) {
        this.score += score;
        System.out.println("Player " + this.connectionId + " Score: " + getScore());
    }

    public int getScore() {
        return this.score;
    }

    //seta um ponto de destino, implementado pela Classe Enemy
    @Override
    public void setRushPoint(Craft player) {

    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    public void keyCodePressed(int keyCode, GameStage currentStage) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.xSpeed = -200;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.xSpeed = 200;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            currentStage.bulletManager.beginFire(connectionId);
        }
    }

    public void keyCodeReleased(int keyCode, GameStage currentStage) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.xSpeed = 0;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.xSpeed = 0;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            currentStage.bulletManager.endFire(connectionId);
        }
    }

    @Override
    public void update(double dt) {
        if (((this.position.x + (int) (this.xSpeed * dt)) > 0) && ((this.position.x + (int) (this.xSpeed * dt)) < (frameWidth - objectSize))) {
            this.position.x += (int) (this.xSpeed * dt);
        }
//        for(int[] s : shots){
//            if(s[0] != -1) {
//                s[1] -= this.bulletSpeed;
//            }
//        }
        try{
            int indexToDestroy = 0;
            for(Point shot : shots){
                if(shot.x != -1) {
                    shot.y -= this.bulletSpeed;
                    if(shot.y < -10){
                        //Elimina stackOverflow Exception
//                    shots.remove(indexToDestroy);
                        destroyBullet(indexToDestroy);//Nao apagar aqui, por em array para o gameStageDeletar
                        //this.bulletsToDestroy.add(indexToDestroy);
                        System.out.println("Bullet destroyed");
                    }
                }
                indexToDestroy++;
            }
        } catch (ConcurrentModificationException e){
            e.printStackTrace();
            System.out.printf("Sendo alterado ao mesmo tempo em outra classe - GameStage");
        }

    }
//
//    private ArrayList<Integer> bulletsToDestroy = new ArrayList<Integer>();
//
//    public ArrayList<Integer> getBulletsToDestroy(){
//        return this.bulletsToDestroy;
//    }
//
//    public void setBulletsToDestroyEmpty(){
//        this.bulletsToDestroy.clear();
//    }

    public void destroyBullet(int index){
        try{
            shots.remove(index);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("ArrayOutOfBounds");
        }

    }

    @Override
    public String getImagePath() {
        if(this.connectionId %2 == 0){
            return "assets/playerShip1_Blue.png";
        } else{
            return "assets/playerShip2_Green.png";
        }
    }

    //ricardo--------------------------------------------------
    @Override
    public String getBulletImagePath() {
        return "assets/lasers/laserBlue.png";
    }
    //ricardo--------------------------------------------------

    public int getConnectionId() {
        return this.connectionId;
    }

//
//    public void deSpawnBullet(int i){
//        shots.get(i).x = -1;
//    }

    public LinkedList<Point> getBullets(){
        return shots;
    }
    //Ricardo-------------------------------------
}
