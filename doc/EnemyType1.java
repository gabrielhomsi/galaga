package galaga.server;

import galaga.shared.gameobjects.Enemy;

public class EnemyType1 extends Enemy {

    public EnemyType1() {//Inimigo vermelho, segue player
        this.type = 1;
        this.life = 1;
        this.speed = 1;
        this.value = 100;
        this.position.x = 0;//some value
        this.position.y = 0;//some value
        //this.shoot = false;
    }

    @Override
    void move() {
        //f(x)=ax�+bx�+cx+d.
        super.move();

    }

    @Override
    void attack(/*Coordinate posicao*/) {

    }
}
