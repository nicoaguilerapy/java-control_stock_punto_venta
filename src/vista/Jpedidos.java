package vista;

import ColorTablas.ColorTabla1;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;

public class Jpedidos extends javax.swing.JFrame {

    Pedidos pedObj;

    ColorTabla1 colTab = new ColorTabla1();

    Integer monto = 0;
    String fecha, fechaT;
    String estadoA = "", detalles = "", pedido = "";

    DefaultTableModel datosPedidos;

    int filaSelecionada, id, estado = 1, x, y;

    public Jpedidos() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Pedidos -" + inicio.version);

        btnPendiente.setEnabled(false);
        btnCancelado.setEnabled(false);
        btnFinalizado.setEnabled(false);

        String fechaAc = inicio.gFechas.getFechaActual();
        txtFecha.setText("" + fechaAc);
        inicio.tools.Busqued(tablePedidos, datosPedidos, txtBuscador);
    }

    private void limpiar() {
        txtPedido.setText("");
        txtDetalles.setText("");
    }

    public void actualizar() {

        id = -1;
        main.pedidosDAO.actualizarLista("");
        datosPedidos = (DefaultTableModel) tablePedidos.getModel();
        datosPedidos.setRowCount(0);

        Object[] fila = new Object[datosPedidos.getColumnCount()];

        for (int i = 0; i < main.pedidosDAO.resultado.size(); i++) {

            String fechaT = main.pedidosDAO.resultado.get(i).getFecha();

            fecha = inicio.gFechas.invertir(fechaT);

            fila[0] = main.pedidosDAO.resultado.get(i).getId();
            fila[1] = fecha;
            fila[2] = main.pedidosDAO.resultado.get(i).getPedido();
            fila[3] = main.pedidosDAO.resultado.get(i).getEstado();
            datosPedidos.addRow(fila);

        }
        inicio.tools.centrar(tablePedidos, new int[]{0, 1});
        inicio.tools.derecha(tablePedidos, new int[]{3});
        inicio.tools.izquierda(tablePedidos, new int[]{2});
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnFinalizado = new javax.swing.JButton();
        txtBuscador = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePedidos = new ColorTablas.ColorTabla1();//javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtPedido = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDetalles = new javax.swing.JTextArea();
        btnCancelado = new javax.swing.JButton();
        btnPendiente = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setTitle(".:: Deudores - EL GRILLO ::.");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        btnFinalizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-tarea-finalizado.png"))); // NOI18N
        btnFinalizado.setText("Finalizado");
        btnFinalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizadoActionPerformed(evt);
            }
        });

        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador::::"));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        tablePedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Descripcion", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePedidos);
        if (tablePedidos.getColumnModel().getColumnCount() > 0) {
            tablePedidos.getColumnModel().getColumn(0).setResizable(false);
            tablePedidos.getColumnModel().getColumn(1).setResizable(false);
            tablePedidos.getColumnModel().getColumn(2).setResizable(false);
            tablePedidos.getColumnModel().getColumn(2).setPreferredWidth(300);
            tablePedidos.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        txtPedido.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pedido:");

        try {
            txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFecha.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Detalles:");

        txtDetalles.setColumns(20);
        txtDetalles.setRows(5);
        jScrollPane2.setViewportView(txtDetalles);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addGap(139, 139, 139)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-tarea-cancelado.png"))); // NOI18N
        btnCancelado.setText("Cancelado");
        btnCancelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanceladoActionPerformed(evt);
            }
        });

        btnPendiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-tarea-pendiente.png"))); // NOI18N
        btnPendiente.setText("Pendiente");
        btnPendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPendienteActionPerformed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-categorias-actualizar.png"))); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnModificar)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar)
                .addGap(33, 33, 33)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnFinalizado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPendiente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBuscador)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFinalizado)
                        .addComponent(btnCancelado)
                        .addComponent(btnPendiente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (estadoA.endsWith("Finalizado")) {
            JOptionPane.showMessageDialog(null, "No se puede modificar un Finalizado", "Cambiar Estado", JOptionPane.ERROR_MESSAGE);
        } else {
            modificar(estadoA);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (estadoA.equals("Cancelado")) {
            eliminar();
        } else {
            JOptionPane.showMessageDialog(null, "Solo se puede eliminar Pedidos en Estado: Cancelado", "Eliminar Pedido", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnFinalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizadoActionPerformed
        if (estadoA.endsWith("Cancelado")) {
            JOptionPane.showMessageDialog(null, "No se puede modificar un Cancelado", "Modificar Pedido", JOptionPane.ERROR_MESSAGE);
        } else {
            modificar("Finalizado");
        }


    }//GEN-LAST:event_btnFinalizadoActionPerformed

    private void tablePedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePedidosMouseClicked
        filaSelecionada = tablePedidos.getSelectedRow();

        filaSelecionada = (Integer) tablePedidos.getValueAt(filaSelecionada, 0);

        for (int i = 0; i < main.pedidosDAO.resultado.size(); i++) {

            if (filaSelecionada == main.pedidosDAO.resultado.get(i).getId()) {
                id = main.pedidosDAO.resultado.get(i).getId();
                txtPedido.setText(main.pedidosDAO.resultado.get(i).getPedido());
                pedido = main.pedidosDAO.resultado.get(i).getPedido();
                txtDetalles.setText(main.pedidosDAO.resultado.get(i).getDetalles());
                detalles = main.pedidosDAO.resultado.get(i).getDetalles();
                estadoA = main.pedidosDAO.resultado.get(i).getEstado();
                fechaT = main.pedidosDAO.resultado.get(i).getFecha();
                fecha = inicio.gFechas.invertir(fechaT);
                txtFecha.setText("" + fecha);
            }

        }

        if (estadoA.equals("Pendiente")) {
            btnPendiente.setEnabled(false);
            btnCancelado.setEnabled(true);
            btnFinalizado.setEnabled(true);
        } else {
            if (estadoA.equals("Finalizado")) {
                btnPendiente.setEnabled(true);
                btnCancelado.setEnabled(true);
                btnFinalizado.setEnabled(false);
            } else {
                btnPendiente.setEnabled(true);
                btnCancelado.setEnabled(false);
                btnFinalizado.setEnabled(true);
            }

        }


    }//GEN-LAST:event_tablePedidosMouseClicked

    private void btnCanceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanceladoActionPerformed
        if (estadoA.endsWith("Finalizado")) {
            JOptionPane.showMessageDialog(null, "No se puede modificar un Finalizado", "Modificar Pedido", JOptionPane.ERROR_MESSAGE);
        } else {
            modificar("Cancelado");
        }
    }//GEN-LAST:event_btnCanceladoActionPerformed

    private void btnPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPendienteActionPerformed
        if (estadoA.endsWith("Finalizado")) {
            JOptionPane.showMessageDialog(null, "No se puede modificar un Finalizado", "Modificar Pedido", JOptionPane.ERROR_MESSAGE);
        } else {
            modificar("Pendiente");
        }
    }//GEN-LAST:event_btnPendienteActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        actualizar();
    }//GEN-LAST:event_formWindowActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelado;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFinalizado;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPendiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablePedidos;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextArea txtDetalles;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JTextField txtPedido;
    // End of variables declaration//GEN-END:variables

    private void agregar() {

        if (txtPedido.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un pedido", "Agregar Pedido", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                pedido = txtPedido.getText();
                fechaT = txtFecha.getText();
                fecha = inicio.gFechas.invertir(fechaT);
                detalles = txtDetalles.getText();
                if (detalles.equals("")) {
                    detalles = "Vacio";
                }


                if (inicio.gFechas.validarFecha(fecha)) {
                    pedObj = new Pedidos(null, pedido, detalles, "Pendiente", fecha, inicio.userObj.getId());
                    main.pedidosDAO.insertar(pedObj);
                    actualizar();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Campos Vacios o Inválidos", "Agregar Pedido", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese un Monto " + e.getMessage(), "Agregar Acreedor", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void modificar(String valor) {

        if (txtPedido.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Ingrese un Pedido", "Modificar Pedido", JOptionPane.ERROR_MESSAGE);
        } else {
            if (valor.equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione un registro", "Modificar Pedido", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    pedido = txtPedido.getText();
                    fechaT = txtFecha.getText();
                    fecha = inicio.gFechas.invertir(fechaT);
                    detalles = txtDetalles.getText();
                    if (detalles.equals("")) {
                        detalles = "Vacio";
                    }

                    if (inicio.gFechas.validarFecha(fecha)) {
                        pedObj = new Pedidos(id, pedido, detalles, valor, fecha, inicio.userObj.getId());
                        main.pedidosDAO.modificar(pedObj);
                        actualizar();
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Campos Vacios o Inválidos", "Modificar Pedido", JOptionPane.ERROR_MESSAGE);
                    }

                    actualizar();
                    limpiar();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ingrese un Monto: " + e.getMessage(), "Modificar Pedido", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void eliminar() {
        pedObj = new Pedidos(id);
        main.pedidosDAO.eliminar(pedObj);
        actualizar();
        limpiar();
    }

}
