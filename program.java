
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.crypto.dsig.spec.XPathFilter2ParameterSpec;
import javax.xml.stream.XMLStreamReader;
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
                //ejecutamos funcion del parser
                for (int i=0;i<listadoFilePaths.length-1;i++){
                    XMLParser(listadoFilePaths[i]);

                }
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

        Common common = new Common();
        Patient patient = new Patient(); 
        //hay que instanciar los lectores de eventos
        try {
            
        // String file = "C:\\Users\\oscar\\Desktop\\PROYECTOS\\PROYECTOS PRODUCION\\PARSER_XML\\XML\\CT1P.XML";
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(fileName));

        //creamos los objetos para darles valor en memoria segun vayamos parseando
        

        //LOGICA 

        // aqui guardaremos lo que estamos trabajando, dandole el valor de START_ELEMENT a esta variable
        // para que cuando llegue el CHARACTER sepamos a que atributo (porque tendra el valor de START_ELEMENT) 
        // le daremos el valor de CHARACTER

        String currentElement = "";
            
            while (reader.hasNext()) {

                // se maneja el tipo de evento con numeros en esta libreria, guardamos ese numero para saber que tipo de evento es
                int evento = reader.next(); 

                // ESTE SWITCH VA A INDETIFICAR EL TIPO DE EVENTO Y ACTUARA SEGUN 
                switch (evento) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentElement =   reader.getLocalName(); //en este caso guardo el START_ELEMENT en current element, 
                        break;
                    
                    case XMLStreamConstants.CHARACTERS:
                        String texto = reader.getText().trim(); //estas funcionas consiguen el texto de CHARACTERS limpio para guardarlo
                        
                        if (!texto.isEmpty()){

                            // hay que hacer que segun el campo que sea se guarde en el setter adecuado
                            // problema si hubiera campos repetidos, pero creo que no hay en XML
                            switch(currentElement){

                                //los casos posibles de common
                                case "Company": common.setCompany(texto);
                                break;

                                case "ModelName": common.setModelName(texto);
                                break;

                                case "MachineNo": common.setMachineNo(texto);
                                break;

                                case "ROMVersion": common.setROMVersion(texto);
                                break;

                                case "Version": common.setVersion(texto);
                                break;

                                case "Date": common.setDate(texto);
                                break;
                                
                                case "Time": common.setTime(texto);
                                break;

                                // PATIENT

                                case "No.": patient.setNo(texto);
                                break;

                                case "ID": patient.setID(texto);
                                break;

                                case "FirstName": patient.setFirstName(texto);
                                break;

                                case "MiddleName": patient.setMiddleName(texto);
                                break;

                                case "LastName": patient.setLastName(texto);
                                break;

                                case "Sex": patient.setSex(texto);
                                break;

                                case "Age": patient.setAge(Integer.parseInt(texto));
                                break;

                                case "DOB": patient.setDOB(LocalDate.parse(texto));
                                break;

                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        currentElement = "";        //ponemos la variable currentElement a "" para volver a ejecutar el bucle en
                        break;                      //con el siguiente
                        
                    
                    default:
                        break;
                }
            }

        }catch (Exception e){
            System.err.println("error en linea 72: " + e);
        }
        finally{
            // aqui se podria cerrar el reader, pero no se como hacerlo sin que me de error, porque el reader es un objeto local de try
        }

        System.out.println("common = "+ common.toString());
        System.out.println("patient = "+ patient.toString());



    }
    
}
