/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Renzo
 */
public class Incidencias {
    private String area;
    private Date fecha;
    private String descripcion;
    private Departamento departamento;
    private TipoIncidencia tipoincidencia;

    public Incidencias(String area, Date fecha, Departamento departamento, TipoIncidencia tipoincidencia) {
        this.area = area;
        this.fecha = fecha;
        this.departamento = departamento;
        this.tipoincidencia = tipoincidencia;
    }

    public Object[] Registro(int id){
        Object[] fila={id,departamento,area,fecha,tipoincidencia,descripcion};
        return fila;       
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
}
