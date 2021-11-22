package ActividadAprendizaje;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import javax.swing.text.html.ListView;
import java.util.ArrayList;

public class controller {
    /*
    El controller hace de in termediario en javaFx entre la interfaz grafica creada con el scene Builder
    y la propia programacion en Java
     */

    //Constructor
    public controller(){

    }

    //TextField
    public TextField newD;

    //Metodo para activiar el boton de descargar
    public void descargar(Event event){
        String texto= newD.getText().toString();

    }
}
