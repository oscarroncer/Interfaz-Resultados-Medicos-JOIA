package structures.POJOS;
import java.time.*;

public class Patient {
    
    // atributos de la clase
    public String No;
    public String ID;
    public String FirstName;
    public String MiddleName;
    public String LastName;
    public String Sex;
    public Integer Age;
    public LocalDate DOB;

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