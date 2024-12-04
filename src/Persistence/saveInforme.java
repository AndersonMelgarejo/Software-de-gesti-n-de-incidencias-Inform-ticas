package Persistence;
import java.io.*;
import javax.swing.JOptionPane;
import Structures.Arboles.*;
public class saveInforme {
    public static void GuardarEnArchivo(ArbolInforme arbol){
        try{
           FileOutputStream fos = new FileOutputStream("ArchivoArbol.bin");
           ObjectOutputStream oos = new ObjectOutputStream(fos);
           oos.writeObject(arbol);
           oos.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,
               "ERROR no se puede guardar en archivo "+ex);
        }
    }//fin guardar
    public static ArbolInforme RecuperarDeArchivo(){
      ArbolInforme arbol = new ArbolInforme();  
        try{
           FileInputStream fis = new FileInputStream("ArchivoArbol.bin");
           ObjectInputStream ois = new ObjectInputStream(fis);
           arbol = (ArbolInforme)ois.readObject();
           ois.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,
               "ERROR no se puede recuperar de archivo"+ex);
        }
        return arbol;
    }//fin recuperar
}//fin class
