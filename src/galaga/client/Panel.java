package galaga.client;

import galaga.shared.Craft;
import galaga.shared.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.LinkedList;


class Panel extends JPanel /*implements ActionListener*/ {
    private final Main main;
    LinkedList<GameObject> gameObjects;
    private int craftId;
    private Image craftImage;

    public Panel(Main main) {
        this.main = main;

        try {
            this.craftId = this.main.getRemoteInterface().getNewCraftId();

            this.main.retrieveFreshScene();

            Craft craft = this.main.getScene().getCraftById(this.craftId);
            ImageIcon craftImageIcon = new ImageIcon(craft.getImagePath());
            this.craftImage = craftImageIcon.getImage();

            System.out.printf("ID:" + this.craftId);
            System.out.printf("Image:" + this.craftImage);

            this.initPanel();


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        for (GameObject gameObject : this.main.getScene().getGameObjects()) {
            //System.out.printf("bool: " + gameObject.isDrawn(gameObject.getId()));
            if(!gameObject.isDrawn(gameObject.getId())){
                //System.out.println("teste");
                ImageIcon gameObjectImageIcon = new ImageIcon(gameObject.getImagePath());
                Image gameObjectImage = gameObjectImageIcon.getImage();
                graphics2D.drawImage(gameObjectImage, gameObject.getX(), gameObject.getY(), this);

                gameObject.draw(gameObject.getId());
            }

        }
    }


    private void initPanel() {
        this.addKeyListener(new TAdapter());
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        this.repaint();
//    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            try {
                main.getRemoteInterface().keyCodeReleased(craftId, e.getKeyCode());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            try {
                main.getRemoteInterface().keyCodePressed(craftId, e.getKeyCode());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

}