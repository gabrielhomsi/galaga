package galaga.server;

public class EnemyType3 extends Enemy {

    public EnemyType3() {//bicho azul
        this.type = 3;
        this.life = 2;
        this.speed = 2;
        this.value = 300;
        this.position.x = 0;//some value
        this.position.y = 0;//some value
    }

    @Override
    void move() {
        //f(x)=ax�+bx�+cx+d.
    }

    @Override
    void attack(/*Coordinate posicao*/) {

    }
}
