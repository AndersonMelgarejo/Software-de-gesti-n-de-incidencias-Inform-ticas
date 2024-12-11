/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Departamento;
import Persistence.SaveDepartamento;
import Processes.ProcessDepartamento;
import Structure.ListasDobles.ListaDoble;
import Structure.ListasDobles.Nodo;
import View.UI_Dashboard;
import View.UI_Departamentos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Renzo
 */
public class DepartamentoController extends PanelController implements ActionListener {
    UI_Departamentos cate;
    ListaDoble  lista;
    Nodo actual;
    int pos;
    boolean editing = true;
    public DepartamentoController(UI_Departamentos cate, UI_Dashboard api) {
        super(cate, api);
        this.cate = cate;
        super.showWindow(cate);
        addListeners();
        
        lista = new ListaDoble();
        lista = SaveDepartamento.RecuperarLista();
        ProcessDepartamento.MostrarDepas(cate, lista);
    }

    @Override
    protected void addListeners() {
        cate.btnRegistrar.addActionListener(this);
        cate.btnConsultar.addActionListener(this);
        cate.btnActualizar.addActionListener(this);
        cate.btnEliminar.addActionListener(this);
        cate.btnOrdenar.addActionListener(this);
    }

    @Override
    protected void reloadWindow() {
    }
    
    private void actualizar(){
        ProcessDepartamento.limpiar(cate);
        SaveDepartamento.GuardarLista(lista);
        ProcessDepartamento.MostrarDepas(cate, lista);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cate.btnRegistrar){
            Departamento depa=ProcessDepartamento.leerDepa(cate);
            lista.InsertarAlFinal(depa);
            actualizar();
        }
        
        if(e.getSource()==cate.btnConsultar){
            String id = JOptionPane.showInputDialog("Ingrese el ID del departamento a actualizar");
        
            // Validar que el ID no sea nulo o vacío
            if (id == null || id.trim().isEmpty()) {
                JOptionPane.showMessageDialog(cate, "Debe ingresar un ID válido.");
                return;
            }
        
            try {
                pos = Integer.parseInt(id.trim()) - 1; // Convertir a número
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(cate, "El ID ingresado no es un número válido.");
                return;
            }
            
            // Validar que el ID esté dentro del rango
            if (pos >= 0 && pos < lista.contarNodos()) {
                Departamento actuper = lista.obtenerNodo(pos);                
                JOptionPane.showMessageDialog(cate,
                        "Usuario:"+actuper.getUser()+
                        "\nNombre:"+actuper.getNombre()+
                        "\nPabellon:"+actuper.getPabellon()+
                        "\nPiso:"+actuper.getPiso()+
                        "\nSalon:"+actuper.getSalon()+
                        "\nFecha:"+actuper.getFechaResFormateada());
            }
        }
        
        if (e.getSource() == cate.btnActualizar) {
            if (editing) {
                String id = JOptionPane.showInputDialog("Ingrese el ID del departamento a actualizar");
        
                // Validar que el ID no sea nulo o vacío
                if (id == null || id.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(cate, "Debe ingresar un ID válido.");
                    return;
                }
        
                try {
                    pos = Integer.parseInt(id.trim()) - 1; // Convertir a número
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(cate, "El ID ingresado no es un número válido.");
                    return;
                }

                // Validar que el ID esté dentro del rango
                if (pos >= 0 && pos < lista.contarNodos()) {
                    Departamento actuper = lista.obtenerNodo(pos);
                    ProcessDepartamento.llenar(cate, actuper);
                    cate.btnRegistrar.setEnabled(false);
                    cate.btnEliminar.setEnabled(false);
                    cate.btnConsultar.setEnabled(false);
                    editing = false;

                } else {
                    JOptionPane.showMessageDialog(cate, "ID fuera de rango.");
                }
            } else {
                Departamento pe = ProcessDepartamento.leerDepa(cate);
                if (pe == null) {
                    return;
                }

                // Actualizar nodo en la lista
                lista.actualizarNodo(pos, pe);

                // Guardar cambios en persistencia
                SaveDepartamento.GuardarLista(lista);
        
                // Limpiar y actualizar formulario
                ProcessDepartamento.limpiar(cate);
                ProcessDepartamento.MostrarDepas(cate, lista);
                cate.btnRegistrar.setEnabled(true);
                cate.btnEliminar.setEnabled(true);
                cate.btnConsultar.setEnabled(true);
                editing = true;
            }
        }
        
        if(e.getSource()==cate.btnEliminar){
            String id = JOptionPane.showInputDialog("➤ Ingrese el ID del departamento para eliminar");
            if (id == null) {
                return;
            }

            int pos = Integer.parseInt(id) - 1;

            // Validar si la posición está en el rango válido
            if (pos >= 0 && pos < lista.contarNodos()) {
                // Obtener el nodo correspondiente al departamento
                Departamento dep = lista.obtenerNodo(pos);

                if (dep != null) {
                    // Mostrar la información del departamento y solicitar confirmación
                    int confirm = JOptionPane.showConfirmDialog(
                    cate,
                "¿Está seguro de que desea eliminar el departamento?\n" +
                        "ID: " + (pos + 1) + "\n" +
                        "Nombre: " + dep.getNombre() + "\n" +
                        "Ambiente: " + dep.getAmbiente() + "\n",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);

                    // Eliminar el nodo si se confirma
                    if (confirm == JOptionPane.YES_OPTION) {
                        Nodo nodoEliminar = lista.ini;
                        for (int i = 0; i < pos; i++) {
                            nodoEliminar = nodoEliminar.sig;
                        }
                        lista.EliminarNodo(nodoEliminar);

                        // Refrescar la lista y guardar los cambios
                        ProcessDepartamento.MostrarDepas(cate, lista);
                        SaveDepartamento.GuardarLista(lista);
                    }
                } else {
                    JOptionPane.showMessageDialog(cate, "Nodo no encontrado");
                }
            } else {
                JOptionPane.showMessageDialog(cate, "ID fuera de rango");
            }

        }
        
        if(e.getSource()==cate.btnOrdenar){
            switch (cate.cbxFiltro.getSelectedIndex()) {
                case 0:
                    ListaDoble copia = new ListaDoble();
                    copia = lista;
                    copia = ListaDoble.PorNombre(copia);
                    ProcessDepartamento.MostrarDepas(cate, copia);
                    break;
                case 1:
                    ListaDoble cp = new ListaDoble();
                    cp = lista;
                    cp = ListaDoble.PorPabellon(cp);
                    ProcessDepartamento.MostrarDepas(cate, cp);
                    break;
                case 2:
                    ListaDoble cpp = new ListaDoble();
                    cpp = lista;
                    cpp = ListaDoble.PorUsuario(cpp);
                    ProcessDepartamento.MostrarDepas(cate, cpp);
                    break;
                default:
                    break;
            }
        }
        
    }
}