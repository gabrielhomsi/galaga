package galaga;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Jogador {
    int pontuacao;
    int vidas;
    int potenciaTiro = 1;
    Coordenada posicao;

    public static void main(String[] args) throws Exception {

//        String host = (args.length < 1) ? null : args[0];
        String host = (args.length < 1) ? "0" : args[0];
        if (host == null) {
            System.err.println("Host is invalid!\n");
            System.exit(1);
        }

        Registry registry = LocateRegistry.getRegistry(host);
        InterfaceRMI stub = (InterfaceRMI) registry.lookup("InterfaceRMI");

        ArrayList<Coordenada> listaPosicao = stub.atualizaCenario();

        for (int i = 0; i < listaPosicao.size(); i++) {
            System.out.println("posição " + i + ": [" + listaPosicao.get(i).x + ", " + listaPosicao.get(i).y + "]");
        }


    }

    void atualizaCenario() {

    }

    void ataque(Coordenada posicao) {

    }

    void move(Coordenada posicao, Coordenada desloc) {

    }


}
