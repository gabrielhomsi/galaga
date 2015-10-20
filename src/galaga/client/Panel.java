package galaga.client;

import galaga.shared.Craft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;


class Panel extends JPanel implements ActionListener {
    private final Main main;

    private int craftId;
    private Image craftImage;

    public Panel(Main main) {
        this.main = main;

        try {
            this.craftId = this.main.getRemoteInterface().getNewCraftId();

            this.main.retrieveFreshScene();

            Craft craft = this.main.getScene().getCraftById(this.craftId);
            ImageIcon craftImageIcon = new ImageIcon(craft.getImagePath(2));
            this.craftImage = craftImageIcon.getImage();

            this.initPanel();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void initPanel() {
        this.addKeyListener(new TAdapter());
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        Craft craft = this.main.getScene().getCraftById(this.craftId);

        graphics2D.drawImage(this.craftImage, craft.getX(), craft.getY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

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