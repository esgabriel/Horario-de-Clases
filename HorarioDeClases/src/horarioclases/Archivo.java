/**
 * Esta clase contiene los metodos necesarios para el manejo de archivos de texto
 * donde se almacenera los registros del horario
 *
 * @author Luis Angel Barrientos Perez
 * @author Carlos Antonio Gallegos Palencia
 * @author Jaime Antonio Hernandez Cabrera
 * @author Gabriel Reyes Cruz
 * @author Jose Angel Rincon Martinez
 * @version 0.1
 */
package horarioclases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Archivo {

    /**
     * El metodo leerArchivo permite abrir y leer el archivo de texto donde se
     * almacenan los registros del horario
     *
     * @param archivo Este parametro recibe el nombre del archivo para abrirlo
     * @return registroFormato
     * @version 0.1
     */
    public String[] leerArchivo(String archivo) {
        String registroArchivo, registros = "";
        String[] registroFormato = {};
        FileReader abrirArchivo = null; //Abrir archivo
        BufferedReader leerArchivo = null; //Leer archivo

        try {
            abrirArchivo = new FileReader(archivo);
            leerArchivo = new BufferedReader(abrirArchivo);

            try {
                while ((registroArchivo = leerArchivo.readLine()) != null) {
                    registros += registroArchivo + "&";
                }
                registroFormato = registros.split("&");
            } catch (IOException errorLectura) {
                System.out.println("Error de lectura");
            }
        } catch (FileNotFoundException errorArhivoNoEncontrado) {
            System.out.println("Archivo no encontrado");
        } finally {
            try {
                if (abrirArchivo != null) {
                    abrirArchivo.close();
                }
            } catch (Exception errorCierre) {
                System.out.println("Error al cerrar archivo de texto");
            }
        }
        return registroFormato;
    }

    /**
     * El metodo buscarRegistro buscar algun dato solicitado por el usuario que
     * se encuentre en el horario
     *
     * @param datoBuscado Este parametro recibe el registro que se desea buscar
     * en el archivo de texto
     * @param archivo Este parametro recibe el nombre del archivo para abrirlo
     * @param opcion Este parametro recibe el tipo de busqueda que se desea
     * realizar
     * @return registro
     * @version 0.1
     */
    public String buscarRegistro(String datoBuscado, String archivo, int opcion) {
        datoBuscado = datoBuscado.toUpperCase();
        String cadena, registro = "";
        Horario horario = new Horario();
        FileReader abrirArchivo = null; //Abrir archivo
        BufferedReader leerArchivo = null; //Leer archivo

        try {
            abrirArchivo = new FileReader(archivo);
            leerArchivo = new BufferedReader(abrirArchivo);

            try {
                while ((cadena = leerArchivo.readLine()) != null) {
                    if (opcion == 1) {
                        if (cadena.split("/")[0].equalsIgnoreCase(datoBuscado)) {
                            registro = cadena;
                        }
                    } else {
                        String expresion = ".*" + datoBuscado + ".*";
                        Pattern patron = Pattern.compile(expresion);
                        Matcher encuentro = patron.matcher(cadena.split("/")[1]);
                        if (encuentro.find()) {
                            registro = cadena;
                        }
                    }
                }
            } catch (IOException errorLectura) {
                System.out.println("Error de lectura");
            }
        } catch (FileNotFoundException errorArhivoNoEncontrado) {
            System.out.println("Archivo no encontrado");
        } finally {
            try {
                if (abrirArchivo != null) {
                    abrirArchivo.close();
                }
            } catch (Exception errorCierre) {
                System.out.println("Error al cerrar archivo de texto");
            }
        }
        return registro;
    }

    /**
     * El metodo agregarRegistro permite agregar datos ingresados por el usuario
     * en el horario
     *
     * @param archivo Este parametro recibe el nombre del archivo para abrirlo
     * @param texto Este parametro recibe el nuevo registro que se agregara al
     * archivo
     * @version 0.1
     */
    public void agregarRegistro(String archivo, String texto) {
        texto = texto.toUpperCase();
        String registroArchivo;
        FileReader abrirLecturaArchivo = null;
        BufferedReader leerArchivo = null;
        try {
            abrirLecturaArchivo = new FileReader(archivo);
            leerArchivo = new BufferedReader(abrirLecturaArchivo);
            FileWriter abrirEscrituraArchivo = null;
            PrintWriter escribirArchivo = null;
            try {
                abrirEscrituraArchivo = new FileWriter("Temporal.txt");
                escribirArchivo = new PrintWriter(abrirEscrituraArchivo);
                boolean registroRepetido = false;
                try {
                    String registro[] = {};
                    String nuevoRegistro[] = texto.split("/");

                    while ((registroArchivo = leerArchivo.readLine()) != null) {
                        registro = registroArchivo.split("/");
                        if (archivo.equals("ExperienciasEducativas.txt")) {

                            if (nuevoRegistro[0].equalsIgnoreCase(registro[0])) {
                                registroRepetido = true;
                            }
                            escribirArchivo.println(registroArchivo);
                        } else {
                            if (registro[0].equalsIgnoreCase(nuevoRegistro[0])) {
                                String registroNuevo = registro[0] + "/";
                                registroRepetido = true;
                                for (int i = 1; i < registro.length; i++) {
                                    if (registro[i].equals(" ")) {
                                        registro[i] = nuevoRegistro[i];
                                    }
                                    registroNuevo += registro[i] + "/";
                                }

                                escribirArchivo.println(registroNuevo);
                            } else {
                                escribirArchivo.println(registroArchivo);
                            }
                        }
                    }
                } catch (IOException errorLectura) {
                    System.out.println("Error de lectura");
                }
                if (!registroRepetido) {
                    escribirArchivo.println(texto);
                }
            } catch (Exception errorAbrirArchivo) {
                errorAbrirArchivo.printStackTrace();
            } finally {
                try {
                    if (null != abrirEscrituraArchivo) {
                        abrirEscrituraArchivo.close();
                    }
                } catch (Exception errorCerrarArchivo) {
                    errorCerrarArchivo.printStackTrace();
                }
            }
        } catch (FileNotFoundException archivoNoEncontrado) {
            System.out.println("Archivo no encontrado");
        } finally {
            try {
                if (abrirLecturaArchivo != null) {
                    abrirLecturaArchivo.close();
                }
            } catch (Exception errorCerrarArchivo) {

            }
        }
        File archivoOriginal = new File(archivo);
        File archivoNuevo = new File("Temporal.txt");
        archivoOriginal.delete();
        archivoNuevo.renameTo(archivoOriginal);
    }

    /**
     * El metodo verificarHora permite validar que la hora ingresada por el
     * usuario no interfiera con otra hora en el horario
     *
     * @param hora Este parametro recibe la hora en que se impartira nueva clase
     * @param dia Este parametro recibe el dia en que se impartira la nueva
     * clase
     * @return disponibilidad
     * @version 0.1
     */
    public boolean verificarHora(String hora, String dia) {

        String registroArchivo;
        boolean disponibilidad = true;
        FileReader abrirArchivo = null; //Abrir archivo
        BufferedReader leerArchivo = null; //Leer archivo

        try {
            abrirArchivo = new FileReader("Horario.txt");
            leerArchivo = new BufferedReader(abrirArchivo);

            try {
                while ((registroArchivo = leerArchivo.readLine()) != null) {
                    String horaTexto = hora + ":00";
                    if (registroArchivo.split("/")[0].equals(horaTexto)) {
                        String diaSemana[] = dia.split(",");
                        String horario[] = registroArchivo.split("/");
                        for (int i = 0; i < diaSemana.length; i++) {
                            if (diaSemana[i].equalsIgnoreCase("Lunes")) {
                                if (!(horario[1].equals(" "))) {
                                    disponibilidad = false;
                                    break;
                                }
                            }
                            if (diaSemana[i].equalsIgnoreCase("Martes")) {
                                if (!(horario[2].equals(" "))) {
                                    disponibilidad = false;
                                    break;
                                }
                            }
                            if (diaSemana[i].equalsIgnoreCase("Miercoles")) {
                                if (!(horario[3].equals(" "))) {
                                    disponibilidad = false;
                                    break;
                                }
                            }
                            if (diaSemana[i].equalsIgnoreCase("Jueves")) {
                                if (!(horario[4].equals(" "))) {
                                    disponibilidad = false;
                                    break;
                                }
                            }
                            if (diaSemana[i].equalsIgnoreCase("Viernes")) {
                                if (!(horario[5].equals(" "))) {
                                    disponibilidad = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            } catch (IOException errorLectura) {
                System.out.println("Error de lectura");
            }
        } catch (FileNotFoundException errorArhivoNoEncontrado) {
            System.out.println("Archivo no encontrado");
        } finally {
            try {
                if (abrirArchivo != null) {
                    abrirArchivo.close();
                }
            } catch (Exception errorCierre) {
                System.out.println("Error al cerrar archivo de texto");
            }
        }
        return disponibilidad;
    }

    /**
     * El metodo modificarRegistro permite eliminar o editar un registro del
     * horario de clases
     *
     * @param archivo Este parametro recibe el nombre del archivo para abrirlo
     * @param datoNuevo Este parametro recibe el nuevo registro que se agregara
     * al archivo
     * @param vacio Este parametro recibe una condicion para saber si el
     * registro nuevo esta vacio
     * @version 0.1
     */
    public void modificarRegistro(String archivo, String datoNuevo, boolean vacio) {
        datoNuevo = datoNuevo.toUpperCase();
        String registroArchivo;
        FileReader abrirLecturaArchivo = null;
        BufferedReader leerArchivo = null;
        try {
            abrirLecturaArchivo = new FileReader(archivo);
            leerArchivo = new BufferedReader(abrirLecturaArchivo);
            FileWriter abrirEscrituraArchivo = null;
            PrintWriter escribirArchivo = null;
            try {
                abrirEscrituraArchivo = new FileWriter("Temporal.txt");
                escribirArchivo = new PrintWriter(abrirEscrituraArchivo);
                try {
                    String registro[] = {};
                    String nuevoRegistro[] = datoNuevo.split("/");
                    while ((registroArchivo = leerArchivo.readLine()) != null) {
                        registro = registroArchivo.split("/");
                        if (vacio) {
                            if (!registro[0].equalsIgnoreCase(nuevoRegistro[0])) {
                                escribirArchivo.println(registroArchivo);
                            }
                        } else {
                            if (registro[0].equalsIgnoreCase(nuevoRegistro[0])) {
                                escribirArchivo.println(datoNuevo);
                            } else {
                                escribirArchivo.println(registroArchivo);
                            }
                        }
                    }
                } catch (IOException errorLectura) {
                    System.out.println("Error de lectura");
                }
            } catch (Exception errorAbrirArchivo) {
                errorAbrirArchivo.printStackTrace();
            } finally {
                try {
                    if (null != abrirEscrituraArchivo) {
                        abrirEscrituraArchivo.close();
                    }
                } catch (Exception errorCerrarArchivo) {
                    errorCerrarArchivo.printStackTrace();
                }
            }
        } catch (FileNotFoundException archivoNoEncontrado) {
            System.out.println("Archivo no encontrado");
        } finally {
            try {
                if (abrirLecturaArchivo != null) {
                    abrirLecturaArchivo.close();
                }
            } catch (Exception errorCerrarArchivo) {

            }
        }
        File archivoOriginal = new File(archivo);
        File archivoNuevo = new File("Temporal.txt");
        archivoOriginal.delete();
        archivoNuevo.renameTo(archivoOriginal);
    }

    /**
     * El metodo mostrarRegistro permite realizar la busqueda del horario de
     * clases que cumpla con los parametros ingresados por el usuario
     *
     * @param archivo Este parametro recibe el nombre del archivo para abrirlo
     * @param datoBuscado Este parametro recibe el registro que desea buscar el
     * usuario
     * @param opcion Este parametro recibe la condicion para realizar una
     * busqueda especifica en el horario
     * @return cadenaFinalEncontrada
     * @version 0.1
     */
    public String[] mostrarRegistro(String archivo, String datoBuscado, String opcion) {
        datoBuscado = datoBuscado.toUpperCase();
        String registro, registrosEncontrados = "";
        String cadenaFinalEncontrada[] = null;
        FileReader abrirArchivo = null;
        BufferedReader leerArchivo = null;

        try {
            abrirArchivo = new FileReader(archivo);
            leerArchivo = new BufferedReader(abrirArchivo);
            boolean encontrado = false; //Variable para saber si la busqueda tiene minimo un resultado
            if (opcion.equalsIgnoreCase("Profesor")) {
                String materia = buscarRegistro(datoBuscado, "ExperienciasEducativas.txt", 2);
                datoBuscado = materia.split("/")[0];
                opcion = "Experiencia Educativa";
            }
            try {

                while ((registro = leerArchivo.readLine()) != null) {
                    if (opcion.equalsIgnoreCase("Experiencia Educativa")) {
                        String expresion = ".*/" + datoBuscado + "-.*";
                        Pattern patron = Pattern.compile(expresion);
                        Matcher encuentro = patron.matcher(registro);
                        if (encuentro.find()) {
                            registrosEncontrados += registro + "&";
                            encontrado = true;
                        }
                    }
                    if (opcion.equalsIgnoreCase("Salon")) {
                        String expresion = ".*-" + datoBuscado + "-.*";
                        Pattern patron = Pattern.compile(expresion);
                        Matcher encuentro = patron.matcher(registro);
                        if (encuentro.find()) {
                            registrosEncontrados += registro + "&";
                            encontrado = true;
                        }
                    }
                }
                if (encontrado) {
                    cadenaFinalEncontrada = registrosEncontrados.split("&");
                }
            } catch (IOException errorLectura) {
                System.out.println("Error de lectura");
            }
        } catch (FileNotFoundException errorArchivoNoEncontrado) {
            System.out.println("Archivo no encontrado");
        } finally {
            try {
                if (abrirArchivo != null) {
                    abrirArchivo.close();
                }
            } catch (Exception errorCerrarArchivo) {

            }
        }
        return cadenaFinalEncontrada;
    }
}
