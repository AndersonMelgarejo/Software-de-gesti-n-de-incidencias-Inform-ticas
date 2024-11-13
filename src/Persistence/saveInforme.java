package Persistence;

import Structures.ListasEnlazadas.ListaEnlazada;
import java.io.*;
import javax.swing.JOptionPane;

public class saveInforme {

    public static String ARCHIVO2 = "DatosInforme.bin";

    public static void GuardarLista(ListaEnlazada Lista) {
        try {
            FileOutputStream fos = new FileOutputStream(ARCHIVO2);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Lista);
            oos.close();
        } catch (Exception ex) {
            JOptionPane.showInputDialog( "ERROR no se puede guardar " + ex);
        }
    }// fin guardar

    public static ListaEnlazada RecuperarLista() {
        ListaEnlazada Lista = new ListaEnlazada();
        try {
            FileInputStream fis = new FileInputStream(ARCHIVO2);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Lista = (ListaEnlazada) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,  "ERROR no se puede recuperar " + ex);
        }
        return Lista;
    }
}
