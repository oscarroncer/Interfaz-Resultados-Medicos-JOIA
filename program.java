
import java.io.File;
import java.nio.file.Files;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import structures.SuperPaciente;
import structures.pruebas.CorrectedIOP;
import structures.unidadesInfo.TMList;
import structures.unidadesInfo.IOPValue;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;

public class Program {

    // en este metodo voy a hacer el flujo del programa, externalizando en funciones el parse, y la comprobacion de si 
    // el dir esta vacio o no
    public static void main(String[] args){
        
        
        Path dir = pathExtractor("path.txt");
        
        // nos lanzamos a ver si podemos ejecutar el codigo con un try catch
        try {

            // antes de ejecutar veremos si en el path hay algo, si no, no entramos en el programa, no vale la pena si no hay nada
            if (pathFull(dir)){    // si recibo true entro y ejecutare el parser, SINTAXIS: lo mismo que *== true

                File[] listadoFilePaths = getFileListPaths(dir); // iterarems por los paths de este array parseando todos los archivos PREGUNTAR SI SE QUIERE ASI
                System.out.println("ha entrado donde empezaremos el parser ficheros = "+ Integer.toString(listadoFilePaths.length));
                //ejecutamos funcion del parser para cada file del array, iterando con un for each

                for (File file : listadoFilePaths) {

                   SuperPaciente superPaciente = XMLParser(file);
                   System.out.println("Super paciente creado: " + superPaciente.toString());
                }
                
            }

        } catch (Exception e) {

            System.err.println("Error de E/S comprobando la carpeta: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);

        }

    }


    //devuelvo el contenido de un directorio recibido String en formato Path
    private static Path pathExtractor(String path) {

        Path pathDef = null;
        try 
        {
            Path pathtxt = Paths.get(path);
            String content = Files.readString(pathtxt);

            pathDef = Paths.get(content);

            

        } 
        catch (Exception e) 
        {
            System.err.println("Error al buscar un path válido: "+ e);
        }
        
        return pathDef;
        
    }




    // hacemos un proceso para saber si el path tiene algo, tengo que incorporar buenas practicas pero no
    // acabo de entender asi
    private static boolean pathFull(Path dir) throws IOException {

        // compruebo si hay algo en el path y devuelvo true
        if (Files.exists(dir) && Files.isDirectory(dir)) {
            
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
            System.out.println("directorio existe");

            if (stream.iterator().hasNext()){
                
                stream.close();
                return true;
                }
            else{
            stream.close();
            return false;
            }
        }
        else{
            return false;
        }
    }

    // esto tiene que iterar sobre todos los files de el directorio dir en variable global y ir sumando el path en objeto path al array
    private static File[] getFileListPaths(Path dir){
            
        File carpeta = dir.toFile(); 

        File[] filePaths = null; // este array lo inicializo fuera para que a devolverlo no me de error en el return
                                 // por tener el array interno en el try{}
        try {
        
        filePaths = carpeta.listFiles();
        

        } catch (Exception e) {System.err.println("Error inesperado:"+e);}
        
        return filePaths;


    }

    //va a haber que ponerle que se ejecute automaticamente mientras en el archivo haya cosas
    private static SuperPaciente XMLParser(File fileName)
    {

        //instanciamos un objeto super paciente donde guardaremos toda la informacion del archivo, para
        // tratarlo como objeto superPaciente al enviarlo al frontend, 
        // y no tener que enviar cada parte por separado, aunque se podria hacer asi tambien, pero creo que es mas limpio asi.

        SuperPaciente superPaciente = new SuperPaciente();

        String currentElement = "";
        String pruebaCurrent = "";
        String ladoOjoCurrent = "";        

        ArrayList<TMList> lecturasCurrent = new ArrayList<TMList>();
        TMList listaCurrent = new TMList("","","");
        IOPValue IOPValueCurrent = new IOPValue("","");

        //Common common = new Common();
        //Patient patient = new Patient(); 
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null; // lo inicializo aqui para poder cerrarlo en el finally, aunque no se si es buena practica, pero no se como hacerlo de otra forma

        try {
            reader = factory.createXMLStreamReader(new FileInputStream(fileName));
        } catch (Exception e) {
            System.err.println("Error creando el XMLStreamReader: " + e.getMessage());
            e.printStackTrace();
            return null; // salgo del metodo si no puedo crear el reader, porque no puedo seguir sin el}
        }
        try 
        {
            
        //LOGICA 

        // aqui guardaremos lo que estamos trabajando, dandole el valor de START_ELEMENT a esta variable
        // para que cuando llegue el CHARACTER sepamos a que atributo (porque tendra el valor de START_ELEMENT) 
        // le daremos el valor de CHARACTER

        //variables de contexto, para saber el lado del ojo y guardarlo y el tipo de pruebaCurrent y tambien guardarlo
            
            while (reader.hasNext()) {

                // se maneja el tipo de evento con numeros en esta libreria, guardamos ese numero para saber que tipo de evento es
                int evento = reader.next(); 

                // ESTE SWITCH VA A INDETIFICAR EL TIPO DE EVENTO Y ACTUARA SEGUN 
                switch (evento) {
                    case XMLStreamConstants.START_ELEMENT:

                        currentElement =  reader.getLocalName(); //en este caso guardo el START_ELEMENT en current element, 
                        
                        //cascada de condicionales para las variables de entorno
                        //pruebaCurrent
                        if ("TM".equals(reader.getLocalName())){pruebaCurrent = "TM";}
                        if ("CorrectedIOP".equals(reader.getLocalName())){pruebaCurrent = "CorrectedIOP";}

                        //ladoOjoCurrent
                        if ("R".equals(reader.getLocalName())){ladoOjoCurrent = "R";}
                        if ("L".equals(reader.getLocalName())){ladoOjoCurrent = "L";}

                        //iniciamos TMList si vemos una lista
                        if ("List".equals(reader.getLocalName()))
                        {

                            listaCurrent = new TMList("","","");
                            lecturasCurrent = new ArrayList<TMList>();

                        }

                        if ("Average".equals(reader.getLocalName())){listaCurrent = new TMList("","","");}
                        
                        //iniciamos IOPValue si vemos que hay algun caso en el que se use
                        if ("Corrected".equals(reader.getLocalName()) 
                            || "Measured".equals(reader.getLocalName()))
                        {
                            IOPValueCurrent = new IOPValue("","");
                        }

                    break;
                    
                    case XMLStreamConstants.CHARACTERS:

                        String texto = reader.getText().trim(); //estas funcionas consiguen el texto de CHARACTERS limpio para guardarlo
                        if (!texto.isEmpty()){

                            // hay que hacer que segun el campo que sea se guarde en el setter adecuado
                            // problema si hubiera campos repetidos, pero creo que no hay en XML
                            switch(currentElement){

                                //trabajamos accediendo al atributo en vez de al getter o setter
                                //ya que todos los atributos son publicos, aunque no es buena practica, pero es mas rapido y limpio que hacer 
                                //un switch para cada campo con su getter o setter, aunque se podria hacer asi tambien, pero creo que es mas 
                                //limpio asi, pero es mas rapido y limpio que hacer un switch para cada campo con su getter o setter, aunque
                                //se podria hacer asi tambien, pero creo que es mas limpio asi

                                //los casos posibles de common
                                case "Company": superPaciente.common.Company = texto;
                                break;

                                case "ModelName": superPaciente.common.ModelName = texto;
                                break;

                                case "MachineNo": superPaciente.common.MachineNo = texto;
                                break;

                                case "ROMVersion": superPaciente.common.ROMVersion = texto;
                                break;

                                case "Version": superPaciente.common.Version = texto;
                                break;

                                case "Date": superPaciente.common.Date = texto;
                                break;
                                
                                case "Time": superPaciente.common.Time = texto;
                                break;

                                // PATIENT 

                                case "No.": superPaciente.patient.No = texto;
                                break;

                                case "ID": superPaciente.patient.ID = texto;
                                break;

                                case "FirstName": superPaciente.patient.FirstName = texto;
                                break;

                                case "MiddleName": superPaciente.patient.MiddleName = texto;
                                break;

                                case "LastName": superPaciente.patient.LastName = texto;
                                break;

                                case "Sex": superPaciente.patient.Sex = texto;
                                break;

                                case "Age": superPaciente.patient.Age = texto;
                                break;

                                case "DOB": superPaciente.patient.DOB = texto;
                                break;

                                case "IOP_mmHg": 

                                    switch (pruebaCurrent)
                                    {
                                        case "TM": listaCurrent.IOP_mmHg = texto;
                                        break;

                                        case "CorrectedIOP" : IOPValueCurrent.IOP_mmHg = texto;
                                        break;
                                    }

                                break;

                                case "IOP_Pa":        
                                
                                    switch (pruebaCurrent)
                                    {
                                        case "TM": listaCurrent.IOP_Pa = texto;
                                        break;

                                        case "CorrectedIOP" : IOPValueCurrent.IOP_Pa = texto;
                                        break;
                                    }

                                break;  
                                
                                case "ConfidenceIndex": listaCurrent.ConfidenceIndex = texto;
                                break;

                                case "Param1":

                                    switch(ladoOjoCurrent)
                                    {
                                        case "R":
                                            superPaciente.rightEye.correctedIOP.Param1 = texto;
                                        break;
                                        case "L":
                                            superPaciente.leftEye.correctedIOP.Param1 = texto;
                                        break;

                                    default:
                                        break;
                                    }
                                break;

                                case "Param2":

                                    switch(ladoOjoCurrent)
                                    {
                                        case "R":
                                            superPaciente.rightEye.correctedIOP.Param2 = texto;
                                        break;
                                        case "L":
                                            superPaciente.leftEye.correctedIOP.Param2 = texto;
                                        break;

                                    default:
                                        break;
                                    }
                                break;

                                case "CCT":

                                switch(ladoOjoCurrent)
                                {
                                    case "R":
                                        superPaciente.rightEye.correctedIOP.CCT = texto;
                                    break;
                                    case "L":
                                        superPaciente.leftEye.correctedIOP.CCT = texto;
                                    break;

                                default:
                                    break;
                                }

                                break;

                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        currentElement = "";   //ponemos la variable currentElement a "" para volver a ejecutar el bucle en
                                                //con el siguiente
                        
                        //en este condicional estamos haciendo todas las situaciones donde hay que resetear las variables de entorno
                        if ("TM".equals(reader.getLocalName()) || "CorrectedIOP".equals(reader.getLocalName()))
                        {
                             pruebaCurrent = "";
                        }
                        
                        //en este condicional, comprobamos si cierra ojo, si lo cierra comprueba la prueba y asigna
                        //segun la prueba y el lado (conocido gracias a saber con el reader el lado "R" o "L")
                        if( "R".equals(reader.getLocalName()) || "L".equals(reader.getLocalName()) )
                        {
                            switch (pruebaCurrent)
                            {
                                case "TM":
                                    
                                    if ("R".equals(reader.getLocalName())) //en caso de cada ojo asignamos y borramos
                                    {
                                        superPaciente.rightEye.TM.lecturas = lecturasCurrent;
                                        lecturasCurrent = null;
                                    }
                                    else if ("L".equals(reader.getLocalName()))
                                    {
                                        superPaciente.leftEye.TM.lecturas = lecturasCurrent;
                                        lecturasCurrent = null;
                                    }
                                
                                break;

                            }

                            ladoOjoCurrent = "";
                        }
                        
                        if ("List".equals(reader.getLocalName()))
                        {
                            lecturasCurrent.add(listaCurrent);

                            listaCurrent = null;
                        }

                        if ("Average".equals(reader.getLocalName()))
                        {
                            switch(ladoOjoCurrent)
                            {
                                case "R":
                                    
                                    superPaciente.rightEye.TM.media = listaCurrent;
                                
                                break;

                                case "L":

                                    superPaciente.rightEye.TM.media = listaCurrent;

                                break;
                            }
                            
                            IOPValueCurrent = null;
                        }

                        
                        if ("Corrected".equals(reader.getLocalName()))
                        {
                            switch(ladoOjoCurrent)
                            {
                                case "R":
                                    
                                    superPaciente.rightEye.correctedIOP.corrected = IOPValueCurrent;
                                
                                break;

                                case "L":

                                    superPaciente.rightEye.correctedIOP.corrected = IOPValueCurrent;
                                
                                break;
                            }
                            
                            IOPValueCurrent = null;
                        }

                        if ("Measured".equals(reader.getLocalName()))
                        {
                            switch(ladoOjoCurrent)
                                    {
                                        case "R":
                                            superPaciente.rightEye.correctedIOP.measured = IOPValueCurrent;
                                        break;
                                        case "L":
                                            superPaciente.leftEye.correctedIOP.measured = IOPValueCurrent;
                                        break;

                                    default:
                                        break;
                                    }
                        }
                 
                    break;

                    default:
                    break;
                }
            }

        }
        catch (Exception error)
        {

        //gracias a esto controlamos los errores y sabemos en que fallan, luego se localiza el 
        //cuando
        System.err.println("EXCEPTION: "+ error.getClass().getName());   
        System.err.println("MESSAGE: "+ error.getMessage());
        error.printStackTrace(); 

        }
        finally
        {   //cerramos el reader en finally para que se ejecute 100%

            if (reader != null){
                try {
                    reader.close();
                } catch (XMLStreamException err) {
                    System.err.println("Error cerrando el reader: " + err.getMessage());
                }
                
            }
            
        }
            
    return superPaciente; // devolvemos el super paciente con toda la informacion del XML, para luego enviarlo al frontend

    }

}



