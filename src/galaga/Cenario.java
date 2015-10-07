package galaga;

//import java.util.Arrays;

import java.util.ArrayList;
import java.util.Random;

public class Cenario {

    ArrayList<Inimigo> inimigos = new ArrayList<>();
    int nInimigos;

    ArrayList<Inimigo> geraInimigos(int n) {
        ArrayList<Inimigo> listaInimigos = new ArrayList<>();
        Random aleatorio = new Random();
        for (int i = 0; i < n; i++) {
            switch (aleatorio.nextInt() % 4) {
                case 0: {
                    listaInimigos.add(new InimigoTipo1());
                    break;
                }
                case 1: {
                    listaInimigos.add(new InimigoTipo2());
                    break;
                }
                case 2: {
                    listaInimigos.add(new InimigoTipo3());
                    break;
                }
                case 3: {
                    listaInimigos.add(new InimigoTipo4());
                    break;
                }
                default: {
                    break;
                }
            }
        }

        return listaInimigos;
    }

}
