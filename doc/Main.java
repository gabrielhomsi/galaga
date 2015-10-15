package galaga.server;

import galaga.shared.RemoteInterface;
import galaga.shared.Scene;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main implements RemoteInterface {
    int pontuacao;
    int vidas;
    int potenciaTiro = 1;
    int speed = 5;//tem que ser mais r�pido que o mais r�pido inimigo (3)
    Coordinate posicao;
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
//
//        String host = (args.length < 1) ? null : args[0];
//        String host = (args.length < 1) ? "0" : args[0];
//        if (host == null) {
//            System.err.println("Host is invalid!\n");
//            System.exit(1);
//        }
//
//        Registry registry = LocateRegistry.getRegistry(host);
//        RemoteInterface stub = (RemoteInterface) registry.lookup("RemoteInterface");
//
//        ArrayList<Coordinate> listaPosicao = stub.atualizaCenario();
//
//        for (int i = 0; i < listaPosicao.size(); i++) {
//            System.out.println("posi��o " + i + ": [" + listaPosicao.get(i).x + ", " + listaPosicao.get(i).y + "]");
//        }
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello, Galaga! (mensagem proveniente de um RMI)";
    }

    @Override
    public Scene getScene() throws RemoteException {
        return this.scene;
    }
//
//    void atualizaCenario() {
//
//    }
//
//    void ataque(Coordinate posicao) {
//
//    }
//
//    void move(Coordinate posicao, Coordinate desloc) {
//
//    }

}
