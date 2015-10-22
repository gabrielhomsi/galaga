package galaga.server;

import galaga.shared.stages.GameStage;

public class Enemy {
    int life;
    Coordinate position;
    Coordinate initialPosition;
    int value;
    int type;//type of enemy,
    int speed;//speed of movement
    //boolean shoot;

    public Enemy(/*int life, Coordinate position, int value*/) {
//        this.position = position;
//        this.value = value;
//        this.life = life;
    }

    void move() {
        //move
        int target = Stage.getClosestPlayer(this.position);
        this.position += desloc();
        portal();
    }

    void abstract attack(/*Coordinate position*/) {

    }

    GameStage scene;
    int screenWidth = scene.getFrameWidth();
    int screenHeight = scene.getTextPositionY();

    void portal(){
        if(this.position.x < 0){
            this.position.x = screenWidth;
        } else if (this.position.x > screenWidth){
            this.position.x = 0;
        }

        if(this.position.y < 0){
            this.position.y = screenHeight;
        } else if (this.position.y > screenHeight){
            this.position.y = 0;
        }

        if(!somePlayer.alive){
            this.position = initialPosition;
        }
    }



}
