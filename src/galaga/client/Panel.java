package galaga.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;


class Panel extends JPanel implements ActionListener {
    private final Main main;
    private final Image craftImage;

    public Panel(Main main) {
        this.main = main;

        ImageIcon craftImageIcon = new ImageIcon(this.main.getScene().getCraft().getImagePath());
        this.craftImage = craftImageIcon.getImage();

        initPanel();
    }

    private void initPanel() {
        this.addKeyListener(new TAdapter());
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.drawImage(this.craftImage, this.main.getScene().getCraft().getX(), this.main.getScene().getCraft().getY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            try {
                main.getRemoteInterface().keyCodeReleased(e.getKeyCode());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            try {
                main.getRemoteInterface().keyCodePressed(e.getKeyCode());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

}