import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paciente {
    public List<String> pacientes = new ArrayList<>();
    private String archivoTxt = "Pacientes.txt";
    private File archivo = new File(archivoTxt);
    private List<String> id = new ArrayList<>();
    private int c = 0;

    public void guardar(String nombre){
        if (!archivo.exists()){
            crearArchivo();
        }else {
            cargarArchivo();
        }
        c = 1;
        //se ingresan los 2 nuevos valores a la hashmap
        pacientes.add(nombre);
        String lista = "";
        //se guardan los valores de la hashmap dentro de un string
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTxt))){
            for (String aux : pacientes){
                lista = lista.concat(c + ";" + aux + ";\n");
                c++;
            }
            //el string se guarda en el archivo de texto
            writer.write(lista);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //crea el archivo
    public void crearArchivo(){
        try {
            archivo.createNewFile();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //lee el archivo de texto
    public void cargarArchivo(){
        pacientes.clear();
        id.clear();
        String aux, str = "";
        int a = 1;
        //se lee el contenido del archivo y se mete dentro de un string
        try (BufferedReader buff = new BufferedReader(new FileReader(archivoTxt))){
            while ((aux = buff.readLine()) != null){
                str = str.concat(aux);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        //con el .split se separan los valores para poder usarlos dentro de una lista
        String[] tokens = str.split(";");
        //usando la lista se pasan los valores a la hashmap
        for (int i = 0; i < tokens.length; i = i + 2) {
            id.add(tokens[i]);
            pacientes.add(tokens[a]);
            a = a + 2;
        }
    }
}
