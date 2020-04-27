/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrocovid;

import Control.Controlador;
import Vista.Formulario;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class RegistroCovid {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        try{
             Formulario f = new Formulario();

        Controlador c = new Controlador(f);
    }catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
    }
    }
}
