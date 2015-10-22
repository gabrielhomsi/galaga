package galaga.server;

import galaga.shared.RemoteInterface;
import galaga.shared.gameobjects.GameObject;
import galaga.shared.stages.GameStage;
import galaga.shared.stages.MenuStage;
import galaga.shared.stages.Stage;

import java.rmi.RemoteException;
import java.util.LinkedList;

public class Main implements RemoteInterface {
    private boolean isGameRunning = false;
    private int numberOfConnections;
    private Stage currentStage;

    public Main() {
        this.numberOfConnections = 0;
        this.currentStage = new MenuStage();
        new GameLoop(this).start();
        new RemoteInterfaceManager().setRemoteInterface(this);
        System.err.println("Server ready");
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public void update(double dt) {
        LinkedList<GameObject> gameObjects = this.currentStage.getGameObjects();

        for (GameObject gameObject : gameObjects) {
            gameObject.update(dt);
        }

//        System.out.println("Server/Main dt " + dt + "ns");
        this.currentStage.notifyTime(dt);

        if (this.currentStage.canGoToNextStage(this)) {
            this.currentStage = this.currentStage.getNextStage(this);
        }
    }

    @Override
    public Stage getCurrentStage() throws RemoteException {
        return this.currentStage;
    }

    @Override
    public int getNewConnectionId() throws RemoteException {
        int newConnectionId = this.numberOfConnections;

        this.numberOfConnections++;

        return newConnectionId;
    }

    @Override
    public void keyCodePressed(int connectionId, int keyCode) throws RemoteException {
        if (this.currentStage instanceof GameStage) {
            GameStage currentStage = (GameStage) this.currentStage;

            currentStage.getCraftByConnectionId(connectionId).keyCodePressed(keyCode);
        }
    }

    @Override
    public void keyCodeReleased(int connectionId, int keyCode) throws RemoteException {
        if (this.currentStage instanceof GameStage) {
            GameStage currentStage = (GameStage) this.currentStage;

            currentStage.getCraftByConnectionId(connectionId).keyCodeReleased(keyCode);
        }
    }

    public boolean getIsGameRunning() {
        return this.isGameRunning;
    }

    public void setIsGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }

    public int getNumberOfConnections() {
        return numberOfConnections;
    }
}
