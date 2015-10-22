package galaga.server;

import galaga.shared.RemoteInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteInterfaceManager {
    public void setRemoteInterface(Main main) {
        try {
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(main, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RemoteInterface", stub);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
