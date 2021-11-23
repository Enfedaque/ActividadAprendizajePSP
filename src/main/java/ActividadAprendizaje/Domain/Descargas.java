package ActividadAprendizaje.Domain;

import java.util.Objects;

public class Descargas {

    private int IdDescarga;
    private String url;

    public Descargas(int idDescarga, String URL) {
        this.IdDescarga = idDescarga;
        this.url = URL;
    }

    public int getIdDescarga() {
        return IdDescarga;
    }

    public void setIdDescarga(int idDescarga) {
        IdDescarga = idDescarga;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Descargas descargas = (Descargas) o;
        return Objects.equals(IdDescarga, descargas.IdDescarga) &&
                Objects.equals(url, descargas.url);
    }

    @Override
    public String toString() {
        return IdDescarga + "    /    " + url;
    }
}
