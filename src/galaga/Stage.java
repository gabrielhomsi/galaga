package galaga;

//import java.util.Arrays;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class Stage implements InterfaceRMI {

    static ArrayList<Player> players = new ArrayList<Player>();
    static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    int nInimigos;//Numero total de enemies
    int nLinhas;//Numero de linhas de enemies
    int nPlayers;//numero de players conectados

    public Stage() {

    }

    public static void main(String[] args) throws Exception {
        Stage cena = new Stage();
        InterfaceRMI stub = (InterfaceRMI) UnicastRemoteObject.exportObject(cena, 0);
        Registry registry = LocateRegistry.getRegistry();
        registry.bind("InterfaceRMI", stub);
        System.out.println("Stage Ready!");
    }

    public static int getDistanceBetween2Points(Coordinate pointA, Coordinate pointB) {
        int distance;
        distance = (int) Math.hypot((pointA.x - pointB.x), (pointA.y - pointB.y));
        return distance;
    }

    public static int getClosestPlayer(Coordinate point) {
        int closestPlayer = 0;//jogador mais próximo
        int distance = 2000000000;//Distancia setada para o "infinito"
        int aux;
        for (int i = 0; i < players.size(); i++) {
            aux = getDistanceBetween2Points(point, players.get(i).posicao);
            if (aux < distance) {
                distance = aux;
                closestPlayer = i;
            }
        }
        return closestPlayer;
    }

    @Override
    public ArrayList<Coordinate> atualizaCenario() throws RemoteException {
        //Enviar o Objeto tod o ou somente a posição?
        ArrayList<Coordinate> listaPosicao = new ArrayList<Coordinate>();
        for (Player player : players) {
            listaPosicao.add(player.posicao);
        }
//        for (int i = 0; i < players.size(); i++) {
//            listaPosicao.add(players.get(i).position);
//        }

        for (Enemy enemy : enemies) {
            listaPosicao.add(enemy.position);
        }
//        for (int i = 0; i < enemies.size(); i++) {
//            listaPosicao.add(enemies.get(i).position);
//        }

        return listaPosicao;
    }

    ArrayList<Enemy> geraInimigos(int n) {
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
        Random aleatorio = new Random();
        for (int i = 0; i < n; i++) {
            switch (aleatorio.nextInt() % 4) {
                case 0: {
                    System.out.println("1");
                    enemyList.add(new EnemyType1());
                    break;
                }
                case 1: {
                    System.out.println("2");
                    enemyList.add(new EnemyType2());
                    break;
                }
                case 2: {
                    System.out.println("3");
                    enemyList.add(new EnemyType3());
                    break;
                }
                case 3: {
                    System.out.println("4");
                    enemyList.add(new EnemyType4());
                    break;
                }
                default: {
                    break;
                }
            }
        }

        enemies = enemyList;
        return enemyList;
    }

}
