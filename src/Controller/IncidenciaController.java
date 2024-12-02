/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Incidencias;
import Persistence.SaveDepartamento;
import Persistence.SaveIncidencias;
import Persistence.SaveTipoIncidencia;
import Processes.ProcessIncidencias;
import Structure.Colas.ColasIncidencias;
import Structure.ListasDobles.ListaDoble;
import Structures.Arreglo_TipoIncidencias;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncidenciaController extends PanelController implements ActionListener {

    UI_Incidencias inci;
    ListaDoble lista;
    Arreglo_TipoIncidencias arreglo;
    ColasIncidencias cola;
    Incidencias incidencia;

    public IncidenciaController(UI_Incidencias inci, UI_Dashboard dash) {
        super(inci, dash);
        this.inci = inci;
        super.showWindow(inci);
        addListeners();
        cola = new ColasIncidencias();
        arreglo = new Arreglo_TipoIncidencias(100);
        lista = new ListaDoble();
        arreglo = SaveTipoIncidencia.loadTipoIncidencia();
        arreglo = SaveTipoIncidencia.loadTipoIncidencia();
        arreglo.ActualizarContador();
        lista = SaveDepartamento.RecuperarLista();
        ProcessIncidencias.cargarComboBoxDepas(inci, lista);
        ProcessIncidencias.cargarComboBox(inci, arreglo);
        SaveIncidencias.Recuperar();
        ProcessIncidencias.mostrarInci(inci, cola);
        
    }

    @Override
    protected void addListeners() {
        inci.btnRegistrar.addActionListener(this);
    }

    @Override
    protected void reloadWindow() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inci.btnRegistrar) {            
                Incidencias incidencia = ProcessIncidencias.leer(inci);
                cola.Encolar(incidencia);
                SaveIncidencias.Guardar(cola);
                ProcessIncidencias.mostrarInci(inci, cola);
                
        }
    }

}
