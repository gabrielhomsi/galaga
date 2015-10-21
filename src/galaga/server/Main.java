package galaga.server;

import galaga.shared.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main implements RemoteInterface {
    long timeToNewWave = 1000;
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

                //createEnemy();
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
                        //createEnemy();
                        newWave();
                    }

                }
            }
        }.start();
    }

    private void createEnemy(int index, int i) {
        Random random = new Random();
//        int positionX = random.nextInt(this.scene.getFrameWidth() - 70);
//        int positionY = random.nextInt(this.scene.getFrameHeight() - 70);

        int positionX = (this.scene.getFrameWidth()/4) + i*30 ;
        int positionY = index*30;



        Enemy enemy = new Enemy(positionX, positionY, this.scene.getFrameWidth(), this.scene.getFrameHeight());
        this.scene.getGameObjects().add(enemy);

        this.enemyMatrix.get(index).add(enemy);
    }

    ArrayList<LinkedList<GameObject>> enemyMatrix = new ArrayList<LinkedList<GameObject>>(10);
    //GameObject[][] enemyMatrix = new GameObject[10][10];

    private void newWave(){
        int index = 0;
        boolean rowEmpty = false;
        System.out.println("WAVE");
        if(this.enemyMatrix.isEmpty()){
            System.out.println("VAZIO");
            for (int j = 0; j < 10; j++) {
                this.enemyMatrix.add(new LinkedList<GameObject>());
                for (int i = 0; i < 10; i++) {
//                    this.enemyMatrix
                    createEnemy(j, i);
                    System.out.println("Enemy Row Created");
                }
            }

        } else{
            for (LinkedList<GameObject> enemyRow : this.enemyMatrix){
//                System.out.println("FOra 1");
//                System.out.println(enemyRow.size());
                if(enemyRow.isEmpty()){
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

       index=0;
       if(!rowEmpty){
           for (LinkedList<GameObject> enemyRow : enemyMatrix){
               for (int i = 0; i < 10 - enemyRow.size(); i++) {//preenche posiçoes vazias das linhas
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
    public boolean isDrawn(int craftId) throws RemoteException{
       return this.scene.getCraftById(craftId).isDrawn(craftId);
    }

    @Override
    public void draw(int craftId){
        this.draw(craftId);
    }

    public boolean getIsGameRunning() {
        return isGameRunning;
    }

    public void setIsGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }
}
