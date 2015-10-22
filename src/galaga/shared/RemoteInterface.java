package galaga.shared;

import galaga.shared.stages.Stage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    Stage getCurrentStage() throws RemoteException;

    int getNewConnectionId() throws RemoteException;

    void keyCodePressed(int connectionId, int keyCode) throws RemoteException;

    void keyCodeReleased(int connectionId, int keyCode) throws RemoteException;
}
