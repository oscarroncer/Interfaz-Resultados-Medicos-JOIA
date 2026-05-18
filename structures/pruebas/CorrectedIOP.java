package structures.pruebas;

import structures.unidadesInfo.IOPValue;

public class CorrectedIOP {

    public String Param1 = new String();
    public String Param2 = new String();   
    public String CCT = new String();
    public IOPValue measured = new IOPValue(null, null);
    public IOPValue corrected = new IOPValue(null, null);

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