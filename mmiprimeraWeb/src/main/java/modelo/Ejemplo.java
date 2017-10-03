/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Ejemplo {
    
    private int id;
    private String nombre;
    private String peso;
    private ArrayList<String> inquietudes = null;

    public Ejemplo(int id, String nombre, String peso) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
    }

    public Ejemplo() {
        inquietudes = new ArrayList();
    }

    public ArrayList<String> getInquietudes() {
        return inquietudes;
    }

    public void addInquietud(String valor)
    {
        inquietudes.add(valor);
    }
    
    public void setInquietudes(ArrayList<String> inquietudes) {
        this.inquietudes = inquietudes;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
