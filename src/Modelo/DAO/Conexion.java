/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author santi
 */
public class Conexion {

    private static Connection cnx = null;
    private static Statement sentencia;
    static ResultSet  resultado;
    
    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cnx = DriverManager.getConnection("jdbc:mysql://localhost/registro_covid", "root", "");
                sentencia= cnx.createStatement();
            } catch (SQLException e) {
                throw new SQLException(e);

            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return cnx;

    }
    
     public int estadoPersonaSalud(String id){
     int estado = 0;
        
    
        String q = "SELECT * FROM personasalud WHERE where persona="+id;
        try {
            resultado = sentencia.executeQuery(q);
            
        } catch (SQLException e) {
            System.out.println(" No se encuentra");
        }
         
        return asignar();
    
    
    
    }
    
    
     public static  int buscarElemento(String Nombre,String tabla,String columna)
     {
        
        String q = "SELECT * FROM "+tabla+" WHERE "+columna+"='"+Nombre+"'";
        try {
            resultado = sentencia.executeQuery(q);
            
        } catch (SQLException e) {
            System.out.println(" No se encuentra");
        }
         
        return asignar();
                
    }
     
    public static String dato(){
    String dato="";
    
    
    
    
    return dato;
    } 
     
    public static  int buscarFecha(String Nombre)
     {
        
        String q = "SELECT * FROM persona WHERE fecha=??";
        try {
            resultado = sentencia.executeQuery(q);
            
        } catch (SQLException e) {
            System.out.println(" No se encuentra");
        }
         
        return asignar();
                
    } 
    
    static  int asignar(){
     
      int id=-1;
      
        try {
            if(resultado.last()){               
                id= resultado.getInt("id");
            }
        } catch (SQLException e) {
        }
      
      return id;
                
    }
    public static void cerrar() throws SQLException {

        if (cnx != null) {

            cnx.close();
        }

    }
}
