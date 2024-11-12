package Persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Structures.Arreglo_TipoIncidencias;

public class SaveTipoIncidencia {
  public static String FILE = "TipoIncidencia.bin";

  public static void saveTipoIncidencia(Arreglo_TipoIncidencias tipoIncidencia) {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE));
      oos.writeObject(tipoIncidencia);
      oos.close();
      System.out.println("Guardado");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Arreglo_TipoIncidencias loadTipoIncidencia() {
    Arreglo_TipoIncidencias tipoIncidencia = new Arreglo_TipoIncidencias(100);
    try {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE));
      tipoIncidencia = (Arreglo_TipoIncidencias) ois.readObject();
      ois.close();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return tipoIncidencia;
  }
}
