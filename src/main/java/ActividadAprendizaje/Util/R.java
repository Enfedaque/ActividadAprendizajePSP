package ActividadAprendizaje.Util;

import java.io.File;
import java.net.URL;

public class R {
    /*
    Clase para pasar la URL del archivo FXML
     */
    public static URL getUI(String name){
        return Thread.currentThread().getContextClassLoader()
                .getResource("UI" + File.separator + name);
    }
}
