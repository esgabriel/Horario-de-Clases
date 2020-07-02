package horariodeclases;

import java.util.Scanner;
import java.io.*;

public class Horario {

    public String crearFormatoRegistro(String materia, String hora, String dia, String salon) {
        String cadena = "";
        final String[] DIAS_SEMANA = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        cadena = hora + ":00/";
        boolean diaCorrecto = false;
        String dias[] = dia.split(",");

        for (int i = 0; i < DIAS_SEMANA.length; i++) {
            for (int j = 0; j < dias.length; j++) {
                if (DIAS_SEMANA[i].equalsIgnoreCase(dias[j])) {
                    cadena += materia + "(" + salon + ")/";
                    diaCorrecto = true;
                    break;
                }
            }
            if (!diaCorrecto) {
                cadena += " /";
            }
            diaCorrecto = false;
        }

        return cadena;
    }

}
