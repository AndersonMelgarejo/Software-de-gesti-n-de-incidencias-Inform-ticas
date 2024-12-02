/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Departamento;
import Model.Personal;
import Persistence.SaveDepartamento;
import Processes.ProcessDepartamento;
import Structure.ListasDobles.ListaDoble;
import Structure.ListasDobles.Nodo;
import View.UI_Dashboard;
import View.UI_Departamentos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Renzo
 */
public class DepartamentoController extends PanelController implements ActionListener {
    UI_Departamentos cate;
    ListaDoble  lista;
    Nodo actual;
    Personal personal;

    public DepartamentoController(UI_Departamentos cate, UI_Dashboard api,Personal personal) {
        super(cate, api);
        this.cate = cate;
        this.personal=personal;
        super.showWindow(cate);
        addListeners();
        
        lista = new ListaDoble();
        lista = SaveDepartamento.RecuperarLista();
        ProcessDepartamento.MostrarDepas(cate, lista);
    }

    @Override
    protected void addListeners() {
        cate.btnRegistrar.addActionListener(this);
    }

    @Override
    protected void reloadWindow() {
    }
    
    private void actualizar(){
        ProcessDepartamento.limpiar(cate);
        SaveDepartamento.GuardarLista(lista);
        ProcessDepartamento.MostrarDepas(cate, lista);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cate.btnRegistrar){
            Departamento depa=ProcessDepartamento.leerDepa(cate,personal);
            lista.InsertarAlFinal(depa);
            actualizar();
        }
    }

}
