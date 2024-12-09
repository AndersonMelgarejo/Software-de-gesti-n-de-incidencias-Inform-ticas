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
    Incidencias incidencia;

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
        ProcessIncidencias.cargarComboBoxDepas(inci, lista);
        ProcessIncidencias.cargarComboBox(inci, arreglo);
        ProcessIncidencias.cargarComboPersonal(inci, clientes);
        mostrarDatosEnTbl();

    }

    @Override
    protected void addListeners() {
        inci.btnRegistrar.addActionListener(this);
        inci.btnConsultar.addActionListener(this);
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
                String mensaje = String.format(
                    "ID: %d\nUsuario: %s\nFecha: %s\nDepartamento: %s\nÁrea: %s\nDescripción: %s\nFecha de la incidencia: %s\nTipo: %s\nNivel de prioridad: %s\nCliente: %s",
                    incidenciaEncontrada.getId(), // ID
                    incidenciaEncontrada.getUser(), // Usuario
                    incidenciaEncontrada.getFecha(), // Fecha
                    incidenciaEncontrada.getDepartamento(), // Departamento
                    incidenciaEncontrada.getArea(), // Área                    
                    incidenciaEncontrada.getDescripcion(), // Descripción
                    incidenciaEncontrada.getFechaFormat(), // Fecha de la incidencia
                    incidenciaEncontrada.getTipoincidencia().getNombre(), // Tipo
                    incidenciaEncontrada.getTipoincidencia().getNivel(),// Nivel
                    incidenciaEncontrada.getPersonal().getUser() //Cliente
                );
                JOptionPane.showMessageDialog(inci, mensaje, "Detalles de la Incidencia", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // No se encontró la incidencia
                JOptionPane.showMessageDialog(inci, "No se encontró una incidencia con el ID ingresado.", "No Encontrado", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(inci, "El ID debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }

}
