package structures.pruebas;

import structures.unidadesInfo.IOPValue;

public class CorrectedIOP {

    public String Param1;
    public String Param2;   
    public String CCT;
    public IOPValue measured;
    public IOPValue corrected;

    @Override
    public String toString(){
        
        String CorrectedIOPListado = "Common{" +
                "Param1 = '" + Param1 + '\'' +
                ", Param2 = '" + Param2 + '\'' +
                ", CCT = '" + CCT + '\'' +
                ", measured = '" + measured.toString() + '\'' +
                ", corrected = '" + corrected.toString() + '\'' +
                '}';
    
        
        return CorrectedIOPListado;
    }

}