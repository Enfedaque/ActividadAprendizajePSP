package ActividadAprendizaje;

import ActividadAprendizaje.Controllers.controller;
import ActividadAprendizaje.Tasks.SplashScreen;
import ActividadAprendizaje.Util.R;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/*Hereda de la clase aplication y sus metodos (Ej: start(Stage stage),...*/
public class Main extends Application {
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        mostrarInicio(stage);

        //Llamo al hilo que me ba a hacer que se este mostrando duranrte 7 segundos
        SplashScreen miSplashScreen=new SplashScreen(7);
        new Thread(miSplashScreen).start();
        //Hago un listener para que cuando haya terminado me salte al metodo que me muestra la
        // interfaz
        miSplashScreen.stateProperty().addListener((ObservableValue, olsState, newState) -> {
            if(newState == Worker.State.SUCCEEDED){
                try {
                    mostrarInterfaz(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //Metodo para cerar la app
    @Override
    public void stop() throws Exception {
        super.stop();
    }

    //Metodo para mostrar el SplashScreen
    public void mostrarInicio(Stage stage) throws IOException {
        ImageView fondo=new ImageView("UI/logo2PSP.PNG");
        AnchorPane root=new AnchorPane();
        root.getChildren().addAll(fondo);
        Scene scene=new Scene(root);
        stage.setTitle("BIENVENIDO");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    //Metodo para mostar el interfaz
    public void mostrarInterfaz(Stage stage) throws IOException{
        controller miController=new controller();
        FXMLLoader lector=new FXMLLoader();
        lector.setLocation(R.getUI("interfaz.fxml"));
        lector.setController(miController);
        VBox vbox=lector.load();
        Scene scene=new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

}

