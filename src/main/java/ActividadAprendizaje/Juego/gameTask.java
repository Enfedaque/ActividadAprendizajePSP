package ActividadAprendizaje.Juego;

import javafx.concurrent.Task;

public class gameTask extends Task<Integer> {

    private int count;

    public gameTask(int count){

        this.count=count;
    }


    @Override
    protected Integer call() throws Exception {
        for(int i=count; i>=0; i--){
            Thread.sleep(1000); //Hago que duerma 1 segundo
            updateValue(i); //Hago que actualice el mesaje que muestro en el controller
            // Le digo a la barra que se pare cuando ya haya acabado la tarea
            updateProgress(i, count); //El primer numero indica donde empiezo el segundo hasta donde voy
        }

        return null;
    }



}
