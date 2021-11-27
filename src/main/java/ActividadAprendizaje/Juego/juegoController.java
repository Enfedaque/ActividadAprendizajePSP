package ActividadAprendizaje.Juego;


import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class juegoController {

    public ProgressBar progressbar;
    public Label label1;
    public Label label2;
    public Label label3;
    private gameTask gameTask;
    private List<gameTask> lista;

    Random r = new Random();

    public juegoController(){
        lista=new ArrayList<>();
    }

    //Metodo iniciar del boton
    @FXML //Sirve para decirle a javaFx que su interfaz se encargue de buscar
    public void iniciar(Event event){ //Parametro Event == View view en Android
        //Cancelo los hilos que se hayan podido quedar en marcha
        for (gameTask hilos : lista){
            hilos.cancel();
        }
        label2.setText("");
        mostrar="";
        int count=15;

        gameTask=new gameTask(count);
        //Añado mi nuevo hilo a la lista
        lista.add(gameTask);

        //Creo un listener que este atento a los cambios con un LAMDA
        gameTask.stateProperty().addListener((ObservableValue, olsState, newState) -> {
            if(newState == Worker.State.SUCCEEDED){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Lo siento, el tiempo ha terminado");
                alert.show();
            }
        });

        //Me creo aleatoriamente el numero a representar
        int numRepresentar=r.nextInt(766586797)+1;
        label1.setText(String.valueOf(numRepresentar));

        /*Aqui vamos a relacionar nuestra tarea con la barra de progreso*/
        //Primer desligo la barra de progreso de cualquier otra tarea por si la he usado en
        // otro hilo o alguna cosa con el UNBIND()*/
        progressbar.progressProperty().unbind(); //"Como si refrescaramos la barra"
        //Ahora le indico con que tarea va a estar  ligada mediante el BIND()
        progressbar.progressProperty().bind(gameTask.progressProperty());
        //Hago que se muestre el avance
        gameTask.valueProperty().addListener((ObservableValue, oldValue, newValue) -> {
            label3.setText(newValue.toString());
        });

        new Thread(gameTask).start();
    }


    @FXML
    public void corregir(){
        if (mostrar.equalsIgnoreCase(label1.getText())){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Objetivo cumplido!!!");
            alert.show();
            gameTask.cancel();
        }else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No es correcto..continua que el tiempo corre...");
            alert.show();
        }
    }

    @FXML
    public void borrar(){
        label2.setText("");
        mostrar="";
    }


    String mostrar=" ";
    public void marcar1(){
        label2.setText(mostrar + "1");
        mostrar=mostrar+ "1";

    }
    public void marcar2(){
        label2.setText(mostrar + "2");
        mostrar=mostrar+ "2";

    }
    public void marcar3(){
        label2.setText(mostrar + "3");
        mostrar=mostrar+ "3";

    }
    public void marcar4(){
        label2.setText(mostrar + "4");
        mostrar=mostrar+ "4";

    }
    public void marcar5(){
        label2.setText(mostrar + "5");
        mostrar=mostrar+ "5";

    }
    public void marcar6(){
        label2.setText(mostrar + "6");
        mostrar=mostrar+ "6";

    }
    public void marcar7(){
        label2.setText(mostrar + "7");
        mostrar=mostrar+ "7";

    }
    public void marcar8(){
        label2.setText(mostrar + "8");
        mostrar=mostrar+ "8";

    }
    public void marcar9(){
        label2.setText(mostrar + "9");
        mostrar=mostrar+ "9";

    }
    public void marcar0(){
        label2.setText(mostrar + "0");
        mostrar=mostrar+ "0";

    }



}
