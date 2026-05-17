
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.stream.XMLStreamReader;

import structures.SuperPaciente;
import structures.pruebas.CorrectedIOP;
import structures.unidadesInfo.TMList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;

public class program {

    // en este metodo voy a hacer el flujo del programa, externalizando en funciones el parse, y la comprobacion de si 
    // el dir esta vacio o no
    public static void main(String[] args){
        
        Path dir = Paths.get("C:\\Users\\oscar\\Desktop\\PROYECTOS\\PROYECTOS PRODUCION\\PARSER_XML\\XML");
        
        // nos lanzamos a ver si podemos ejecutar el codigo con un try catch
        try {

            // antes de ejecutar veremos si en el path hay algo, si no, no entramos en el programa, no vale la pena si no hay nada
            if (pathFull(dir)){    // si recibo true entro y ejecutare el parser, SINTAXIS: lo mismo que *== true

                File[] listadoFilePaths = getFileListPaths(dir); // iterarems por los paths de este array parseando todos los archivos PREGUNTAR SI SE QUIERE ASI
                System.out.println("ha entrado donde empezaremos el parser ficheros = "+ Integer.toString(listadoFilePaths.length));
                //ejecutamos funcion del parser para cada file del array, iterando con un for each

                for (File file : listadoFilePaths) {

                    XMLParser(file);
                }
                
            }

        } catch (Exception e) {

            System.err.println("Error de E/S comprobando la carpeta: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);

        }

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
    private static void XMLParser(File fileName){

        //instanciamos un objeto super paciente donde guardaremos toda la informacion del archivo, para
        // tratarlo como objeto superPaciente al enviarlo al frontend, 
        // y no tener que enviar cada parte por separado, aunque se podria hacer asi tambien, pero creo que es mas limpio asi.

        SuperPaciente superPaciente = new SuperPaciente();

        //Common common = new Common();
        //Patient patient = new Patient(); 
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null; // lo inicializo aqui para poder cerrarlo en el finally, aunque no se si es buena practica, pero no se como hacerlo de otra forma

        try {
            reader = factory.createXMLStreamReader(new FileInputStream(fileName));
        } catch (Exception e) {
            System.err.println("Error creando el XMLStreamReader: " + e.getMessage());
            e.printStackTrace();
            return; // salimos del método si no podemos crear el reader
        }
        
        try {
            
        //LOGICA 

        // aqui guardaremos lo que estamos trabajando, dandole el valor de START_ELEMENT a esta variable
        // para que cuando llegue el CHARACTER sepamos a que atributo (porque tendra el valor de START_ELEMENT) 
        // le daremos el valor de CHARACTER

        //variables de contexto, para saber el lado del ojo y guardarlo y el tipo de pruebaCurrent y tambien guardarlo

        String currentElement = "";
        String pruebaCurrent = "";
        String ladoOjoCurrent = "";

        ArrayList<TMList> lecturasCurrent = new ArrayList<TMList>();
        
            
            while (reader.hasNext()) {

                // se maneja el tipo de evento con numeros en esta libreria, guardamos ese numero para saber que tipo de evento es
                int evento = reader.next(); 

                // ESTE SWITCH VA A INDETIFICAR EL TIPO DE EVENTO Y ACTUARA SEGUN 
                switch (evento) {
                    case XMLStreamConstants.START_ELEMENT:

                        currentElement =   reader.getLocalName(); //en este caso guardo el START_ELEMENT en current element, 
                        
                        //cascada de condicionales para las variables de entorno
                        //pruebaCurrent
                        if (reader.getLocalName() == "TM"){pruebaCurrent = "TM";}
                        if (reader.getLocalName() == "CorrectedIOP"){pruebaCurrent = "CorrectedIOP";}

                        //ladoOjoCurrent
                        if (reader.getLocalName() == "R"){ladoOjoCurrent = "R";}
                        if (reader.getLocalName() == "L"){ladoOjoCurrent = "L";}

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

                                case "Age": superPaciente.patient.Age = Integer.parseInt(texto);
                                break;

                                case "DOB": superPaciente.patient.DOB = LocalDate.parse(texto);
                                break;

                                //entramos en measure

                                //empezamos a rellenar de lo mas pequeño a lo mas grande en este caso
                                case "List": 
                                
                                //aqui actuaremos si es ojoDerecho
                                if("R".equals(ladoOjoCurrent)){
                                    switch (pruebaCurrent) {
                                        case "TM":
                                            //inicialioo una lista que se añadira al arrayList lecturasCurrent, para asignar esa lista a superPaciente luego
                                            TMList listita = new TMList();
                                            reader.next();
                                            listita.IOP_mmHg = reader.getText().trim();
                                            lecturasCurrent.add(listita);
                                            break;
                                    
                                        case "CorrectedIOP":
                                            
                                            break;
                                        
                                        default:
                                            break;
                                    }
                                }
                                
                                //aqui actuaremos si es ojoIzquierdo
                                if("L".equals(ladoOjoCurrent)){
                                    switch (pruebaCurrent) {
                                        case "TM":
                                            

                                            break;
                                    
                                        default:
                                            break;
                                    }
                                }


                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        currentElement = "";   //ponemos la variable currentElement a "" para volver a ejecutar el bucle en
                                                //con el siguiente
                        
                        // en este condicional estamos haciendo todas las situaciones donde hay que resetear las variables de entorno
                        if ("TM".equals(reader.getLocalName()) || "CorrectedIOP".equals(reader.getLocalName()))
                        {
                             pruebaCurrent = "";
                        }
                        else if( "R".equals(reader.getLocalName()) || "R".equals(reader.getLocalName()) )
                        {
                            ladoOjoCurrent = "";
                        }

                        break;                 
                        
                    default:
                        break;
                }
            }

        }catch (Exception e){
            System.err.println("error en linea 72: " + e);
        }
        finally{

            if (reader != null){
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    System.err.println("Error cerrando el reader: " + e.getMessage());
                }
                
            }
            // aqui se podria cerrar el reader, pero no se como hacerlo sin que me de error, porque el reader es un objeto local de try
        }

        System.out.println("common = "+ superPaciente.common.toString());
        System.out.println("patient = "+ superPaciente.patient.toString());




    }
    
}
