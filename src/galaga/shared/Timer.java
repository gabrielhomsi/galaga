package galaga.shared;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Werneck-01-ADM on 15/10/2015.
 */
public class Timer {

    private long timeI = 0;
    private long timeF = 0;
    //private long currentDate;

    public static String getCurrentDate() {
        //Padrão
        //http://docs.oracle.com/javase/1.5.0/docs/api/java/text/SimpleDateFormat.html
        return new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss:SS").format(new Date(System
                .currentTimeMillis()));
    }

    public void timerStart() {
        timeI = System.currentTimeMillis();
    }

    public long timerStop() {
        timeF = System.currentTimeMillis();
        return (timeF - timeI);
    }

    public long getTimeTemporized(){
        //pega o tempo atual e subtrai com o tempo inicial startado no timerStart()
        return (timerStop() - timeI)/1000;//returna tempo em segundos
    }




}
