/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.PersonaVO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class PersonaDAO {
    public void guardar(Connection conexion,PersonaVO personaVO, int estado)throws SQLException{
    
    try{
    PreparedStatement consulta;
    consulta=conexion.prepareStatement("INSERT INTO persona(nombre,direccion,telefono,genero,fechanacimiento,documento,estado)"+"values(?,?,?,?,?,?,?)");
    
    consulta.setString(1, personaVO.getNombre());
    consulta.setString(2, personaVO.getDireccion());
    consulta.setString(3, personaVO.getTelefono());
    consulta.setString(4, personaVO.getGenero());
    consulta.setDate(5, (Date)personaVO.getFechaNacimiento());
    consulta.setString(6, personaVO.getDocumento());
    consulta.setInt(7, estado);
    consulta.executeUpdate();
    
    }catch(SQLException e)
    {
        throw new SQLException(e);
    }
    }
}

