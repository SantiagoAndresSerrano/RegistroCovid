/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;


import Modelo.VO.PacienteVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class PacienteDAO {
    public void guardar(Connection conexion,PacienteVO pacienteVO,int idProcedencia,int clinica,int estado,int persona)throws SQLException{
    
    try{
    PreparedStatement consulta;
    consulta=conexion.prepareStatement("INSERT INTO paciente(lugarprocedencia,fechadireccion,estado,clinica,persona)"+"values(?,?,?,?,?)");
    
    consulta.setInt(1,idProcedencia);
    consulta.setDate(2, pacienteVO.getFechaDeteccion());
    consulta.setInt(3, estado);
    consulta.setInt(4,clinica);
    consulta.setInt(5,persona);
  
    consulta.executeUpdate();
    
    }catch(SQLException e)
    {
        throw new SQLException(e);
    }
    }
}
