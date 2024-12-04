/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ArrayList.ListaPersonal;
import Model.AsignarPersonal;
import Model.Incidencias;
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
    int pos;
    boolean editing = true;
    public AsignacionController(UI_Asignacion asignacion, UI_Dashboard app) {
        super(asignacion, app);
        this.asignacion = asignacion;
        
        this.cola = SaveIncidencias.Recuperar(); // Recuperar la cola desde almacenamiento
        super.showWindow(asignacion);
        lista =  new ListaPersonal();
        lista= SavePersonal.RecuperarEstudiantes();        
        ProcessAsignarPersonal.cargarComboBoxConIncidencias(asignacion.cbxIncidencias, cola.getCola());
        ProcessAsignarPersonal.cargarComboPersonal(asignacion.cbxPersonal, lista);
        pila = new PilaAsignacionPersonal();
        pila =  SaveAsignarPersonal.Recuperar();
        ProcessAsignarPersonal.MostrarInf(asignacion, pila);
        addListeners();
    }

    @Override
    protected void addListeners() {
        asignacion.btnRegistrar.addActionListener(this);
        asignacion.btnConsultar.addActionListener(this);
        asignacion.btnActualizar.addActionListener(this);
        asignacion.btnEliminar.addActionListener(this);
    }

    @Override
    protected void reloadWindow() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == asignacion.btnRegistrar) {
            // Podrías realizar otra acción en lugar de volver a cargar el combo box
            pila.Apilar(ProcessAsignarPersonal.leer(asignacion));
            ProcessAsignarPersonal.MostrarInf(asignacion, pila);
        }
        if(e.getSource()==asignacion.btnConsultar){
            String codbus=JOptionPane.showInputDialog("Ingrese codigo a buscar...");
            AsignarPersonal encontrado = pila.BuscarOperario(codbus);
            if(encontrado==null){
                JOptionPane.showMessageDialog(asignacion,"Codigo "+codbus+" no existe en la pila");
            }else{
                JOptionPane.showMessageDialog(asignacion,encontrado.toString());
            }
        }
        if (e.getSource() == asignacion.btnActualizar) {
            // Paso 1: Solicitar el ID de la incidencia a actualizar
            String id = JOptionPane.showInputDialog("Ingrese el ID de la incidencia a actualizar");
            if (id == null || id.trim().isEmpty()) {
                JOptionPane.showMessageDialog(asignacion, "ID no válido");
                return;
            }
    
            // Paso 2: Buscar la asignación correspondiente en la pila
            AsignarPersonal asignacionExistente = pila.BuscarOperario(id);
            if (asignacionExistente == null) {
                JOptionPane.showMessageDialog(asignacion, "No se encontró la incidencia con ID: " + id);
                return;
            }

            // Paso 3: Mostrar información actual y solicitar nuevos valores
            JOptionPane.showMessageDialog(asignacion, "Datos actuales:\n" + asignacionExistente.toString());
            String nuevoEstado = JOptionPane.showInputDialog("Ingrese el nuevo estado (EN PROCESO / ATENDIDO / DERIVADO):", asignacionExistente.getEstado());
            String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripción:", asignacionExistente.getDescripcion());
            
            if (nuevoEstado == null || nuevaDescripcion == null) {
                JOptionPane.showMessageDialog(asignacion, "Actualización cancelada.");
                return;
            }

            // Paso 4: Actualizar los valores en el objeto
            asignacionExistente.setEstado(nuevoEstado);
            asignacionExistente.setDescripcion(nuevaDescripcion);

            // Paso 5: Guardar cambios y recargar la vista
            SaveAsignarPersonal.Guardar(pila); // Guardar los cambios persistentes
            ProcessAsignarPersonal.MostrarInf(asignacion, pila); // Actualizar la interfaz
            JOptionPane.showMessageDialog(asignacion, "Asignación actualizada correctamente.");
        }
        
        if(e.getSource()==asignacion.btnEliminar){
            int resp = JOptionPane.showConfirmDialog(null,"Deseas retirar a \n"+pila.UltimoObjeto().toString(),"Confirmar",JOptionPane.YES_NO_OPTION);
           if(resp==0){
               pila.Desapilar();
               ProcessAsignarPersonal.MostrarInf(asignacion, pila);
           }
        }
    }
    
}

