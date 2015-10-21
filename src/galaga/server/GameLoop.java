package galaga.server;

public class GameLoop extends Thread {
    private Main main;
    private long timeToNewWave = 1000;

    public GameLoop(Main main) {
        this.main = main;
    }

    public void run() {
        this.main.setIsGameRunning(true);

        long t0 = System.currentTimeMillis();

        long startWave = t0;

        //createEnemy();
        while (this.main.getIsGameRunning()) {
            long t1 = System.currentTimeMillis();

            if ((t1 - t0) >= 33) {
                double dt = (t1 - t0) / 1000.0;

                this.main.update(dt);

                t0 = System.currentTimeMillis();
            }

            if ((t1 - startWave) > this.timeToNewWave) {
                startWave += this.timeToNewWave;
                System.out.println("New Wave after 60secs!");
                //createEnemy();
                this.main.newWave();
            }

        }
    }
}
