package structures.POJOS;

import structures.pruebas.*;

public class Eye {

    enum lado {LEFT, RIGHT}

    public lado ladoOjo;

    //declaramos las pruebas y iremos llenando los atributos segun hayan pruebas en el XML
    public TM TM;
    public CorrectedIOP correctedIOP;
    
    // Getters y Setters

    public lado getLadoOjo() {
        return ladoOjo;
    }

    public void setLadoOjo(lado ladoOjo) {
        this.ladoOjo = ladoOjo;
    }

    public TM getTM() {
        return TM;
    }

    public void setTM(TM TM) {
        this.TM = TM;
    }

    public CorrectedIOP getCorrectedIOP() {
        return correctedIOP;
    }

    public void setCorrectedIOP(CorrectedIOP correctedIOP) {
        this.correctedIOP = correctedIOP;
    }
    
}
