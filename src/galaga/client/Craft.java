package galaga.client;

/**
 * Created by brunodg on 18/10/15.
 */

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.Image;

public class Craft{
    private int dx, dy, x, y;
    private Image image;

    public Craft() {

        initCraft();
    }

    private void initCraft() {
        ImageIcon ii = new ImageIcon("assets/playerShip1_Blue.png");
        image = ii.getImage();
        x = 40;
        y = 60;
    }

    public void move() {
        x+=dx;
        y+=dy;
    }

    public int getX() { return x; }
    public int gety() { return y; }
    public Image getImage() { return image; }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT){
            dx = -1;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
        if (key == KeyEvent.VK_UP){
            dy = -1;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT){
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP){
            dy = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}