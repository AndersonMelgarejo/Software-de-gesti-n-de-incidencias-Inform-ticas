package Persistence;

import Structure.ListasDobles.ListaDoble;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author YUFFRY
 */
public class SaveDepartamento {
    public static String FILE="Deparamento.bin";
    public static void GuardarLista(ListaDoble Lista){
       try{
           FileOutputStream fos=new FileOutputStream(FILE);
           ObjectOutputStream oos =  new ObjectOutputStream(fos);
           oos.writeObject(Lista);
           oos.close();
       }catch(Exception ex){
           JOptionPane.showInputDialog("ERROR no se puede guardar "+ex);
       }       
    }// fin guardar
    
    public static ListaDoble RecuperarLista(){
        ListaDoble Lista=new ListaDoble();
       try{
           FileInputStream fis =  new FileInputStream(FILE);
           ObjectInputStream ois = new ObjectInputStream(fis);
           Lista = (ListaDoble)ois.readObject();
           ois.close();
       }catch(Exception ex){
           JOptionPane.showMessageDialog(null,"ERROR no se puede recuperar..."+ex);
       }
       return Lista;
    }
}
