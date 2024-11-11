/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Renzo
 */
public class Personal {
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String user;
    private String contraseña;
    private String cargo;
    
    public void segunCargo(){
        if(cargo.equals("Soporte tecnico"))cargo="Soporte tecnico";
        if(cargo.equals("Administrador de sistemas"))cargo="Administrador de sistemas";
        if(cargo.equals("Especialista de redes"))cargo="Especialista de redes";
        if(cargo.equals("Seguridad informatica"))cargo="Seguridad informatica";
    }

    public Personal() {    }
   
    public Personal(String nombre, String apellido, String correo, String telefono, String user, String contraseña, String cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.user = user;
        this.contraseña = contraseña;
        this.cargo = cargo;
    }
        
    public String getNombre() {        return nombre;   }
    public void setNombre(String nombre) {        this.nombre = nombre;  }
    public String getApellido() {       return apellido;}
    public void setApellido(String apellido) {        this.apellido = apellido;    }
    public String getCorreo() {        return correo;    }
    public void setCorreo(String correo) {        this.correo = correo;    }
    public String getTelefono() {        return telefono;    }
    public void setTelefono(String telefono) {        this.telefono = telefono;    }
    public String getUser() {        return user;    }
    public void setUser(String user) {        this.user = user;    }
    public String getContraseña() {        return contraseña;    }
    public void setContraseña(String contraseña) {        this.contraseña = contraseña;    }
    public String getCargo() {        return cargo;    }
    public void setCargo(String cargo) {        this.cargo = cargo;    }
}
