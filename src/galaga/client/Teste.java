package galaga.client;

import javax.swing.*;
import java.awt.*;

class Teste extends JPanel {
    private String mensagem;

    public Teste(String mensagem) {
        this.mensagem = mensagem;
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawString(this.mensagem, 100, 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

}