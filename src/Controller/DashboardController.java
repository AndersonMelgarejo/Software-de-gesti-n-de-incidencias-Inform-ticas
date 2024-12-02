package Controller;

import Model.Personal;
import View.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author franc
 */
public class DashboardController implements ActionListener {

    public UI_Dashboard vista;
    Personal personal;

    public DashboardController(UI_Dashboard dash,Personal personal) {
        this.vista = dash;
        this.personal=personal;
        initializeListeners();
        launchApp();
    }

    private void initializeListeners() {
        vista.btnCaategoria.addActionListener(this);
        vista.btnDashboard.addActionListener(this);
        vista.btnDepartamento.addActionListener(this);
        vista.btnPersonal.addActionListener(this);
        vista.btnInforme.addActionListener(this);
        vista.btnIncidencias.addActionListener(this);
        vista.btnExit.addActionListener(this);
        vista.btnAsignar.addActionListener(this);
    }

    void launchApp() {
        vista.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);
        vista.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(153, 0, 51));
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        showHome();
        vista.btnDashboard.setSelected(true);
    }

    private void showHome() {
        UI_Home home = new UI_Home();
        HomeController controllerHome = new HomeController(home, vista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == vista.btnDashboard) {
            handleDashboardAction();
        } else if (source == vista.btnCaategoria) {
            handleCategoriaAction();
        } else if (source == vista.btnDepartamento) {
            handleDepartamentoAction();
        } else if (source == vista.btnPersonal) {
            handlePersonalAction();
        } else if (source == vista.btnInforme) {
            handleInformeAction();
        } else if (source == vista.btnIncidencias) {
            handleIncidenciasAction();
        } else if (source == vista.btnExit) {
            System.exit(0);
        } else if (source == vista.btnAsignar) {
            handleAsignarAction();
        }
    }

    private void handleDashboardAction() {
        showHome();
        resetButtons();
        vista.btnDashboard.setSelected(true);
    }

    private void handleCategoriaAction() {
        UI_Categorias cat = new UI_Categorias();
        cat.txtName.putClientProperty("JTextField.placeholderText", "Ingrese el nombre de la categoria");
        cat.cbCategoria.putClientProperty("JTextField.placeholderText", "Ingrese el nombre de la categoria");
        CategoriaController ctrlCat = new CategoriaController(cat, vista);
        resetButtons();
        vista.btnCaategoria.setSelected(true);
    }

    private void handleDepartamentoAction() {
        UI_Departamentos depa = new UI_Departamentos();
        depa.txtNombre.putClientProperty("JTextField.placeholderText", "Ingrese el nombre del departamento");
        depa.txtSalon.putClientProperty("JTextField.placeholderText", "Ingrese el salon del departamento");
        DepartamentoController ctrlDepa = new DepartamentoController(depa, vista,personal);
        resetButtons();
        vista.btnDepartamento.setSelected(true);
    }

    private void handlePersonalAction() {
        UI_Personal perso = new UI_Personal();
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

    private void handleInformeAction() {
        UI_Informe info = new UI_Informe();
        InformeController ctrlInfo = new InformeController(info, vista);
        resetButtons();
        vista.btnInforme.setSelected(true);
    }

    private void handleIncidenciasAction() {
        UI_Incidencias inci = new UI_Incidencias();
        inci.txtArea.putClientProperty("JTextField.placeholderText", "Ejem: A0101");
        IncidenciaController ctrlInci = new IncidenciaController(inci, vista);
        resetButtons();
        vista.btnIncidencias.setSelected(true);
    }

    private void handleAsignarAction() {
        UI_Asignacion asig = new UI_Asignacion();
        AsignacionController ctrlAsig = new AsignacionController(asig, vista);
        resetButtons();
        vista.btnAsignar.setSelected(true);
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
