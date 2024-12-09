/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Renzo
 */
public class Incidencias implements Serializable{
    private int id;
    private String user;
    private Timestamp fecha;
    private Departamento departamento;
    private String area;    
    private String descripcion;
    private Date fechaincidencia;
    private TipoIncidencia tipoincidencia;    
    private Personal personal;

    public Incidencias() {  
        this.id=id;
    }

    public Incidencias(String user, Timestamp fecha, Departamento departamento, String area, String descripcion, Date fechaincidencia, TipoIncidencia tipoincidencia, Personal personal) {       
        this.user = user;
        this.fecha = fecha;
        this.departamento = departamento;
        this.area = area;
        this.descripcion = descripcion;
        this.fechaincidencia = fechaincidencia;
        this.tipoincidencia = tipoincidencia;
        this.personal = personal;
    }    
    
    public Object[] Registro(int i){
        Object[] fila={id,user,getFechaActual(),departamento.getNombre(),
                       area,descripcion,getFechaFormat(),tipoincidencia.getNombre(),
                       tipoincidencia.getNivel(),personal.getUser()};
        return fila;       
    }
    
    @Override
    public String toString(){
        return tipoincidencia.getNombre();
    }
    public String detalles(){
        return String.format(
        "ID: %d\nUsuario: %s\nFecha: %s\nDepartamento: %s\nÁrea: %s\nDescripción: %s\nFecha de la incidencia: %s\nTipo: %s\nNivel de prioridad: %s\nCliente: %s",
        id,                   // ID
        user,                 // Usuario
        getFechaActual(),                // Fecha
        departamento.getNombre(),         // Departamento
        area,                 // Área                    
        descripcion,          // Descripción
        getFechaFormat(),          // Fecha de la incidencia
        tipoincidencia.getNombre(), // Tipo
        tipoincidencia.getNivel(),  // Nivel
        personal.getUser()    // Cliente
    );
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
    
    public String getFechaFormat() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fechaincidencia);
    }
    
    public Timestamp getFechaActual() {
    if (fecha == null) {
        // Si fecha es null, asigna la fecha actual
        fecha = new Timestamp(System.currentTimeMillis());
    }
    Timestamp timestamp = new Timestamp(fecha.getTime());
    return timestamp;
}

    
    public TipoIncidencia getTipoincidencia() {
        return tipoincidencia;
    }

    public void setTipoincidencia(TipoIncidencia tipoincidencia) {
        this.tipoincidencia = tipoincidencia;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getFechaincidencia() {
        return fechaincidencia;
    }

    public void setFechaincidencia(Date fechaincidencia) {
        this.fechaincidencia = fechaincidencia;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    
    
}
