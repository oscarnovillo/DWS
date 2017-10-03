/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.mmiprimeraweb;

//import java.time.LocalDateTime;

import java.util.Date;


/**
 *
 * @author oscar
 */
public class CalculoPeso {
    
    private Date ultimallamada;
    
    private String nombre = "";
    private String peso = "";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
    
    
    
    public void registro(String nombre,String peso)
    {
        this.nombre = nombre;
        this.peso = peso;
    }
    
    public String adelgazamiento(String workout)
    {
       // ultimallamada = LocalDateTime.now();
        
        int iPeso = Integer.parseInt(peso);
        iPeso -= 30 / Integer.parseInt(workout);
        peso = iPeso + "";
        return peso;
    }
    
    public void logout()
    {
        nombre = null;
    }
    
}
