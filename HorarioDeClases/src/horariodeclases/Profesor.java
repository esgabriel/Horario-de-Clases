/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horariodeclases;

/**
 *
 * @author TheHu
 */
public class Profesor 
{

    private String nombreProfesor;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public Profesor() 
    {
        this.nombreProfesor = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
    }

    public void añadirProfesor(String nombreProfesor, String apellidoPaterno, String apellidoMaterno) 
    {
        this.nombreProfesor = nombreProfesor;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
}
