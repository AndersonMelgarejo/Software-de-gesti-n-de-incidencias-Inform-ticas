package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Informe implements Serializable {

    private Incidencias incidencia;
    private String accionesTomadas;
    private Date fechaRes;
    private String estado;
    private AsignarPersonal personal;
    private String descripcion;

    public Informe(Incidencias incidencia, AsignarPersonal personal, String accionesTomadas, String estado, String descripcion, Date fechaRes) {
        this.incidencia = incidencia;  
        this.personal = personal;  
        this.accionesTomadas = accionesTomadas;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fechaRes = fechaRes;
    }

    // Método que devuelve el registro del informe en un formato de fila de tabla
    public Object[] getRegistro() {
        return new Object[]{
            incidencia.getId(),
            accionesTomadas,
            estado,
            personal.getPersonal(),
            descripcion,
            getFechaResFormateada()
        };
    }

    @Override
    public String toString() {
        return String.format(
                "------------------------------------\n"
                + "ID de la incidencia......... %s\n"
                + "Asignado.................... %s\n"
                + "Acciones tomadas............ %s\n"
                + "Estado...................... %s\n"
                + "Fecha de resolución......... %s\n"
                + "Descripción................. %s\n"
                + "------------------------------------",
                incidencia.getId(),
                personal.getPersonal(), // Muestra el personal asignado
                accionesTomadas,
                estado,
                getFechaResFormateada(),
                descripcion
        );
    }

    public String getFechaResFormateada() {
        if (fechaRes != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            return formato.format(fechaRes);
        }
        return "Fecha no disponible"; // Si fechaRes es null, devuelve un valor por defecto
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

    public AsignarPersonal getPersonal() {
        return personal;
    }

    public void setPersonal(AsignarPersonal personal) {
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
