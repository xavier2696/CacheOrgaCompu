/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tarea1cache;

/**
 *
 * @author xavier2696
 */
public class Linea {
    
    private int etiqueta;
    private boolean valido;
    private boolean modificado;
    private int[] datos = new int[8];

    public int[] getDatos() {
        return datos;
    }
    
    public int getDato(int pos){
        return datos[pos];
    }
    
    public void setDato(int pos, int dato){
        this.datos[pos] = dato;
    }

    public void setDatos(int[] datos) {
        this.datos = datos;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }
    

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }
    

    public int getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }

}
