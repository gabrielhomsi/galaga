package galaga.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    Scene getScene() throws RemoteException;

    int getNewCraftId() throws RemoteException;

    void keyCodePressed(int playerId, int keyCode) throws RemoteException;

    void keyCodeReleased(int playerId, int keyCode) throws RemoteException;
}
