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

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);

        timer = new Timer(DELAY, this);
        timer.start();
    }


    private void doDrawing(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        ImageIcon imageIcon = new ImageIcon("assets/playerShip1_Blue.png");
        Image image = imageIcon.getImage();

        g2D.drawImage(image, scene.getCraft().getX(), scene.getCraft().getY(), this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scene.getCraft().move();
        repaint();
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