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
public class Horario {

    private String fecha;
    private String hora;

    public void agregarHorario() {
        //Salon salon = new Salon();
        Scanner in = new Scanner(System.in);

        System.out.print("Ingrese el nombre de la Experiencia Educativa: ");
        String experienciaEducativa = in.nextLine();
        if(true)
        {
            Profesor profesor = new Profesor();
        }
    }

    public void verHorario()
    {
        Archivo archivo = new Archivo();
        
        System.out.println("Hora\tLunes\tMartes\tMiercoles\tJueves\tViernes\t");
        archivo.leerArchivo("Horario.txt");
        System.out.println("Experiencia Educativa\t\tProfesor");
        archivo.leerArchivo("ExperienciasEducativas.txt");
        
    }
    
    public void crearFormatoHorario(String registro)
    {
        String formato[] = registro.split("/");
        
        for(int i=0; i<formato.length; i++)
        {
            System.out.print(formato[i]+"\t");
        }
        System.out.println(" ");
    }
}
