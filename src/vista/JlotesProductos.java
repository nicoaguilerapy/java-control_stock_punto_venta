package vista;

import java.awt.Toolkit;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.LotesProductos;
import modelo.Usuarios;
import modelo.balance.Balance;

public class JlotesProductos extends javax.swing.JFrame {

    LotesProductos lotesProductos;
    private DefaultTableModel datosLotesProductos;
    private TableRowSorter<TableModel> sorter;

    Integer id = -1, filaSeleccionada;
    String fecha = "", estado = "";
    public JlotesDetalles jd;

    public JlotesProductos() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Lotes de Productos -" + inicio.version);

        btnVer.setEnabled(false);
        btnModificar.setEnabled(false);
        btnImprimir.setEnabled(false);

        datosLotesProductos = (DefaultTableModel) table.getModel();
        inicio.tools.Busqued(table, datosLotesProductos, txtBuscador1);

        sorter = new TableRowSorter<>(datosLotesProductos);
        table.setRowSorter(sorter);
        table.getSelectionModel().setSelectionInterval(0, 0);

    }

    public void actualizar() {

        id = -1;
        main.lotesProductosDAO.actualizarLista("  where estado <> 'Oculto'  ");
        datosLotesProductos.setRowCount(0);

        Object[] fila = new Object[datosLotesProductos.getColumnCount()];

        for (int i = 0; i < main.lotesProductosDAO.resultadoLotesProductos.size(); i++) {
            Usuarios usuario = (Usuarios) main.usuariosDAO.mapUsuarios.get(main.lotesProductosDAO.resultadoLotesProductos.get(i).getIdUsuarios());

            fila[0] = main.lotesProductosDAO.resultadoLotesProductos.get(i).getId();
            fila[1] = main.lotesProductosDAO.resultadoLotesProductos.get(i).getFecha();
            fila[2] = main.lotesProductosDAO.resultadoLotesProductos.get(i).getFacturanum();
            fila[3] = main.lotesProductosDAO.resultadoLotesProductos.get(i).getEstado();
            fila[4] = main.lotesProductosDAO.resultadoLotesProductos.get(i).getGastototal();
            fila[5] = main.lotesProductosDAO.resultadoLotesProductos.get(i).getIvacredito();
            fila[6] = usuario.getUsuario();
            datosLotesProductos.addRow(fila);

        }

        inicio.tools.centrar(table, new int[]{0, 1, 2, 3, 6});
        inicio.tools.derecha(table, new int[]{4, 5});
        inicio.tools.decimalTable(table, 4);
        inicio.tools.decimalTable(table, 5);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBuscador = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtBuscador1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnNueva = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador::::"));

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        txtBuscador1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador::::"));
        txtBuscador1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscador1KeyPressed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fecha", "Factura ", "Estado", "Gasto Total", "IVA Credito", "Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        btnNueva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-categorias-agregar.png"))); // NOI18N
        btnNueva.setText("Nueva Entrada");
        btnNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaActionPerformed(evt);
            }
        });

        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-ver.png"))); // NOI18N
        btnVer.setText("Ver Mercaderias");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-categorias-editar.png"))); // NOI18N
        btnModificar.setText("Modificar Entrada");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-categorias-eliminar.png"))); // NOI18N
        btnAnular.setText("Anular Entrada");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-informe-imprimir.png"))); // NOI18N
        btnImprimir.setText("Imprimir Compra");
        btnImprimir.setMaximumSize(new java.awt.Dimension(143, 39));
        btnImprimir.setMinimumSize(new java.awt.Dimension(143, 39));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
                    .addComponent(txtBuscador1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNueva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVer)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txtBuscador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNueva)
                    .addComponent(btnVer)
                    .addComponent(btnModificar)
                    .addComponent(btnAnular)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
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

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        filaSeleccionada = table.getSelectedRow();

        filaSeleccionada = (Integer) table.getValueAt(filaSeleccionada, 0);

        for (int i = 0; i < main.lotesProductosDAO.resultadoLotesProductos.size(); i++) {

            if (main.lotesProductosDAO.resultadoLotesProductos.get(i).getId() == filaSeleccionada) {

                lotesProductos = main.lotesProductosDAO.resultadoLotesProductos.get(i);
                System.out.println(main.lotesProductosDAO.resultadoLotesProductos.get(i).toString());

                id = lotesProductos.getId();
                estado = lotesProductos.getEstado();

                if (estado.equals("Pendiente")) {
                    btnModificar.setEnabled(true);
                    btnVer.setEnabled(false);
                    btnAnular.setEnabled(true);
                    btnImprimir.setEnabled(false);
                } else {
                    if (estado.equals("Ingresado")) {
                        btnModificar.setEnabled(false);
                        btnVer.setEnabled(true);
                        btnAnular.setEnabled(true);
                        btnImprimir.setEnabled(true);
                    } else {
                        if (estado.equals("Anulado")) {
                            btnModificar.setEnabled(false);
                            btnVer.setEnabled(true);
                            btnAnular.setEnabled(false);
                            btnImprimir.setEnabled(false);
                        }
                    }
                }

            }
        }
    }//GEN-LAST:event_tableMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificarLote();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaActionPerformed
        crearLote();
    }//GEN-LAST:event_btnNuevaActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        verLote();
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        try {
            int inforTipo = JOptionPane.showOptionDialog(null, "Estas Seguro que decia anular ID: " + lotesProductos.getId() + ", Factura Nº: " + lotesProductos.getFacturanum(),
                    "Anular Entrada de Mercaderias", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                    new Object[]{"Si, Anular", "No, Cancelar"}, "salir");
            try {
                if (inforTipo == 0) {
                    anularLote();
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Elegir elemento a Anular", "Entrada de Mercadería", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        actualizar();
    }//GEN-LAST:event_formWindowActivated

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        imprimir();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtBuscador1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscador1KeyPressed
        lotesProductos = null;
        id = -1;
    }//GEN-LAST:event_txtBuscador1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNueva;
    private javax.swing.JButton btnVer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtBuscador1;
    // End of variables declaration//GEN-END:variables

    public void crearLote() {
        lotesProductos = new LotesProductos(null, inicio.fechaYMD, "", "Oculto", 0, 0, 1, inicio.userObj.getId());
        main.lotesProductosDAO.insertar(lotesProductos);
        main.lotesProductosDAO.actualizarLista("");
        lotesProductos = main.lotesProductosDAO.getUltimoLote();

        jd = new JlotesDetalles(lotesProductos, true);
        jd.setVisible(true);
        jd.setLocationRelativeTo(null);
    }

    public void anularLote() {
        lotesProductos.setEstado("Anulado");
        Balance balObj;
        try {
            balObj = (Balance) main.balanceDAO.listar(" where concepto='#(" + lotesProductos.getId() + ")Compra de Mercaderias Nº: " + lotesProductos.getFacturanum() + "'", "ivadebito").get(0);
            main.balanceDAO.eliminar(balObj, "ivadebito");
        } catch (Exception e) {
        }

        try {
            balObj = (Balance) main.balanceDAO.listar(" where concepto='#(" + lotesProductos.getId() + ")Compra de Mercaderias Nº: " + lotesProductos.getFacturanum() + "'", "caja").get(0);
            main.balanceDAO.eliminar(balObj, "caja");
        } catch (Exception e) {
        }

        if (main.lotesProductosDAO.modificar(lotesProductos)) {
            main.lotesProductosDAO.actualizarLista("");
            actualizar();
        }
    }

    public void modificarLote() {
        jd = new JlotesDetalles(lotesProductos, true);
        jd.setVisible(true);
        jd.setLocationRelativeTo(null);
    }

    public void verLote() {
        jd = new JlotesDetalles(lotesProductos, false);
        jd.setVisible(true);
        jd.setLocationRelativeTo(null);
    }

    public void imprimir() {
        if (lotesProductos != null) {
            inicio.gReportes.entradaMercaderias(lotesProductos);
        }
    }
}
