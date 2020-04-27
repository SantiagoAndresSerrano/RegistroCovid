/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.PersonaSaludVO;
import Modelo.VO.PruebaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class PruebaDAO {

    public void guardar(Connection conexion,PruebaVO pruebaVO,int idpersona)throws SQLException{
    
    try{
    PreparedStatement consulta;
    consulta=conexion.prepareStatement("INSERT INTO prueba(fecha,positivo,personasalud)"+"values(?,?,?)");
    consulta.setDate(1, pruebaVO.getFecha());
    consulta.setBoolean(2, pruebaVO.getPositivo());
    consulta.setInt(3, idpersona);

    consulta.executeUpdate();
    
    }catch(SQLException e)
    {
        throw new SQLException(e);
    }
    }
}
