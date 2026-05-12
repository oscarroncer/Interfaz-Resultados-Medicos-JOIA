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

    public Common getCommon() {
        System.out.println("Common: " + this.Company + " " + this.ModelName + " " + this.MachineNo + " " + this.ROMVersion + " " + this.Version + " " + this.Date + " " + this.Time);
        return this;
    }
    // Company
    public String getCompany() {
        return this.Company;
    }

    public void setCompany(String company) {
        this.Company = company;
    }

    // ModelName
    public String getModelName() {
        return this.ModelName;
    }

    public void setModelName(String modelName) {
        this.ModelName = modelName;
    }

    // MachineNo
    public String getMachineNo() {
        return this.MachineNo;
    }

    public void setMachineNo(String machineNo) {
        this.MachineNo = machineNo;
    }

    // ROMVersion
    public String getROMVersion() {
        return this.ROMVersion;
    }

    public void setROMVersion(String ROMVersion) {
        this.ROMVersion = ROMVersion;
    }

    // Version
    public String getVersion() {
        return this.Version;
    }

    public void setVersion(String version) {
        this.Version = version;
    }

    // Date
    public String getDate() {
        return this.Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    // Time
    public String getTime() {
        return this.Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }
}