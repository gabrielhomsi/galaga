package galaga.client;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;
import galaga.shared.Scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.JPanel;


class Panel extends JPanel implements ActionListener{
    private Scene scene;
    private Timer timer;
    private Craft craft;
    private final int DELAY = 10;


    public Panel(Scene scene) {
        this.scene = scene;
        initPanel();
    }

    private void initPanel() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);

        craft = new Craft();

        timer = new Timer(DELAY, this);
        timer.start();
    }


    private void doDrawing(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        //g2D.drawString(this.scene.getTextMessage(), this.scene.getTextPositionX(), this.scene.getTextPositionY());
        g2D.drawImage(craft.getImage(), craft.getX(), craft.gety(), this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        craft.move();
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }

}