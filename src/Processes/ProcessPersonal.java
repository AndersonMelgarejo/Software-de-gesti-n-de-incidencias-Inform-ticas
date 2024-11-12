/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processes;
import View.UI_Personal;
import ArrayList.ListaPersonal;
import Model.Personal;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Renzo
 */
public class ProcessPersonal {   
    public static void Limpiar(UI_Personal pe){
        pe.txtNombre.setText("");
        pe.txtApellido.setText("");
        pe.txtCorreo.setText("");
        pe.txtMovil.setText("");
        pe.txtPass.setText("");
        pe.txtUser.setText("");
        pe.cbxCargo.setSelectedIndex(0);
        pe.cbxOrdenar.setSelectedIndex(0);
    }
    public static void Llenar(UI_Personal pe,Personal p){
        pe.txtNombre.setText(p.getNombre());
        pe.txtApellido.setText(p.getApellido());
        pe.txtCorreo.setText(p.getCorreo());
        pe.txtMovil.setText(p.getTelefono());
        pe.txtUser.setText(p.getUser());
        pe.txtPass.setText(p.getPassword());
        pe.cbxCargo.setSelectedItem(p.getCargo());
    }
    public static void MostrarEst(UI_Personal pe,ListaPersonal lista){
        String campos[]={"ID","Nombre","Apellido","Correo","Telefono","Usuario","Contraseña","Cargo"}; 
        DefaultTableModel mt = new DefaultTableModel(null,campos);
        pe.tblPersonal.setModel(mt);
        for (int i = 0; i < lista.Cantidad(); i++) {
           Personal p = lista.Recuperar(i);

            // Verifica si el objeto Personal es nulo antes de llamar a Registro
            if (p != null) {
                mt.addRow(p.Registro(i + 1));
            } else {            
            }
        }
    }//fin mostrarest
    public static Personal LeerPersonal(UI_Personal pe){
        if(pe.txtNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(pe, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(pe.txtApellido.getText().isEmpty()){
            JOptionPane.showMessageDialog(pe, "El apellido no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(pe.txtCorreo.getText().isEmpty()){
            JOptionPane.showMessageDialog(pe, "El correo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(pe.txtMovil.getText().isEmpty()){
            JOptionPane.showMessageDialog(pe, "El teléfono no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(pe.txtUser.getText().isEmpty()){
            JOptionPane.showMessageDialog(pe, "El nombre de usuario no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(pe.txtPass.getText().isEmpty()){
            JOptionPane.showMessageDialog(pe, "Debe de escribir una contraseña", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (pe.cbxCargo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(pe, "Debe un cargo para el personal", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Personal p =  new Personal();
        p.setNombre( pe.txtNombre.getText());
        p.setApellido(pe.txtApellido.getText());
        p.setCorreo(pe.txtCorreo.getText());
        p.setTelefono(pe.txtMovil.getText());
        p.setUser(pe.txtUser.getText());
        p.setPassword(pe.txtPass.getText());
        p.setCargo(pe.cbxCargo.getSelectedItem().toString());
        p.segunCargo();
        return p;
    }
    public static void generarUser(UI_Personal pe){
        String uuid = UUID.randomUUID().toString().substring(0, 4);
        String nombres = pe.txtNombre.getText().replace(" ", "");
        String apellidos = pe.txtApellido.getText().replace(" ", "");
        String user=(nombres + '.' + apellidos + '.' + uuid).toLowerCase();
        pe.txtUser.setText(user);
    }
}
