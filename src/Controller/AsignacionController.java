/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ArrayList.ListaPersonal;
import Model.AsignarPersonal;
import Persistence.SaveAsignarPersonal;
import Persistence.SaveIncidencias;
import Persistence.SavePersonal;
import Processes.ProcessAsignarPersonal;
import Structure.Colas.ColasIncidencias;
import Structure.Pilas.PilaAsignacionPersonal;
import View.UI_Asignacion;
import View.UI_Dashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author franc
 */
public class AsignacionController extends PanelController implements ActionListener {
    private final UI_Asignacion asignacion;
    private final ColasIncidencias cola;
    PilaAsignacionPersonal pila;
    ListaPersonal lista;
    boolean editing = true;

    public AsignacionController(UI_Asignacion asignacion, UI_Dashboard app) {
        super(asignacion, app);
        this.asignacion = asignacion;

        this.cola = SaveIncidencias.Recuperar(); // Recuperar la cola desde almacenamiento
        super.showWindow(asignacion);
        lista = new ListaPersonal();
        lista = SavePersonal.RecuperarEstudiantes();
        ProcessAsignarPersonal.cargarComboBoxConIncidencias(asignacion.cbxIncidencias, cola.getCola());
        ProcessAsignarPersonal.cargarComboPersonal(asignacion.cbxPersonal, lista);
        pila = new PilaAsignacionPersonal();
        pila = SaveAsignarPersonal.Recuperar();
        ProcessAsignarPersonal.MostrarInf(asignacion, pila);
        addListeners();
    }

    @Override
    protected void addListeners() {
        asignacion.btnRegistrar.addActionListener(this);
        asignacion.btnConsultar.addActionListener(this);
        asignacion.btnActualizar.addActionListener(this);
        asignacion.btnDesapilar.addActionListener(this);
        asignacion.btnEliminar.addActionListener(this);
        asignacion.btnPrimero.addActionListener(this);
        asignacion.btnUltimo.addActionListener(this);
        asignacion.btnOrdenar.addActionListener(this);
    }

