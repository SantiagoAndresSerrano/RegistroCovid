/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.LugarProcedenciaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class LugarProcedenciaDAO 
{
    public void guardar(Connection conexion,LugarProcedenciaVO lugarVO)throws SQLException{
    
    try{
    PreparedStatement consulta;
    consulta=conexion.prepareStatement("INSERT INTO lugar(nombre)"+"values(?)");
    consulta.setString(1, lugarVO.getNombre());
    consulta.executeUpdate();
    
    
    }catch(SQLException e)
    {
        throw new SQLException(e);
    }
    }
}
