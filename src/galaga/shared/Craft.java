package galaga.shared;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class Craft implements Serializable, GameObject {
    private int id;

    private int x;
    private int y;

    private int frameWidth = 800;//Scene variable

    private int objectSize = 100;//120 ótimo valor

    private int xSpeed;

    public Craft(int id) {
        this.id = id;

        this.x = 40;
        this.y = 470;

        this.xSpeed = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void keyCodePressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.xSpeed = -200;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.xSpeed = 200;
        }
    }

    public void keyCodeReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            this.xSpeed = 0;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.xSpeed = 0;
        }
    }

    @Override
    public void update(double dt) {
        if(((this.x + (int) (this.xSpeed * dt)) > 0) && ((this.x + (int) (this.xSpeed * dt)) < (frameWidth - objectSize))){
            this.x += (int) (this.xSpeed * dt);
        }

    }
//funcionando, antigo
//    @Override
//    public String getImagePath() {
//        return "assets/playerShip1_Blue.png";
//    }


    @Override
    public String getImagePath(int type) {
        //lista de tipos de objetos/imagens, opções abaixo são exemplos
        switch (type){
            case 1:
                return "assets/playerShip1_Blue.png";
            case 2:
                return "assets/enemies/enemyBlack1.png";
            default:
                return "assets/enemies/enemyBlack2.png";
        }

    }

    public int getId() {
        return this.id;
    }
}