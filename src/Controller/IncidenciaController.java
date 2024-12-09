/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ArrayList.ListaPersonal;
import Model.Incidencias;
import Persistence.SaveDepartamento;
import Persistence.SaveIncidencias;
import Persistence.SavePersonal;
import Persistence.SaveTipoIncidencia;
import Processes.ProcessIncidencias;
import Structure.Colas.ColasIncidencias;
import Structure.ListasDobles.ListaDoble;
import Structures.Arreglo_TipoIncidencias;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class IncidenciaController extends PanelController implements ActionListener {

    UI_Incidencias inci;
    ListaDoble lista;
    Arreglo_TipoIncidencias arreglo;
    ColasIncidencias cola;
    ListaPersonal clientes;   
    boolean editing = true;

    public IncidenciaController(UI_Incidencias inci, UI_Dashboard dash) {
        super(inci, dash);
        this.inci = inci;
        super.showWindow(inci);
        addListeners();
        cola = SaveIncidencias.Recuperar();
        arreglo = new Arreglo_TipoIncidencias(100);
        lista = new ListaDoble();
        arreglo = SaveTipoIncidencia.loadTipoIncidencia();
        arreglo = SaveTipoIncidencia.loadTipoIncidencia();
        arreglo.ActualizarContador();
        lista = SaveDepartamento.RecuperarLista();
        clientes =  new ListaPersonal();
        clientes= SavePersonal.RecuperarEstudiantes();
        
        ProcessIncidencias.cargarComboBox(inci, arreglo);
        ProcessIncidencias.cargarComboPersonal(inci, clientes);        
        ProcessIncidencias.cargarComboBoxDepas(inci, lista);
        inci.cbxDepar.setSelectedIndex(0);
        mostrarDatosEnTbl();
    }

    @Override
    protected void addListeners() {
        inci.btnRegistrar.addActionListener(this);
        inci.btnConsultar.addActionListener(this);
        inci.btnActualizar.addActionListener(this);
        inci.btnDesencolar.addActionListener(this);
        inci.btnMirarPrimero.addActionListener(this);
        inci.btnMirarUltimo.addActionListener(this);
        inci.btnOrdenar.addActionListener(this);
    }

    @Override
    protected void reloadWindow() {
    }

    public void mostrarDatosEnTbl(){
        ProcessIncidencias.mostrarInci(inci, cola);
        ProcessIncidencias.anchito(inci);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inci.btnRegistrar) {
            handleRegistrarAction();
        }
        if(e.getSource() == inci.btnConsultar){
            handleConsultarAction();
        }        
        if(e.getSource()==inci.btnDesencolar){            
            handleDesencolarAction();
        }        
        if(e.getSource()==inci.btnMirarPrimero){
            JOptionPane.showMessageDialog(null,cola.VerPrimero().detalles());
        }        
        if(e.getSource()==inci.btnMirarUltimo){
            JOptionPane.showMessageDialog(null,cola.VerUltimo().detalles());
        }        
        if(e.getSource()==inci.btnOrdenar){
            handleOrdenarAction();
        }        
        if(e.getSource()==inci.btnActualizar){
            handleActualizarInciAction();
        }
    }

    private void handleRegistrarAction(){
        Incidencias incidencia = ProcessIncidencias.leer(inci);
        cola.Encolar(incidencia);
        SaveIncidencias.Guardar(cola);
        ProcessIncidencias.limpiar(inci);
        mostrarDatosEnTbl();
    }
    
    private void handleConsultarAction(){
        if (cola.estaVacia()) {
            JOptionPane.showMessageDialog(inci, "No hay incidencias registradas.", "Consulta de Incidencias", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Solicitar el ID de la incidencia que se desea buscar
        String inputId = JOptionPane.showInputDialog(inci, "Ingrese el ID de la incidencia:", "Consultar Incidencia", JOptionPane.QUESTION_MESSAGE);

        // Validar entrada
        if (inputId == null || inputId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(inci, "Debe ingresar un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(inputId); // Convertir el input a un número entero

            // Buscar la incidencia en la cola por ID
            Incidencias incidenciaEncontrada = cola.buscarIncidenciaPorId(id);

            if (incidenciaEncontrada != null) {
                // Mostrar detalles de la incidencia encontrada
                String mensaje = incidenciaEncontrada.detalles();
                JOptionPane.showMessageDialog(inci, mensaje, "Detalles de la Incidencia", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // No se encontró la incidencia
                JOptionPane.showMessageDialog(inci, "No se encontró una incidencia con el ID ingresado.", "No Encontrado", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(inci, "El ID debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleActualizarInciAction() {
        String id = JOptionPane.showInputDialog("Ingrese el ID de la incidencia a actualizar");
        if (id == null || id.isEmpty()) {
            return; // Si no se ingresa un ID, salir del método
        }

        int idBuscado;
        try {
            idBuscado = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(inci, "ID inválido. Debe ser un número.");
            return;
        }
        if (editing) {
            // Recuperar la incidencia por ID
            Incidencias incidenciaExistente = cola.buscarIncidenciaPorId(idBuscado);
            if (incidenciaExistente != null) {
                // Llenar la interfaz con los datos existentes de la incidencia
                ProcessIncidencias.llenar(inci, incidenciaExistente);                
                // Configurar modo edición: deshabilitar botones
                inci.btnRegistrar.setEnabled(false);
                inci.btnDesencolar.setEnabled(false);
                inci.btnConsultar.setEnabled(false);
                editing = false;
            } else {
                JOptionPane.showMessageDialog(inci, "Error: no se encontró la incidencia con el ID proporcionado.");
            }
        } else {
            // Leer la nueva información desde la interfaz
            Incidencias nuevaInci = ProcessIncidencias.leer(inci);
            if (nuevaInci == null) {
                JOptionPane.showMessageDialog(inci, "Error: no se pudo leer la incidencia actualizada.");
                return;
            }
        
            // Recuperar la incidencia anterior usando el ID
            cola = SaveIncidencias.Recuperar();
            Incidencias incidenciaExistente = cola.buscarIncidenciaPorId(idBuscado);
            if (incidenciaExistente != null) {
                // Remover la incidencia antigua
                cola.getCola().remove(incidenciaExistente);

                // Agregar la incidencia actualizada
                cola.Encolar(nuevaInci);

                // Guardar los cambios y reordenar la cola
                cola.reordenarPorNivel();
                SaveIncidencias.Guardar(cola);

                // Limpiar la interfaz y actualizar la tabla
                ProcessIncidencias.limpiar(inci);
                ProcessIncidencias.mostrarInci(inci, cola);

                // Habilitar botones y salir del modo edición
                inci.btnRegistrar.setEnabled(true);
                inci.btnDesencolar.setEnabled(true);
                inci.btnConsultar.setEnabled(true);
                editing = true;
            } else {
                JOptionPane.showMessageDialog(inci, "Error: no se pudo encontrar la incidencia para actualizar.");
            }
        }
    }

    private void handleDesencolarAction(){
        int resp = JOptionPane.showConfirmDialog(null,
            "Deseas eliminar la incidencia: \n"+cola.VerPrimero().detalles(), 
            "Desencolar confirmación",
            JOptionPane.YES_NO_OPTION);
        if(resp==0){
            cola.Desencolar();
            SaveIncidencias.Guardar(cola);
            mostrarDatosEnTbl();
        }
    }
    
    private void handleOrdenarAction(){
        switch (inci.cbxFiltrar.getSelectedIndex()) {
            case 0:
                cola.ordenarPorId();
                mostrarDatosEnTbl();
                break;
            case 1:
                cola.reordenarPorNivel();
                mostrarDatosEnTbl();
                break;
            case 2:
                cola.reordenarPorTipoIncidencia();
                mostrarDatosEnTbl();
                break;
        }
    }
    
}
