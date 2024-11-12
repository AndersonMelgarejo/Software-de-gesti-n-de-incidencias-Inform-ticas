/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.UI_Dashboard;
import View.UI_Departamentos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Renzo
 */
public class DepartamentoController extends PanelController implements ActionListener {
    UI_Departamentos categoria;

    public DepartamentoController(UI_Departamentos cate, UI_Dashboard api) {
        super(cate, api);
        this.categoria = cate;
        super.showWindow(cate);
    }

    @Override
    protected void addListeners() {
    }

    @Override
    protected void reloadWindow() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
