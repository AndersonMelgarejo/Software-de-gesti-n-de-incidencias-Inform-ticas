/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Persistence.SavePersonal;
import View.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author franc
 */
public class DashboardController implements ActionListener {

    public static UI_Dashboard vista;
    // vistas de panel
    UI_Home home = null;
    UI_Categorias cat = null;
    UI_Personal perso = null;
    UI_Asignacion asig = null;
    UI_Incidencias inci = null;
    UI_Informe info = null;
    UI_Departamentos depa = null;

    public DashboardController(UI_Dashboard dash) {
        this.vista = dash;
        vista.btnCaategoria.addActionListener(this);
        vista.btnDashboard.addActionListener(this);
        vista.btnDepartamento.addActionListener(this);
        vista.btnPersonal.addActionListener(this);
        vista.btnInforme.addActionListener(this);
        vista.btnIncidencias.addActionListener(this);
        vista.btnExit.addActionListener(this);
        vista.btnAsignar.addActionListener(this);
        launchApp();
    }

    void launchApp() {
        vista.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);
        vista.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(153, 0, 51));
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        showHome();
        vista.btnDashboard.setSelected(true);
    }

    void showHome() {
        home = new UI_Home();
        HomeController controllerHome = new HomeController(home, vista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnDashboard) {
            showHome();
            resetButtons();
            vista.btnDashboard.setSelected(true);
        }
        if (e.getSource() == vista.btnCaategoria) {
            cat = new UI_Categorias();
            cat.txtName.putClientProperty("JTextField.placeholderText", "Ingrese el nombre de la categoria");
            cat.datePick.putClientProperty("JTextField.placeholderText", "Ingrese el nombre de la categoria");
            CategoriaController ctrlCat = new CategoriaController(cat, vista);
            resetButtons();
            vista.btnCaategoria.setSelected(true);
        }
        if (e.getSource() == vista.btnDepartamento) {
            depa = new UI_Departamentos();
            depa.txtName.putClientProperty("JTextField.placeholderText", "Ingrese el nombre del departamento");
            depa.txtSalon.putClientProperty("JTextField.placeholderText", "Ingrese el salon del departamento");
            DepartamentoController ctrlDepa = new DepartamentoController(depa, vista);
            resetButtons();
            vista.btnDepartamento.setSelected(true);
        }
        if (e.getSource() == vista.btnPersonal) {
            perso = new UI_Personal();
            perso.txtNombre.putClientProperty("JTextField.placeholderText", "Ingrese el nombre del personal");
            perso.txtApellido.putClientProperty("JTextField.placeholderText", "Ingrese el apellido del personal");
            perso.txtCorreo.putClientProperty("JTextField.placeholderText", "Ingrese el correo del personal");
            perso.txtMovil.putClientProperty("JTextField.placeholderText", "Ingrese el movil del personal");
            perso.txtPass.putClientProperty("JTextField.placeholderText", "Ingrese la contraseña del personal");
            perso.txtUser.putClientProperty("JTextField.placeholderText", "Autogenera el usuario");

            PersonalController ctrlPerso = new PersonalController(perso, vista);
            resetButtons();
            vista.btnPersonal.setSelected(true);
        }

        if (e.getSource() == vista.btnInforme) {
            info = new UI_Informe();
            InformeController ctrlInfo = new InformeController(info, vista);
            resetButtons();
            vista.btnInforme.setSelected(true);
        }

        if (e.getSource() == vista.btnIncidencias) {
            inci = new UI_Incidencias();
            inci.txtArea.putClientProperty("JTextField.placeholderText", "Ejem: A0101");
            IncidenciaController ctrlInci = new IncidenciaController(inci, vista);
            resetButtons();
            vista.btnIncidencias.setSelected(true);
        }

        if (e.getSource() == vista.btnExit) {
            System.exit(0);
        }
        if (e.getSource() == vista.btnAsignar) {
            asig = new UI_Asignacion();
            AsignacionController ctrlAsig = new AsignacionController(asig, vista);
            resetButtons();
            vista.btnAsignar.setSelected(true);
        }

    }

    private void resetButtons() {
        vista.btnDashboard.setSelected(false);
        vista.btnCaategoria.setSelected(false);
        vista.btnIncidencias.setSelected(false);
        vista.btnDepartamento.setSelected(false);
        vista.btnPersonal.setSelected(false);
        vista.btnInforme.setSelected(false);
        vista.btnAsignar.setSelected(false);
    }

}
