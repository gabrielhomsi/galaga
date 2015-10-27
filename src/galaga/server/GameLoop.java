package galaga.server;

import galaga.shared.stages.GameStage;
import galaga.shared.stages.Stage;

public class GameLoop extends Thread {
    private static final int MILLISECONDS_PER_ITERATION = 33;

    private Main main;

    //private long time = 0;

    public GameLoop(Main main) {
        this.main = main;
    }

    public void run() {
        this.main.setIsGameRunning(true);

        long t0 = System.currentTimeMillis();
        // time = t0;

        while (this.main.getIsGameRunning()) {
            long t1 = System.currentTimeMillis();

            if ((t1 - t0) >= MILLISECONDS_PER_ITERATION) {
                //aprox. 30 iter/sec
                double dt = (t1 - t0) / 1000.0;//SECONDS
                //aprox. 0.03 iter/sec ---> 33 secs para cada atualizacao
//                System.out.println("GameLoop dt " + dt + "s");
                this.main.update(dt);

                try{
                    Stage stage = this.main.getCurrentStage();
                    if(stage instanceof GameStage) {
                        ((GameStage) stage).checkColision();
                    }
                } catch(Exception e){
                    e.printStackTrace();
                    System.out.println("Deu RUim");
                }

                t0 = System.currentTimeMillis();
            }

            //if((t1 - time) >= timeToNewWave){

            // }
        }
    }
}
