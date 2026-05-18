package structures.unidadesInfo;

public class IOPValue {

    public String IOP_mmHg;
    public String IOP_Pa;
    
    public IOPValue (String IOP_mmHg, String IOP_Pa)
    {
        this.IOP_mmHg = IOP_mmHg;
        this.IOP_Pa = IOP_Pa;
    }

    
    @Override
    public String toString() {
        return "IOPValue{" + 
        "IOP_mmHg = " + IOP_mmHg + 
        ", IOP_Pa = " + IOP_Pa + '}';
    }
}
