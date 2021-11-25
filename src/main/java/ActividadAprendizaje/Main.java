package ActividadAprendizaje;

import ActividadAprendizaje.Controllers.controller;
import ActividadAprendizaje.Util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*Hereda de la clase aplication y sus metodos (Ej: start(Stage stage),...*/
public class Main extends Application {
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Me creo el controller para la interfaz y emparejo para que sea visible
        controller miController=new controller();
        FXMLLoader lector=new FXMLLoader();
        lector.setLocation(R.getUI("interfaz.fxml"));
        lector.setController(miController);
        VBox vbox=lector.load();
        Scene scene=new Scene(vbox);
        stage.setScene(scene);
        stage.show();

    }

    //Metodo para cerar la app
    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

