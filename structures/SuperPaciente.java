package structures;

import structures.POJOS.Common;
import structures.POJOS.Eye;
import structures.POJOS.Patient;

public class SuperPaciente {

    // atributos 
    public Common common = new Common();
    public Patient patient = new Patient();
    public Eye leftEye = new Eye();
    public Eye rightEye = new Eye();

    // Getters and Setters

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Eye getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(Eye leftEye) {
        this.leftEye = leftEye;
    }

    public Eye getRightEye() {
        return rightEye;
    }

    public void setRightEye(Eye rightEye) {
        this.rightEye = rightEye;
    }
    
}
