package structures.POJOS;

public class Common {

    // atributos de la clase
    public String Company = new String();
    public String ModelName = new String();
    public String MachineNo = new String();
    public String ROMVersion = new String();
    public String Version = new String();
    public String Date = new String();
    public String Time = new String();

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