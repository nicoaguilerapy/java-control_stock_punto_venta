package vista.reportes;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.balance.Balance;
import modelo.balance.BalanceReport;
import modelo.persona.Clientes;
import modelo.persona.Personas;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import vista.Jbuscar;
import vista.inicio;
import vista.main;

public class JinformesClientes extends javax.swing.JFrame {

    List<Balance> BalanceArray;

    DefaultTableModel datosTable;
    Integer ingreso = 0, egreso = 0;

    String tipo = "", fecha, fechaT, condicion2, condicion;

    List lista;
    int pdf = 0;
    File PDF;
    byte[] datosPDF;
    Jbuscar jbc;
    Personas cliObj;

    public JinformesClientes() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Informe Clientes -" + inicio.version);
        //btnImprimir.setEnabled(false);

        fecha = inicio.gFechas.getFechaActual();
        txtFechaI.setText("" + fecha);
        txtFechaF.setText("" + fecha);

        actualizarCBusuario();

        if (inicio.userObj.getRango().equals("Operador")) {
            cbCaja.setEnabled(false);
        }

    }

    public void generarInforme() {
        if (!txtCIN.getText().equals("")) {

            JasperPrint jP = null;
            if (cbFiltrar.getSelectedItem().toString().equals("GENERAL")) {
                jP = inicio.gReportes.reporteClientesGeneral(cliObj.getId(), inicio.gFechas.invertir(txtFechaI.getText()), inicio.gFechas.invertir(txtFechaF.getText()), getIdUsuario(), txtCIN.getText());
            } else {
                jP = inicio.gReportes.reporteClientesDetallado(cliObj.getId(), inicio.gFechas.invertir(txtFechaI.getText()), inicio.gFechas.invertir(txtFechaF.getText()), getIdUsuario(), txtCIN.getText());
            }

            JRViewer jRViewer = new JRViewer(jP);
            //se elimina elementos del contenedor JPanel
            panelInformes.removeAll();
            //para el tamaño de l reporte se agrega un BorderLayout
            panelInformes.setLayout(new BorderLayout());
            panelInformes.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            panelInformes.repaint();
            panelInformes.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccionar un cliente", "Informe Cliente", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCBusuario() {
        cbCaja.removeAllItems();

        try {
            for (int i = 0; i < main.usuariosDAO.resultadoUsuarios.size(); i++) {
                cbCaja.addItem(main.usuariosDAO.resultadoUsuarios.get(i).getUsuario());
            }
        } catch (Exception e) {
            System.out.println("Usuarios vacio");
        }

        setIdUsuario(inicio.userObj.getId());

    }

    private int getIdUsuario() {

        for (int i = 0; i < main.usuariosDAO.resultadoUsuarios.size(); i++) {
            String aux = main.usuariosDAO.resultadoUsuarios.get(i).getUsuario();
            String aux2 = (String) cbCaja.getSelectedItem();
            if (aux.equals(aux2)) {
                return main.usuariosDAO.resultadoUsuarios.get(i).getId();
            }
        }
        return -1;
    }

    private void setIdUsuario(int id) {
        String aux = "";
        for (int i = 0; i < main.usuariosDAO.resultadoUsuarios.size(); i++) {

            if (main.usuariosDAO.resultadoUsuarios.get(i).getId() == id) {
                aux = main.usuariosDAO.resultadoUsuarios.get(i).getUsuario();
            }

            cbCaja.setSelectedItem(aux);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelInformes = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtFechaI = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaF = new javax.swing.JFormattedTextField();
        btnGenerarInforme = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbCaja = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbFiltrar = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtCIN = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnTodos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(".:: INFORME CAJA - ELGRILLO ::.");

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        javax.swing.GroupLayout panelInformesLayout = new javax.swing.GroupLayout(panelInformes);
        panelInformes.setLayout(panelInformesLayout);
        panelInformesLayout.setHorizontalGroup(
            panelInformesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1107, Short.MAX_VALUE)
        );
        panelInformesLayout.setVerticalGroup(
            panelInformesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelInformes);

        jPanel5.setBackground(new java.awt.Color(0, 102, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informes Especificos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        try {
            txtFechaI.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFechaI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFechaI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFechaIKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INICIO:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("FIN:");

        try {
            txtFechaF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFechaF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFechaF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFechaFKeyPressed(evt);
            }
        });

        btnGenerarInforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-informe-comprobar.png"))); // NOI18N
        btnGenerarInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarInformeActionPerformed(evt);
            }
        });
        btnGenerarInforme.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGenerarInformeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFechaI)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(txtFechaF)
                .addGap(204, 204, 204)
                .addComponent(btnGenerarInforme))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-informe-imprimir.png"))); // NOI18N
        btnExportar.setText("Imprimir");
        btnExportar.setMaximumSize(new java.awt.Dimension(73, 33));
        btnExportar.setMinimumSize(new java.awt.Dimension(73, 33));
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccionar Caja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        cbCaja.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbCaja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("USUARIO:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        cbFiltrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GENERAL", "DETALLADO" }));
        cbFiltrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFiltrarItemStateChanged(evt);
            }
        });
        cbFiltrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbFiltrarKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TIPO:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(cbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 102, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        txtCIN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCINActionPerformed(evt);
            }
        });
        txtCIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCINKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CIN:");

        btnBuscarCliente.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-historial-buscar.png"))); // NOI18N
        btnBuscarCliente.setPreferredSize(new java.awt.Dimension(95, 28));
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("NOMBRE:");

        btnTodos.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnTodos.setText("Todos los Clientes");
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCIN, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTodos)
                .addGap(53, 53, 53))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTodos))
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed

    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnGenerarInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarInformeActionPerformed
        generarInforme();
    }//GEN-LAST:event_btnGenerarInformeActionPerformed

    private void cbFiltrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFiltrarItemStateChanged

    }//GEN-LAST:event_cbFiltrarItemStateChanged

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        if (txtCIN.getText().equals("")) {
            jbc = new Jbuscar("informesclientes");
            jbc.setVisible(true);
            jbc.setLocationRelativeTo(null);
        } else {
            buscarCliente();
        }
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtCINKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCINKeyPressed
        char c = (char) evt.getKeyCode();
        if (c == 10) {
            buscarCliente();
        }
    }//GEN-LAST:event_txtCINKeyPressed

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
        txtCIN.setText("TODOS");
        txtNombre.setText("TODOS");
        cbFiltrar.requestFocus();
        cliObj = new Clientes(-1);
    }//GEN-LAST:event_btnTodosActionPerformed

    private void txtCINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCINActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCINActionPerformed

    private void cbFiltrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbFiltrarKeyPressed
        char c = (char) evt.getKeyCode();
        if (c == 10) {
            txtFechaI.requestFocus();
        }
    }//GEN-LAST:event_cbFiltrarKeyPressed

    private void txtFechaIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaIKeyPressed
        char c = (char) evt.getKeyCode();
        if (c == 10) {
            txtFechaF.requestFocus();
        }
    }//GEN-LAST:event_txtFechaIKeyPressed

    private void txtFechaFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaFKeyPressed
        char c = (char) evt.getKeyCode();
        if (c == 10) {
            btnGenerarInforme.requestFocus();
        }
    }//GEN-LAST:event_txtFechaFKeyPressed

    private void btnGenerarInformeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGenerarInformeKeyPressed
        char c = (char) evt.getKeyCode();
        if (c == 10) {
            generarInforme();
        }
    }//GEN-LAST:event_btnGenerarInformeKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnGenerarInforme;
    private javax.swing.JButton btnTodos;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbCaja;
    private javax.swing.JComboBox<String> cbFiltrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelInformes;
    public static javax.swing.JTextField txtCIN;
    private javax.swing.JFormattedTextField txtFechaF;
    private javax.swing.JFormattedTextField txtFechaI;
    public static javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    public void buscarCliente() {
        int sw = 0;

        for (int i = 0; i < main.personasDAO.resultadoClientes.size(); i++) {

            if (txtCIN.getText().equals(main.personasDAO.resultadoClientes.get(i).getCINRUC())) {
                cliObj = main.personasDAO.resultadoClientes.get(i);
                txtCIN.setText("" + cliObj.getCINRUC());
                txtNombre.setText(main.personasDAO.resultadoClientes.get(i).getNombres() + " " + main.personasDAO.resultadoClientes.get(i).getApellidos());
                System.out.println("buscarCliente() " + main.personasDAO.resultadoClientes.get(i).getCINRUC());
                sw = 1;
            }
        }

        if (sw == 0) {
            txtNombre.setText("");
            cliObj = null;

        } else {
            cbFiltrar.requestFocus();
        }

    }

}
