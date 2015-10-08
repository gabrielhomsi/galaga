package galaga;

public class EnemyType4 extends Enemy {

    public EnemyType4() {//bicho amarelo, atira
        this.type = 4;
        this.life = 1;
        this.speed = 2;
        this.value = 300;
        this.position.x = 0;//some value
        this.position.y = 0;//some value
    }

    @Override
    void move() {

    }

    @Override
    void ataque(/*/Coordinate posicao*/) {

    }
}
