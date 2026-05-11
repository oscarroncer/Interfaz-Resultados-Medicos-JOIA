package structures;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TMEye {

    enum lado {LEFT, RIGHT}

    public lado ladoOjo;
    public ArrayList<TMList> lecturas = new ArrayList<TMList>();
    public IOPValue media;


    public lado getLadoOjo() {
        return ladoOjo;
    }

    public void setLadoOjo(lado ladoOjo) {
        this.ladoOjo = ladoOjo;
    }

    public ArrayList<TMList> getLecturas(){
        return this.lecturas;
    }

    public void setLecturas(ArrayList<TMList> lecturas) {
        this.lecturas = lecturas;
    }

    public IOPValue getMedia() {
        return this.media;
    }   

    public void setMedia(IOPValue media) {
        this.media = media;
    }


    
}
