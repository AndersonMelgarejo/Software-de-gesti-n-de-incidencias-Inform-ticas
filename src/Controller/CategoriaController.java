/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.UI_Categorias;
import View.UI_Dashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.TipoIncidencia;
import Persistence.SaveTipoIncidencia;
import Processes.ProcessTipoIncidencia;
import Structures.Arreglo_TipoIncidencias;

/**
 *
 * @author franc
 */
public class CategoriaController extends PanelController implements ActionListener {

    UI_Categorias categoriaui;
    Arreglo_TipoIncidencias arreglo;
    // editar
    boolean editing = true;
    int pos = 0;

    public CategoriaController(UI_Categorias cat, UI_Dashboard dash) {
        super(cat, dash);
        this.categoriaui = cat;
        super.showWindow(categoriaui);
        arreglo = new Arreglo_TipoIncidencias(100);
        arreglo = SaveTipoIncidencia.loadTipoIncidencia();
        arreglo.ActualizarContador();
        ProcessTipoIncidencia.MostrarTipoIncidencia(cat, arreglo.getArreglo());
        addListeners();
    }

    @Override
    protected void addListeners() {
        categoriaui.btnRegistrar.addActionListener(this);
        categoriaui.btnConsultar.addActionListener(this);
        categoriaui.btnEliminar.addActionListener(this);
        categoriaui.btnActualizar.addActionListener(this);
    }

    @Override
    protected void reloadWindow() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == categoriaui.btnRegistrar) {
            handleRegistrarAction();
        } else if (source == categoriaui.btnConsultar) {
            handleConsultarAction();
        } else if (source == categoriaui.btnEliminar) {
            handleEliminarAction();
        } else if (source == categoriaui.btnActualizar) {
            handleActualizarAction();
        }

    }

    private void handleRegistrarAction() {
        TipoIncidencia tipoIncidencia = ProcessTipoIncidencia.LeerTipoIncidencia(categoriaui);
        arreglo.agregar(tipoIncidencia);
        SaveTipoIncidencia.saveTipoIncidencia(arreglo);
        ProcessTipoIncidencia.LimpiaCampos(categoriaui);
        ProcessTipoIncidencia.MostrarTipoIncidencia(categoriaui, arreglo.getArreglo());
    }

    private void handleConsultarAction() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del tipo de incidencia a consultar");
        int pos = Integer.parseInt(id) - 1;
        System.out.println(pos);
        if (pos >= 0 && pos < arreglo.cantidad()) {
            TipoIncidencia tipoIncidencia = arreglo.obtener(pos);
            JOptionPane.showMessageDialog(categoriaui,
                    "Nombre: " + tipoIncidencia.getNombre() + "\nNivel: " + tipoIncidencia.getNivel() + "\nCategoria: "
                            + tipoIncidencia.getCategoria() + "\nDescripcion: " + tipoIncidencia.getDescripcion()
                            + "\nFecha Modificacion: " + tipoIncidencia.getFechaMod());
        } else {
            JOptionPane.showMessageDialog(categoriaui, "ID no encontrado");
        }
    }

    private void handleEliminarAction() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del tipo de incidencia a eliminar");
        int pos = Integer.parseInt(id) - 1;
        if (pos >= 0 && pos < arreglo.cantidad()) {
            // Obtener el objeto a eliminar
            TipoIncidencia tipoIncidencia = arreglo.obtener(pos);

            // Mostrar la información del objeto y solicitar confirmación
            int confirm = JOptionPane.showConfirmDialog(
                    categoriaui,
                    "¿Está seguro de que desea eliminar el siguiente tipo de incidencia?\n" +
                            "ID: " + (pos + 1) + "\n" +
                            "Nombre: " + tipoIncidencia.getNombre() + "\n" +
                            "Descripción: " + tipoIncidencia.getDescripcion(),
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION);

            // Eliminar el objeto si se confirma
            if (confirm == JOptionPane.YES_OPTION) {
                arreglo.eliminar(pos);
                ProcessTipoIncidencia.MostrarTipoIncidencia(categoriaui, arreglo.getArreglo());
                SaveTipoIncidencia.saveTipoIncidencia(arreglo);
            }
        } else {
            JOptionPane.showMessageDialog(categoriaui, "ID no encontrado");
        }
    }

    private void handleActualizarAction() {
        if (editing) {
            String id = JOptionPane.showInputDialog("Ingrese el ID del tipo de incidencia a actualizar");
            if (id == null) {
                return;
            }
            pos = Integer.parseInt(id) - 1;
            if (pos >= 0 && pos < arreglo.cantidad()) {
                TipoIncidencia updateTipoIncidencia = arreglo.obtener(pos);
                ProcessTipoIncidencia.LlenarCampos(categoriaui, updateTipoIncidencia);
                categoriaui.btnRegistrar.setEnabled(false);
                categoriaui.btnEliminar.setEnabled(false);
                categoriaui.btnConsultar.setEnabled(false);
                editing = false;
            } else {
                JOptionPane.showMessageDialog(categoriaui, "ID no encontrado");
            }

        } else {
            TipoIncidencia tipoIncidencia = ProcessTipoIncidencia.LeerTipoIncidencia(categoriaui);
            arreglo.modificar(pos, tipoIncidencia);
            SaveTipoIncidencia.saveTipoIncidencia(arreglo);
            ProcessTipoIncidencia.LimpiaCampos(categoriaui);
            ProcessTipoIncidencia.MostrarTipoIncidencia(categoriaui, arreglo.getArreglo());
            categoriaui.btnRegistrar.setEnabled(true);
            categoriaui.btnEliminar.setEnabled(true);
            categoriaui.btnConsultar.setEnabled(true);
            editing = true;
        }
    }

}
