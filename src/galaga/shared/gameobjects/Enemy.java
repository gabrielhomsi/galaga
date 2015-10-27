package galaga.shared.gameobjects;

import java.awt.*;
import java.util.Random;

public class Enemy implements GameObject {
    int switcher = 0;
    //teste
    boolean startMove = false;//delay para começar o movimento

    private int screenWidth;//Scene variable
    private int screenHeight;//Scene variable
    private int objectSize = 0;//120 �timo valor
    private int xSpeed;
    private int ySpeed;
    private Point position;

    public Enemy(Point position, int screenWidth, int screenHeight /*int rowNumber*/) {
        this.position = position;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.xSpeed = 100;
        this.ySpeed = 100;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void update(double dt) {

        if (startMove) {
            updateX(dt);
            updateY(dt);
        } else {
            switcher++;
            if (switcher > 30) {
                startMove = !startMove;
                switcher = 0;
            }
        }


    }

    // @Override
    public void updateX(double dt/*time*/) {
        // if (((this.x + (int) (this.xSpeed * dt)) > 0) && ((this.x + (int) (this.xSpeed * dt)) < (screenWidth - objectSize))) {
        if (switcher < 20) {
            this.position.x += (int) /*(this.xSpeed * dt) + */(this.xSpeed * dt) * switcher / 10;
        } else {
            this.position.x += (int) /*(this.xSpeed * dt) + */(this.xSpeed * dt);
            switcher *= -1;
        }
        switcher++;
        //}
        portal();
    }

    //@Override
    public void updateY(double dt/*time*/) {
        //if (((this.y + (int) (this.ySpeed * dt)) > 0) && ((this.y + (int) (this.ySpeed * dt)) < (screenWidth - objectSize))) {
        if (Math.abs(switcher) < 10) {
            this.position.y += (int) (this.ySpeed * dt);// + (this.ySpeed * dt * Math.cos(randomNumber(180)));
        } else {
            this.position.y += (int) (this.ySpeed * dt * Math.cos(45));// + (this.ySpeed * dt * Math.cos(randomNumber(180)));
        }

        //}
        portal();
    }

    public int randomNumber(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

    @Override
    public String getImagePath() {
        return "assets/enemies/enemyBlack1.png";
    }

    //Enemy "AI"

    void move() {
        //move
//        Scene scene = new Scene();
//        int target = scene.getClosestPlayerConnectionId(this.position);
//        this.position.x += updateX();
//        this.position.y +=updateY();
        portal();
    }

    void portal() {
        if (this.position.x < 0) {
            this.position.x = screenWidth;
        } else if (this.position.x > screenWidth) {
            this.position.x = 0;
        }

        if (this.position.y < 0) {
            this.position.y = screenHeight;
        } else if (this.position.y > screenHeight) {
            this.position.y = 0;
        }

//        if(!somePlayer.alive){
//            this.position = initialPosition;
//        }
    }


}