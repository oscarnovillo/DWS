/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datoshttp;

/**
 *
 * @author oscar
 */
public class MetaMensajeWS {
    
    private TipoMensaje tipo;
    private Object contenido;

    public MetaMensajeWS() {
    }

    public TipoMensaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoMensaje tipo) {
        this.tipo = tipo;
    }

    public Object getContenido() {
        return contenido;
    }

    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }
    
    
    
    
}
