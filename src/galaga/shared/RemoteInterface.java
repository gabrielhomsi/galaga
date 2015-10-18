package galaga.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    Scene getScene() throws RemoteException;
}
