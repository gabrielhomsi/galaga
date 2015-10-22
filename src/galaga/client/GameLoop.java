package galaga.client;

public class GameLoop extends Thread {
    private static final int MILLISECONDS_PER_ITERATION = 33;

    private Main main;

    public GameLoop(Main main) {
        this.main = main;
    }

    public void run() {
        this.main.setIsGameRunning(true);

        long t0 = System.currentTimeMillis();

        while (this.main.getIsGameRunning()) {
            long t1 = System.currentTimeMillis();

            if (t1 - t0 >= MILLISECONDS_PER_ITERATION) {
                this.main.retrieveFreshStage();
                this.main.repaint();

                t0 = System.currentTimeMillis();
            }
        }
    }
}
