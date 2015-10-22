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

        this.currentStage.notifyTime(dt);

        if (this.currentStage.canGoToNextStage(this)) {
            this.currentStage = this.currentStage.getNextStage();
        }
    }

    @Override
    public Stage getCurrentStage() throws RemoteException {
        return this.currentStage;
    }

    @Override
    public int getNewConnectionId() throws RemoteException {
        this.numberOfConnections++;

        return this.numberOfConnections;
    }

    @Override
    public void keyCodePressed(int craftId, int keyCode) throws RemoteException {
        if (this.currentStage instanceof GameStage) {
            GameStage currentStage = (GameStage) this.currentStage;

            currentStage.getCraftById(craftId).keyCodePressed(keyCode);
        }
    }

    @Override
    public void keyCodeReleased(int craftId, int keyCode) throws RemoteException {
        if (this.currentStage instanceof GameStage) {
            GameStage currentStage = (GameStage) this.currentStage;

            currentStage.getCraftById(craftId).keyCodeReleased(keyCode);
        }
    }

    @Override
    public boolean isDrawn(int craftId) throws RemoteException {
        if (this.currentStage instanceof GameStage) {
            GameStage currentStage = (GameStage) this.currentStage;

            return currentStage.getCraftById(craftId).isDrawn(craftId);
        }

        return false;
    }

    @Override
    public void draw(int craftId) {
        this.draw(craftId);
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
