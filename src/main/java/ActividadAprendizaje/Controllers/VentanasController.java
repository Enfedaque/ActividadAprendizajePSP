package ActividadAprendizaje.Controllers;

import ActividadAprendizaje.Tasks.Ventanas;
import com.sun.javafx.logging.Logger;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.LogManager;

public class VentanasController implements Initializable {

    public Label labelUrl;
    public ProgressBar barraProgreso;
    public Label textoProgreso;
    private Ventanas miVentana;
    public String urlText;

    /*
    Aqui controlare cada panel de descarga, con sus metodos, iniciar, parar, ...
     */

    public VentanasController(String urlText) {
        this.urlText=urlText;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelUrl.setText(urlText);
    }

    //Metodo para iniciar la descarga al darle al boton iniciar
    @FXML
    public void iniciar(Event event) throws MalformedURLException {
        try {
            //Mando al usuario a ver donde quiere guardar la descarga
            FileChooser miFileChooser = new FileChooser();
            File miFile = miFileChooser.showSaveDialog(labelUrl.getScene().getWindow());
            if (miFile == null) {
                return;
            }

            miVentana = new Ventanas(labelUrl.getText(), miFile);

            barraProgreso.progressProperty().unbind();
            barraProgreso.progressProperty().bind(miVentana.progressProperty());

            miVentana.stateProperty().addListener((observableValue, oldState, newState) -> {
                System.out.println(observableValue.toString());
                if (newState == Worker.State.SUCCEEDED) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("La descarga ha terminado");
                    alert.show();
                }
            });

            miVentana.messageProperty().addListener((observableValue, oldValue, newValue) ->
                    textoProgreso.setText(newValue));

            new Thread(miVentana).start();
        }catch (MalformedURLException me){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Algo ha salido mal");
            alert.show();
        }
    }

    //Metodo para parar la descarga
    @FXML
    public void parar() {

        if (miVentana != null)
            miVentana.cancel();
    }

}
