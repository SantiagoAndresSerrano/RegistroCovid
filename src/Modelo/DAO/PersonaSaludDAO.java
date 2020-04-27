/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.PersonaSaludVO;
import Modelo.VO.PersonaVO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class PersonaSaludDAO {
     public void guardar(Connection conexion,PersonaSaludVO personaSaludVO,int idEspecialidad,int clinica,int persona)throws SQLException{
    
    try{
    PreparedStatement consulta;
    consulta=conexion.prepareStatement("INSERT INTO personasalud(especialidad,clinica,persona)"+"values(?,?,?)");
    
    consulta.setInt(1, idEspecialidad);
    consulta.setInt(2, clinica);
    consulta.setInt(3, persona);
    consulta.executeUpdate();
    
    }catch(SQLException e)
    {
        throw new SQLException(e);
    }
    }
}
