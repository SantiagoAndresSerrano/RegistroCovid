/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.DAO.ClinicaDAO;
import Modelo.DAO.Conexion;
import Modelo.DAO.LugarProcedenciaDAO;
import Modelo.DAO.PacienteDAO;
import Modelo.DAO.PersonaDAO;
import Modelo.DAO.PersonaSaludDAO;
import Modelo.DAO.PruebaDAO;
import Modelo.VO.ClinicaVO;
import Modelo.VO.EstadoVO;
import Modelo.VO.LugarProcedenciaVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.PersonaSaludVO;
import Modelo.VO.PersonaVO;
import Modelo.VO.PruebaVO;
import Vista.Formulario;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class Controlador implements ActionListener {

    private Formulario f;
    private ClinicaVO c;

    public Controlador() {
    }

    public Controlador(Formulario f) {
        super();
        this.f = f;
        f.setVisible(true);
        actionListener(this);
        f.muertas.setEditable(false);
        f.sospechosas.setEditable(false);
        f.positivas.setEditable(false);
        f.negativas.setEditable(false);
        f.recuperadas.setEditable(false);

    }

    private void actionListener(ActionListener e) {

        f.botonRegistrar.addActionListener(e);
        f.botonRegistrarPrueba1.addActionListener(e);
        f.comboEspecialidad.addActionListener(e);
        f.comboEstado.addActionListener(e);
        f.comboEstadoP1.addActionListener(e);
        f.comboTipoPersona.addActionListener(e);
        f.btenRegistrarClinica.addActionListener(e);
        f.btnBuscar.addActionListener(e);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
///////////////////////////////////////////////////////////////////////////////////////
        if (ae.getSource() == f.btenRegistrarClinica) 
        {
            String nombre = f.jtNombreClinica.getText();
            String direccion = f.jtDireccionClinica.getText();

            c = new ClinicaVO(nombre, direccion);
            try {
                ClinicaDAO cdao = new ClinicaDAO();
                if (!this.existeElemento(nombre, "clinica", "nombre")) {
                    cdao.guardar(Conexion.obtener(), c);
                    
                    JOptionPane.showMessageDialog(null, "Clinica" + nombre + " registrada con exito");
                    
                    
                    try {
                        f.elminarClinicas();
                        f.mostrarDato("clinica", f.jComboBox1, "nombre");

                    } catch (ClassNotFoundException | SQLException e) {
                        JOptionPane.showMessageDialog(f, e.getMessage());
                    }
                    
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Clinica repetida, o faltan datos");
                }

            } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(f, e.getMessage());
            }

        }
        ///////////////////////////////////////////////////////////////////////////////////////
        if (ae.getSource() == f.comboTipoPersona) 
        {

            if (f.comboTipoPersona.getSelectedItem().equals("Paciente")) {
                f.habilitarComponentes(f.panelRegistroPersona);
                f.comboEspecialidad.setEnabled(false);
                
            } else if (f.comboTipoPersona.getSelectedItem().equals("Seleccione")) {
                f.deshabilitarComponentes(f.panelRegistroPersona);
                f.comboTipoPersona.setEnabled(true);
                
            } else {
                f.habilitarComponentes(f.panelRegistroPersona);

                f.jtFechaContagio.setEnabled(false);
                f.jtProcedencia.setEnabled(false);
                f.comboEspecialidad.setEnabled(true);
                f.comboEstado.setEnabled(false);
            }

        }
        ///////////////////////////////////////////////////////////////////////////////////////
        if (ae.getSource() == f.botonRegistrar) 
        {

            String nombre = f.jtNombre.getText();
            String direccion = f.jtDireccion.getText();
            String telefono = f.jtTelefono.getText();
            String documento = f.jtDocumento.getText();
            String genero = f.jtGenero.getText();
            String nombreClinica = f.jComboBox1.getSelectedItem().toString();

            if (f.comboTipoPersona.getSelectedItem().equals("Paciente")) 
            {

                EstadoVO estado = estado();
                LugarProcedenciaVO lugar = new LugarProcedenciaVO(f.jtProcedencia.getText());
                String fechas = f.jtFechaNacimiento.getText();
                String[] fs = fechas.split("/");
                int anio = Integer.parseInt(fs[0]);
                int mes = Integer.parseInt(fs[1]);
                int dia = Integer.parseInt(fs[2]);

                java.sql.Date fechaNacimiento = new java.sql.Date(anio - 1900, mes - 1, dia);

                fechas = f.jtFechaContagio.getText();
                fs = fechas.split("/");
                anio = Integer.parseInt(fs[0]);
                mes = Integer.parseInt(fs[1]);
                dia = Integer.parseInt(fs[2]);

                java.sql.Date fechaContagio = new java.sql.Date(anio - 1900, mes - 1, dia);

                PacienteVO paciente = new PacienteVO(lugar, fechaContagio, estado, true);
                paciente.setDireccion(direccion);
                paciente.setDocumento(documento);
                paciente.setNombre(nombre);
                paciente.setGenero(genero);
                paciente.setTelefono(telefono);
                paciente.setFechaNacimiento(fechaNacimiento);

                try {

                    PacienteDAO paDAO = new PacienteDAO();
                    PersonaDAO peDAO = new PersonaDAO();
                    PersonaVO persona = paciente;
                    
                    
                    
                    if (!existeDocumento(documento)) {

                        LugarProcedenciaDAO lg = new LugarProcedenciaDAO();
                        lg.guardar(Conexion.obtener(), lugar);
                        int estadoPersona;
                        
                        if (f.comboEstado.getSelectedItem().equals("positivo"))
                            estadoPersona=1;
                        else
                            estadoPersona=0;
                        
                        peDAO.guardar(Conexion.obtener(), persona, estadoPersona);

                        paDAO.guardar
                        (
                                Conexion.obtener(), paciente,
                                Conexion.buscarElemento(lugar.getNombre(), "lugar", "nombre"),
                                Conexion.buscarElemento(nombreClinica, "clinica", "nombre"),
                                Conexion.buscarElemento(f.comboEstado.getSelectedItem().toString(), "estado", "descripcion"),
                                Conexion.buscarElemento(documento, "persona", "documento")
                        );

                        JOptionPane.showMessageDialog(f, "Paciente " + nombre + " registrado");
                    } else 
                        JOptionPane.showMessageDialog(f, "Repetido");

                    

                } catch (ClassNotFoundException | SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            ///////////////////////////////////////////////////////////////////////////////////////
            if (f.comboTipoPersona.getSelectedItem().equals("PersonaSalud")) {
                String fechas = f.jtFechaNacimiento.getText();
                String[] fs = fechas.split("/");
                int anio = Integer.parseInt(fs[0]);
                int mes = Integer.parseInt(fs[1]);
                int dia = Integer.parseInt(fs[2]);

                java.sql.Date fechaNacimiento = new java.sql.Date(anio - 1900, mes - 1, dia);
                PersonaSaludVO perso = new PersonaSaludVO(documento,nombre,direccion,telefono,genero,fechaNacimiento);


                PersonaSaludDAO personaSalud = new PersonaSaludDAO();
                PersonaDAO peDAO = new PersonaDAO();
                PersonaVO persona = perso;

                try {
                    if (!existeDocumento(documento)) {

                        peDAO.guardar(Conexion.obtener(), persona, 0);

                        personaSalud.guardar(
                                Conexion.obtener(), perso,
                                Conexion.buscarElemento(f.comboEspecialidad.getSelectedItem().toString(), "especialidad", "descripcion"),
                                Conexion.buscarElemento(f.jComboBox1.getSelectedItem().toString(), "clinica", "nombre"),
                                Conexion.buscarElemento(documento, "persona", "documento")
                        );

                        JOptionPane.showMessageDialog(f, f.comboEspecialidad.getSelectedItem().toString() + " registrado");
                    } else 
                        JOptionPane.showMessageDialog(f, "Repetido");

                    
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(f, ex.getMessage());
                }

            }

        }
        ///////////////////////////////////////////////////////////////////////////////////////
        if (ae.getSource() == f.botonRegistrarPrueba1) {
            try {

                Boolean x = false;

                if (f.comboEstadoP1.getSelectedItem().equals("Positivo")) 
                    x = true;
                 else 
                    if (f.comboEstadoP1.getSelectedItem().equals("Negativo")) 
                        x = false;
                    
                

                int id = Conexion.buscarElemento(f.jTextField1.getText(), "persona", "documento");
                int idSalud = Conexion.buscarElemento(Integer.toString(id), "personasalud", "persona");

                if (idSalud != -1) {

                    String fechas = f.jTextField3.getText();
                    String[] fs = fechas.split("/");
                    int anio = Integer.parseInt(fs[0]);
                    int mes = Integer.parseInt(fs[1]);
                    int dia = Integer.parseInt(fs[2]);
                    java.sql.Date fechaPrueba = new java.sql.Date(anio - 1900, mes - 1, dia);
                    PruebaDAO pr = new PruebaDAO();
                    
                     PreparedStatement stmt;
                    Connection cnx = Conexion.obtener();  
                    
                    if(x)        
                        stmt = cnx.prepareStatement("UPDATE persona SET estado=1 WHERE id="+Integer.toString(id));
                    
                    else
                        stmt = cnx.prepareStatement("UPDATE persona SET estado=0 WHERE id="+Integer.toString(id));
                    
                    stmt.executeUpdate();
                    
                    
                    pr.guardar(Conexion.obtener(), new PruebaVO(fechaPrueba, x, new PersonaSaludVO()), idSalud);
                    JOptionPane.showMessageDialog(f, "Prueba registrada");

                } else {

                    JOptionPane.showMessageDialog(f, "Persona salud no encontrada");
                }

            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(f, ex.getMessage());
            }

        }
        ///////////////////////////////////////////////////////////////////////////////////////////
        if (ae.getSource() == f.btnBuscar) {
            try {
                f.muertas.setText(cantidad("6","persona","paciente","estado"));
                f.sospechosas.setText(cantidad("1","persona","paciente","estado"));
                f.positivas.setText(cantidad("1","estado","persona","estado"));
                f.negativas.setText(cantidad("3","persona","paciente","estado"));
                f.recuperadas.setText(cantidad("4","persona","paciente","estado"));
                f.areaPersonasEnRiesgo.setText(f.mostrarDatos());
                
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(f, ex.getMessage());
            }

        }

    }

  
    
    
    public String cantidad(String tipoEstado, String datoContar,String tabla,String estado) throws ClassNotFoundException {

        try {
            Connection cnx = Conexion.obtener();
            PreparedStatement consulta;
            consulta = cnx.prepareStatement("select "
                    + "count("+datoContar+") as cantidada "
                    + "from "+tabla+" where "+estado+"=" + tipoEstado);
            ResultSet rs = consulta.executeQuery();
            String totalc = "";

            if (rs.next()) {

                totalc = totalc + rs.getInt("cantidada") + " ";

            }

            if (totalc.equals("")) {
                totalc = "0";
            }
            return totalc;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";

    }

    public void ocultarOMostrar(Boolean instruccion) {

        f.jtNombre.setEditable(instruccion);
        f.jtDireccion.setEditable(instruccion);
        f.jtTelefono.setEditable(instruccion);
        f.jtDocumento.setEditable(instruccion);
        f.jtGenero.setEditable(instruccion);

    }

    public EstadoVO estado() {

        if (f.comboEstado.getSelectedItem().equals("Muerto")) {

            return EstadoVO.MUERTO;
        }
        if (f.comboEstado.getSelectedItem().equals("Sospechoso")) {

            return EstadoVO.SOSPECHOSO;
        }
        if (f.comboEstado.getSelectedItem().equals("Positivo")) {

            return EstadoVO.POSITIVO;
        }
        if (f.comboEstado.getSelectedItem().equals("Negativo")) {

            return EstadoVO.NEGATIVO;
        }
        if (f.comboEstado.getSelectedItem().equals("Recuperado")) {

            return EstadoVO.RECUPERADO;
        }
        return null;

    }

    public Boolean existeElemento(String datoBuscar, String tabla, String columna) throws ClassNotFoundException {

        try {
            Connection conexion = Conexion.obtener();
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("SELECT * FROM " + tabla + " WHERE " + columna + " = ?");
            consulta.setString(1, datoBuscar);

            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
        }

        return false;
    }

    public Boolean existeDocumento(String documento) throws ClassNotFoundException {
        try {
            Connection conexion = Conexion.obtener();
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("SELECT * FROM persona WHERE documento = ?");
            consulta.setString(1, documento);

            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {

                return true;
            }
            return false;
        } catch (SQLException e) {
        }

        return false;
    }

}
