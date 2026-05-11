package structures;
public class IOPValue {

    public Double IOP_mmHg;
    public Double IOP_Pa;

    public String getIOPValueString() {
        return "IOPValue{" + "IOP_mmHg = " + IOP_mmHg + ", IOP_Pa = " + IOP_Pa + '}';
    }

    public Double getIOP_mmHg() {
        return IOP_mmHg;
    }

    public void setIOP_mmHg(Double IOP_mmHg) {
        this.IOP_mmHg = IOP_mmHg;
    }

    public Double getIOP_Pa() {
        return IOP_Pa;
    }

    public void setIOP_Pa(Double IOP_Pa) {
        this.IOP_Pa = IOP_Pa;
    }


}
