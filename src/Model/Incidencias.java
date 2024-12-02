/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Renzo
 */
public class Incidencias implements Serializable{
    private String area;
    private Date fecha;
    private String descripcion;
    private Departamento departamento;
    private TipoIncidencia tipoincidencia;
    private String user;

    public Incidencias() {
    }
    
    public Incidencias(String area, Date fecha,String descripcion, Departamento departamento, TipoIncidencia tipoincidencia,String user) {
        this.area = area;
        this.fecha = fecha;
        this.descripcion=descripcion;
        this.departamento = departamento;
        this.tipoincidencia = tipoincidencia;
        this.user=user;
    }

    public Object[] Registro(int id){
        Object[] fila={id,user,departamento.getNombre(),area,getFechaFormat(),descripcion,tipoincidencia.getNombre(),tipoincidencia.getNivel()};
        return fila;       
    }
    
    public String getFechaFormat() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
        
}
