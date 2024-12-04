/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Structure.Pilas.PilaAsignacionPersonal;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Jim
 */
public class SaveAsignarPersonal {
    public static String FILE="PilasAsignarPersonal.bin";
    public static void Guardar(PilaAsignacionPersonal pila){
       try{
           FileOutputStream fos=new FileOutputStream(FILE);
           ObjectOutputStream oos =  new ObjectOutputStream(fos);
           oos.writeObject(pila);
           oos.close();
       }catch(Exception ex){
           JOptionPane.showInputDialog("ERROR no se puede guardar "+ex);
       }       
    }// fin guardar
    
    public static PilaAsignacionPersonal Recuperar(){
        PilaAsignacionPersonal pila =new PilaAsignacionPersonal();
       try{
           FileInputStream fis =  new FileInputStream(FILE);
           ObjectInputStream ois = new ObjectInputStream(fis);
           pila = (PilaAsignacionPersonal)ois.readObject();
           ois.close();
       }catch(Exception ex){
           JOptionPane.showInputDialog("ERROR no se puede recuperar..."+ex);
       }
       return pila;
    } 
}
