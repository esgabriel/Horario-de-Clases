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
public class Archivo 
{
    
    
    public void leerArchivo (String archivo)
  {
      String cadena;
      Horario horario = new Horario();
        FileReader abrirArchivo = null; //Abrir archivo
        BufferedReader leerArchivo = null; //Leer archivo

        try {
            abrirArchivo = new FileReader(archivo);
            leerArchivo = new BufferedReader(abrirArchivo);

            try {
                while ((cadena = leerArchivo.readLine()) != null) {
                    horario.crearFormatoHorario(cadena);
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
  }
    
    
  public void añadirRegistro (String nombreArchivo, String registro)
  {
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
  
  public void buscarRegistro(String datoBuscado, String archivo)
  {
     String cadena;
      Horario horario = new Horario();
        FileReader abrirArchivo = null; //Abrir archivo
        BufferedReader leerArchivo = null; //Leer archivo

        try {
            abrirArchivo = new FileReader(archivo);
            leerArchivo = new BufferedReader(abrirArchivo);

            try {
                while ((cadena = leerArchivo.readLine()) != null) {
                    horario.crearFormatoHorario(cadena);
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
  }
  
  public void borrarRegistro(){
      
  }
  
  public boolean verificarHora(String hora, String dia){
        
        String cadena;
        boolean disponibilidad = true;
        FileReader abrirArchivo = null; //Abrir archivo
        BufferedReader leerArchivo = null; //Leer archivo

        try {
            abrirArchivo = new FileReader("Horario.txt");
            leerArchivo = new BufferedReader(abrirArchivo);

            try {
                while ((cadena = leerArchivo.readLine()) != null) {
                    if (cadena.split("/")[0].equals(hora)) {
                        String diaSemana[] = dia.split(",");
                        String horario[] = cadena.split("/");
                        
                        for (int i = 0; i < diaSemana.length; i++) {
                            // 5 / Mate / 
                            if (diaSemana[i].equalsIgnoreCase("Lunes")) {
                                if (!horario[1].equals(" ")) {
                                    disponibilidad = false;
                                    break;
                                }
                            }
                            if (diaSemana[i].equalsIgnoreCase("Martes")) {
                                if (!horario[2].equals(" ")) {
                                    disponibilidad = false;
                                    break;
                                }
                            }
                            if (diaSemana[i].equalsIgnoreCase("Miercoles")) {
                                if (!horario[3].equals(" ")) {
                                    disponibilidad = false;
                                    break;
                                }
                            }
                            if (diaSemana[i].equalsIgnoreCase("Jueves")) {
                                if (!horario[4].equals(" ")) {
                                    disponibilidad = false;
                                    break;
                                }
                            }
                            if (diaSemana[i].equalsIgnoreCase("Viernes")) {
                                if (!horario[5].equals(" ")) {
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
