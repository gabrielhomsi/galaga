package galaga.client;

import galaga.shared.stages.MenuStage;
import galaga.server.Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Created by brunodg on 05/11/15.
 */
public class MouseInput implements MouseListener {

    private Main main;

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        //botão Jogar
        if (mx >= 350 && mx <= 450) {
            if (my >= 300 && my <= 350) {
                //Apertou o Play
                MenuStage menu = new MenuStage();
                menu.canGoToNextStage(this.main);
                menu.getNextStage(this.main);
            }
        }

       /* public Rectangle button1 = new Rectangle(350, 300, 100, 50);
        public Rectangle button2 = new Rectangle(350, 400, 100, 50); */
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseExited (MouseEvent e) {

    }

}
