package galaga.server;

import galaga.shared.RemoteInterface;
import galaga.shared.Scene;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main implements RemoteInterface {
    private Scene scene = new Scene();

    public static void main(String[] args) throws Exception {
        try {
            Main obj = new Main();
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RemoteInterface", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public Scene getScene() throws RemoteException {
        return this.scene;
    }
}
