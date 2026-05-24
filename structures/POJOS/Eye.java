package structures.POJOS;

import structures.pruebas.*;

public class Eye {

    //declaramos las pruebas y iremos llenando los atributos segun hayan pruebas en el XML
    public TM TM = new TM();
    public CorrectedIOP correctedIOP = new CorrectedIOP();

    @Override
    public String toString()
    {
        return "TM: " + TM.toString() + "\n" +
               "CorrectedIOP: " + correctedIOP.toString() + "\n";
    }
    

}
