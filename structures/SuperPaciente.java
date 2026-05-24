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

public String toString()
    {
        return "Paciente: " + patient.toString() + "\n" +
               "Common: " + common.toString() + "\n" +
               "Ojo izquierdo: " + leftEye.toString() + "\n" +
               "Ojo derecho: " + rightEye.toString() + "\n";

    }        
}
