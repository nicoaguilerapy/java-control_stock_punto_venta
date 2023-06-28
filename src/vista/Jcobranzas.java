package vista;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Ventas;
import modelo.VentasDetalles;
import modelo.balance.Balance;

public class Jcobranzas extends javax.swing.JFrame {

    JcobranzasPago jmp;
    JventasVer jmv;

    DefaultTableModel datosTable;
    private TableRowSorter<TableModel> sorter;

    Ventas ventaObj;
    Integer filaSeleccionada = -1, id = -1;
    String clienteCIN, clienteApellido, clienteNombre, estado, fecha;

    ArrayList<Ventas> ventasArrayL;

    public Jcobranzas() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Cobranzas -" + inicio.version);

        datosTable = (DefaultTableModel) table.getModel();

        inicio.tools.Busqued(table, datosTable, txtBuscador);
        limpiarFechas();

        if (inicio.userObj.getRango().equals("Administrador")) {
            btnPagar.setEnabled(false);
        } else {
            btnModificar.setText("Ver");
            Image img = new ImageIcon(getClass().getResource("/imagenes/icon-ver.png")).getImage();
            ImageIcon img2 = new ImageIcon(img.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnModificar.setIcon(img2);
        }

        sorter = new TableRowSorter<>(datosTable);
        table.setRowSorter(sorter);
        table.getSelectionModel().setSelectionInterval(0, 0);
    }

    public void limpiarFechas() {
        String fecha = inicio.gFechas.getFechaActual();
        txtFechaF.setText(fecha);
        txtFechaI.setText(fecha);
        filaSeleccionada = -1;
        id = -1;
    }

