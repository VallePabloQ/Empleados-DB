/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Lenovo FX
 */
abstract class Persona {
    private String nombres,apellidos,direccion,telefono,fechN;

    public Persona(){}
    public Persona(String nombres, String apellidos, String direccion, String telefono, String fechN) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechN = fechN;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechN() {
        return fechN;
    }

    public void setFechN(String fechN) {
        this.fechN = fechN;
    }
    
    protected void agregar(){}
    protected void modificar(){}
    protected void eliminar(){}
}
