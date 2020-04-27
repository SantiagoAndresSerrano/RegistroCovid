/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.VO;
import java.sql.Date;
/**
 *
 * @author santi
 */
public class PacienteVO extends PersonaVO {
    LugarProcedenciaVO procedencia;
    Date fechaDeteccion;
    EstadoVO estado;
    Boolean casa;
    
    public PacienteVO(){}

    public PacienteVO(LugarProcedenciaVO procedencia, Date fechaDeteccion, EstadoVO estado, Boolean casa) {
        this.procedencia = procedencia;
        this.fechaDeteccion = fechaDeteccion;
        this.estado = estado;
        this.casa = casa;
    }
    
    public String estado(){
    
        if(estado == EstadoVO.MUERTO){
            return "muerto";
        }
        if(estado == EstadoVO.NEGATIVO){
            return "negativo";
        }
        if(estado == EstadoVO.POSITIVO){
            return "positivo";
        }
        if(estado == EstadoVO.RECUPERADO){
            return "recuperado";
        }
        if(estado == EstadoVO.SOSPECHOSO){
            return "sospechoso";
        }
        
        return null;
    }
    
    public LugarProcedenciaVO getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(LugarProcedenciaVO procedencia) {
        this.procedencia = procedencia;
    }

    public Date getFechaDeteccion() {
        return fechaDeteccion;
    }

    public void setFechaDeteccion(Date fechaDeteccion) {
        this.fechaDeteccion = fechaDeteccion;
    }

    public EstadoVO getEstado() {
        return estado;
    }

    public void setEstado(EstadoVO estado) {
        this.estado = estado;
    }

    public Boolean getCasa() {
        return casa;
    }

    public void setCasa(Boolean casa) {
        this.casa = casa;
    }

    @Override
    public String toString() {
        return "Paciente{" + "procedencia=" + procedencia + ", fechaDeteccion=" + fechaDeteccion + ", estado=" + estado + ", casa=" + casa + '}';
    }
    
}
