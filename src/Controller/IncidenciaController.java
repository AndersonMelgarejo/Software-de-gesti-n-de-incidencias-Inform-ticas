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
            Incidencias incidencia = ProcessIncidencias.leer(inci);
            cola.Encolar(incidencia);
            SaveIncidencias.Guardar(cola);
            mostrarDatosEnTbl();
        }
        if(e.getSource() == inci.btnConsultar){
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
        
        if(e.getSource()==inci.btnDesencolar){
            
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
        
        if(e.getSource()==inci.btnMirarPrimero){
            JOptionPane.showMessageDialog(null,cola.VerPrimero().detalles());
        }
        
        if(e.getSource()==inci.btnMirarUltimo){
            JOptionPane.showMessageDialog(null,cola.VerUltimo().detalles());
        }
        
        if(e.getSource()==inci.btnOrdenar){
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

}
