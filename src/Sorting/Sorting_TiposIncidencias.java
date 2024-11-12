package Sorting;

import Model.TipoIncidencia;
import Structures.Arreglo_TipoIncidencias;

/**
 *
 * @author franc
 */
public class Sorting_TiposIncidencias {

  public static TipoIncidencia[] bubbleSort(TipoIncidencia[] arreglo, boolean asc) {
    TipoIncidencia temp;
    for (int i = 0; i < Arreglo_TipoIncidencias.contador; i++) {
      for (int j = 1; j < (Arreglo_TipoIncidencias.contador - i); j++) {
        int comparacion = arreglo[j - 1].getNombre().compareTo(arreglo[j].getNombre());
        if ((asc && comparacion > 0) || (!asc && comparacion < 0)) {
          temp = arreglo[j - 1];
          arreglo[j - 1] = arreglo[j];
          arreglo[j] = temp;
        }
      }
    }
    return arreglo;
  }

  // ordenamiento por insercion, criterio nivel (getNivel) alta, media, baja
  public static TipoIncidencia[] insertionSort(TipoIncidencia[] arreglo, boolean asc) {
    TipoIncidencia temp;
    for (int i = 1; i < Arreglo_TipoIncidencias.contador; i++) {
      temp = arreglo[i];
      int j = i - 1;
      while (j >= 0 && arreglo[j] != null && temp != null) {
        int comparacion = Integer.compare(getNivelPrioridad(arreglo[j].getNivel()), getNivelPrioridad(temp.getNivel()));
        if ((asc && comparacion > 0) || (!asc && comparacion < 0)) {
          arreglo[j + 1] = arreglo[j];
          j--;
        } else {
          break;
        }
      }
      arreglo[j + 1] = temp;
    }
    return arreglo;
  }

  // Método para obtener la prioridad del nivel
  private static int getNivelPrioridad(String nivel) {
    switch (nivel) {
      case "Alta":
        return 1;
      case "Media":
        return 2;
      case "Baja":
        return 3;
      default:
        return Integer.MAX_VALUE; // Para niveles desconocidos
    }
  }

  // ordenamiento por seleccion, criterio fechaMod (getFechaMod)
  public static TipoIncidencia[] selectionSort(TipoIncidencia[] arreglo, boolean asc) {
    for (int i = 0; i < Arreglo_TipoIncidencias.contador - 1; i++) {
      int min = i;
      for (int j = i + 1; j < Arreglo_TipoIncidencias.contador; j++) {
        int comparacion = arreglo[j].getFechaMod().compareTo(arreglo[min].getFechaMod());
        if ((asc && comparacion < 0) || (!asc && comparacion > 0)) {
          min = j;
        }
      }
      TipoIncidencia temp = arreglo[min];
      arreglo[min] = arreglo[i];
      arreglo[i] = temp;
    }
    return arreglo;
  }
}
