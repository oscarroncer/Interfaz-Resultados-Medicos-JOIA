package structures.unidadesInfo;

public class IOPValue {

    public Double IOP_mmHg;
    public Double IOP_Pa;
    
    @Override
    public String toString() {
        return "IOPValue{" + 
        "IOP_mmHg = " + IOP_mmHg + 
        ", IOP_Pa = " + IOP_Pa + '}';
    }
}
