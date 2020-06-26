/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horariodeclases;

import java.util.Scanner;

/**
 *
 * @author TheHu
 */
public class HorarioDeClases {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        Horario horario = new Horario();

        String opcion = "";

        do {
            System.out.println("Bienvenido al Sistema de Horarios.");
            System.out.println("Opcion 1: Agregar Experiencia Educativa");
            System.out.println("Opcion 2: Eliminar Experiencia Educativa (Metodo en construccion)");
            System.out.println("Opcion 3: Modificar Experiencia Educativa (Metodo en construccion)");
            System.out.println("Opcion 4: Mostrar Horario");
            System.out.println("Opcion 0: Salir");
            System.out.print("Ingresa la opción deseada: ");
            opcion = entrada.nextLine();

            if (opcion.equals("1")) {
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("Agregar experiencia educativa al horario: ");
                horario.agregarHorario();
            }
            if (opcion.equals("2")) {
                System.out.println("~~Metodo en construccion~~");
            }
            if (opcion.equals("3")) {
                System.out.println("~~Metodo en construccion~~");
            }
            if (opcion.equals("4")) {
                horario.verHorario();
            } else {
                if (opcion.equals("0")) {
                    System.out.println("Gracias");
                } else {
                    if (!(Integer.parseInt(opcion) <= 4)) {
                        System.out.println("Ingreso un caracter Incorrecto. ");
                    }
                }
            }
        } while (!opcion.equals("0"));
    }

}
