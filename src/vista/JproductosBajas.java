package vista;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.BajasProductos;
import modelo.Productos;

public class JproductosBajas extends javax.swing.JFrame {

    Productos prodObj;
    DefaultTableModel datosProductos;
    Jbuscar jbc;

    public JproductosBajas() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Bajas Productos -" + inicio.version);

        txtProducto.setEnabled(false);
        txtMotivo.setEnabled(false);
        txtCantidad.setEnabled(false);

        datosProductos = (DefaultTableModel) table.getModel();
        inicio.tools.Busqued(table, datosProductos, txtBuscador);

        actualizar("");
    }

    public void buscarProducto() {
        try {
            prodObj = (Productos) main.productosDAO.map.get(txtCodigo.getText());
            if (prodObj.isIlimitado()) {
                JOptionPane.showMessageDialog(null, "No se puede agregar un producto ilimitado", "Agregar Lote", JOptionPane.ERROR_MESSAGE);
            } else {
                txtProducto.setText("" + prodObj.getNombre());
                txtMotivo.setEnabled(true);
                txtCantidad.setEnabled(true);
                txtCantidad.requestFocus();
            }
        } catch (Exception e) {
            prodObj = null;
            txtProducto.setText("No Encontrado");
        }
    }

    public void getProducto(Productos obj) {
        txtCodigo.setText(obj.getCodigo());
        buscarProducto();
    }

    public void actualizar(String condicion) {
        Integer suma = 0;
        datosProductos.setRowCount(0);
        main.bajasProductosDAO.actualizarLista(condicion);

        Object[] fila = new Object[datosProductos.getColumnCount()];

        for (int i = 0; i < main.bajasProductosDAO.resultadoBajas.size(); i++) {

            Productos prod = (Productos) main.productosDAO.mapId.get(main.bajasProductosDAO.resultadoBajas.get(i).getIdProducto());
            suma = main.lotesDetallesDAO.getUltimoValor(prod.getId()) * main.bajasProductosDAO.resultadoBajas.get(i).getCantidad();

            fila[0] = main.bajasProductosDAO.resultadoBajas.get(i).getId();
            fila[1] = inicio.gFechas.invertir(main.bajasProductosDAO.resultadoBajas.get(i).getFecha());
            fila[2] = "[" + prod.getCodigo() + "] " + prod.getNombre();
            fila[3] = main.bajasProductosDAO.resultadoBajas.get(i).getMotivo();
            fila[4] = main.bajasProductosDAO.resultadoBajas.get(i).getCantidad();
            fila[5] = inicio.format.format(suma);

            datosProductos.addRow(fila);
        }

        txtValorP.setText(inicio.format.format(suma));

    }

    private void limpiar() {
        txtProducto.setEnabled(false);
        txtMotivo.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtProducto.setText("");
        txtMotivo.setText("");
        txtCantidad.setText("");
        txtCodigo.setText("");
        prodObj = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscarProd = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        txtMotivo = new javax.swing.JTextField();
        btnBaja = new javax.swing.JButton();
        btnBuscarProd1 = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtBuscador = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtFechaI = new javax.swing.JFormattedTextField();
        txtFechaF = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        btnGenerarInforme = new javax.swing.JButton();
        txtValorP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnBaja1 = new javax.swing.JButton();

        btnBuscarProd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnBuscarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-historial-buscar.png"))); // NOI18N
        btnBuscarProd.setPreferredSize(new java.awt.Dimension(95, 28));
        btnBuscarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProdActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Cod. Producto:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Motivo:");

        txtCodigo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        txtProducto.setEditable(false);
        txtProducto.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtMotivo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMotivoKeyPressed(evt);
            }
        });

        btnBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-producto-eliminar.png"))); // NOI18N
        btnBaja.setText("Dar Baja");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });
        btnBaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBajaKeyPressed(evt);
            }
        });

        btnBuscarProd1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnBuscarProd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-historial-buscar.png"))); // NOI18N
        btnBuscarProd1.setPreferredSize(new java.awt.Dimension(95, 28));
        btnBuscarProd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProd1ActionPerformed(evt);
            }
        });

        txtCantidad.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Cantidad:");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fecha", "Producto", "Motivo", "Cantidad", "Perdida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador::::"));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("INICIO:");

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

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("FIN:");

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

        txtValorP.setEditable(false);
        txtValorP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtValorP.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Perdidas en Bajas:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGenerarInforme)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtValorP, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGenerarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtValorP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        btnBaja1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-producto-agregar.png"))); // NOI18N
        btnBaja1.setText("Anular Baja");
        btnBaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaja1ActionPerformed(evt);
            }
        });
        btnBaja1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBaja1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                            .addComponent(txtBuscador)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarProd1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBaja, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnBaja1)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscarProd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBaja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBaja1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdActionPerformed
        if (txtCodigo.getText().equals("")) {
            jbc = new Jbuscar("bajaproducto");
            jbc.setVisible(true);
            jbc.setLocationRelativeTo(null);
        } else {
            buscarProducto();
        }
    }//GEN-LAST:event_btnBuscarProdActionPerformed

    private void btnBuscarProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProd1ActionPerformed
        if (txtCodigo.getText().equals("")) {
            jbc = new Jbuscar("bajaproducto");
            jbc.setVisible(true);
            jbc.setLocationRelativeTo(null);
        } else {
            buscarProducto();
        }
    }//GEN-LAST:event_btnBuscarProd1ActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        buscarProducto();
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        darBaja();
    }//GEN-LAST:event_btnBajaActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        int c = evt.getKeyCode();
        if (c == 10) {
            if (txtCodigo.getText().equals("")) {
                jbc = new Jbuscar("bajaproducto");
                jbc.setVisible(true);
                jbc.setLocationRelativeTo(null);
            } else {
                buscarProducto();
            }
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        int c = evt.getKeyCode();
        if (c == 10) {
            txtMotivo.requestFocus();
        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtMotivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyPressed
        int c = evt.getKeyCode();
        if (c == 10) {
            btnBaja.requestFocus();
        }
    }//GEN-LAST:event_txtMotivoKeyPressed

    private void btnBajaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBajaKeyPressed
        int c = evt.getKeyCode();
        if (c == 10) {
            darBaja();
        }
    }//GEN-LAST:event_btnBajaKeyPressed

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

    private void btnGenerarInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarInformeActionPerformed

        actualizar("WHERE fecha BETWEEN '" + inicio.gFechas.invertir(txtFechaI.getText()) + "' and '" + inicio.gFechas.invertir(txtFechaF.getText()) + "' ");
    }//GEN-LAST:event_btnGenerarInformeActionPerformed

    private void btnGenerarInformeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGenerarInformeKeyPressed
        char c = (char) evt.getKeyCode();
        if (c == 10) {
            actualizar("WHERE fecha BETWEEN '" + inicio.gFechas.invertir(txtFechaI.getText()) + "' and '" + inicio.gFechas.invertir(txtFechaF.getText()) + "' ");
        }
    }//GEN-LAST:event_btnGenerarInformeKeyPressed

    private void btnBaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaja1ActionPerformed
        eliminar();
    }//GEN-LAST:event_btnBaja1ActionPerformed

    private void btnBaja1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBaja1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBaja1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnBaja1;
    private javax.swing.JButton btnBuscarProd;
    private javax.swing.JButton btnBuscarProd1;
    private javax.swing.JButton btnGenerarInforme;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtCodigo;
    private javax.swing.JFormattedTextField txtFechaF;
    private javax.swing.JFormattedTextField txtFechaI;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtProducto;
    public static javax.swing.JTextField txtValorP;
    // End of variables declaration//GEN-END:variables

    private void darBaja() {
        Integer cant = 0;
        BajasProductos bajasobj = null;
        if (!txtMotivo.equals("")) {
            try {
                cant = Integer.parseInt(txtCantidad.getText());

                if (main.jProductos.cantidadProd(prodObj.getId()) >= Integer.parseInt(txtCantidad.getText())) {
                    bajasobj = new BajasProductos(null, inicio.fechaYMD, txtMotivo.getText(), cant, prodObj.getId(), inicio.userObj.getId());
                    if (main.bajasProductosDAO.insertar(bajasobj)) {
                        JOptionPane.showMessageDialog(null, "Producto: " + prodObj.getCodigo() + "(" + cant + ") por " + txtMotivo.getText(), "Baja de Productos", JOptionPane.INFORMATION_MESSAGE);
                        limpiar();
                        main.jProductos.actualizar();
                        actualizar("");
                        txtCodigo.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad no existente en Inventario", "Baja de Productos", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
            }

        }
    }

    public void eliminar() {
        Integer id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
        int reply = JOptionPane.showConfirmDialog(null, "Eliminar baja ID: " + id, "Eliminar Baja", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            BajasProductos bajasobj = new BajasProductos(id);
            main.bajasProductosDAO.eliminar(bajasobj);
            actualizar("");
        }

    }
}
