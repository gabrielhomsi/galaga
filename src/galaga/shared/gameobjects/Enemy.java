package galaga.shared.gameobjects;

import java.awt.*;
import java.util.Random;

public class Enemy implements GameObject {
    int switcher = 0;
    //Ricardo----------------------------------------
    //teste
    boolean startMove = false;//delay para começar o movimento
    //Ricardo----------------------------------------
    private boolean live = true;
    private int screenWidth;//Scene variable
    private int screenHeight;//Scene variable
    private int objectSize = 0;//120 �timo valor
    private int xSpeed;
    private int ySpeed;
    private Point position;

    private double timeToMove;
    private double timePassed = 0;

    private Point rushPoint = null;
    private String imagePath;

    private int type;

    public Enemy(Point position, int screenWidth, int screenHeight, /*int rowNumber*/double timeToMove) {
        this.position = position;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        int rnd = new Random().nextInt() % 6;

        if (rnd == 0) {
            setEnemyType(0);
            this.imagePath = "assets/enemies/enemyBlack1.png";
        } else if (rnd == 1) {
            setEnemyType(1);
            this.imagePath = "assets/enemies/enemyBlack2.png";
        } else if (rnd == 2) {
            setEnemyType(2);
            this.imagePath = "assets/enemies/ufoBlue.png";
        } else if (rnd == 3) {
            setEnemyType(3);
            this.imagePath = "assets/enemies/ufoGreen.png";
        } else if (rnd == 4) {
            setEnemyType(4);
            this.imagePath = "assets/enemies/ufoRed.png";
        } else if (rnd == 5) {
            setEnemyType(5);
            this.imagePath = "assets/enemies/ufoYellow.png";
        }

        this.xSpeed = 100;
        this.ySpeed = 100;
        this.timeToMove = timeToMove;
    }

    public int getEnemyType() {
        return this.type;
    }

    public void setEnemyType(int type) {
        this.type = type;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }


    @Override
    public void setRushPoint(Craft player) {
        //Ricardo----------------------------------------
        //if(live == false) return;
        //Ricardo----------------------------------------
        this.rushPoint = player.getPosition();
    }

    @Override
    public void update(double dt) {
        //Ricardo----------------------------------------
        //if(live == false) return;
        //Ricardo----------------------------------------
        if(startMove){
            //System.out.println(this.timePassed + ", passed/dt ," + dt);
            this.timePassed += dt;
            //System.out.println("passed/to move: " + this.timePassed + " / " + this.timeToMove);
            if ((this.timePassed >= this.timeToMove) && (this.timePassed <= this.timeToMove + 2)) {
                updateX(dt);
                updateY(dt);
            } else if (this.timePassed > this.timeToMove + 2) {
                this.timePassed = 0;
            }

        } else{
            switcher++;
            if(switcher > 30){
                startMove = !startMove;
                switcher=0;
            }
        }


    }

   // @Override
    public void updateX(double dt/*time*/) {
        // if (((this.x + (int) (this.xSpeed * dt)) > 0) && ((this.x + (int) (this.xSpeed * dt)) < (screenWidth - objectSize))) {
        if (switcher < 20) {
            this.position.x += (int) /*(this.xSpeed * dt) + */(this.xSpeed * dt) * switcher / 3;
        } else {
//            this.position.x += (int) /*(this.xSpeed * dt) + */(this.xSpeed * dt);
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
        return this.imagePath;
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

    //Ricardo----------------------------------------
    @Override
    public String getBulletImagePath() {
        return "assets/lasers/laserBlue.png";
    }
    public boolean isAlive() {
        return this.live;
    }

    public void Die() {
        this.live = false;
        this.position.x = -1;
        this.position.y = -1;
    }

    //Ricardo----------------------------------------
}
