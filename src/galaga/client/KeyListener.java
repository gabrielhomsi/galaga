package galaga.client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

public class KeyListener extends KeyAdapter {
    private final Main main;
    private SoundHandler shotSound;

    public void makeSoundShot(){
        String soundPath = "assets/Sounds/sfx_laser1.wav";
        boolean loop = false;
//        shotSound.playSound(soundPath, loop);
        SoundHandler.playSound(soundPath, loop);
    }

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
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//                makeSoundShot();


            }

        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 27) {
            System.exit(0);
        }
    }
}
