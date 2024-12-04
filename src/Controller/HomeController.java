/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.UI_Dashboard;
import View.UI_Home;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;

import javax.swing.Action;

import Model.Incidencias;
import Persistence.SaveIncidencias;
import Processes.ProcessHome;
import Structure.Colas.ColasIncidencias;
import java.awt.event.ActionEvent;

/**
 *
 * @author franc
 */
public class HomeController extends PanelController implements ActionListener {

    UI_Home home;
    Incidencias incidencia;
    ColasIncidencias cola;

    public HomeController(UI_Home panel, UI_Dashboard app) {
        super(panel, app);
        this.home = panel;
        super.showWindow(panel);
        cola = SaveIncidencias.Recuperar();
        ProcessHome.mostrarInci(panel, cola);
        addListeners();
    }

    @Override
    protected void addListeners() {
        home.btnFiltrar.addActionListener(this);
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
            String fechaInicio = formato.format(home.pickDate1.getDate());
            String fechaFin = formato.format(home.pickDate2.getDate());

            System.out.println(fechaInicio);
            System.out.println(fechaFin);
            ProcessHome.mostrarInciFechas(home, cola, home.pickDate1.getDate().toString(),
                    home.pickDate2.getDate().toString());
        }
    }

}
