/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.UI_Dashboard;
import View.UI_Home;

import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import Model.Incidencias;
import Persistence.SaveAsignarPersonal;
import Persistence.SaveIncidencias;
import Processes.ProcessHome;
import Structure.Colas.ColasIncidencias;
import Structure.Pilas.PilaAsignacionPersonal;
import java.awt.event.ActionEvent;

/**
 *
 * @author franc
 */
public class HomeController extends PanelController implements ActionListener {

    UI_Home home;
    Incidencias incidencia;
    ColasIncidencias cola;
    PilaAsignacionPersonal pila;

    public HomeController(UI_Home panel, UI_Dashboard app) {
        super(panel, app);
        this.home = panel;
        super.showWindow(panel);
        cola = SaveIncidencias.Recuperar();
        pila = SaveAsignarPersonal.Recuperar();
        ProcessHome.mostrarInci(panel, cola);
        addListeners();
        ProcessHome.mostrarTotalInci(home, cola);
        ProcessHome.mostrarInciEstado(panel, pila);
        ProcessHome.mostrarTotalSol(panel, pila, cola);
        ProcessHome.mostrarTotalInciEstado(panel, pila);
    }

    @Override
    protected void addListeners() {
        home.btnFiltrar.addActionListener(this);
        home.btnFiltrarEstado.addActionListener(this);
    }

    @Override
    protected void reloadWindow() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home.btnFiltrar) {
            // formato de fecha dd/MM/yyyy
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            // Validar si las fechas están vacías
            if (home.pickDate1.getDate() == null || home.pickDate2.getDate() == null) {
                // Mostrar alerta al usuario
                int response = javax.swing.JOptionPane.showConfirmDialog(null,
                        "Falta un campo de fecha. ¿Desea mostrar todas las incidencias?",
                        "Campos vacíos",
                        javax.swing.JOptionPane.YES_NO_OPTION);

                // Si el usuario elige mostrar todas las incidencias
                if (response == javax.swing.JOptionPane.YES_OPTION) {
                    ProcessHome.mostrarInci(home, cola);
                }
                return;
            }

            // Obtener y convertir la fecha de inicio
            LocalDate localDateInicio = home.pickDate1.getDate();
            Date fechaInicio = Date.from(localDateInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
            String fechaInicioStr = formato.format(fechaInicio);
            System.out.println(fechaInicioStr);

            // Obtener y convertir la fecha de fin
            LocalDate localDateFin = home.pickDate2.getDate();
            Date fechaFin = Date.from(localDateFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
            String fechaFinStr = formato.format(fechaFin);
            System.out.println(fechaFinStr);

            ProcessHome.mostrarInciFechas(home, cola, fechaInicio, fechaFin);
        } else if (e.getSource() == home.btnFiltrarEstado) {
            String estado = home.cbEstado.getSelectedItem().toString();
            if (estado.equals("[Seleccionar]")) {
                ProcessHome.mostrarInciEstado(home, pila);
                return;

            }
            ProcessHome.mostrarInciEstadoFiltroRecursivo(home, pila, estado);
        }

    }

}
