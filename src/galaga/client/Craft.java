package galaga.client;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.Image;

public class Craft {
    private int x;
    private int y;

    private int dx;
    private int dy;

    private Image image;

    public Craft() {
        initCraft();
    }

    private void initCraft() {
        ImageIcon imageIcon = new ImageIcon("assets/playerShip1_Blue.png");
        this.image = imageIcon.getImage();
        this.x = 40;
        this.y = 60;
    }

    public void move() {
        this.x += this.dx;
        this.y += this.dy;
    }

    public int getX() {
        return this.x;
    }

    public int gety() {
        return this.y;
    }

    public Image getImage() {
        return this.image;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            this.dx = -1;
        } else if (key == KeyEvent.VK_RIGHT) {
            this.dx = 1;
        } else if (key == KeyEvent.VK_UP) {
            this.dy = -1;
        } else if (key == KeyEvent.VK_DOWN) {
            this.dy = 1;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            this.dx = 0;
        } else if (key == KeyEvent.VK_RIGHT) {
            this.dx = 0;
        } else if (key == KeyEvent.VK_UP) {
            this.dy = 0;
        } else if (key == KeyEvent.VK_DOWN) {
            this.dy = 0;
        }
    }
}