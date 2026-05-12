package structures.pruebas;

import java.util.ArrayList;
import structures.unidadesInfo.TMList;

public class TM {

    //atributos de la clase
    public ArrayList<TMList> lecturas = new ArrayList<TMList>();
    public TMList media;

    // getters y setters
    public ArrayList<TMList> getLecturas() {
        return lecturas;
    }

    public void setLecturas(ArrayList<TMList> lecturas) {
        this.lecturas = lecturas;
    }

    public TMList getMedia() {
        return media;
    }

    public void setMedia(TMList media) {
        this.media = media;
    }
    
}
