/**
 * Esta clase contiene los metodos para dar formato a la tabla de horario
 *
 * @author Luis Angel Barrientos Perez
 * @author Carlos Antonio Gallegos Palencia
 * @author Jaime Antonio Hernandez Cabrera
 * @author Gabriel Reyes Cruz
 * @author Jose Angel Rincon Martinez
 * @version 0.1
 */
package horariodeclases;

public class Horario {

    /**
     * El metodo crearFormatoRegistro permite crear el formato para almacenar
     * los registros en el archivo "Horario.txt"
     *
     * @param materia
     * @param hora
     * @param dia
     * @param salon
     * @return formatoRegistro
     * @version 0.1
     */
    public String crearFormatoRegistro(String materia, String hora, String dia, String salon) {
        String formatoRegistro = "";
        final String[] DIAS_SEMANA = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        formatoRegistro = hora + ":00/";
        boolean diaCorrecto = false;
        String dias[] = dia.split(",");

        for (int i = 0; i < DIAS_SEMANA.length; i++) {
            for (int j = 0; j < dias.length; j++) {
                if (DIAS_SEMANA[i].equalsIgnoreCase(dias[j])) {
                    formatoRegistro += materia + "-" + salon + "-/";
                    diaCorrecto = true;
                    break;
                }
            }
            if (!diaCorrecto) {
                formatoRegistro += " /";
            }
            diaCorrecto = false;
        }

        return formatoRegistro;
    }

    /**
     * El metodo ordenarHorario permite ordenar las horas ingresadas de menor a
     * mayor en el horario
     *
     * @param horario
     * @return horarioOrdenado
     * @version 0.1
     */
    public String[] ordenarHorario(String[] horario) {
        String horarioOrdenado[] = new String[horario.length];
        final int HORA_CLASE = 20;
        int posicion = 0;
        for (int i = 8; i <= HORA_CLASE; i++) {
            for (int j = 0; j < horario.length; j++) {
                int horaActual = Integer.parseInt(horario[j].split("/")[0].split(":")[0]);
                if (horaActual == i) {
                    horarioOrdenado[posicion] = horario[j];
                    posicion++;
                    break;
                }
            }
        }
        return horarioOrdenado;
    }

}
