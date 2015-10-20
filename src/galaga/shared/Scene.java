package galaga.shared;

import java.io.Serializable;
import java.util.LinkedList;

public class Scene implements Serializable {
    private int lastNewCraftId;

    private int frameWidth = 800;
    private int frameHeight = 600;

    private LinkedList<GameObject> gameObjects;

    public Scene() {
        this.lastNewCraftId = 0;
        this.gameObjects = new LinkedList<>();
    }

    public int getFrameWidth() {
        return this.frameWidth;
    }

    public int getFrameHeight() {
        return this.frameHeight;
    }

    public Craft getCraftById(int craftId) {
        for (GameObject gameObject : this.gameObjects) {
            if (gameObject instanceof Craft) {
                Craft craft = (Craft) gameObject;

                if (craftId == craft.getId()) {
                    return craft;
                }
            }
        }

        return null;
    }

    public LinkedList<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    public Craft makeNewCraft() {
        Craft newCraft = new Craft(this.lastNewCraftId + 1);
        this.lastNewCraftId++;
        this.gameObjects.add(newCraft);
        return newCraft;
    }


    public int getDistanceBetween2Points(int pointAX, int pointAY, int pointBX, int pointBY) {
        int distance;
        distance = (int) Math.hypot((pointAX - pointBX), (pointAY - pointBY));
        return distance;
    }

    public int getClosestPlayerId(int positionX, int positionY) {
        int closestPlayer = 0;//jogador mais pr?ximo
        int distance = Integer.MAX_VALUE;//Distancia setada para o "infinito"
        int aux;

        for (GameObject gameObject : this.gameObjects){
            if(gameObject.getClass().isInstance(Craft.class)){
                System.out.printf("Player");
                aux = getDistanceBetween2Points(positionX, positionY, gameObject.getX(), gameObject.getY());
                if(aux < distance){
                    closestPlayer = gameObject.getId();
                }
            } else{
                System.out.println("Enemy");
            }
        }

        return closestPlayer;
    }
}
