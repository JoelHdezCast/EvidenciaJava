import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Main {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        Doctor doctorHandler = new Doctor();
        Paciente pacienteHandler = new Paciente();
        Cita citaHandler = new Cita();
        List<String> credenciales;
        int aceptado = 0, opcion = 0;
        boolean accept;
        Main m = new Main();
        do {
            credenciales = m.Login();
            accept = m.admin(credenciales);
            if (accept = true){
                aceptado = 1;
            }
        }while (aceptado == 0);

        do {
            m.Menu();
            opcion = Integer.parseInt(m.reader.readLine());
            switch (opcion){
                case 1:
                    System.out.println("Ingresa el nombre del doctor: ");
                    String nombre = m.reader.readLine();
                    System.out.println("Ingresa la especialidad del doctor");
                    String espec = m.reader.readLine();
                    doctorHandler.guardar(nombre, espec);
                    System.out.println("Guardado");
                    break;
                case 2:
                    System.out.println("Ingresa el nombre del paciente: ");
                    String nombrep = m.reader.readLine();
                    pacienteHandler.guardar(nombrep);
                    System.out.println("Guardado");
                    break;
                case 3:
                    System.out.println("Ingresa el nombre del doctor: ");
                    String nombrec = m.reader.readLine();
                    System.out.println("Ingresa el nombre del paciente: ");
                    String nombrepc = m.reader.readLine();
                    System.out.println("Ingresa la fecha: ");
                    String fecha = m.reader.readLine();
                    System.out.println("Ingresa el motivo: ");
                    String motivo = m.reader.readLine();
                    citaHandler.guardar(fecha, motivo, nombrec, nombrepc);
                    System.out.println("Guardado");
                    break;
                case 4:
                    System.out.println("Citas agendadas: ");
                    citaHandler.mostrarCitas();
                    break;
                default:
                    System.out.println("Ingresa una opcion valida");
            }
        }while (opcion != 5);


    }
    public List<String> Login() throws IOException {
        List<String> usuario = new ArrayList<>();
        System.out.println("Login");
        System.out.println("------------------------------");
        System.out.println("Ingrese su id: ");
        usuario.add(reader.readLine());
        System.out.println("Ingrese su contraseña: ");
        usuario.add(reader.readLine());
        return usuario;
    }
    public void Menu(){
        System.out.println("Administracion de citas");
        System.out.println("------------------------------");
        System.out.println("Seleccione una opcion: ");
        System.out.println("1. Dar de alta doctor");
        System.out.println("2. Dar de alta paciente");
        System.out.println("3. Crear cita");
        System.out.println("4. Ver citas");
        System.out.println("5. SALIR");
    }
    public boolean admin(List<String> cred)throws IOException{
        boolean accept;
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/administradores.properties"));
        System.out.println(prop.get("user"));
        String id = prop.get("id").toString();
        String pass = prop.get("password").toString();
        if (cred.get(0).equals(id) && cred.get(1).equals(pass)){
            accept = true;
        }else
            accept = false;
        return accept;
    }
}
