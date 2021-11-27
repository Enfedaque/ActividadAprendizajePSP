package ActividadAprendizaje.Controllers;

import ActividadAprendizaje.Domain.Descargas;
import ActividadAprendizaje.Juego.juegoController;
import ActividadAprendizaje.Util.R;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class controller extends Window {
    /*
    El controller hace de in termediario en javaFx entre la interfaz grafica creada con el scene Builder
    y la propia programacion en Java
     */
    //Constructor
    public controller(){

        listaVentanas=new ArrayList<>();
    }

    private int contadorId=0;
    public TextField newD;
    //ListView donde muestro todas las descargas mandadas(no tienen porque estar descargadas)
    public ListView<Descargas> lvTotales;
    public TabPane tpDescargas;

    //Me guardo en una lista cada pestaña lanzada(cada controller)(Que aun no estan descargadas)
    public static List<VentanasController> listaVentanas;
    //Path de donde guardar las descargas
    public static String path="";

    //Metodo para activiar el boton de añadir
    @FXML
    public void descargar(Event event){
        if(newD.getText().equalsIgnoreCase("")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Debe indicar una URL a descargar");
            alert.show();
            return;
        }
        if (path.equalsIgnoreCase("")){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Configure antes la ruta de descarga");
            alert.show();
            return;
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
            VentanasController miController=new VentanasController(texto);
            loader.setLocation(R.getUI("descargas.fxml"));
            loader.setController(miController);
            VBox general=loader.load();

            String nombrePestana=texto.substring(texto.lastIndexOf("/") + 1);
            tpDescargas.getTabs().add(new Tab(nombrePestana, general));
            tpDescargas.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

            //Añado la descarga a mi lista de ventanas
            listaVentanas.add(miController);

        }catch (IOException io){
            io.printStackTrace();
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
                miController.parar();
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
            FXMLLoader loader=new FXMLLoader();
            completadasController miController=new completadasController();
            loader.setLocation(R.getUI("completadas.fxml"));
            loader.setController(miController);
            VBox general=loader.load();

            tpDescargas.getTabs().add(new Tab("Descargas COMPLETADAS", general));
            tpDescargas.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

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
            FXMLLoader loader=new FXMLLoader();
            fallidasController miController=new fallidasController();
            loader.setLocation(R.getUI("fallidas.fxml"));
            loader.setController(miController);
            VBox general=loader.load();

            tpDescargas.getTabs().add(new Tab("Descargas FALLIDAS", general));
            tpDescargas.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        }catch (IOException io){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ha ocurrido algun problema");
            alert.show();
        }
    }

    //Metodo que me permite jugar al juego que cree
    @FXML
    public void juego(Event event) throws IOException {
        try{
            FXMLLoader loader=new FXMLLoader();
            juegoController miController=new juegoController();
            loader.setLocation(R.getUI("Juego.fxml"));
            loader.setController(miController);
            VBox general=loader.load();

            tpDescargas.getTabs().add(new Tab("JUEGO", general));
            tpDescargas.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        }catch (IOException io){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ha ocurrido algun problema");
            alert.show();
        }

    }

    //Metodo que me deja configurar la ruta de descarga
    @FXML
    public void rutaDescarga(Event event){
        //Mando al usuario a ver donde quiere guardar la descarga
        try{
            //Vacio el path que pueda haber anterior
            path="";
            DirectoryChooser directoryChooser=new DirectoryChooser();
            File file = directoryChooser.showDialog(this);
            path = file.getPath ();
        }catch (NullPointerException npe){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No ha elegido ningun directorio");
            alert.show();
        }

    }

    //Metodo que tiene un listener para saber que si se cierra una pestaña se cancele el hilo de esa pestaña
    public void srfgtsdrg(Event event){
        if (tpDescargas.tabClosingPolicyProperty().equals(true)){
            //todo, aun no se muy bien como hacerlo
        }
    }


}
