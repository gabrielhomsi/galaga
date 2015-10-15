package galaga.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteInterface extends Remote {
    //ArrayList<Coordinate> atualizaCenario() throws RemoteException;
    String sayHello() throws RemoteException;
}
