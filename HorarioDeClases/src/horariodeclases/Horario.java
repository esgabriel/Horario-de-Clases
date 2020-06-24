/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horariodeclases;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author TheHu
 */
public class Horario 
{
    private String fecha;
    private String hora;
    
    public void agregarHorario()
    {
        //Salon salon = new Salon();
        Scanner in = new Scanner(System.in);
        
        System.out.print("Ingrese el nombre de la Experiencia Educativa: ");
        String experienciaEducativa=in.nextLine();
        
        
    }
    
    public static void leerArchivo() 
    {
        String cadena;
        FileReader fr = null; //Abrir archivo
        BufferedReader br = null; //Leer archivo

        try {
            fr = new FileReader("ExperienciasEducativas.txt");
            br = new BufferedReader(fr);

            try {
                while ((cadena = br.readLine()) != null) {
                    System.out.println(cadena);
                }
            } catch (IOException e2) {
                System.out.println("Error de lectura");
            }
        } catch (FileNotFoundException e2) {
            System.out.println("Archivo no encontrado");
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar archivo de texto");
            }
        }
    }

    public static void agregarTexto(String texto) 
    {
        FileWriter experienciasEducativas = null; //Abrir el archivo
        PrintWriter pw = null; //Escribir en el archivo

        try {
            experienciasEducativas = new FileWriter("ExperienciasEducativas.txt", true);
            pw = new PrintWriter(experienciasEducativas);

            System.out.println("Escribimos en el archivo");
            pw.println(texto);
        } catch (Exception e) {
            System.out.println("Error al abrir el archivo");
        } finally {
            try {
                if (null != experienciasEducativas) {
                    experienciasEducativas.close();
                }
            } catch (Exception e2) {
                System.out.println("Error al cerrar el archivo");
            }
        }
    }
}
