package galaga;

//import java.util.Arrays;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class Cenario implements InterfaceRMI {

    ArrayList<Jogador> jogadores = new ArrayList<>();
    ArrayList<Inimigo> inimigos = new ArrayList<>();
    int nInimigos;

    public Cenario() {

    }

    public static void main(String[] args) throws Exception {
        Cenario cena = new Cenario();
        InterfaceRMI stub = (InterfaceRMI) UnicastRemoteObject.exportObject(cena, 0);
        Registry registry = LocateRegistry.getRegistry();
        registry.bind("InterfaceRMI", stub);
        System.out.println("Cenario Ready!");
    }

    @Override
    public ArrayList<Coordenada> atualizaCenario() throws RemoteException {
        //Enviar o Objeto tod o ou somente a posição?
        ArrayList<Coordenada> listaPosicao = new ArrayList<>();
        for (Jogador jogador : jogadores) {
            listaPosicao.add(jogador.posicao);
        }
//        for (int i = 0; i < jogadores.size(); i++) {
//            listaPosicao.add(jogadores.get(i).posicao);
//        }

        for (Inimigo inimigo : inimigos) {
            listaPosicao.add(inimigo.posicao);
        }
//        for (int i = 0; i < inimigos.size(); i++) {
//            listaPosicao.add(inimigos.get(i).posicao);
//        }

        return listaPosicao;
    }

    ArrayList<Inimigo> geraInimigos(int n) {
        ArrayList<Inimigo> listaInimigos = new ArrayList<>();
        Random aleatorio = new Random();
        for (int i = 0; i < n; i++) {
            switch (aleatorio.nextInt() % 4) {
                case 0: {
                    System.out.println("1");
                    listaInimigos.add(new InimigoTipo1());
                    break;
                }
                case 1: {
                    System.out.println("2");
                    listaInimigos.add(new InimigoTipo2());
                    break;
                }
                case 2: {
                    System.out.println("3");
                    listaInimigos.add(new InimigoTipo3());
                    break;
                }
                case 3: {
                    System.out.println("4");
                    listaInimigos.add(new InimigoTipo4());
                    break;
                }
                default: {
                    break;
                }
            }
        }

        inimigos = listaInimigos;
        return listaInimigos;
    }

}
