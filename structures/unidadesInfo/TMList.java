package structures.unidadesInfo;
public class TMList {

    public String IOP_mmHg = new String();
    public String IOP_Pa = new String();
    public String ConfidenceIndex = new String();

    //Constructor
    public TMList(String IOP_mmHg, String IOP_Pa, String ConfidenceIndex)
    {
        this.IOP_mmHg = IOP_mmHg;
        this.IOP_Pa = IOP_Pa;
        this.ConfidenceIndex = ConfidenceIndex;
    }

    @Override
    public String toString() {
        return "TMList{" + "IOP_mmHg = " + IOP_mmHg + ", IOP_Pa = " + IOP_Pa + ", ConfidenceIndex = " + ConfidenceIndex + '}';
    }

}
