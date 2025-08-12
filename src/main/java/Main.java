import javax.sound.midi.SysexMessage;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<String, Integer> mapaConcurrente = new ConcurrentHashMap<>();
        mapaConcurrente.put("contador",0);



        /*
        es necesario que sea runnable para que la tarea se ejecute dentro del metodo run
        que es lo que hará el hilo cuando se inicie con start()
         */
        Runnable tarea = new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=1000; i++){
                    mapaConcurrente.compute("contador", (key, valorActual) -> valorActual +1);
                }
            }
        };
        Thread hilo1 = new Thread(tarea);
        Thread hilo2 = new Thread(tarea);
        Thread hilo3 = new Thread(tarea);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        //sirve para que se espere hasta que terminen los hilos para ejecutar la siguiente parte del programa
        hilo1.join();
        hilo2.join();
        hilo3.join();

        System.out.println("Contador total: " + mapaConcurrente.get("contador"));

    }








}
