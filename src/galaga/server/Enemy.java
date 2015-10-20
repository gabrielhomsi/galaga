package galaga.server;


//import galaga.client.Panel;
import galaga.shared.Craft;
import galaga.shared.RemoteInterface;
import galaga.shared.Scene;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;


public class Enemy {
    private int craftId;
    private Image craftImage;

    private Main main;

    public Enemy(Main main){
        this.main = main;
        try {
            this.craftId = this.main.getNewCraftId();

            Craft craft = this.main.getScene().getCraftById(this.craftId);
            //selecionar tipo de inimigo aleatóriamente
            ImageIcon craftImageIcon = new ImageIcon(craft.getImagePath(1/*getEnemyType()*/));
            this.craftImage = craftImageIcon.getImage();

        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    public int getEnemyType(){
        Random random = new Random();

        return random.nextInt() % 4;
    }

//        private RemoteInterface remoteInterface;
//        private boolean isGameRunning = false;
//        private Scene scene;
//
//        public Enemy() {
//            try {
//                this.initializeRemoteInterface();
//                this.retrieveFreshScene();
//                this.buildPanel();
//                this.startGameLoop();
//            } catch (Exception e) {
//                System.err.println("Client exception: " + e.toString());
//                e.printStackTrace();
//            }
//        }
//
//        protected RemoteInterface getRemoteInterface() {
//            return this.remoteInterface;
//        }
//
//        protected Scene getScene() {
//            return this.scene;
//        }
//
//        private void startGameLoop() {
//            Main main = this;
//
//            new Thread("RefreshScreen") {
//                public void run() {
//                    main.setIsGameRunning(true);
//
//                    long start = System.currentTimeMillis();
//
//                    while (main.getIsGameRunning()) {
//                        long now = System.currentTimeMillis();
//
//                        if (now - start >= 33) {
//                            main.retrieveFreshScene();
//                            main.repaint();
//                            start = System.currentTimeMillis();
//                        }
//                    }
//                }
//            }.start();
//        }
//
//        protected void retrieveFreshScene() {
//            try {
//                this.scene = this.remoteInterface.getScene();
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//
//        public boolean getIsGameRunning() {
//            return isGameRunning;
//        }
//
//        public void setIsGameRunning(boolean isGameRunning) {
//            this.isGameRunning = isGameRunning;
//        }
//    }




}
