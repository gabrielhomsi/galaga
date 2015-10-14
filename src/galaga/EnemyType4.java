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
    void attack(/*/Coordinate posicao*/) {
        int target = Stage.getClosestPlayer(this.position);
        //  long timerI = 0;//temporizar para atirar mesmo nao indo acertar o alvo
        //timer em ms
        //timerI = System.currentTimeMillis();
        while (Stage.players.get(target).posicao.x != this.position.x) {
            move();
            //  timer++;//teste, por com tempo
            //  if(timer%2000 <5){
            //atira();
            //  }
        }
    }
}