    @Override
    protected void reloadWindow() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == asignacion.btnRegistrar) {
            handleRegistrar();
        }
        if (e.getSource() == asignacion.btnConsultar) {
            handleConsultar();
        }
        if (e.getSource() == asignacion.btnActualizar) {
            handleActualizar();
        }

        if (e.getSource() == asignacion.btnDesapilar) {
            handleDesapilar();
        }

        if (e.getSource() == asignacion.btnEliminar) {
            handleEliminar();
        }

        if (e.getSource() == asignacion.btnPrimero) {
            JOptionPane.showMessageDialog(asignacion, pila.PrimerObjeto().toString());
        }

        if (e.getSource() == asignacion.btnUltimo) {
            JOptionPane.showMessageDialog(asignacion, pila.UltimoObjeto().toString());
        }

        if (e.getSource() == asignacion.btnOrdenar) {
            handleOrdenar();
        }
    }

    private void handleRegistrar(){
        AsignarPersonal asi = ProcessAsignarPersonal.leer(asignacion);
            if (asi == null) {
                return;
            }
            pila.Apilar(asi);
            ProcessAsignarPersonal.MostrarInf(asignacion, pila);
    }
    
    private void handleConsultar(){
        String codbus = JOptionPane.showInputDialog("Ingrese codigo a buscar...");
        AsignarPersonal encontrado = pila.BuscarOperario(codbus);
        if (encontrado == null) {
            JOptionPane.showMessageDialog(asignacion, "Codigo " + codbus + " no existe en la pila");
        } else {
            JOptionPane.showMessageDialog(asignacion, encontrado.toString());
        }
    }
    
    private void handleActualizar(){
            if (editing) {
                // Solicitar el ID del operario a actualizar
                String id = JOptionPane.showInputDialog("Ingrese el ID de la asignación a actualizar");
                if (id == null || id.isEmpty()) {
                    return; // Si no se ingresa un ID, salir del método
                }

                // Buscar la asignación correspondiente en la pila
                AsignarPersonal asignacionExistente = pila.BuscarOperario(id);
                if (asignacionExistente == null) {
                    JOptionPane.showMessageDialog(asignacion, "No se encontró la asignación con ID: " + id);
                    return;
                }

                // Llenar los campos de la interfaz con los datos encontrados
                ProcessAsignarPersonal.Llenar(asignacion, asignacionExistente);

                // Deshabilitar botones y configurar modo de edición
                asignacion.btnRegistrar.setEnabled(false);
                asignacion.btnDesapilar.setEnabled(false);
                asignacion.btnConsultar.setEnabled(false);
                editing = false;
            } else {
                // Leer los datos actualizados desde la interfaz
                AsignarPersonal nuevaAsignacion = ProcessAsignarPersonal.leer(asignacion);
                if (nuevaAsignacion == null) {
                    return; // Si no se puede leer, salir del método
                }

                // Actualizar el objeto en la pila
                Stack<AsignarPersonal> temporal = new Stack<>();
                boolean actualizado = false;

                while (!pila.VerificarVacio()) {
                    AsignarPersonal actual = pila.UltimoObjeto();
                    pila.Desapilar();

                    if (actual.getIncidencia().getId() == nuevaAsignacion.getIncidencia().getId()) {
                        temporal.push(nuevaAsignacion); // Agregar la versión actualizada
                        actualizado = true;
                    } else {
                        temporal.push(actual); // Mantener los demás elementos
                    }
                }

                // Reconstruir la pila original
                while (!temporal.isEmpty()) {
                    pila.Apilar(temporal.pop());
                }

                if (!actualizado) {
                    JOptionPane.showMessageDialog(asignacion, "No se pudo actualizar la asignación.");
                } else {
                    // Guardar cambios y actualizar la interfaz
                    SaveAsignarPersonal.Guardar(pila);
                    ProcessAsignarPersonal.MostrarInf(asignacion, pila);
                    JOptionPane.showMessageDialog(asignacion, "Asignación actualizada correctamente.");
                }

                // Habilitar botones y salir del modo de edición
                asignacion.btnRegistrar.setEnabled(true);
                asignacion.btnDesapilar.setEnabled(true);
                asignacion.btnConsultar.setEnabled(true);
                editing = true;
            }
    }
    
    private void handleDesapilar(){
            int resp = JOptionPane.showConfirmDialog(null, "Deseas retirar a \n" + pila.UltimoObjeto().toString(),
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                pila.Desapilar();
                ProcessAsignarPersonal.MostrarInf(asignacion, pila);
            }
    }
    
    private void handleEliminar(){
            try {
                // Solicitar el ID como número entero
                String inputId = JOptionPane.showInputDialog("Ingrese el ID de la asignación a eliminar");
                if (inputId == null || inputId.isEmpty())
                    return;

                int id = Integer.parseInt(inputId); // Convertir el ID a entero

                // Intentar eliminar la asignación por ID
                boolean eliminado = pila.eliminarAsignacionPorId(id);

                if (eliminado) {
                    SaveAsignarPersonal.Guardar(pila);
                    ProcessAsignarPersonal.MostrarInf(asignacion, pila);
                    JOptionPane.showMessageDialog(asignacion, "Asignación eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(asignacion, "No se encontró una asignación con el ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(asignacion, "El ID ingresado debe ser un número válido.");
            }
    }
    
    private void handleOrdenar(){
            switch (asignacion.cbxFiltro.getSelectedIndex()) {
                case 0:
                    pila.ordenarPorId();
                    ProcessAsignarPersonal.MostrarInf(asignacion, pila);
                    break;
                case 1:
                    pila.ordenarPorEstado();
                    ProcessAsignarPersonal.MostrarInf(asignacion, pila);
                    break;
                case 2:
                    pila.ordenarPorPersonal();
                    ProcessAsignarPersonal.MostrarInf(asignacion, pila);
                    break;
                default:
                    break;
            }
    }
    
}
