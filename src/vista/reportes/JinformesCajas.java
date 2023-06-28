package vista.reportes;

import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.balance.Balance;
import modelo.balance.BalanceReport;
import vista.inicio;
import vista.main;

public class JinformesCajas extends javax.swing.JFrame {

    List<Balance> BalanceArray;

    DefaultTableModel datosTable;
    Integer ingreso = 0, egreso = 0;

    String tipo = "", fecha, fechaT, condicion2, condicion;

    List lista;
    int pdf = 0;
    File PDF;
    byte[] datosPDF;

    int filaSelecionada, id = -1;

    public JinformesCajas(String aux) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Informe " + aux + " -" + inicio.version);
        //btnImprimir.setEnabled(false);

        fecha = inicio.gFechas.getFechaActual();
        txtFechaI.setText("" + fecha);
        txtFechaF.setText("" + fecha);

        datosTable = (DefaultTableModel) tableInforme.getModel();

        tipo = aux;

        actualizarCBano();
        actualizarCBusuario();

        if (inicio.userObj.getRango().equals("Operador")) {
            cbCaja.setEnabled(false);
        }

        lbEgreso.setVisible(false);
        cbEgreso.setVisible(false);
    }

    public void generarInforme(String condicion) {
        Integer aux = -1;
        ingreso = 0;
        egreso = 0;
        datosTable = (DefaultTableModel) tableInforme.getModel();
        datosTable.setRowCount(0);

        Object[] fila = new Object[datosTable.getColumnCount()];

        BalanceArray = main.balanceDAO.listar(condicion, tipo);
        Integer ingresoM = 0, egresoM = 0;
        String mes = "", mesAux = "";
        int sw = 0;
        boolean isEgreso = false;
        boolean isIngreso = false;
        boolean isTodos = false;
        boolean isVentasCompras = false;

        if (cbFiltrar.getSelectedItem().equals("Egreso")) {
            isEgreso = true;
        } else {
            if (cbFiltrar.getSelectedItem().equals("Ingreso")) {
                isIngreso = true;
            } else {
                if (cbFiltrar.getSelectedItem().equals("Todos")) {
                    isTodos = true;
                } else {
                    if (cbFiltrar.getSelectedItem().equals("Ventas y Gastos/Compras")) {
                        isVentasCompras = true;
                    }
                }
            }
        }

        String condi = cbEgreso.getSelectedItem().toString();
        boolean sw1 = false;
        if (condi.equals("Todos")) {
            sw1 = true;
        }

        System.out.println("vista.JinformeCyB.generarInforme() " + cbEgreso.getSelectedIndex());

        if (!isVentasCompras) {
            for (int i = 0; i < BalanceArray.size(); i++) {

                if (!BalanceArray.get(i).getConcepto().contains("# Cierre") && !BalanceArray.get(i).getConcepto().contains("# Apertura")
                        && !BalanceArray.get(i).getConcepto().contains("*Cierre") && !BalanceArray.get(i).getConcepto().contains("*Apertura") && !BalanceArray.get(i).getConcepto().contains("&Sin")) {

                    if ((isIngreso && BalanceArray.get(i).getEgreso() == 0) || (isEgreso && BalanceArray.get(i).getIngreso() == 0) || isTodos) {
                        if (isEgreso && sw1 || isTodos || isIngreso || (!sw1 && BalanceArray.get(i).getConcepto().contains(condi))) {

                            ingreso = ingreso + BalanceArray.get(i).getIngreso();
                            egreso = egreso + BalanceArray.get(i).getEgreso();

                            if (sw == 0) {
                                mesAux = inicio.gFechas.getMes(BalanceArray.get(i).getFecha());
                                ingresoM = ingresoM + BalanceArray.get(i).getIngreso();
                                egresoM = egresoM + BalanceArray.get(i).getEgreso();
                                sw = 1;
                            } else {
                                mes = inicio.gFechas.getMes(BalanceArray.get(i).getFecha());
                                if (mesAux.equals(mes)) {

                                    ingresoM = ingresoM + BalanceArray.get(i).getIngreso();
                                    egresoM = egresoM + BalanceArray.get(i).getEgreso();

                                } else {

                                    try {
                                        aux = Integer.parseInt(mesAux);
                                    } catch (Exception e) {
                                        aux = -1;
                                    }
                                    ingresarTabla("Sumas Totales de " + inicio.gFechas.getMesString(aux), ingresoM, egresoM);

                                    if (ingresoM > egresoM) {
                                        ingresarTabla("Diferencia de Sumas Totales", ingresoM - egresoM, 0);
                                    } else {
                                        if (egresoM > ingresoM) {
                                            ingresarTabla("Diferencia de Sumas Totales", 0, egresoM - ingresoM);
                                        } else {
                                            ingresarTabla("Diferencia de Sumas Totales", 0, 0);
                                        }
                                    }

                                    mesAux = mes;
                                    ingresoM = 0;
                                    egresoM = 0;
                                    ingresoM = ingresoM + BalanceArray.get(i).getIngreso();
                                    egresoM = egresoM + BalanceArray.get(i).getEgreso();
                                }
                            }

                            fila[0] = inicio.gFechas.invertir(BalanceArray.get(i).getFecha());
                            fila[1] = BalanceArray.get(i).getConcepto();
                            fila[2] = BalanceArray.get(i).getIngreso();
                            fila[3] = BalanceArray.get(i).getEgreso();
                            datosTable.addRow(fila);
                        }
                    }
                }
            }

            try {
                aux = Integer.parseInt(mesAux);
            } catch (Exception e) {
                aux = -1;
            }
            ingresarTabla("Sumas Totales de " + inicio.gFechas.getMesString(aux), ingresoM, egresoM);

            if (ingresoM > egresoM) {
                ingresarTabla("Diferencia de Sumas Totales", ingresoM - egresoM, 0);
            } else {
                if (egresoM > ingresoM) {
                    ingresarTabla("Diferencia de Sumas Totales", 0, egresoM - ingresoM);
                } else {
                    ingresarTabla("Diferencia de Sumas Totales", 0, 0);
                }
            }

            ingresarTabla("Sumas Totales del RANGO DE FECHAS", ingreso, egreso);

            if (ingreso > egreso) {
                ingresarTabla("Diferencia de Sumas Totales", ingreso - egreso, 0);
            } else {
                if (egresoM > ingresoM) {
                    ingresarTabla("Diferencia de Sumas Totales", 0, egreso - ingreso);
                } else {
                    ingresarTabla("Diferencia de Sumas Totales", 0, 0);
                }
            }

            inicio.tools.decimalTable(tableInforme, 3);
            inicio.tools.decimalTable(tableInforme, 2);
        } else {
            for (int i = 0; i < BalanceArray.size(); i++) {

                if (!BalanceArray.get(i).getConcepto().contains("# Cierre") && !BalanceArray.get(i).getConcepto().contains("# Apertura")
                        && !BalanceArray.get(i).getConcepto().contains("*Cierre") && !BalanceArray.get(i).getConcepto().contains("*Apertura") && !BalanceArray.get(i).getConcepto().contains("&Sin")) {

                    if (BalanceArray.get(i).getConcepto().contains("Venta en Caja") || BalanceArray.get(i).getConcepto().contains("Compra de") || BalanceArray.get(i).getConcepto().contains("Gasto")) {

                        ingreso = ingreso + BalanceArray.get(i).getIngreso();
                        egreso = egreso + BalanceArray.get(i).getEgreso();

                        if (sw == 0) {
                            mesAux = inicio.gFechas.getMes(BalanceArray.get(i).getFecha());
                            ingresoM = ingresoM + BalanceArray.get(i).getIngreso();
                            egresoM = egresoM + BalanceArray.get(i).getEgreso();
                            sw = 1;
                        } else {
                            mes = inicio.gFechas.getMes(BalanceArray.get(i).getFecha());
                            if (mesAux.equals(mes)) {

                                ingresoM = ingresoM + BalanceArray.get(i).getIngreso();
                                egresoM = egresoM + BalanceArray.get(i).getEgreso();

                            } else {

                                try {
                                    aux = Integer.parseInt(mesAux);
                                } catch (Exception e) {
                                    aux = -1;
                                }
                                ingresarTabla("Sumas Totales de " + inicio.gFechas.getMesString(aux), ingresoM, egresoM);

                                if (ingresoM > egresoM) {
                                    ingresarTabla("Diferencia de Sumas Totales", ingresoM - egresoM, 0);
                                } else {
                                    if (egresoM > ingresoM) {
                                        ingresarTabla("Diferencia de Sumas Totales", 0, egresoM - ingresoM);
                                    } else {
                                        ingresarTabla("Diferencia de Sumas Totales", 0, 0);
                                    }
                                }

                                mesAux = mes;
                                ingresoM = 0;
                                egresoM = 0;
                                ingresoM = ingresoM + BalanceArray.get(i).getIngreso();
                                egresoM = egresoM + BalanceArray.get(i).getEgreso();
                            }
                        }

                        fila[0] = inicio.gFechas.invertir(BalanceArray.get(i).getFecha());
                        fila[1] = BalanceArray.get(i).getConcepto();
                        fila[2] = BalanceArray.get(i).getIngreso();
                        fila[3] = BalanceArray.get(i).getEgreso();
                        datosTable.addRow(fila);
                    }
                }
            }

            try {
                aux = Integer.parseInt(mesAux);
            } catch (Exception e) {
                aux = -1;
            }
            ingresarTabla("Sumas Totales de " + inicio.gFechas.getMesString(aux), ingresoM, egresoM);

            if (ingresoM > egresoM) {
                ingresarTabla("Diferencia de Sumas Totales", ingresoM - egresoM, 0);
            } else {
                if (egresoM > ingresoM) {
                    ingresarTabla("Diferencia de Sumas Totales", 0, egresoM - ingresoM);
                } else {
                    ingresarTabla("Diferencia de Sumas Totales", 0, 0);
                }
            }

            ingresarTabla("Sumas Totales del RANGO DE FECHAS", ingreso, egreso);

            if (ingreso > egreso) {
                ingresarTabla("Diferencia de Sumas Totales", ingreso - egreso, 0);
            } else {
                if (egresoM > ingresoM) {
                    ingresarTabla("Diferencia de Sumas Totales", 0, egreso - ingreso);
                } else {
                    ingresarTabla("Diferencia de Sumas Totales", 0, 0);
                }
            }

            inicio.tools.decimalTable(tableInforme, 3);
            inicio.tools.decimalTable(tableInforme, 2);
        }
    }

    private void actualizarCBano() {
        cbAno.removeAllItems();
        ArrayList<String> fechasArray = new ArrayList();
        main.balanceDAO.actualizarCaja("");
        int size = main.balanceDAO.resultadoCaja.size();
        size--;
        fechasArray = inicio.gFechas.yearArrayList(main.balanceDAO.resultadoCaja.get(0).getFecha(), main.balanceDAO.resultadoCaja.get(size).getFecha());
        for (int i = 0; i < fechasArray.size(); i++) {
            cbAno.addItem(fechasArray.get(i));
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
        tableInforme =  new ColorTablas.ColorTablaInformes();
        jPanel4 = new javax.swing.JPanel();
        cbAno = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbMes = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnComprobarRap = new javax.swing.JButton();
        btnComprobarRap1 = new javax.swing.JButton();
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
        lbEgreso = new javax.swing.JLabel();
        cbEgreso = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(".:: INFORME CAJA - ELGRILLO ::.");

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

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
        if (tableInforme.getColumnModel().getColumnCount() > 0) {
            tableInforme.getColumnModel().getColumn(0).setResizable(false);
            tableInforme.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableInforme.getColumnModel().getColumn(1).setResizable(false);
            tableInforme.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableInforme.getColumnModel().getColumn(2).setResizable(false);
            tableInforme.getColumnModel().getColumn(3).setResizable(false);
            tableInforme.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        jPanel4.setBackground(new java.awt.Color(0, 102, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informes Rápidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        cbAno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("AÑO:");

        cbMes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("MES:");

        btnComprobarRap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-informe-comprobar.png"))); // NOI18N
        btnComprobarRap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprobarRapActionPerformed(evt);
            }
        });

        btnComprobarRap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-informe-comprobar.png"))); // NOI18N
        btnComprobarRap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprobarRap1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnComprobarRap1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnComprobarRap)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnComprobarRap1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(btnComprobarRap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 102, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informes Especificos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnComprobarEspe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComprobarEspe, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        cbFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Ingreso", "Egreso", "Ventas y Gastos/Compras" }));
        cbFiltrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFiltrarItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("FILTRAR:");

        lbEgreso.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbEgreso.setForeground(new java.awt.Color(255, 255, 255));
        lbEgreso.setText("EGRESO:");

        cbEgreso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbEgreso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Compra de Mercaderias", "Gastos Operativos", "Insumos", "Bienes Activos" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEgreso)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbFiltrar, 0, 1, Short.MAX_VALUE)
                    .addComponent(cbEgreso, 0, 172, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEgreso))
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
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExportar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
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

    private void btnComprobarRapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprobarRapActionPerformed
        comprobarMes();
    }//GEN-LAST:event_btnComprobarRapActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        exportarExcel();
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnComprobarEspeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprobarEspeActionPerformed
        comprobarRango();
    }//GEN-LAST:event_btnComprobarEspeActionPerformed

    private void btnComprobarRap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprobarRap1ActionPerformed
        comprobarAno();
    }//GEN-LAST:event_btnComprobarRap1ActionPerformed

    private void cbFiltrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFiltrarItemStateChanged
        if (cbFiltrar.getSelectedItem().equals("Egreso")) {
            cbEgreso.setVisible(true);
            lbEgreso.setVisible(true);
        } else {
            cbEgreso.setVisible(false);
            lbEgreso.setVisible(false);
        }
    }//GEN-LAST:event_cbFiltrarItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprobarEspe;
    private javax.swing.JButton btnComprobarRap;
    private javax.swing.JButton btnComprobarRap1;
    private javax.swing.JButton btnExportar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbAno;
    private javax.swing.JComboBox<String> cbCaja;
    private javax.swing.JComboBox<String> cbEgreso;
    private javax.swing.JComboBox<String> cbFiltrar;
    private javax.swing.JComboBox<String> cbMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbEgreso;
    private javax.swing.JTable tableInforme;
    private javax.swing.JFormattedTextField txtFechaF;
    private javax.swing.JFormattedTextField txtFechaI;
    // End of variables declaration//GEN-END:variables

    public void comprobarMes() {
        String year, mouth;
        if (cbMes.getSelectedIndex() + 1 <= 9) {
            mouth = "0" + (cbMes.getSelectedIndex() + 1);
        } else {
            mouth = "" + (cbMes.getSelectedIndex() + 1);
        }

        year = (String) cbAno.getSelectedItem();

        String condicion = "WHERE fecha BETWEEN '" + year + "/" + mouth + "/01' AND '" + year + "/" + mouth + "/31' AND idUsuarios='" + getIdUsuario() + "'";

        generarInforme(condicion);
    }

    public void comprobarRango() {
        String fechaI = inicio.gFechas.invertir(txtFechaI.getText());
        String fechaF = inicio.gFechas.invertir(txtFechaF.getText());

        String condicion = "WHERE fecha BETWEEN '" + fechaI + "' AND '" + fechaF + "' AND idUsuarios='" + getIdUsuario() + "'";

        generarInforme(condicion);
    }

    public void comprobarAno() {
        String year = (String) cbAno.getSelectedItem();

        String condicion = "WHERE fecha BETWEEN '" + year + "/01/01' AND '" + year + "/12/31' AND idUsuarios='" + getIdUsuario() + "'";

        generarInforme(condicion);
    }

    public void exportarExcel() {
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

    private void ingresarTabla(String concepto, Integer x, Integer y) {
        Object[] fila = new Object[datosTable.getColumnCount()];
        fila[0] = "";
        fila[1] = concepto;
        fila[2] = x;
        fila[3] = y;

        datosTable.addRow(fila);
    }
}
