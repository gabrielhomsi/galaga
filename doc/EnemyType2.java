package galaga.server;

import galaga.shared.gameobjects.Enemy;

public class EnemyType2 extends Enemy {

    public EnemyType2() {//bicho que suga
        this.type = 2;
        this.life = 2;
        this.speed = 1;
        this.value = 200;
        this.position.x = 0;//some value
        this.position.y = 0;//some value
        //this.shoot = false;
    }

    @Override
    void move() {
        super.move();
        if(playerInRange()){
            attack();
        }

    }

    @Override
    void attack(/*Coordinate posicao*/) {
        int target = Stage.getClosestPlayer(this.position);
        while (Stage.players.get(target).posicao.x != this.position.x) {
            move();
        }
    }
}
