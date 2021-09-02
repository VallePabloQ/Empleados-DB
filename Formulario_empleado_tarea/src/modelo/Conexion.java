/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo FX
 */
public class Conexion {
    public Connection conexionBD;
    private final String puerto = "3306";
    private final String bd = "db_empresa_2021";
    private final String urlConexion = String.format("jdbc:mysql://127.0.0.1:%s/%s",puerto, bd);
    private final String usuario = "root";
    private final String contra = "root";
    private final String jbdc = "com.mysql.cj.jdbc.Driver";
    
    public void abrir_conexion() {
        try{
        Class.forName(jbdc);
        conexionBD = DriverManager.getConnection(urlConexion,usuario,contra);
        //JOptionPane.showMessageDialog(null,"Conexion Exitosa","Mensaje", JOptionPane.INFORMATION_MESSAGE);
        
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            System.out.println("Error:"+ ex.getMessage());
        }
    }
    
    public void cerrar_conexion() {
        try{
        conexionBD.close();
        
        }catch(SQLException ex){
            System.out.println("Error:"+ ex.getMessage());
        }
    }
}
