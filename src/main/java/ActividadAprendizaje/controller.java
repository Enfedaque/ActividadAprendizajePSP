package ActividadAprendizaje;

import ActividadAprendizaje.Domain.Descargas;
import ActividadAprendizaje.Util.R;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;



public class controller{
    private int contadorId=0;
    /*
    El controller hace de in termediario en javaFx entre la interfaz grafica creada con el scene Builder
    y la propia programacion en Java
     */
    //Constructor
    public controller(){
    }

    public TextField newD;
    public ListView<Descargas> lvTotales;

    //Metodo para activiar el boton de añadir
    public void descargar(Event event){
        try{
            String texto= newD.getText();
            Descargas miDescarga=new Descargas(contadorId, texto);
            contadorId++;
            lvTotales.getItems().add(miDescarga);

        }catch (Exception ex){
            ex.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ha ocurrido algun problema");
            alert.show();
        }

    }

    //Metodo para activiar el boton de descargas completadas
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
