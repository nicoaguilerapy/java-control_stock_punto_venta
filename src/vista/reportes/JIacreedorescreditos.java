package vista.reportes;

import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Acreedores;
import modelo.Creditos;
import modelo.CreditosPagos;
import modelo.CreditosVentas;
import modelo.Ventas;
import modelo.balance.Balance;
import modelo.balance.BalanceReport;
import modelo.persona.Personas;
import vista.inicio;
import vista.main;

public class JIacreedorescreditos extends javax.swing.JFrame {

    List<Balance> BalanceArray;

    List<Acreedores> acreedoresArray;

    List<Creditos> creditosArray;
    List<CreditosPagos> creditosPagosArray;
    List<CreditosVentas> creditosVentasArray;

    DefaultTableModel datosTable;
    Integer ingreso = 0, egreso = 0;

    String tipo = "", fecha, fechaT, condicion2, condicion;

    List lista;
    int pdf = 0;
    File PDF;
    byte[] datosPDF;

    int filaSelecionada, id = -1;

    public JIacreedorescreditos() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Informe Acreedores y Creditos -" + inicio.version);
        //btnImprimir.setEnabled(false);

        fecha = inicio.gFechas.getFechaActual();
        txtFechaI.setText("" + fecha);
        txtFechaF.setText("" + fecha);

        actualizarCBusuario();

        datosTable = (DefaultTableModel) tableInforme.getModel();

        lbCreditos.setVisible(false);
        cbCreditos.setVisible(false);

