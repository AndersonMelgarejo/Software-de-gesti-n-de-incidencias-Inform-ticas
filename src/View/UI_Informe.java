/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

/**
 *
 * @author LENOVO
 */
public class UI_Informe extends javax.swing.JPanel {

    /**
     * Creates new form UI_Informe
     */
    public UI_Informe() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new Styles.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        cbxAcciones = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        datePick = new Styles.DatePickerCustom();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        atxtDescripcion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInformes = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        panelRound2 = new Styles.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbxAcciones1 = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(245, 246, 250));

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setPreferredSize(new java.awt.Dimension(878, 463));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        jLabel1.setText("Informe de Incidencias Informáticas");
        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N

        cbxAcciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Seleccionar]" }));
        cbxAcciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAccionesActionPerformed(evt);
            }
        });

        jLabel3.setText("Acciones Tomadas");
        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        jLabel4.setText("Fecha de resolución");
        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        atxtDescripcion.setColumns(20);
        atxtDescripcion.setRows(5);
        atxtDescripcion.setPreferredSize(new java.awt.Dimension(232, 74));
        jScrollPane1.setViewportView(atxtDescripcion);

        jLabel5.setText("Descripcion de la solución");
        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        btnRegistrar.setText("Registrar");
        btnRegistrar.setBackground(new java.awt.Color(153, 0, 51));
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));

        btnConsultar.setText("Consultar");
        btnConsultar.setBackground(new java.awt.Color(0, 121, 153));
        btnConsultar.setBorderPainted(false);
        btnConsultar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnConsultar.setForeground(new java.awt.Color(255, 255, 255));

        btnActualizar.setText("Actualizar");
        btnActualizar.setBackground(new java.awt.Color(68, 23, 38));
        btnActualizar.setBorderPainted(false);
        btnActualizar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));

        tblInformes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblInformes);

        jSeparator1.setForeground(new java.awt.Color(153, 0, 51));

        panelRound2.setBackground(new java.awt.Color(0, 153, 51));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("#CAT-0000");
        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        btnEliminar.setText("Eliminar");
        btnEliminar.setBackground(new java.awt.Color(255, 153, 0));
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Estado");
        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EN PROCESO", "ATENDIDO", "DERIVADO" }));
        cbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoActionPerformed(evt);
            }
        });

        jLabel8.setText("ID de incidencia ");
        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        cbxAcciones1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Seleccionar]", "Baja", "Media", "Alta" }));
        cbxAcciones1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAcciones1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 32, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel1)
                                .addGap(12, 12, 12)
                                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxAcciones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8))
                                .addGap(15, 15, 15)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(cbxAcciones1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(datePick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(19, 19, 19))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3))
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxAcciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(0, 0, 0)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(datePick, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEstadoActionPerformed

    private void cbxAccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAccionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAccionesActionPerformed

    private void cbxAcciones1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAcciones1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAcciones1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea atxtDescripcion;
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnConsultar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnRegistrar;
    public javax.swing.JComboBox<String> cbxAcciones;
    public javax.swing.JComboBox<String> cbxAcciones1;
    public javax.swing.JComboBox<String> cbxEstado;
    public Styles.DatePickerCustom datePick;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private Styles.PanelRound panelRound1;
    private Styles.PanelRound panelRound2;
    public javax.swing.JTable tblInformes;
    // End of variables declaration//GEN-END:variables
}
