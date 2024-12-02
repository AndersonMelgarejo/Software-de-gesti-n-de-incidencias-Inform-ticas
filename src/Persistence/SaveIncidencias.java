/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Structure.Colas.ColasIncidencias;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Renzo
 */
public class SaveIncidencias {
    public static String ARCHIVO="Incidencias.bin";    
    public static void Guardar(ColasIncidencias Lista){
       try{
           FileOutputStream fos=new FileOutputStream(ARCHIVO);
           ObjectOutputStream oos =  new ObjectOutputStream(fos);
           oos.writeObject(Lista);
           oos.close();
       }catch(Exception ex){
           JOptionPane.showMessageDialog(null,"ERROR no se puede guardar "+ex);
       }       
    }// fin guardar
    
    public static ColasIncidencias Recuperar(){
        ColasIncidencias Lista=new ColasIncidencias();
       try{
           FileInputStream fis =  new FileInputStream(ARCHIVO);
           ObjectInputStream ois = new ObjectInputStream(fis);
           Lista = (ColasIncidencias)ois.readObject();
           ois.close();
       }catch(Exception ex){
           JOptionPane.showMessageDialog(null,"ERROR no se puede recuperar..."+ex);
       }
       return Lista;
    }
}
