package galaga.client;

import galaga.shared.RemoteInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteInterfaceManager {
    public RemoteInterface getRemoteInterface() {
        try {
            String host = "127.0.0.1";
            Registry registry = LocateRegistry.getRegistry(host);
            return (RemoteInterface) registry.lookup("RemoteInterface");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
