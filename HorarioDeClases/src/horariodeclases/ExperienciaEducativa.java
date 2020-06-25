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
public class ExperienciaEducativa 
{
    private int idExperienciaEducativa;
    private String nombreExperienciaEducativa;
    private int salon;
    
    public ExperienciaEducativa(){
        this.idExperienciaEducativa = 0;
        this.nombreExperienciaEducativa ="";
        this.salon = 1;//
    }
    
    public void añadirExperienciaEducativa(int idExperiencia, String NombreExperiencia){
        this.idExperienciaEducativa = idExperiencia;
        this.nombreExperienciaEducativa = NombreExperiencia;
    } 
}
