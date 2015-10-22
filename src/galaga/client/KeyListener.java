package galaga.client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

public class KeyListener extends KeyAdapter {
    private final Main main;

    public KeyListener(Main main) {
        this.main = main;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            this.main.getRemoteInterface().keyCodeReleased(this.main.getConnectionId(), e.getKeyCode());
        } catch (RemoteException e1) {
            e1.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            this.main.getRemoteInterface().keyCodePressed(this.main.getConnectionId(), e.getKeyCode());
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }
}
