/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

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
    UI_Home home = null;

    public DashboardController(UI_Dashboard dash) {
        this.vista = dash;
        vista.btnCaategoria.addActionListener(this);
        vista.btnDashboard.addActionListener(this);
        vista.btnDepartamento.addActionListener(this);
        vista.btnPersonal.addActionListener(this);
        vista.btnInforme.addActionListener(this);
        vista.btnIncidencias.addActionListener(this);
        vista.btnExit.addActionListener(this);
        launchApp();
    }

    void launchApp() {
        vista.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);
        vista.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(153, 0, 51));
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        home = new UI_Home();
        ChangePanel(home);
        vista.btnDashboard.setSelected(true);
    }

    public void ChangePanel(JPanel box) {
        box.setPreferredSize(new Dimension(1000, 500)); // Tamaño inicial

        vista.jpInternal.removeAll();
        vista.jpInternal.setLayout(new BorderLayout());
        vista.jpInternal.add(box, BorderLayout.CENTER);
        vista.jpInternal.revalidate();
        vista.jpInternal.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnDashboard) {
            ChangePanel(home);
            resetButtons();
            vista.btnDashboard.setSelected(true);
        }
        if (e.getSource() == vista.btnCaategoria) {
            UI_Categorias cat = new UI_Categorias();
            cat.txtName.putClientProperty("JTextField.placeholderText", "Ingrese el nombre de la categoria");
            cat.datePick.putClientProperty("JTextField.placeholderText", "Ingrese el nombre de la categoria");
            ChangePanel(cat);
            resetButtons();
            vista.btnCaategoria.setSelected(true);
        }
        if(e.getSource()==vista.btnDepartamento){
            UI_Departamentos depa = new UI_Departamentos();
            ChangePanel(depa);
            resetButtons();
            vista.btnDepartamento.setSelected(true);
        }
        if(e.getSource()==vista.btnPersonal){
            UI_Personal perso= new UI_Personal();
            ChangePanel(perso);
            resetButtons();
            vista.btnPersonal.setSelected(true);
        }
        
        if(e.getSource()==vista.btnInforme){
            UI_Informe info = new UI_Informe();
            ChangePanel(info);
            resetButtons();
            vista.btnInforme.setSelected(true);
        }
        
        if(e.getSource()==vista.btnIncidencias){
            UI_Incidencias inci = new UI_Incidencias();
            ChangePanel(inci);
            resetButtons();
            vista.btnIncidencias.setSelected(true);
        }
        
        if(e.getSource()==vista.btnExit){
            System.exit(0);
        }
        
    }

    private void resetButtons() {
        vista.btnDashboard.setSelected(false);
        vista.btnCaategoria.setSelected(false);
        vista.btnIncidencias.setSelected(false);
        vista.btnDepartamento.setSelected(false);
        vista.btnPersonal.setSelected(false);   
        vista.btnInforme.setSelected(false);
    }

}
