package galaga.server;

import galaga.shared.GameObject;
import galaga.shared.RemoteInterface;
import galaga.shared.Scene;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class Main implements RemoteInterface {
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

                while (main.getIsGameRunning()) {
                    long now = System.currentTimeMillis();

                    if (now - start >= 33) {
                        double dt = (now - start) / 1000.0;

                        main.update(dt);

                        start = System.currentTimeMillis();
                    }
                }
            }
        }.start();
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
    public void keyCodePressed(int keyCode) throws RemoteException {
        this.scene.getCraft().keyCodePressed(keyCode);
    }

    @Override
    public void keyCodeReleased(int keyCode) throws RemoteException {
        this.scene.getCraft().keyCodeReleased(keyCode);
    }

    public boolean getIsGameRunning() {
        return isGameRunning;
    }

    public void setIsGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }
}
