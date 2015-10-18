package galaga.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    Scene getScene() throws RemoteException;

    void keyCodePressed(int keyCode) throws RemoteException;

    void keyCodeReleased(int keyCode) throws RemoteException;
}
