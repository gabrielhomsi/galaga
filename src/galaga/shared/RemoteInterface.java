package galaga.shared;

import galaga.shared.stages.Stage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    Stage getCurrentStage() throws RemoteException;

    int getNewConnectionId() throws RemoteException;

    void keyCodePressed(int playerId, int keyCode) throws RemoteException;

    void keyCodeReleased(int playerId, int keyCode) throws RemoteException;

    boolean isDrawn(int craftId) throws RemoteException;

    void draw(int craftId) throws RemoteException;
}
