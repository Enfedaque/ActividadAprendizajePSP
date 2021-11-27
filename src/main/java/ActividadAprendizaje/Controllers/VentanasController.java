package ActividadAprendizaje.Controllers;

import ActividadAprendizaje.Tasks.Ventanas;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VentanasController implements Initializable {

    public Label labelUrl;
    public ProgressBar barraProgreso;
    public Label textoProgreso;
    private Ventanas miVentana;
    public String urlText;

    public VentanasController(String urlText) {
        this.urlText=urlText;
    }
    //Constructor vacio
    public VentanasController(){}

    //Listas donde voy a guardar las descargas completadas
    public static List<String> listaDescargasCompletadas;
    //Lista donde voy a guardar las descargas canceladas
    public static List<String> listaDescargasCanceladas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelUrl.setText(urlText);
    }

    //Metodo para iniciar la descarga al darle al boton iniciar
    @FXML
    public void iniciar(Event event) throws MalformedURLException {
        try {
            //Mando al usuario a ver donde quiere guardar la descarga
            //TODO, solo si quiero que pueda seleccionarlo cada vez
            /*FileChooser miFileChooser = new FileChooser();
            File miFile = miFileChooser.showSaveDialog(labelUrl.getScene().getWindow());
            if (miFile == null) {
                return;
            }*/

            //Lo guardo en la direccion de path que le he indicado en la configuracion del boton en
            // en controller*/
            miVentana = new Ventanas(labelUrl.getText(), new File(controller.path));

            barraProgreso.progressProperty().unbind();
            barraProgreso.progressProperty().bind(miVentana.progressProperty());

            miVentana.stateProperty().addListener((observableValue, oldState, newState) -> {
                System.out.println(observableValue.toString());
                if (newState == Worker.State.SUCCEEDED) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("La descarga ha terminado");
                    alert.show();
                    //Como se ha descargado bien la añado a la lista de completadas
                    //TODO , me da fallo
                    completadasController.listaCompletadas.getItems().add(miVentana.toString());
                    //Lo añado tambien a mi lista
                    listaDescargasCompletadas.add(miVentana.toString());
                }else {
                    //Como no se ha descargado la añado a la lista de canceladas
                    //TODO , me da fallo
                    fallidasController.listaFallidas.getItems().add(miVentana.toString());
                    //Lo añado tambien a mi lista
                    listaDescargasCanceladas.add(miVentana.toString());
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

    @Override
    public String toString() {
        return "Url= " + labelUrl;
    }
}