        if (inicio.userObj.getRango().equals("Operador")) {
            cbCaja.setEnabled(false);
        }
    }

    public void getTipoInforme(String condicion) {
        Integer aux = -1;
        ingreso = 0;
        egreso = 0;
        datosTable = (DefaultTableModel) tableInforme.getModel();
        datosTable.setRowCount(0);

        Object[] fila = new Object[datosTable.getColumnCount()];

        if (cbFiltrar.getSelectedIndex() == 0) {
            acreedoresArray = main.acreedoresDAO.listar(condicion);
            generarAcreedores();
        } else {
            creditosArray = main.creditosDAO.listar("");
            generarCreditos(condicion);
        }
    }

    public void generarAcreedores() {
        System.out.println("vista.reportes.JIacreedores.generarAcreedores()");
        Integer ingresoM = 0, egresoM = 0;
        for (Acreedores acree : acreedoresArray) {
            egresoM = 0;
            System.out.println("");
            ingresarTabla(acree.getFecha(), acree.getDescripcion(), acree.getMonto().toString(), "0");
            BalanceArray = main.balanceDAO.listar(" where concepto='#(" + acree.getId() + ")Pago de Prestamo'", "caja");
            for (Balance bal : BalanceArray) {
                System.out.println(bal.toString());
                ingresarTabla(bal.getFecha(), bal.getConcepto(), "0", bal.getEgreso().toString());
                egresoM = egresoM + bal.getEgreso();
            }

            ingresarTabla("", "Sumatorias", acree.getMonto().toString(), egresoM.toString());

            if (ingresoM > egresoM) {
                ingresarTabla("", "Diferencia de Sumas Totales", (acree.getMonto() - egresoM) + "", "0");
            } else {
                if (egresoM > ingresoM) {
                    ingresarTabla("", "Diferencia de Sumas Totales", "0", (egresoM - acree.getMonto()) + "");
                } else {
                    ingresarTabla("", "Diferencia de Sumas Totales", "0", "0");
                }
            }

            ingresarTabla("", "", "", "");

        }

        inicio.tools.decimalTable(tableInforme, 3);
        inicio.tools.decimalTable(tableInforme, 2);
    }

    public void generarCreditos(String condicion) {
        System.out.println("vista.reportes.JIacreedores.generarCreditos()");
        Integer ingresoM = 0, egresoM = 0;
        Personas per = null;
        Ventas ven = null;
        String condicion2 = condicion.replace("WHERE", " and ");
        Integer cond = cbCreditos.getSelectedIndex();
        boolean isPagos = false;
        boolean isVentas = false;
        boolean isTodos = false;

        if (cond == 0) {
            isTodos = true;
        } else {
            if (cond == 1) {
                isVentas = true;
            } else {
                if (cond == 2) {
                    isPagos = true;
                }
            }
        }

        for (Creditos cred : creditosArray) {

            egresoM = 0;
            ingresoM = 0;
            per = (Personas) main.personasDAO.mapClientes.get(cred.getIdClientes());

            creditosVentasArray = main.creditosVentasDAO.listar(" where idcreditos='" + cred.getId() + "'" + condicion2);
            creditosPagosArray = main.creditosPagosDAO.listar(" where idcreditos='" + cred.getId() + "'" + condicion2);
            if (!creditosVentasArray.isEmpty() && !creditosPagosArray.isEmpty()) {
                ingresarTabla("", per.getNombreCompleto(), "", "");

                if (isVentas || isTodos) {
                    try {

                        for (CreditosVentas credVen : creditosVentasArray) {
                            ven = main.ventasDAO.getVenta(credVen.getIdVentas());
                            //System.out.println(credVen.toString());
                            ingresarTabla(credVen.getFecha(), "Venta Nº: " + credVen.getIdVentas(), "0", "" + ven.getTotalACobrar());
                            egresoM = egresoM + ven.getTotalACobrar();
                        }
                    } catch (Exception e) {
                    }
                }

                if (isPagos || isTodos) {
                    try {
                        for (CreditosPagos credPag : creditosPagosArray) {
                            System.out.println(credPag.toString());
                            ingresarTabla(credPag.getFecha(), "Pago ID Nº: " + credPag.getId(), "" + credPag.getPago(), "0");
                            ingresoM = ingresoM + credPag.getPago();
                        }
                    } catch (Exception e) {
                    }
                }

                ingresarTabla("", "Sumatorias", "" + ingresoM, "" + egresoM);

                if (ingresoM > egresoM) {
                    ingresarTabla("", "Diferencia de Sumas Totales", (ingresoM - egresoM) + "", "0");
                } else {
                    if (egresoM > ingresoM) {
                        ingresarTabla("", "Diferencia de Sumas Totales", "0", (egresoM - ingresoM) + "");
                    } else {
                        ingresarTabla("", "Diferencia de Sumas Totales", "0", "0");
                    }
                }

                ingresarTabla("", "", "", "");
            }
        }
        inicio.tools.decimalTable(tableInforme, 3);
        inicio.tools.decimalTable(tableInforme, 2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableInforme =  new ColorTablas.ColorTablaInformes();
        jPanel5 = new javax.swing.JPanel();
        txtFechaI = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaF = new javax.swing.JFormattedTextField();
        btnComprobarEspe = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbCaja = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbFiltrar = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbCreditos = new javax.swing.JComboBox<>();
        lbCreditos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));

        tableInforme.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "CONCEPTO", "INGRESO", "EGRESO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableInforme);

        jPanel5.setBackground(new java.awt.Color(0, 51, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fechas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        try {
            txtFechaI.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFechaI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        btnComprobarEspe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-informe-comprobar.png"))); // NOI18N
        btnComprobarEspe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprobarEspeActionPerformed(evt);
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
                .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(btnComprobarEspe)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnComprobarEspe, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jPanel2.setBackground(new java.awt.Color(0, 51, 153));
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

        jPanel3.setBackground(new java.awt.Color(0, 51, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        cbFiltrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acreedores", "Creditos" }));
        cbFiltrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFiltrarItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("FILTRAR:");

        cbCreditos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbCreditos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Ventas", "Pagos" }));

        lbCreditos.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbCreditos.setForeground(new java.awt.Color(255, 255, 255));
        lbCreditos.setText("CREDITOS:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(lbCreditos))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCreditos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbFiltrar, 0, 150, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCreditos)
                    .addComponent(cbCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExportar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
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

    private void btnComprobarEspeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprobarEspeActionPerformed
        comprobarRango();
    }//GEN-LAST:event_btnComprobarEspeActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        exportarExcel((String) cbFiltrar.getSelectedItem());
    }//GEN-LAST:event_btnExportarActionPerformed

    private void cbFiltrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFiltrarItemStateChanged
        if (cbFiltrar.getSelectedItem().equals("Creditos")) {
            lbCreditos.setVisible(true);
            cbCreditos.setVisible(true);
        } else {
            lbCreditos.setVisible(false);
            cbCreditos.setVisible(false);
        }
    }//GEN-LAST:event_cbFiltrarItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprobarEspe;
    private javax.swing.JButton btnExportar;
    private javax.swing.JComboBox<String> cbCaja;
    private javax.swing.JComboBox<String> cbCreditos;
    private javax.swing.JComboBox<String> cbFiltrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCreditos;
    private javax.swing.JTable tableInforme;
    private javax.swing.JFormattedTextField txtFechaF;
    private javax.swing.JFormattedTextField txtFechaI;
    // End of variables declaration//GEN-END:variables

    public void comprobarRango() {
        String fechaI = inicio.gFechas.invertir(txtFechaI.getText());
        String fechaF = inicio.gFechas.invertir(txtFechaF.getText());

        String condicion = "WHERE fecha BETWEEN '" + fechaI + "' AND '" + fechaF + "' AND idUsuarios='" + getIdUsuario() + "'";

        getTipoInforme(condicion);
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

    public void exportarExcel(String tipo) {
        BalanceReport listaCaj = null;
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("titulo", "Reporte de " + tipo);

        List lista = new ArrayList();

        for (int i = 0; i < tableInforme.getRowCount(); i++) {

            listaCaj = new BalanceReport(
                    tableInforme.getValueAt(i, 0).toString(),
                    tableInforme.getValueAt(i, 1).toString(),
                    tableInforme.getValueAt(i, 2).toString(),
                    tableInforme.getValueAt(i, 3).toString()
            );
            lista.add(listaCaj);
        }

        inicio.gReportes.iniciarReporteList(map, lista);
    }

    private void ingresarTabla(String fech, String concepto, String x, String y) {
        Object[] fila = new Object[datosTable.getColumnCount()];
        fila[0] = fech;
        fila[1] = concepto;
        fila[2] = x;
        fila[3] = y;

        datosTable.addRow(fila);
    }

}
