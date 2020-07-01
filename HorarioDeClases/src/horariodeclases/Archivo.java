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
        String cadena, textoSalida = "";
        String[] salida = {};
        Horario horario = new Horario();
        FileReader abrirArchivo = null; //Abrir archivo
        BufferedReader leerArchivo = null; //Leer archivo

        try {
            abrirArchivo = new FileReader(archivo);
            leerArchivo = new BufferedReader(abrirArchivo);

            try {
                while ((cadena = leerArchivo.readLine()) != null) {
                    textoSalida += cadena + "&";
                }
                salida = textoSalida.split("&");
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
        return salida;
    }

    public void agregarRegistro(String nombreArchivo, String registro) {
        FileWriter abrirArchivo = null; //Abrir el archivo
        PrintWriter escribirArchivo = null; //Escribir en el archivo

        try {
            abrirArchivo = new FileWriter(nombreArchivo, true);
            escribirArchivo = new PrintWriter(abrirArchivo);

            escribirArchivo.println(registro);
        } catch (Exception errorAbrir) {
            System.out.println("Error al abrir el archivo");
        } finally {
            try {
                if (null != abrirArchivo) {
                    abrirArchivo.close();
                }
            } catch (Exception errorCerrar) {
                System.out.println("Error al cerrar el archivo");
            }
        }

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
        //Materias - - - Redes/Vergara
        String cadena;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            FileWriter fichero = null;
            PrintWriter pw = null;
            try {
                fichero = new FileWriter("Temporal.txt");
                pw = new PrintWriter(fichero);
                try {
                    String arreglo1[] = {};
                    String arreglo2[] = texto.split("/");
                    
                    boolean eleccion = false;
                    while ((cadena = br.readLine()) != null) {
                        arreglo1 = cadena.split("/");
                        //if (cadena.split("/")[0].equalsIgnoreCase(texto.split("/")[0])) {
                        if (archivo.equals("ExperienciasEducativas.txt")) {
                            if (texto.equalsIgnoreCase(arreglo1[0])) {
                                eleccion = true;
                                pw.println(cadena);
                            }
                        } else {
                            if (arreglo1[0].equalsIgnoreCase(arreglo2[0])) {
                                String cadenaFinal = arreglo1[0] + "/";
                                eleccion = true;
                                for (int i = 1; i < arreglo1.length; i++) {
                                    if (arreglo1[i].equals(" ")) {
                                        arreglo1[i] = arreglo2[i];
                                    }
                                    cadenaFinal += arreglo1[i] + "/";
                                    /*if (i == arreglo1.length - 1) {
                                        cadenaFinal += arreglo1[i];
                                    } else {
                                        cadenaFinal += arreglo1[i] + "/";
                                    }*/
                                }
                                
                                pw.println(cadenaFinal);
                            } else {
                                pw.println(cadena);
                            }
                        }
                    }
                    if (!eleccion) {
                        pw.println(texto);
                    }
                } catch (IOException e2) {
                    System.out.println("Error de lectura");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != fichero) {
                        fichero.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (FileNotFoundException e2) {
            System.out.println("Archivo no encontrado");
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {

            }
        }
        File archivo1 = new File(archivo);
        File archivo2 = new File("Temporal.txt");
        archivo1.delete();
        archivo2.renameTo(archivo1);
    }

    public boolean verificarHora(String hora, String dia) {

        String cadena;
        boolean disponibilidad = true;
        FileReader abrirArchivo = null; //Abrir archivo
        BufferedReader leerArchivo = null; //Leer archivo

        try {
            abrirArchivo = new FileReader("Horario.txt");
            leerArchivo = new BufferedReader(abrirArchivo);

            try {
                while ((cadena = leerArchivo.readLine()) != null) {
                    String horaTexto = hora + ":00";
                    if (cadena.split("/")[0].equals(horaTexto)) {
                        String diaSemana[] = dia.split(",");
                        String horario[] = cadena.split("/");
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
