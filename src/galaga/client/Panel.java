package galaga.client;

import galaga.shared.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


class Panel extends JPanel implements ActionListener {
    private final int DELAY = 10;
    private Scene scene;
    private Timer timer;

    public Panel(Scene scene) {
        this.scene = scene;
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

        ImageIcon imageIcon = new ImageIcon("assets/playerShip1_Blue.png");
        Image image = imageIcon.getImage();

        graphics2D.drawImage(image, scene.getCraft().getX(), scene.getCraft().getY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            scene.getCraft().keyCodeReleased(e.getKeyCode());
        }

        @Override
        public void keyPressed(KeyEvent e) {
            scene.getCraft().keyCodePressed(e.getKeyCode());
        }
    }

}