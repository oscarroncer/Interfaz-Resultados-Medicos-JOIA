package structures;
import java.time.*;

public class Patient {
    
    // atributos de la clase
    public String No;
    public String ID;
    public String FirstName;
    public String MiddleName;
    public String LastName;
    public String Sex;
    public int Age;
    public LocalDate DOB;

    //-------------------------------------------- GETTERS Y SETTERS -----------------------------------------------
    
    public Patient getPatient() {
        System.out.println("Patient: " + this.No + " " + this.ID + " " + this.FirstName + " " + this.MiddleName + " " + this.LastName + " " + this.Sex);
        return this;
    }

    // No
    public String getNo() {
        return this.No;
    }

    public void setNo(String no) {
        this.No = no;
    }

    // ID
    public String getID() {
        return this.ID;
    }

    public void setID(String id) {
        this.ID = id;
    }

    // FirstName
    public String getFirstName() {
        return this.FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    // MiddleName
    public String getMiddleName() {
        return this.MiddleName;
    }

    public void setMiddleName(String middleName) {
        this.MiddleName = middleName;
    }

    // LastName
    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    // Sex
    public String getSex() {
        return this.Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }

    // Age
    public int getAge() {
        return this.Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    // DOB
    public LocalDate getDOB() {
        return this.DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }
}