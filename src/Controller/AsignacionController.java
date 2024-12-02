/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Incidencias;
import Persistence.SaveIncidencias;
import Processes.ProcessAsignarPersonal;
import Structure.Colas.ColasIncidencias;
import View.UI_Asignacion;
import View.UI_Dashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author franc
 */
public class AsignacionController extends PanelController implements ActionListener {
    private final UI_Asignacion asignacion;
    private final ColasIncidencias cola;

    public AsignacionController(UI_Asignacion asignacion, UI_Dashboard app) {
        super(asignacion, app);
        this.asignacion = asignacion;
        this.cola = SaveIncidencias.Recuperar(); // Recuperar la cola desde almacenamiento
        super.showWindow(asignacion);
        cargarIncidencias(); // Cargar las incidencias en el combo box
    }

    @Override
    protected void addListeners() {
        asignacion.btnRegistrar.addActionListener(this);
    }

    @Override
    protected void reloadWindow() {
        cargarIncidencias(); // Recargar las incidencias en caso necesario
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == asignacion.btnRegistrar) {
            // Podrías realizar otra acción en lugar de volver a cargar el combo box
            cargarIncidencias();
        }
    }

    private void cargarIncidencias() {
        // Llamar al método estático para cargar el combo box
        ProcessAsignarPersonal.cargarComboBoxConIncidencias(asignacion.cbxIncidencias, cola.getCola());
    }
}

