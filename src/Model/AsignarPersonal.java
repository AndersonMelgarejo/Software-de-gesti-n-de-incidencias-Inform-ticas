package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private LocalTime hora;
    private String estado;
    private String descripcion;

    public AsignarPersonal() {
    }

    public AsignarPersonal(String asignador, Incidencias incidencia, Personal personal, Date fecha, LocalTime hora,
            String estado, String descripcion) {
        this.asignador = asignador;
        this.incidencia = incidencia;
        this.personal = personal;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Object[] Registro(int id) {
        Object[] fila = { incidencia.getId(), getHoraFormat(), asignador, personal, getFechaFormat(), estado,
                descripcion };
        return fila;
    }

    @Override
    public String toString() {
        return "------------------------------------" +
                "\n ID de la incidencia......... " + incidencia.getId() +
                "\n Hora registrada............. " + getHoraFormat() +
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

    public String getHoraFormat() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        return hora.format(formato);
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
