package ActividadAprendizaje;

import ActividadAprendizaje.Controllers.VentanasController;
import ActividadAprendizaje.Domain.Descargas;
import ActividadAprendizaje.Threads.Ventanas;
import ActividadAprendizaje.Util.R;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class controller{
    private int contadorId=0;
    /*
    El controller hace de in termediario en javaFx entre la interfaz grafica creada con el scene Builder
    y la propia programacion en Java
     */
    //Constructor
    public controller(){
        listaVentanas=new ArrayList<>();
    }

    public TextField newD;
    public ListView<Descargas> lvTotales;
    public TabPane tpDescargas;
    public Button btStopAll;
    //Me guardo en una lista cada descarga
    private List<VentanasController> listaVentanas;

    //Metodo para activiar el boton de añadir
    @FXML
    public void descargar(Event event){
        while(newD.getText().equalsIgnoreCase("")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Debe indicar una URL a descargar");
            alert.show();
        }
        try{
            //Aqui lo añado a la ListView
            String texto= newD.getText();
            Descargas miDescarga=new Descargas(contadorId, texto);
            contadorId++;
            lvTotales.getItems().add(miDescarga);
            newD.clear();
            newD.requestFocus();

            //Aqui hago que se cree una pestaña del tabpane cada vez
            FXMLLoader loader=new FXMLLoader();
            VentanasController miController=new VentanasController();
            loader.setLocation(R.getUI("descargas.fxml"));
            loader.setController(miController);
            VBox general=loader.load();
            tpDescargas.getTabs().add(new Tab("Descarga", general));

            //Añado la descarga a mi lista de venatnas
            listaVentanas.add(miController);

        }catch (Exception ex){
            ex.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ha ocurrido algun problema");
            alert.show();
        }

    }

    //Metodo que me anula todas las descargas
    @FXML
    public void StopAll(Event event){
        try{
            for (VentanasController miController : listaVentanas){
                miController.stop();
            }
        }catch (Exception ex){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ha ocurrido algun problema");
            alert.show();
        }

    }


    //Metodo para activiar el boton de descargas completadas
    @FXML
    public void btCompletadas(Event event) throws IOException {
        //Mando a la nueva ventana
        try{
            controller miController=new controller();
            FXMLLoader lector=new FXMLLoader();
            lector.setLocation(R.getUI("completadas.fxml"));
            lector.setController(miController);
            VBox vbox=lector.load();
            Scene scene=new Scene(vbox);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (IOException io){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ha ocurrido algun problema");
            alert.show();
        }
    }

    //Metodo para activiar el boton de descargas totales
    @FXML
    public void btTotales(Event event) throws IOException {
        try{
            controller miController=new controller();
            FXMLLoader lector=new FXMLLoader();
            lector.setLocation(R.getUI("totales.fxml"));
            lector.setController(miController);
            VBox vbox=lector.load();
            Scene scene=new Scene(vbox);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();



        }catch (IOException io){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ha ocurrido algun problema");
            alert.show();
        }
    }

    //Metodo para activiar el boton de descargas fallidas
    @FXML
    public void btFallidas(Event event) throws IOException {
        try{
            controller miController=new controller();
            FXMLLoader lector=new FXMLLoader();
            lector.setLocation(R.getUI("fallidas.fxml"));
            lector.setController(miController);
            VBox vbox=lector.load();
            Scene scene=new Scene(vbox);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (IOException io){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ha ocurrido algun problema");
            alert.show();
        }
    }

    @FXML
    public void btHome(Event event){
        try{
            controller miController=new controller();
            FXMLLoader lector=new FXMLLoader();
            lector.setLocation(R.getUI("interfaz.fxml"));
            lector.setController(miController);
            VBox vbox=lector.load();
            Scene scene=new Scene(vbox);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (IOException io){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ha ocurrido algun problema");
            alert.show();
        }
    }

}
