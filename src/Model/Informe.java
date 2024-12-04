package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Informe implements Serializable {

    private Incidencias incidencia;
    private String accionesTomadas;
    private Date fechaRes;
    private String estado;
    private Personal personal;
    private String descripcion;

    public Informe() {
    }

    public Informe(Incidencias incidencia, String accionesTomadas, String estado, Personal personal, String descripcion, Date fechaRes) {
        this.incidencia = incidencia;
        this.accionesTomadas = accionesTomadas;
        this.estado = estado;
        this.personal = personal;
        this.descripcion = descripcion;
        this.fechaRes = fechaRes;
    }

    public Object[] getRegistro() {
        Object[] fila = {
            incidencia.getId(),
            accionesTomadas,
            estado,
            personal, 
            descripcion,
            getFechaResFormateada()
        };
        return fila;
    }

    @Override
    public String toString() {
        return "------------------------------------" +
            "\n ID de la incidencia......... " + incidencia.getId() +
            "\n Acciones tomadas............ " + accionesTomadas +
            "\n Estado...................... " + estado +
            "\n Personal asignado........... " + personal.getNombre() +
            "\n Fecha de resolución......... " + getFechaResFormateada() +
            "\n Descripción................. " + descripcion +
            "\n------------------------------------";
    }

    public String getFechaResFormateada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fechaRes);
    }

    // Getters y Setters
    public Incidencias getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencias incidencia) {
        this.incidencia = incidencia;
    }

    public String getAccionesTomadas() {
        return accionesTomadas;
    }

    public void setAccionesTomadas(String accionesTomadas) {
        this.accionesTomadas = accionesTomadas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRes() {
        return fechaRes;
    }

    public void setFechaRes(Date fechaRes) {
        this.fechaRes = fechaRes;
    }
}
