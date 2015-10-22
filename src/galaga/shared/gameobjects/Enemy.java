package galaga.shared.gameobjects;

import java.util.Random;

public class Enemy implements GameObject {
    int switcher = 0;
    private int x;
    private int y;
    private int id;
    private boolean isDrawn = false;
    private int screenWidth;//Scene variable
    private int screenHeight;//Scene variable
    private int objectSize = 0;//120 �timo valor
    private int xSpeed;
    private int ySpeed;

    public Enemy(int positioX, int positionY, int screenWidth, int screenHeight /*int rowNumber*/) {
        this.x = positioX;
        this.y = positionY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;


        this.xSpeed = 100;
        this.ySpeed = 100;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public void update(double dt) {
//        updateX(dt);
//        updateY(dt);

    }

    @Override
    public void updateX(double dt/*time*/) {
        // if (((this.x + (int) (this.xSpeed * dt)) > 0) && ((this.x + (int) (this.xSpeed * dt)) < (screenWidth - objectSize))) {
        if (switcher < 20) {
            this.x += (int) /*(this.xSpeed * dt) + */(this.xSpeed * dt) * switcher / 10;
        } else {
            this.x += (int) /*(this.xSpeed * dt) + */(this.xSpeed * dt);
            switcher *= -1;
        }
        switcher++;
        //}
        portal();
    }

    @Override
    public void updateY(double dt/*time*/) {
        //if (((this.y + (int) (this.ySpeed * dt)) > 0) && ((this.y + (int) (this.ySpeed * dt)) < (screenWidth - objectSize))) {
        if (Math.abs(switcher) < 10) {
            this.y += (int) (this.ySpeed * dt);// + (this.ySpeed * dt * Math.cos(randomNumber(180)));
        } else {
            this.y += (int) (this.ySpeed * dt * Math.cos(45));// + (this.ySpeed * dt * Math.cos(randomNumber(180)));
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

    public int getConnectionId() {
        return this.id;
    }

    public boolean isDrawn(int craftId) {
        return isDrawn;
    }

    public void draw(int craftId) {
        this.isDrawn = true;
    }


    //Enemy "AI"

    void move() {
        //move
//        Scene scene = new Scene();
//        int target = scene.getClosestPlayerId(this.x, this.y);
//        this.x += updateX();
//        this.y +=updateY();
        portal();
    }

    void portal() {
        if (this.x < 0) {
            this.x = screenWidth;
        } else if (this.x > screenWidth) {
            this.x = 0;
        }

        if (this.y < 0) {
            this.y = screenHeight;
        } else if (this.y > screenHeight) {
            this.y = 0;
        }

//        if(!somePlayer.alive){
//            this.position = initialPosition;
//        }
    }


}