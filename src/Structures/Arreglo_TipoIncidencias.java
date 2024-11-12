package Structures;

import Model.TipoIncidencia;
import java.io.Serializable;

public class Arreglo_TipoIncidencias implements Serializable {

  public static void setContador(int n_contador) {
    contador = n_contador;
  }

  public static int contador = 0;

  private TipoIncidencia[] arreglo;

  public Arreglo_TipoIncidencias(int cant) {
    arreglo = new TipoIncidencia[cant];
  }

  public void agregar(TipoIncidencia tipoIncidencia) {
    arreglo[contador] = tipoIncidencia;
    contador++;
  }

  public void eliminar(int i) {
    if (i >= 0 && i < contador) {
      for (int j = i; j < contador - 1; j++) {
        arreglo[j] = arreglo[j + 1];
      }
      arreglo[contador - 1] = null; // Eliminar referencia al último elemento
      contador--;
    } else {
      System.out.println("Índice fuera de rango.");
    }
  }

  public TipoIncidencia obtener(int i) {
    return arreglo[i];
  }

  public int cantidad() {
    return contador;
  }

  public void modificar(int i, TipoIncidencia tipoIncidencia) {
    arreglo[i] = tipoIncidencia;
  }

  public TipoIncidencia[] getArreglo() {
    return arreglo;
  }

  public void setArreglo(TipoIncidencia[] arreglo) {
    this.arreglo = arreglo;
  }

  public void ActualizarContador() {
    contador = 0;
    for (int pos = 0; pos < arreglo.length; pos++) {
      if (arreglo[pos] != null) {
        contador++;
      }
    }
  }

}
