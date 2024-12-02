package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author @YUFFRY
 */
public class Departamento implements Serializable{
    
    private String nombre;
    private String pabellon;
    private String piso;
    private String salon;
    private Date fecha;
    private String ambiente;
    private String user;

    public Departamento() {
    }
    
    public Departamento(String nombre, String pabellon, String piso, String salon, Date fecha, String direccion,String user) {
        this.nombre = nombre;
        this.pabellon = pabellon;
        this.piso = piso;
        this.salon = salon;
        this.fecha = fecha;
        this.ambiente = direccion;
        this.user=user;
    }
    
    public void generarAmbiente(){
        ambiente=pabellon+piso+salon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPabellon() {
        return pabellon;
    }

    public void setPabellon(String pabellon) {
        this.pabellon = pabellon;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public String getFechaResFormateada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }
    
    public Object[] Registro(int id){
        Object[] fila = {id,user,nombre,pabellon,piso,salon,getFechaResFormateada(),ambiente};
        return fila;
    }
}
