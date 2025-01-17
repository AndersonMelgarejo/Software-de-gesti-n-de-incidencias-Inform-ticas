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
    boolean editing = true;
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
        this.perso.btnOrdenar.addActionListener(this);
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
            handleRegistrarAction();
        }
        if (e.getSource() == perso.btnConsultar) { 
            handleConsultarAction();
        }
        if(e.getSource()==perso.btnActualizar){
            handleActualizaraAction();
        }        
        if (e.getSource() == perso.btnEliminar) {    
            handleEliminarAction();
        }
        if(e.getSource()==perso.btnOrdenar){
            handleOrdenarAction();
        }
    }

    private void handleRegistrarAction(){
        pe = ProcessPersonal.LeerPersonal(perso);
        if(pe==null){
            return;
        }
        lista.Agregar(pe);
        SavePersonal.GuardarPersonal(lista);
        ProcessPersonal.Limpiar(perso);
        ProcessPersonal.MostrarEst(perso, lista);
        JOptionPane.showMessageDialog(null,"EL PERSONAL FUE REGISTRADO");
    }
    
    private void handleConsultarAction(){
        String id = JOptionPane.showInputDialog("➤ Ingrese el ID para buscar");
        
            if (id == null) {
                return;
            }    
            try {
                int pos = Integer.parseInt(id) - 1;
                
                if (pos >= 0 && pos < lista.Cantidad()) {
                    Personal p = lista.Recuperar(pos);
                        
                    ProcessPersonal.Llenar(perso, p);
                } else {
                    JOptionPane.showMessageDialog(null, "ID no encontrado en la lista.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID inválido. Por favor, ingrese un número.");
            }
    }
    
    private void handleActualizaraAction(){
        if (editing) {
                String id = JOptionPane.showInputDialog("Ingrese el ID del personal a actualizar");
                if (id == null) {
                    return;
                }
                pos = Integer.parseInt(id) - 1;
                if (pos >= 0 && pos < lista.Cantidad()) {
                    Personal actuper = lista.Recuperar(pos);
                    ProcessPersonal.Llenar(perso, actuper);
                    perso.btnRegistrar.setEnabled(false);
                    perso.btnEliminar.setEnabled(false);
                    perso.btnConsultar.setEnabled(false);
                    editing = false;
                } else {
                    JOptionPane.showMessageDialog(perso, "ID no encontrado");
                }
            } else {
                Personal pe = ProcessPersonal.LeerPersonal(perso);
                if (pe == null) {
                    return;
                }
                lista.Actualizar(pos, pe);
                SavePersonal.GuardarPersonal(lista);
                ProcessPersonal.Limpiar(perso);
                ProcessPersonal.MostrarEst(perso, lista);
                perso.btnRegistrar.setEnabled(true);
                perso.btnEliminar.setEnabled(true);
                perso.btnConsultar.setEnabled(true);
                editing = true;
            }    
    }
    
    private void handleEliminarAction(){
        String id = JOptionPane.showInputDialog("➤ Ingrese el ID para eliminar");
        if (id == null) {
            return;
        } 
        int pos = Integer.parseInt(id) - 1;
        if (pos >= 0 && pos < lista.Cantidad()) {
            // Obtener el objeto a eliminar
            Personal pe = lista.Recuperar(pos);

            // Mostrar la información del objeto y solicitar confirmación
            int confirm = JOptionPane.showConfirmDialog(
                    perso,
                    "¿Está seguro de que desea eliminar al personal?\n" +
                            "ID: " + (pos + 1) + "\n" +
                            "Nombre: " + pe.getNombre() + "\n" +
                            "Apellido: " + pe.getApellido()+ "\n" +
                            "Usuario: " + pe.getUser()+ "\n"+
                            "Cargo: " + pe.getCargo()+ "\n",                            
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION);

            // Eliminar el objeto si se confirma
            if (confirm == JOptionPane.YES_OPTION) {
                lista.Eliminar(pos);
                ProcessPersonal.MostrarEst(perso, lista);
                SavePersonal.GuardarPersonal(lista);
            }
        } else {
            JOptionPane.showMessageDialog(perso, "ID no encontrado");
        }
    }
    
    private void handleOrdenarAction(){
        switch (perso.cbxOrdenar.getSelectedIndex()) {
                case 0:
                    ProcessPersonal.MostrarEst(perso, lista);
                    break;
                case 1:
                    ListaPersonal auxiliar= new ListaPersonal();
                    auxiliar=lista;
                    ProcessPersonal.ordenarPorNombre(auxiliar);
                    ProcessPersonal.MostrarEst(perso, auxiliar);
                    break;
                case 2:
                    ListaPersonal auxiliar2 = new ListaPersonal();
                    auxiliar2 = lista;
                    ProcessPersonal.ordenarPorApellido(auxiliar2);
                    ProcessPersonal.MostrarEst(perso, auxiliar2);            
                    break;
                case 3:
                    ListaPersonal auxiliar3 = new ListaPersonal();
                    auxiliar3 = lista;
                    ProcessPersonal.ordenarPorCargo(auxiliar3);
                    ProcessPersonal.MostrarEst(perso, auxiliar3);
                    break;
                case 4:
                    ListaPersonal auxiliar4 = new ListaPersonal();
                    auxiliar4 = lista;
                    ProcessPersonal.ordenarPorUsuario(auxiliar4);
                    ProcessPersonal.MostrarEst(perso, auxiliar4);            
                    break;
                default:
                    break;
        }
    }
}
