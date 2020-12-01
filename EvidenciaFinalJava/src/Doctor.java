import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doctor {
    public HashMap<String, String> doctores = new HashMap<>();
    private String archivoTxt = "Doctores.txt";
    private File archivo = new File(archivoTxt);
    private List<String> id = new ArrayList<>();
    private int c = 0;

    public void guardar(String nombre, String especialidad){
        if (!archivo.exists()){
            crearArchivo();
        }else {
            cargarArchivo();
        }
        c = 1;
        //se ingresan los 2 nuevos valores a la hashmap
        doctores.put(nombre, especialidad);
        String lista = "";
        //se guardan los valores de la hashmap dentro de un string
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTxt))){
            for (Map.Entry<String, String> aux : doctores.entrySet()){
                lista = lista.concat(c + ";" + aux.getKey() + ";" + aux.getValue() + ";\n");
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
        doctores.clear();
        id.clear();
        String aux, str = "";
        int a = 1, b = 2;
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
        for (int i = 0; i < tokens.length; i = i + 3) {
            id.add(tokens[i]);
            doctores.put(tokens[a], tokens[b]);
            a = a + 3;
            b = b + 3;
        }
    }
}
