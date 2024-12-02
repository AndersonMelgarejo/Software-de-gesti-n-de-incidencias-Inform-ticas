package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jim
 */
public class AsignarPersonal {
    private String asignador;
    private Incidencias incidencia;
    private Personal personal;
    private Date fecha;
    private String estado;
    private String descripcion;

    public AsignarPersonal() {
    }    

    public AsignarPersonal(String asignador, Incidencias incidencia, Personal personal, Date fecha, String estado, String descripcion) {
        this.asignador = asignador;
        this.incidencia = incidencia;
        this.personal = personal;
        this.fecha = fecha;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Object[] Registro(int id){
            Object[] fila={incidencia.Registro(id),asignador,personal.getUser(),getFechaFormat(),estado,descripcion};
        return fila;       
    }
    public void segunEstado(){
        if(estado.equals("EN PROCESO"))estado="EN PROCESOS";
        if(estado.equals("ATENDIDO"))estado="ATENDIDO";
        if(estado.equals("DERIVADO"))estado="DERIVADO";
    }
    public String getFechaFormat() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
