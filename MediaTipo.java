package backend;

public class MediaTipo {

    private String tipo;
    private double media;

    public MediaTipo(String tipo, double media) {
        this.tipo = tipo;
        this.media = media;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMedia() {
        return media;
    }
}
