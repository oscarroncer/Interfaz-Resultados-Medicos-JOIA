package structures.pruebas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import structures.unidadesInfo.TMList;

public class TM {

    //atributos de la clase
    public ArrayList<TMList> lecturas = new ArrayList<TMList>();
    public TMList media = new TMList(null, null, null);

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Lecturas:\n");
        for (TMList lectura : lecturas) {
            sb.append(lectura.toString()).append("\n");
        }
        sb.append("Media: ").append(media.toString());
        return sb.toString();
    }
}
