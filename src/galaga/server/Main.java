package galaga.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Main {
    int pontuacao;
    int vidas;
    int potenciaTiro = 1;
    int speed = 5;//tem que ser mais r�pido que o mais r�pido inimigo (3)
    Coordinate posicao;

    public static void main(String[] args) throws Exception {

//        String host = (args.length < 1) ? null : args[0];
        String host = (args.length < 1) ? "0" : args[0];
        if (host == null) {
            System.err.println("Host is invalid!\n");
            System.exit(1);
        }

        Registry registry = LocateRegistry.getRegistry(host);
        InterfaceRMI stub = (InterfaceRMI) registry.lookup("InterfaceRMI");

        ArrayList<Coordinate> listaPosicao = stub.atualizaCenario();

        for (int i = 0; i < listaPosicao.size(); i++) {
            System.out.println("posi��o " + i + ": [" + listaPosicao.get(i).x + ", " + listaPosicao.get(i).y + "]");
        }


    }

    void atualizaCenario() {

    }

    void ataque(Coordinate posicao) {

    }

    void move(Coordinate posicao, Coordinate desloc) {

    }


}
