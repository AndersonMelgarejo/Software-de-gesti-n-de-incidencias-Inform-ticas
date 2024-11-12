package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author franc
 */
public class TipoIncidencia implements Serializable {
  private String nombre;
  private String nivel;
  private String categoria;
  private String descripcion;
  private Date fechaMod;

  public Object[] Registro(int i) {
    Object[] registro = { i, nombre, nivel, categoria, descripcion, getFechaModFormateada() };
    return registro;
  }

  public TipoIncidencia(String nombre, String nivel, String categoria, String descripcion, Date fechaMod) {
    this.nombre = nombre;
    this.nivel = nivel;
    this.categoria = categoria;
    this.descripcion = descripcion;
    this.fechaMod = fechaMod;
  }

  public TipoIncidencia() {
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNivel() {
    return nivel;
  }

  public void setNivel(String nivel) {
    this.nivel = nivel;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Date getFechaMod() {
    return fechaMod;
  }

  public void setFechaMod(Date fechaMod) {
    this.fechaMod = fechaMod;
  }

  public String getFechaModFormateada() {
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    return formato.format(fechaMod);
  }

}
