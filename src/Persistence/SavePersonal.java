/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;
import ArrayList.ListaPersonal;
import java.io.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Renzo
 */
public class SavePersonal {
    public static String FILE="Personal.bin";
    
    public static void GuardarPersonal(ListaPersonal Lista){
       try{
           FileOutputStream fos=new FileOutputStream(FILE);
           ObjectOutputStream oos =  new ObjectOutputStream(fos);
           oos.writeObject(Lista);
           oos.close();
       }catch(Exception ex){
           JOptionPane.showInputDialog("ERROR no se puede guardar "+ex);
       }       
    }
    
    public static ListaPersonal RecuperarEstudiantes(){
        ListaPersonal Lista=new ListaPersonal();
       try{
           FileInputStream fis =  new FileInputStream(FILE);
           ObjectInputStream ois = new ObjectInputStream(fis);
           Lista = (ListaPersonal)ois.readObject();
           ois.close();
       }catch(Exception ex){
           JOptionPane.showMessageDialog(null,"ERROR no se puede recuperar..."+ex);
       }
       return Lista;
    }
}
