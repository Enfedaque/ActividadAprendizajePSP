package ActividadAprendizaje.Tasks;

import ActividadAprendizaje.Controllers.VentanasController;
import javafx.concurrent.Task;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Ventanas extends Task<Integer> {

    private URL url;
    private File file;

    public Ventanas(String urlText, File file) throws MalformedURLException {
        this.url = new URL(urlText);
        this.file = file;
    }

    @Override
    protected Integer call() throws Exception {

        updateMessage("Conectando con el servidor . . .");

        URLConnection urlConnection = url.openConnection();
        double fileSize = urlConnection.getContentLength();
        BufferedInputStream in = new BufferedInputStream(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte dataBuffer[] = new byte[1024];
        int bytesRead;
        int totalRead = 0;
        double downloadProgress = 0;

        while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
            downloadProgress = ((double) totalRead / fileSize);
            updateProgress(downloadProgress, 1);
            updateMessage(downloadProgress * 100 + " %");

            fileOutputStream.write(dataBuffer, 0, bytesRead);
            totalRead += bytesRead;

            if (isCancelled()) {

                return null;
            }
        }

        updateProgress(1, 1);
        updateMessage("100 %");

        return null;
    }

    @Override
    public String toString() {
        return url + " / " + file;
    }
}
