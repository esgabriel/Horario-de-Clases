/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horariodeclases;

import java.io.*;

/**
 *
 * @author TheHu
 */
public class Archivo {

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

    public String buscarRegistro(String datoBuscado, String archivo) {
        String cadena, registro = "";
        Horario horario = new Horario();
        FileReader abrirArchivo = null; //Abrir archivo
        BufferedReader leerArchivo = null; //Leer archivo

        try {
            abrirArchivo = new FileReader(archivo);
            leerArchivo = new BufferedReader(abrirArchivo);

            try {
                while ((cadena = leerArchivo.readLine()) != null) {
                    if (cadena.split("/")[0].equalsIgnoreCase(datoBuscado)) {
                        registro = cadena;
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

    public void modificarTexto(String archivo, String texto) {

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
                    String nuevoRegistro[] = texto.split("/");

                    boolean registroRepetido = false;
                    while ((registroArchivo = leerArchivo.readLine()) != null) {
                        registro = registroArchivo.split("/");
                        //if (cadena.split("/")[0].equalsIgnoreCase(texto.split("/")[0])) {
                        if (archivo.equals("ExperienciasEducativas.txt")) {
                            if (texto.equalsIgnoreCase(registro[0])) {
                                registroRepetido = true;
                                escribirArchivo.println(registroArchivo);
                            }
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
                    if (!registroRepetido) {
                        escribirArchivo.println(texto);
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
                            // 5 / Mate / 
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

}
