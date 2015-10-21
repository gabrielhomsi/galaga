package galaga.server;

import galaga.shared.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Main implements RemoteInterface {
    ArrayList<LinkedList<GameObject>> enemyMatrix = new ArrayList<LinkedList<GameObject>>(10);
    private boolean isGameRunning = false;
    private Scene scene;

    public Main() {
        this.scene = new Scene();

        new GameLoop(this).start();
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

    private void createEnemy(int index, int i) {
        Random random = new Random();
//        int positionX = random.nextInt(this.scene.getFrameWidth() - 70);
//        int positionY = random.nextInt(this.scene.getFrameHeight() - 70);

        int positionX = (this.scene.getFrameWidth() / 4) + i * 30;
        int positionY = index * 30;


        Enemy enemy = new Enemy(positionX, positionY, this.scene.getFrameWidth(), this.scene.getFrameHeight());
        this.scene.getGameObjects().add(enemy);

        this.enemyMatrix.get(index).add(enemy);
    }
    //GameObject[][] enemyMatrix = new GameObject[10][10];

    protected void newWave() {
        int index = 0;
        boolean rowEmpty = false;
        System.out.println("WAVE");
        if (this.enemyMatrix.isEmpty()) {
            System.out.println("VAZIO");
            for (int j = 0; j < 10; j++) {
                this.enemyMatrix.add(new LinkedList<GameObject>());
                for (int i = 0; i < 10; i++) {
//                    this.enemyMatrix
                    createEnemy(j, i);
                    System.out.println("Enemy Row Created");
                }
            }

        } else {
            for (LinkedList<GameObject> enemyRow : this.enemyMatrix) {
//                System.out.println("FOra 1");
//                System.out.println(enemyRow.size());
                if (enemyRow.isEmpty()) {
                    rowEmpty = true;
                    for (int i = 0; i < 10; i++) {
                        createEnemy(index, i);
                        System.out.println("Enemy Row Created");
                    }
                    break;
                }
                index++;
            }
        }

        index = 0;
        if (!rowEmpty) {
            for (LinkedList<GameObject> enemyRow : enemyMatrix) {
                for (int i = 0; i < 10 - enemyRow.size(); i++) {//preenche posi�oes vazias das linhas
                    createEnemy(index, i);
                    System.out.println("'Buraco' preenchido");
                }
                index++;
            }
        }

        //createEnemy();
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

    @Override
    public boolean isDrawn(int craftId) throws RemoteException {
        return this.scene.getCraftById(craftId).isDrawn(craftId);
    }

    @Override
    public void draw(int craftId) {
        this.draw(craftId);
    }

    public boolean getIsGameRunning() {
        return isGameRunning;
    }

    public void setIsGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }
}
