package structures.pruebas;

import structures.unidadesInfo.IOPValue;

public class CorrectedIOP {

    public String Param1;
    public String Param2;   
    public String CCT;
    public IOPValue measured;
    public IOPValue corrected;

    // Getters and Setters

    public String getParam1() {
        return Param1;
    }

    public void setParam1(String Param1) {
        this.Param1 = Param1;
    }

    public String getParam2() {
        return Param2;
    }

    public void setParam2(String Param2) {
        this.Param2 = Param2;
    }

    public String getCCT() {
        return CCT;
    }

    public void setCCT(String CCT) {
        this.CCT = CCT;
    }

    public IOPValue getMeasured(){
        return this.measured;
    }

    public void setMeasured(IOPValue measured) {
        this.measured = measured;
    }

    public IOPValue getCorrected(){
        return this.corrected;
    }

    public void setCorrected(IOPValue corrected) {
        this.corrected = corrected;
    }
}