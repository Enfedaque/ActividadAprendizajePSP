package ActividadAprendizaje.Tasks;

import javafx.concurrent.Task;

public class SplashScreen extends Task<Integer> {

    private int count;

    public SplashScreen(int count) {

        this.count=count;
    }

    @Override
    protected Integer call() throws Exception {


        for(int i=count; i>=0; i--){
            Thread.sleep(1000); //Hago que duerma 1 segundo
            }

        return null;
    }
}
