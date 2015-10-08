package galaga;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InterfaceRMI extends Remote {
    ArrayList<Coordenada> atualizaCenario() throws RemoteException;


}
