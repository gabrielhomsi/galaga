package galaga.server;

import galaga.shared.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class Main implements RemoteInterface {
    long timeToNewWave = 6000;
    private boolean isGameRunning = false;
    private Scene scene;

    public Main() {
        this.scene = new Scene();

        this.startGameLoop();
    }

    public static void main(String[] args) throws Exception {
        try {
            Main main = new Main();
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(main, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RemoteInterface", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    private void startGameLoop() {
        Main main = this;

        new Thread("RefreshScreen") {
            public void run() {
                main.setIsGameRunning(true);

                long start = System.currentTimeMillis();

                long startWave = start;

                while (main.getIsGameRunning()) {
                    long now = System.currentTimeMillis();

                    if (now - start >= 33) {
                        double dt = (now - start) / 1000.0;

                        main.update(dt);

                        start = System.currentTimeMillis();
                    }

                    if (now - startWave > timeToNewWave) {
                        startWave += timeToNewWave;
                        System.out.println("New Wave after 60secs!");
                        createEnemy();
                    }

                }
            }
        }.start();
    }

    private void createEnemy() {
        Enemy enemy = new Enemy();
        this.scene.getGameObjects().add(enemy);
    }

    public void update(double dt) {
        LinkedList<GameObject> gameObjects = this.scene.getGameObjects();

        for (GameObject gameObject : gameObjects) {
            gameObject.update(dt);
        }
    }

    @Override
    public Scene getScene() throws RemoteException {
        return this.scene;
    }

    @Override
    public int getNewCraftId() throws RemoteException {
        Craft newCraft = this.scene.makeNewCraft();
        return newCraft.getId();
    }

    @Override
    public void keyCodePressed(int craftId, int keyCode) throws RemoteException {
        this.scene.getCraftById(craftId).keyCodePressed(keyCode);
    }

    @Override
    public void keyCodeReleased(int craftId, int keyCode) throws RemoteException {
        this.scene.getCraftById(craftId).keyCodeReleased(keyCode);
    }

    public boolean getIsGameRunning() {
        return isGameRunning;
    }

    public void setIsGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }
}
