package Controller;

import ArrayList.ListaPersonal;
import Model.Informe;
import Persistence.SaveAsignarPersonal;

import Persistence.SaveIncidencias;
import Persistence.SavePersonal;
import Persistence.saveInforme;
import Processes.ProcessInforme;
import Structure.Colas.ColasIncidencias;
import Structure.Pilas.PilaAsignacionPersonal;
import Structures.Arboles.ArbolInforme;
import Structures.Arboles.NodoInforme;
import View.UI_Informe;
import View.UI_Dashboard;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformeController extends PanelController implements ActionListener {
    
    private final UI_Informe info;
    private final DefaultTableModel modTabla;
    private ArbolInforme arbol; // Árbol de informes
    private NodoInforme actual;
    private final ColasIncidencias cola;
    private final PilaAsignacionPersonal pila;
    ListaPersonal lista;

    public InformeController(UI_Informe info, UI_Dashboard dash) {
        
        super(info, dash);
        this.info = info;
        super.showWindow(info);
        addListeners();

        // Inicializar el árbol de informes desde archivo
        this.cola = SaveIncidencias.Recuperar();
        this.pila = SaveAsignarPersonal.Recuperar();
        lista = SavePersonal.RecuperarEstudiantes();
        arbol = saveInforme.RecuperarDeArchivo();
        if (arbol == null) {
            arbol = new ArbolInforme();
        }

        ProcessInforme.cargarComboBoxConIncidencias(info.cbxIncidencias, cola.getCola());
        modTabla = (DefaultTableModel) info.tblInformes.getModel();
        actualizarTabla();
    }

    @Override
    protected void reloadWindow() {
        // Implementar si es necesario
    }

    private void actualizarTabla() {
        ProcessInforme.LimpiarTabla(modTabla);
        arbol.MostrarEnOrden(arbol.getRaiz(), modTabla);
    }

    private void actualizarVista() {
        ProcessInforme.LimpiaCampos(info);
        saveInforme.GuardarEnArchivo(arbol);
        actualizarTabla();
    }

    @Override
    protected void addListeners() {
        info.btnRegistrar.addActionListener(this);
        info.btnConsultar.addActionListener(this);
        info.btnActualizar.addActionListener(this);
        info.btnEliminar.addActionListener(this);
        info.btnOrdenar.addActionListener(this); // Agregado listener para el botón de ordenar
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == info.btnRegistrar) {
                if (camposIncompletos()) {
                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                
                Informe nuevoInforme = ProcessInforme.LeerInforme(info,pila);
                if (nuevoInforme != null) {
                    // Verificar si ya existe un informe con el mismo ID de incidencia
                    NodoInforme nodoExistente = arbol.BuscarPorID(nuevoInforme.getIncidencia().getId());
                    if (nodoExistente != null) {
                        JOptionPane.showMessageDialog(null, "Ya existe un informe registrado para esta incidencia.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Si no existe, agregar el nuevo informe
                    arbol.setRaiz(arbol.Agregar(arbol.getRaiz(), nuevoInforme));
                    actualizarVista();
                    JOptionPane.showMessageDialog(null, "Informe registrado exitosamente.");
                }
            } else if (e.getSource() == info.btnConsultar) {
                String idText = JOptionPane.showInputDialog("Ingrese el ID del informe a buscar:");
                if (idText == null || idText.trim().isEmpty()) {
                    return;
                }

                try {
                    int idBuscado = Integer.parseInt(idText);
                    actual = arbol.BuscarPorID(idBuscado);

                    if (actual == null) {
                        JOptionPane.showMessageDialog(null, "El ID del informe no existe.",
                                "ID no encontrado", JOptionPane.ERROR_MESSAGE);
                    } else {
                        ProcessInforme.MostrarDatosNodo(actual, info);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número válido.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == info.btnActualizar) {
                if (actual == null) {
                    JOptionPane.showMessageDialog(null, "Debe consultar un informe antes de actualizarlo.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (camposIncompletos()) {
                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Informe informeActualizado = ProcessInforme.LeerInforme(info,pila);
                if (informeActualizado != null) {
                    actual.setElemento(informeActualizado);
                    actualizarVista();
                    JOptionPane.showMessageDialog(null, "Informe actualizado exitosamente.");
                }
            } else if (e.getSource() == info.btnEliminar) {
                if (actual == null) {
                    JOptionPane.showMessageDialog(null, "Debe consultar un informe antes de eliminarlo.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro de que desea eliminar el informe?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Corrected to use the incidence ID
                    arbol.setRaiz(arbol.Eliminar(arbol.getRaiz(), actual.getElemento().getIncidencia().getId()));
                    actual = null;
                    actualizarVista();
                    JOptionPane.showMessageDialog(null, "Informe eliminado exitosamente.");
                }
            } else if (e.getSource() == info.btnOrdenar) { 
                String criterio = (String) info.cbxOrdenar.getSelectedItem();

                if ("ID".equals(criterio)) {
                    ProcessInforme.ordenarPorID(arbol, info);
                } else if ("Estado".equals(criterio)) {
                    ProcessInforme.ordenarPorEstado(arbol, info);
                } else if ("Fecha".equals(criterio)) {
                    ProcessInforme.ordenarPorFecha(arbol, info);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean camposIncompletos() {
        return info.cbxAccionesTomadas.getSelectedIndex() == 0
                || info.cbxEstado.getSelectedIndex() == 0
                || info.atxtDescripcion.getText().trim().isEmpty();
    }

    private void cargarDesdeArchivo() {
        arbol = saveInforme.RecuperarDeArchivo();
        if (arbol == null) {
            arbol = new ArbolInforme();
        }
        actualizarTabla();
    }
}

