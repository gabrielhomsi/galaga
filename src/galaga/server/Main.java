package galaga.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
/*
    Antes de executar o servidor, rode o seguinte comando:
    rmiregistry -J-Djava.rmi.server.codebase=file:/home/gabriel/galaga/out/production/galaga/
    Adapte o caminho para o equivalente na sua maquina
 */

public class Main implements RemoteInterface {
    int pontuacao;
    int vidas;
    int potenciaTiro = 1;
    int speed = 5;//tem que ser mais r�pido que o mais r�pido inimigo (3)
    Coordinate posicao;

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
        return "Hello, Galaga!";
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
