package galaga.shared.gameobjects;

import java.awt.*;
import java.awt.event.KeyEvent;
//-------------------------------------
//ricardo
import java.util.LinkedList;
//ricardo

//-------------------------------------

public class Craft implements GameObject {
    //-----------------------------------------------
    //Ricardo
    //evitar multiplos tiros serem criados juntos
    private boolean quickPush;
//    private LinkedList<int[]> shots;
    private LinkedList<Point> shots;
    private int bulletSpeed;
    //Riacardo
    //-----------------------------------------------
    private LinkedList<Bullet> bullets;

    private int connectionId;
    private int frameWidth;
    private Point position;

    private int xSpeed;

    private int objectSize = 30;//120 �timo valor


    public Craft(int connectionId, int frameWidth) {
        this.connectionId = connectionId;
        this.frameWidth = frameWidth;
        this.position = new Point(0, 530);
        this.xSpeed = 0;
        //Ricardo-------------------------------------
        this.bulletSpeed = 5;
//        this.shots = new LinkedList<int[]>();
        this.shots = new LinkedList<Point>();
        //Ricardo-------------------------------------
        this.bullets = new LinkedList<Bullet>();
    }

    //seta um ponto de destino, implementado pela Classe Enemy
    @Override
    public void setRushPoint(Craft player) {

    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    public void keyCodePressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.xSpeed = -200;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.xSpeed = 200;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            // Tiros aqui (keyPressed)
            //Ricardo------------------------------
            if(quickPush){
                quickPush = false;
                spawnBullet();
            }
            //Ricardo  ----------------------------
        }
    }

    public void keyCodeReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.xSpeed = 0;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.xSpeed = 0;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            // Tiros aqui (keyReleased)
            //Ricardo
            quickPush = true;
            //Ricardo-------------------------------------
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
        int indexToDestroy = 0;
        for(Point shot : shots){
            if(shot.x != -1) {
                shot.y -= this.bulletSpeed;
                if(shot.y < -10){
                    //Elimina stackOverflow Exception
                    shots.remove(indexToDestroy);
                    System.out.println("Bullet destroyed");
                }
            }
            indexToDestroy++;
        }

    }

    @Override
    public String getImagePath() {
        return "assets/playerShip1_Blue.png";
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

    //Ricardo-------------------------------------
    private void spawnBullet(){
        Point playerActualPosition = new Point();
        playerActualPosition.x = this.getPosition().x;
        playerActualPosition.y = this.getPosition().y;
        shots.push(playerActualPosition);
    }

    public void deSpawnBullet(int i){
        shots.get(i).x = -1;
    }

    public LinkedList<Point> getBullets(){
        return shots;
    }
    //Ricardo-------------------------------------
}
