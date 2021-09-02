/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Lenovo FX
 */
public class Empleado extends Persona {
    private String codEmpleado,puesto;
    private int id,idPst;
    Conexion cn;

    public Empleado(){}
    public Empleado(int idPst, String puesto) {
        this.idPst = idPst;
        this.puesto = puesto;
    }
    
    public Empleado(int id, String codEmpleado, String puesto, String nombres, String apellidos, String direccion, String telefono, String fechN, int idPst) {
        super(nombres, apellidos, direccion, telefono, fechN);
        this.codEmpleado = codEmpleado;
        this.puesto = puesto;
        this.id = id;
        this.idPst = idPst;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdPst() {
        return idPst;
    }

    public void setIdPst(int idPst) {
        this.idPst = idPst;
    }

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    @Override
    public String toString() {
        return idPst + " - " + puesto;
    }
    
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "SELECT id_empleado as id,codigo,nombres,apellidos,direccion,telefono,fecha_nacimiento,puesto FROM empleados inner join puestos USING(id_puesto);";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","Codigo","Nombres","Apellidos","Direccion","Telefono","F. Nacimiento","Puesto"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[8];
            while(consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("codigo");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                datos[7] = consulta.getString("puesto");
                tabla.addRow(datos);
            }
            
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return tabla;
    }
    
    public ArrayList<Empleado> getEmpleado() {
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        
        try{
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "SELECT * FROM puestos;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            
            while(consulta.next()) {
                Empleado empleados = new Empleado();
                empleados.setIdPst(consulta.getInt("id_puesto"));
                empleados.setPuesto(consulta.getString("puesto"));
                listaEmpleados.add(empleados);
            }
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return listaEmpleados;
    }
    
    @Override
    public void agregar(){
        try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "INSERT INTO empleados(codigo,nombres,apellidos,direccion,telefono,fecha_nacimiento,id_puesto) VALUES(?,?,?,?,?,?,?);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getCodEmpleado());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFechN());
            parametro.setString(7, getPuesto());
            
            int exec = parametro.executeUpdate();
            cn.cerrar_conexion();
            
            JOptionPane.showMessageDialog(null,Integer.toString(exec) + " Registro Ingresado", "Mensaje",JOptionPane.INFORMATION_MESSAGE);
        
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        /*System.out.println("Codigo del Empleado: " + getCodEmpleado());
        System.out.println("Puesto: " + getPuesto());
        System.out.println("Nombre: " + getNombres());
        System.out.println("Apellidos: " + getApellidos());
        System.out.println("Dirección: " + getDireccion());
        System.out.println("Telefono: " + getTelefono());
        System.out.println("F Nacimiento: " + getFechN());
        System.out.println("____________________________________");*/
    }
    @Override
    public void modificar() {
        try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "UPDATE empleados SET codigo = ?,nombres = ?,apellidos = ?,direccion = ?,telefono = ?,fecha_nacimiento = ?,id_puesto = ? WHERE id_empleado = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getCodEmpleado());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFechN());
            parametro.setString(7, getPuesto());
            parametro.setInt(8,getId());
            
            int exec = parametro.executeUpdate();
            cn.cerrar_conexion();
            
            JOptionPane.showMessageDialog(null,Integer.toString(exec) + " Registro Actualizado", "Mensaje",JOptionPane.INFORMATION_MESSAGE);
        
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
    }
    @Override
    public void eliminar() {
        try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "DELETE from empleados WHERE id_empleado = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,getId());
            
            int exec = parametro.executeUpdate();
            cn.cerrar_conexion();
            
            JOptionPane.showMessageDialog(null,Integer.toString(exec) + " Registro Eliminado", "Mensaje",JOptionPane.INFORMATION_MESSAGE);
        
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
    }
}
