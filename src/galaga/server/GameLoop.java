package galaga.server;

import galaga.shared.WaveManager;

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
                double dt = (t1 - t0) / 1000.0;//NANOSECONDS
                //aprox. 0.03 iter/sec ---> 33 secs para cada atualizacao
//                System.out.println("GameLoop dt " + dt + "ns");
                this.main.update(dt);

                t0 = System.currentTimeMillis();
            }

            //if((t1 - time) >= timeToNewWave){

           // }
        }
    }
}
