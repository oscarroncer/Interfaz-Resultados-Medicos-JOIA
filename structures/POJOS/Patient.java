package structures.POJOS;

public class Patient {
    
    // atributos de la clase
    public String No = new String();
    public String ID= new String();
    public String FirstName = new String();
    public String MiddleName = new String();
    public String LastName = new String();
    public String Sex = new String();
    public String Age = new String();
    public String DOB = new String();

    //-------------------------------------------- GETTERS Y SETTERS -----------------------------------------------
    
    @Override
    public String toString() {
        return "Patient{" +
                "No='" + No + '\'' +
                ", ID='" + ID + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", MiddleName='" + MiddleName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Age=" + Age +
                ", DOB=" + DOB +
                '}';
    }

}