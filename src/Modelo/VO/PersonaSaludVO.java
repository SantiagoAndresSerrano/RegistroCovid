/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.VO;

import java.util.Date;

/**
 *
 * @author santi
 */
public class PersonaSaludVO extends PersonaVO {
    EspecialidadVO especialidad;

    public PersonaSaludVO() {
    }

    public PersonaSaludVO(String documento, String nombre, String direccion, String telefono, String genero, Date fechaNacimiento) {
        super(documento, nombre, direccion, telefono, genero, fechaNacimiento);
    }

    public EspecialidadVO getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadVO especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "PersonaSalud{" + "especialidad=" + especialidad + '}';
    }
    
}
