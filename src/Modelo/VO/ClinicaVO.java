/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.VO;




/**
 *
 * @author santi
 */
public class ClinicaVO {
    String nombre,direccion;
    
    
    public ClinicaVO() {
        
    }

    public ClinicaVO(String nombre, String direccion) {
       
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Clinica{" + "nombre=" + nombre + ", direccion=" + direccion + '}';
    }
    
}
