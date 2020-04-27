/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.ClinicaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class ClinicaDAO {
    public void guardar(Connection conexion,ClinicaVO clinicaDAO)throws SQLException{
    
    try{
    PreparedStatement consulta;
    consulta=conexion.prepareStatement("INSERT INTO clinica(nombre,direccion)"+"values(?,?)");
    consulta.setString(1, clinicaDAO.getNombre());
    consulta.setString(2, clinicaDAO.getDireccion());
    consulta.executeUpdate();
    
    
    }catch(SQLException e)
    {
        throw new SQLException(e);
    }
    }
}
