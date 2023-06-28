package vista;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Productos;
import modelo.Ventas;
import modelo.VentasDetalles;
import modelo.balance.Balance;
import modelo.persona.Clientes;
import modelo.persona.Personas;

public class JventasVer extends javax.swing.JFrame {

    Ventas venta;
    ArrayList<VentasDetalles> detVentaList;
    String data[][] = {};
    String columnNames1[] = {"ID", "CODIGO", "PRODUCTO", "CANTIDAD", "UNIT", "SUBTOTAL"};

    DefaultTableModel datos;

    public JventasVer(Ventas obj) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Editar Venta -" + inicio.version);

        Image img = new ImageIcon(getClass().getResource("/imagenes/icon-informe-imprimir.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnImprimir.setIcon(img2);

        img = new ImageIcon(getClass().getResource("/imagenes/icon-enviarcorreo.png")).getImage();
        img2 = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnEnviarCorreo.setIcon(img2);

        venta = obj;

        txtID.setText("" + venta.getId());

        Personas persona = (Personas) main.personasDAO.mapClientes.get(venta.getIdClientes());

        txtDocumento.setText("" + persona.getCINRUC());
        txtNombreApellido.setText(persona.getNombres() + " " + persona.getApellidos());
        txtMonto.setText("" + venta.getTotalACobrar());

        if (!obj.getFechaPago().equals("")) {
            txtFechaPago.setText(inicio.gFechas.invertir(obj.getFechaPago()));
        } else {
            txtFechaPago.setText(inicio.fechaDMY);
        }
        txtFechaCre.setText(inicio.gFechas.invertir(venta.getFecha()));

        detVentaList = new ArrayList();
        detVentaList = main.detVentasDAO.listar(" where idVentas='" + venta.getId() + "'");

        datos = new DefaultTableModel(data, columnNames1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(datos);

        datos.setRowCount(0);

        Object[] fila = new Object[datos.getColumnCount()];

        for (int i = 0; i < detVentaList.size(); i++) {

            Productos producto = (Productos) main.productosDAO.mapId.get(detVentaList.get(i).getIdProductos());

            fila[0] = detVentaList.get(i).getId();
            fila[1] = producto.getCodigo();
            fila[2] = producto.getNombre();
            fila[3] = detVentaList.get(i).getCantidad();
            fila[4] = detVentaList.get(i).getPrecio();
            fila[5] = detVentaList.get(i).getSubtotal();

            datos.addRow(fila);
        }

        inicio.tools.centrar(table, new int[]{0, 1, 3, 4, 5});
        inicio.tools.izquierda(table, new int[]{2});
        inicio.tools.decimalTable(table, 3);
        inicio.tools.decimalTable(table, 4);
        inicio.tools.decimalTable(table, 5);

        if (inicio.userObj.getRango().equals("Operador")) {
            btnGuardar.setEnabled(false);
            txtFechaPago.setEnabled(false);
        }

        if (venta.getEstado().equals("Pagado") || venta.getEstado().equals("Pagado Financiado")) {
            btnEnviarCorreo.setEnabled(true);
        } else {
            btnEnviarCorreo.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtNombreApellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        txtFechaCre = new javax.swing.JTextField();
        txtFechaPago = new javax.swing.JFormattedTextField();
        txtMonto = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnEnviarCorreo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Nombre y Apellido:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Fecha de Creación:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Monto:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Fecha de Pago:");

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtNombreApellido.setEditable(false);
        txtNombreApellido.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Documento:");

        txtDocumento.setEditable(false);
        txtDocumento.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtFechaCre.setEditable(false);
        txtFechaCre.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        try {
            txtFechaPago.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFechaPago.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtMonto.setEditable(false);
        txtMonto.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-cerrarcajas-cerrar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnImprimir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-cerrarcajas-cerrar.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnEnviarCorreo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEnviarCorreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-cerrarcajas-cerrar.png"))); // NOI18N
        btnEnviarCorreo.setText("Enviar Correo");
        btnEnviarCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarCorreoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(279, 279, 279))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNombreApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFechaCre, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDocumento))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaPago, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(txtMonto))
                        .addContainerGap(72, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviarCorreo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFechaCre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnImprimir)
                    .addComponent(btnEnviarCorreo))
                .addGap(13, 13, 13))
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if (venta.getEstado().equals("Pagado") || venta.getEstado().equals("Pagado Financiado")) {
            inicio.gReportes.boleta(venta, false);
        } else {
            JOptionPane.showMessageDialog(null, "No se puede imprimir Ventas no Pagadas", "Imprimir Boleta", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnEnviarCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarCorreoActionPerformed
        if (venta.getEstado().equals("Pagado") || venta.getEstado().equals("Pagado Financiado")) {
            inicio.gReportes.boleta(venta, true);
        } else {
            JOptionPane.showMessageDialog(null, "No se puede imprimir Ventas no Pagadas", "Enviar correo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEnviarCorreoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarCorreo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtFechaCre;
    private javax.swing.JFormattedTextField txtFechaPago;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNombreApellido;
    // End of variables declaration//GEN-END:variables

    private void guardar() {
        String fecha = inicio.gFechas.invertir(txtFechaPago.getText());
        if (inicio.gFechas.validarFecha(fecha)) {

            Balance balObj = (Balance) main.balanceDAO.listar(" where concepto like '%#(" + venta.getId() + ")Venta%'  ", "caja").get(0);
            balObj.setFecha(inicio.gFechas.invertir(txtFechaPago.getText()));
            main.balanceDAO.modificar(balObj);

            balObj = (Balance) main.balanceDAO.listar(" where concepto like '%#(" + venta.getId() + ")Venta%'  ", "ivadebito").get(0);
            balObj.setFecha(inicio.gFechas.invertir(txtFechaPago.getText()));
            main.balanceDAO.modificar(balObj);

            venta.setFechaPago(inicio.gFechas.invertir(txtFechaPago.getText()));
            main.ventasDAO.modificar(venta);

            JOptionPane.showMessageDialog(null, "Modificado con Éxito", "Modificar Venta", JOptionPane.INFORMATION_MESSAGE);
            main.ventasDAO.actualizarLista("");

            if (inicio.gAyC.getApertura(fecha) == null) {
                inicio.gAyC.actualizarAyC();
                main.balanceDAO.actualizarCierres();
            }

            this.dispose();

        } else {
            JOptionPane.showMessageDialog(null, "Seleccionar una fecha válida", "Modificar Venta", JOptionPane.ERROR_MESSAGE);
        }
    }
}
