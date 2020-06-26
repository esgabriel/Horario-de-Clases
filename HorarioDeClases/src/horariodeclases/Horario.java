package horariodeclases;

import java.util.Scanner;
import java.io.*;

public class Horario {

    private String dia;
    private String hora;

    public void agregarHorario() {
        //Salon salon = new Salon();
        Scanner leerConsola = new Scanner(System.in);
        String opcion = "";
        Archivo archivo = new Archivo();
        boolean disponibilidad = false;
        System.out.print("Ingrese el nombre de la Experiencia Educativa: ");
        String experienciaEducativa = leerConsola.nextLine();
        System.out.println("Ingresa el nombre completo del maestro (empezar por apellido paterno): ");
        String nombreProfesor = leerConsola.nextLine();

        do {
            System.out.println("Ingrese los días que se impartira la clase(Los dias deben ser separados por coma ','): ");
            String diaClase = leerConsola.nextLine();
            System.out.println("Ingresar la hora de clase de la Experiencia Educativa: ");
            String horaClase = leerConsola.nextLine();
            
            disponibilidad = archivo.verificarHora(horaClase, diaClase);
            
            if (disponibilidad) {
                System.out.println("Ingrese el salón de clases");
                int salonClases = leerConsola.nextInt();
                archivo.añadirRegistro("ExperienciasEducativas.txt", experienciaEducativa + "/" + nombreProfesor);
                String horarioCompleto = crearFormatoRegistro(experienciaEducativa, horaClase, diaClase, salonClases);
                archivo.añadirRegistro("Horario.txt", horarioCompleto);
            } else {
                System.out.println("La hora y día ingresados No se encuentran disponibles");
                System.out.println("Si desea salir del registro ingrese cero (0) de lo contrario ingresa cualquier otra opción: ");
                opcion = leerConsola.nextLine();
                if (opcion.equals("0")) {
                    disponibilidad=true;
                }
            }
        } while (!disponibilidad);

    }

    public void verHorario() {
        Archivo archivo = new Archivo();

        System.out.println("Hora\tLunes\t\tMartes\t\tMiercoles\t\tJueves\t\tViernes\t\t");
        archivo.leerArchivo("Horario.txt");
        System.out.println("\nExperiencia Educativa\tProfesor");
        archivo.leerArchivo("ExperienciasEducativas.txt");
        System.out.println("\n");

    }

    public String crearFormatoRegistro(String materia, String hora, String dia, int salon) {
        String cadena = "";
        final String[] DIAS_SEMANA = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        cadena = hora + ":00/";
        boolean diaCorrecto = false;
        String dias[] = dia.split(",");

        for (int i = 0; i < DIAS_SEMANA.length; i++) {
            for (int j = 0; j < dias.length; j++) {
                if (DIAS_SEMANA[i].equalsIgnoreCase(dias[j])) {
                    cadena += materia + " (" + salon + ")/";
                    diaCorrecto = true;
                    break;
                }
            }
            if (!diaCorrecto) {
                cadena+=" /";
            }
            diaCorrecto=false;
        }
        return cadena;
    }

    public void crearFormatoHorario(String registro) {
        String formato[] = registro.split("/");

        for (int i = 0; i < formato.length; i++) {
            System.out.print(formato[i] + "\t");
        }
        System.out.println(" ");
    }

}