    public void actualizar(String tipo, String condicion) {
        int idV = -1, c = 0, idC;

        datosTable.setRowCount(0);

        ventasArrayL = new ArrayList<>();
        ventasArrayL = main.ventasDAO.listar(condicion);

        Object[] fila = new Object[8];

        for (int i = 0; i < ventasArrayL.size(); i++) {

            if (tipo.equals("Todos")) {

                fila[0] = ventasArrayL.get(i).getId();
                fila[1] = ventasArrayL.get(i).getFecha();
                try {
                    fila[2] = ventasArrayL.get(i).getFechaPago();
                } catch (Exception e) {
                    fila[2] = "";
                }

                fila[3] = ventasArrayL.get(i).getIdClientes();
                fila[6] = ventasArrayL.get(i).getEstado();
                fila[7] = ventasArrayL.get(i).getTotalACobrar();

                datosTable.addRow(fila);
            } else {

                if (ventasArrayL.get(i).getEstado().equals(tipo)) {

                    fila[0] = ventasArrayL.get(i).getId();
                    fila[1] = inicio.gFechas.invertir(ventasArrayL.get(i).getFecha());
                    try {
                        fila[2] = inicio.gFechas.invertir(ventasArrayL.get(i).getFechaPago());
                    } catch (Exception e) {
                        fila[2] = "";
                    }
                    fila[3] = ventasArrayL.get(i).getIdClientes();
                    fila[6] = ventasArrayL.get(i).getEstado();
                    fila[7] = ventasArrayL.get(i).getTotalACobrar();

                    datosTable.addRow(fila);
                }
            }
        }

        try {
            getCliente();
            inicio.tools.decimalTable(table, 7);
            inicio.tools.centrar(table, new int[]{0});
        } catch (Exception e) {
            System.out.println("vista.Jcobranzas.actualizar() Error tabla vacio");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtBuscador = new javax.swing.JTextField();
        cbFiltrar = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        txtFechaF = new javax.swing.JFormattedTextField();
        txtFechaI = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnPagar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new ColorTablas.ColorTablaCobranzas();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        cbFiltrar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Pendiente", "Pagado", "Financiado" }));
        cbFiltrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFiltrarItemStateChanged(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-categorias-actualizar.png"))); // NOI18N
        btnActualizar.setToolTipText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        try {
            txtFechaF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFechaF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        try {
            txtFechaI.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFechaI.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setText("Inicio:");

        jLabel3.setText("Fin:");

        jLabel4.setText("Filtrar:");

        jLabel5.setText("Buscador:");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-historial-buscar.png"))); // NOI18N
        jButton2.setToolTipText("Buscar por Fecha");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-ventas-seleccion.png"))); // NOI18N
        btnPagar.setText("Pagar");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-cobranzas-anular.png"))); // NOI18N
        btnAnular.setText("Anular");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-usuario-modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(115, 115, 115)
                .addComponent(btnAnular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnPagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(5, 5, 5)
                        .addComponent(cbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtBuscador)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(15, 15, 15))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "FECHA CREACIÓN", "FECHA PAGO", "CIN", "APELLIDOS", "NOMBRES", "ESTADO", "MONTO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizar("Todos", "");
        limpiarFechas();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String fecha1 = txtFechaI.getText();
        String fecha = inicio.gFechas.invertir(fecha1);
        fecha1 = fecha;
        String fecha2 = txtFechaF.getText();
        fecha = inicio.gFechas.invertir(fecha2);
        fecha2 = fecha;

        if (inicio.gFechas.validarFecha(fecha1) && inicio.gFechas.validarFecha(fecha2)) {
            actualizar("Todos", " WHERE fechapago BETWEEN '" + fecha1 + "' AND '" + fecha2 + "'");
        } else {
            JOptionPane.showMessageDialog(null, "Fechas vacias o incorrectas", "Comprobar Informe", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        try {
            pagar();
        } catch (Exception e) {
            System.out.println("vista.Jcobranzas.btnSeleccionarActionPerformed() seleccionar");
        }
    }//GEN-LAST:event_btnPagarActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        try {
            anular();
            actualizar((String) cbFiltrar.getSelectedItem(), "");
        } catch (Exception e) {
            System.out.println("vista.Jcobranzas.btnSeleccionarActionPerformed() anular" + e.getMessage());
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
        actualizar((String) cbFiltrar.getSelectedItem(), "");
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        filaSeleccionada = table.getSelectedRow();
        id = Integer.parseInt(table.getValueAt(filaSeleccionada, 0).toString());
        System.out.println("vista.Jcobranzas.tableMouseClicked()  ID: " + id);

        ventaObj = main.ventasDAO.getVenta(id);
        if (ventaObj != null) {

        } else {
            System.out.println("No encontrado");
        }

    }//GEN-LAST:event_tableMouseClicked

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void cbFiltrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFiltrarItemStateChanged
        actualizar((String) cbFiltrar.getSelectedItem(), "");
    }//GEN-LAST:event_cbFiltrarItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JComboBox<String> cbFiltrar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JFormattedTextField txtFechaF;
    private javax.swing.JFormattedTextField txtFechaI;
    // End of variables declaration//GEN-END:variables

    public void getCliente() {
        int i, j, idV, idC;

        for (i = 0; i < datosTable.getRowCount(); i++) {
            idV = Integer.parseInt(table.getValueAt(i, 3).toString());

            for (j = 0; j < main.personasDAO.resultadoClientes.size(); j++) {
                idC = main.personasDAO.resultadoClientes.get(j).getId();

                if (idV == idC) {
                    clienteCIN = main.personasDAO.resultadoClientes.get(j).getCINRUC();
                    clienteApellido = main.personasDAO.resultadoClientes.get(j).getApellidos();
                    clienteNombre = main.personasDAO.resultadoClientes.get(j).getNombres();
                }
            }
            table.setValueAt(clienteApellido, i, 4);
            table.setValueAt(clienteNombre, i, 5);
        }
    }

    public boolean pagar() {
        if (!inicio.login.jMenu.cierreCaja && inicio.login.jMenu.aperturaCaja) {
            if (ventaObj.getEstado().equals("Pendiente")) {

                jmp = new JcobranzasPago(ventaObj);
                jmp.setVisible(true);
                jmp.setLocationRelativeTo(null);
                return true;

            } else {
                JOptionPane.showMessageDialog(null, "No puedes seleccionar una Venta ya Pagada", "Error al Seleccionar Venta", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Caja cerrada", "Seleccionar Venta", JOptionPane.ERROR_MESSAGE);
        }

        return false;

    }

    public boolean anular() {
        if (!inicio.login.jMenu.cierreCaja && inicio.login.jMenu.aperturaCaja) {

            if (ventaObj.getEstado().equals("Pagado")) {
                int inforTipo = JOptionPane.showOptionDialog(null,
                        "¿Quieres anular La Venta Nº" + ventaObj.getId() + " y Factura: " + ventaObj.getFacturanum() + "?",
                        "Selecciona Caja de Anulación",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                        new Object[]{"Caja"}, "salir");
                try {
                    if (inforTipo == 0) {

                        Balance balObj = (Balance) main.balanceDAO.listar(" where concepto like '%#(" + ventaObj.getId() + ") Venta%' or concepto like '%#(" + ventaObj.getId() + ")Venta%' ", "caja").get(0);
                        main.balanceDAO.eliminar(balObj, "caja");

                        balObj = (Balance) main.balanceDAO.listar(" where concepto like '%#(" + ventaObj.getId() + ")Venta%'  ", "ivadebito").get(0);
                        main.balanceDAO.eliminar(balObj, "ivadebito");

                        ventaObj.setEstado("Anulado");
                        main.ventasDAO.modificar(ventaObj);

                        inicio.gAyC.actualizarAyC();
                    } else {
                        if (inforTipo == 1) {
                            /*Banco banco = new Banco(null,
                                    "#(" + ventaObj.getId() + ")Anulacion en Banco Factura Nº: " + ventaObj.getFacturanum(),
                                    0,
                                    ventaObj.getTotalACobrar(),
                                    inicio.fechaYMD,
                                    inicio.userObj.getId());

                            Ivadebito ivadebito = new Ivadebito(null,
                                    "#(" + ventaObj.getId() + ")Anulacion en Banco Factura Nº: " + ventaObj.getFacturanum(),
                                    0,
                                    ventaObj.getIvaACobrar(),
                                    inicio.fechaYMD,
                                    inicio.userObj.getId());

                            System.out.println(banco.toString());
                            System.out.println(ivadebito.toString());

                            main.balanceDAO.insertar(banco);
                            main.balanceDAO.insertar(ivadebito);

                            ventaObj.setEstado("Anulado");
                            main.ventasDAO.modificar(ventaObj);*/

                        } else {
                            if (inforTipo == 2) {
                                /* Tarjeta tarjeta = new Tarjeta(null,
                                        "#(" + ventaObj.getId() + ")Anulacion en Tarjeta Factura Nº: " + ventaObj.getFacturanum(),
                                        0,
                                        ventaObj.getTotalACobrar(),
                                        inicio.fechaYMD,
                                        inicio.userObj.getId());

                                Ivadebito ivadebito = new Ivadebito(null,
                                        "#(" + ventaObj.getId() + ")Anulacion en Tarjeta Factura Nº: " + ventaObj.getFacturanum(),
                                        0,
                                        ventaObj.getIvaACobrar(),
                                        inicio.fechaYMD,
                                        inicio.userObj.getId());

                                System.out.println(tarjeta.toString());
                                System.out.println(ivadebito.toString());

                                main.balanceDAO.insertar(tarjeta);
                                main.balanceDAO.insertar(ivadebito);

                                ventaObj.setEstado("Anulado");
                                main.ventasDAO.modificar(ventaObj);*/

                            }
                        }
                    }
                } catch (Exception e) {
                }
            } else {
                if (ventaObj.getEstado().equals("Pendiente")) {
                    ventaObj.setEstado("Anulado");
                    main.ventasDAO.modificar(ventaObj);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una venta para anular", "Error al Seleccionar Venta", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Caja cerrada", "Seleccionar Venta", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public void modificar() {
        filaSeleccionada = table.getSelectedRow();
        id = Integer.parseInt(table.getValueAt(filaSeleccionada, 0).toString());

        for (int i = 0; i < main.ventasDAO.ventasArray.size(); i++) {

            if (Objects.equals(main.ventasDAO.ventasArray.get(i).getId(), id)) {
                ventaObj = main.ventasDAO.ventasArray.get(i);

                jmv = new JventasVer(ventaObj);
                jmv.setVisible(true);
                jmv.setLocationRelativeTo(null);

            }
        }
    }

}
