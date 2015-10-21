package galaga.client;

public class GameLoop extends Thread {
    private Main main;

    public GameLoop(Main main) {
        this.main = main;
    }

    public void run() {
        this.main.setIsGameRunning(true);

        long t0 = System.currentTimeMillis();

        //teste
        int count = 0;
        //fim Teste
        while (this.main.getIsGameRunning()) {
            long t1 = System.currentTimeMillis();

            if (t1 - t0 >= 33) {
                this.main.retrieveFreshScene();
                this.main.repaint();
                t0 = System.currentTimeMillis();

                //teste
                count++;
            }
            //teste
            if (count > 100) {
                count = 0;
                //printGameObjects();
            }
        }
    }
}
