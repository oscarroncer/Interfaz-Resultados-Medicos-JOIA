package structures.POJOS;

public class Common {

    // atributos de la clase
    public String Company;
    public String ModelName;
    public String MachineNo;
    public String ROMVersion;
    public String Version;
    public String Date;
    public String Time;

    //-------------------------------------------- GETTERS Y SETTERS -----------------------------------------------

    @Override
    public String toString() {
        return "Common{" +
                "Company='" + Company + '\'' +
                ", ModelName='" + ModelName + '\'' +
                ", MachineNo='" + MachineNo + '\'' +
                ", ROMVersion='" + ROMVersion + '\'' +
                ", Version='" + Version + '\'' +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
   }