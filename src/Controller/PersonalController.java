/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ArrayList.ListaPersonal;
import Model.Personal;
import Persistence.SavePersonal;
import Processes.ProcessPersonal;
import View.UI_Dashboard;
import View.UI_Personal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author Renzo
 */
public class PersonalController extends PanelController implements ActionListener{

    UI_Personal perso;
    ListaPersonal lista;
    Personal pe;
    int pos;
    public PersonalController(UI_Personal perso, UI_Dashboard apo) {
        super(perso, apo);
        this.perso=perso;
        super.showWindow(panel);
        lista =  new ListaPersonal();
        lista= SavePersonal.RecuperarEstudiantes();
        ProcessPersonal.MostrarEst(perso, lista);
        addListeners();
    }

    @Override
    protected void addListeners() {
        this.perso.btnAutoGenerarUser.addActionListener(this);
        this.perso.btnRegistrar.addActionListener(this);
        this.perso.btnConsultar.addActionListener(this);
        this.perso.btnActualizar.addActionListener(this);
        this.perso.btnEliminar.addActionListener(this);
    }

    @Override
    protected void reloadWindow() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        if (e.getSource() == perso.btnAutoGenerarUser) {                        
            ProcessPersonal.generarUser(perso);
        }
        if(e.getSource()==perso.btnRegistrar){
           pe = ProcessPersonal.LeerPersonal(perso);
           lista.Agregar(pe);
           SavePersonal.GuardarPersonal(lista);
           ProcessPersonal.Limpiar(perso);
           ProcessPersonal.MostrarEst(perso, lista);
           JOptionPane.showMessageDialog(null,"EL PERSONAL FUE REGISTRADO"); 
        }
        if (e.getSource() == perso.btnConsultar) { 
            String id = JOptionPane.showInputDialog("➤ Ingrese el ID para buscar");
        
            if (id == null) {
                return;
            }    
            try {
                int pos = Integer.parseInt(id) - 1;
                
                if (pos >= 0 && pos < lista.Cantidad()) {
                    Personal p = lista.Recuperar(pos);
                        
                    perso.txtNombre.setText(p.getNombre());
                    perso.txtApellido.setText(p.getApellido());
                    perso.txtCorreo.setText(p.getCorreo());
                    perso.txtMovil.setText(p.getTelefono());
                    perso.txtUser.setText(p.getUser());
                    perso.txtPass.setText(p.getPassword());
                    perso.cbxCargo.setSelectedItem(p.getCargo());
                } else {
                    JOptionPane.showMessageDialog(null, "ID no encontrado en la lista.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID inválido. Por favor, ingrese un número.");
            }
        }

        if(e.getSource()==perso.btnActualizar){            
            pe = ProcessPersonal.LeerPersonal(perso);
            lista.Actualizar(pos, pe);
            SavePersonal.GuardarPersonal(lista);
            ProcessPersonal.Limpiar(perso);
            ProcessPersonal.MostrarEst(perso, lista);
        }
        if (e.getSource() == perso.btnEliminar) {
    // Solicita el nombre de usuario a eliminar
    String usuario = JOptionPane.showInputDialog(null, "Ingrese el nombre de usuario que desea eliminar:");
    if (usuario != null && !usuario.isEmpty()) {
        // Confirma la eliminación
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean eliminado = ProcessPersonal.eliminarUsuario(usuario, lista);
            if (eliminado) {
                SavePersonal.GuardarPersonal(lista); // Guarda los cambios en el archivo
                ProcessPersonal.Limpiar(perso); // Limpia los campos de entrada
                ProcessPersonal.MostrarEst(perso, lista); // Actualiza la tabla
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de usuario válido.");
    }
}

    }
    
}
