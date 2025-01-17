package Model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jim
 */
public class AsignarPersonal implements Serializable {
    private String asignador;
    private Incidencias incidencia;
    private Personal personal;
    private Date fecha;
    private Timestamp hora;
    private String estado;
    private String descripcion;

    public AsignarPersonal() {
    }

    public AsignarPersonal(String asignador, Incidencias incidencia, Personal personal, Date fecha,
            Timestamp hora, String estado, String descripcion) {
        this.asignador = asignador;
        this.incidencia = incidencia;
        this.personal = personal;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Object[] Registro(int id) {
        Object[] fila = { incidencia.getId(), getFechaActual(), asignador, personal, getFechaFormat(), estado,
                descripcion };
        return fila;
    }

    @Override
    public String toString() {
        return "------------------------------------" +
                "\n ID de la incidencia......... " + incidencia.getId() +
                "\n Fecha - Hora registrada..... " + getFechaFormat() +
                "\n Asignador de la solución.... " + asignador +
                "\n Personal asignado........... " + personal +
                "\n Fecha de la solución........ " + getFechaFormat() +
                "\n Estado...................... " + estado +
                "\n Descripcion................. " + descripcion +
                "\n------------------------------------";
    }

    public void segunEstado() {
        if (estado.equals("EN PROCESO"))
            estado = "EN PROCESO";
        if (estado.equals("ATENDIDO"))
            estado = "ATENDIDO";
        if (estado.equals("DERIVADO"))
            estado = "DERIVADO";
    }

    public String getFechaFormat() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }

    public Timestamp getFechaActual() {
        if (hora == null) {
            // Si fecha es null, asigna la fecha actual
            hora = new Timestamp(System.currentTimeMillis());
        }
        Timestamp timestamp = new Timestamp(hora.getTime());
        return timestamp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAsignador() {
        return asignador;
    }

    public void setAsignador(String asignador) {
        this.asignador = asignador;
    }

    public Incidencias getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencias incidencia) {
        this.incidencia = incidencia;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
