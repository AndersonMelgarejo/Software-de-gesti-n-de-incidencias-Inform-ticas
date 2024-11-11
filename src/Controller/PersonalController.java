/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ArrayList.ListaPersonal;
import Model.Personal;
import Persistence.SavePersonal;
import Processes.ProcessPersonal;
import View.UI_Dashboard;
import View.UI_Personal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author Renzo
 */
public class PersonalController extends PanelController implements ActionListener{

    UI_Personal perso;
    ListaPersonal lista;
    Personal pe;
    public PersonalController(UI_Personal perso, UI_Dashboard apo) {
        super(perso, apo);
        this.perso=perso;
        lista =  new ListaPersonal();
        lista= SavePersonal.RecuperarEstudiantes();
        ProcessPersonal.MostrarEst(perso, lista);
        addListeners();
    }

    @Override
    protected void addListeners() {
        this.perso.btnAutoGenerarUser.addActionListener(this);
        this.perso.btnRegistrar.addActionListener(this);
        this.perso.btnConsultar.addActionListener(this);
        this.perso.btnActualizar.addActionListener(this);
        this.perso.btnEliminar.addActionListener(this);
    }

    @Override
    protected void reloadWindow() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource()==perso.btnRegistrar){
           pe = ProcessPersonal.LeerPersonal(perso);
           lista.Agregar(pe);
           SavePersonal.GuardarPersonal(lista);
           ProcessPersonal.Limpiar(perso);
           ProcessPersonal.MostrarEst(perso, lista);
           JOptionPane.showMessageDialog(null,"EL PERSONAL FUE REGISTRADO"); 
        }
        if (e.getSource() == perso.btnAutoGenerarUser) {                        
            ProcessPersonal.generarUser(perso);
        } 
    }
    
}
