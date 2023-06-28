package vista;

import herramientas.NumberToText;
import modelo.balance.Balance;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Productos;
import modelo.Ventas;
import modelo.VentasDetalles;
import modelo.persona.Personas;

public class JcobranzasPago extends javax.swing.JFrame {

    Ventas venta;

    Balance balObj, balObj2, ivaObj;
    ArrayList<VentasDetalles> detVentaList;
    HashMap<String, Object> map;
    DefaultTableModel datos;
    String data[][] = {};
    String columnNames1[] = {"ID", "CODIGO", "PRODUCTO", "CANTIDAD", "UNIT", "SUBTOTAL"};

    Integer ivaTotal = 0, montosiniva = 0, descuento = 0;

    public JcobranzasPago(Ventas obj) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Métodos de Pago -" + inicio.version);

        venta = obj;
        System.out.println(venta.toString());

        txtFecha.setText("" + inicio.gFechas.getFechaActual());
        txtPrecio.setText(inicio.format.format(venta.getTotal()));
        txtDescuento.setText(venta.getDescuento() + "");

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

        Personas persona = (Personas) main.personasDAO.mapClientes.get(venta.getIdClientes());

        txtDocumento.setText("" + persona.getCINRUC());
        txtCliente.setText(persona.getNombres() + " " + persona.getApellidos());

        inicio.tools.centrar(table, new int[]{0, 1, 3, 4, 5});
        inicio.tools.izquierda(table, new int[]{2});
        inicio.tools.decimalTable(table, 3);
        inicio.tools.decimalTable(table, 4);
        inicio.tools.decimalTable(table, 5);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbMonto = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtDescuento = new javax.swing.JTextField();
        lbMonto1 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtDocumento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione una Caja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-ventas-banco.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-ventas-cajero.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-ventas-tarjeta.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Caja");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Tarjeta");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Banco");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Propiedades de la Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Seleccione un Método de Pago:");

        lbMonto.setBackground(new java.awt.Color(255, 255, 255));
        lbMonto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbMonto.setForeground(new java.awt.Color(255, 255, 255));
        lbMonto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbMonto.setText("Monto:");

        txtPrecio.setEditable(false);
        txtPrecio.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtDescuento.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtDescuento.setText("0");
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });

        lbMonto1.setBackground(new java.awt.Color(255, 255, 255));
        lbMonto1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbMonto1.setForeground(new java.awt.Color(255, 255, 255));
        lbMonto1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbMonto1.setText("Descuento:");

        try {
            txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFecha.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Fecha:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(lbMonto1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMonto1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles de la Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

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

        txtDocumento.setEditable(false);
        txtDocumento.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Documento:");

        txtCliente.setEditable(false);
        txtCliente.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cliente:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed

    }//GEN-LAST:event_jPanel1KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        int c = evt.getKeyCode();
        if (c == 67) {
            pagar("caja");
        } else {
            if (c == 84) {
                pagar("tarjeta");
            } else {
                if (c == 66) {
                    pagar("banco");
                }
            }
        }
    }//GEN-LAST:event_formKeyPressed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        try {
            descuento = Integer.parseInt(txtDescuento.getText());
        } catch (Exception e) {
            descuento = 0;
        }

        venta.setDescuento(descuento);
        if (venta.getTotal() - descuento >= 0) {
            pagar("caja");
        } else {
            JOptionPane.showMessageDialog(null, "El monto a cobrar no puede ser negativo", "Descuento", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        /*try {
            descuento = Integer.parseInt(txtDescuento.getText());
        } catch (Exception e) {
            descuento = 0;
        }

        venta.setDescuento(descuento);
        if (venta.getTotal() - descuento >= 0) {
            pagar("tarjeta");
        } else {
            JOptionPane.showMessageDialog(null, "El monto a cobrar no puede ser negativo", "Descuento", JOptionPane.ERROR_MESSAGE);
        }*/

    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        /* try {
            descuento = Integer.parseInt(txtDescuento.getText());
        } catch (Exception e) {
            descuento = 0;
        }

        venta.setDescuento(descuento);
        if (venta.getTotal() - descuento >= 0) {
            pagar("banco");
        } else {
            JOptionPane.showMessageDialog(null, "El monto a cobrar no puede ser negativo", "Descuento", JOptionPane.ERROR_MESSAGE);
        }*/
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtDescuentoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMonto;
    private javax.swing.JLabel lbMonto1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables

    private void pagar(String tipo) {
        String fechaTmp = inicio.gFechas.invertir(txtFecha.getText());

        venta.setFechaPago(inicio.gFechas.invertir(txtFecha.getText()));

        String texto = "(" + venta.getId() + ")";
        String caja = "#" + texto + "Venta en Caja Factura Nº: ";
        String tarjeta = "#" + texto + "Venta en Tarjeta Operación Nº: ";
        String bancario = "#" + texto + "Venta en Banco Operación Nº: ";

        if (tipo.equals("caja")) {
            caja = caja + venta.getId();
            balObj = new Balance(null, caja, venta.getTotalACobrar(), 0, venta.getFechaPago(), inicio.userObj.getId(), tipo);
            ivaObj = new Balance(null, caja, venta.getIvaACobrar(), 0, venta.getFechaPago(), inicio.userObj.getId(), "ivadebito");
        } else {
            if (tipo.equals("tarjeta")) {
                tarjeta = tarjeta + venta.getId();
                balObj = new Balance(null, tarjeta, venta.getTotalACobrar(), 0, venta.getFechaPago(), inicio.userObj.getId(), tipo);
                ivaObj = new Balance(null, tarjeta, venta.getIvaACobrar(), 0, venta.getFechaPago(), inicio.userObj.getId(), "ivadebito");

            } else {
                if (tipo.equals("banco")) {
                    bancario = bancario + venta.getId();
                    balObj = new Balance(null, bancario, venta.getTotalACobrar(), 0, venta.getFechaPago(), inicio.userObj.getId(), tipo);
                    ivaObj = new Balance(null, bancario, venta.getIvaACobrar(), 0, venta.getFechaPago(), inicio.userObj.getId(), "ivadebito");
                }
            }
        }

        main.balanceDAO.insertar(ivaObj);
        main.balanceDAO.insertar(balObj);

        System.out.println(balObj.toString());

        venta.setFacturanum("");
        venta.setFormaPago(tipo);
        venta.setEstado("Pagado");

        venta.setIdCobrador(inicio.userObj.getId());
        main.ventasDAO.modificar(venta);

        //System.out.println("Monto Sin Iva:" + montosiniva);
        System.out.println("vista.JmetodosPago.pagar() " + venta.toString());
        System.out.println("Monto Con Iva:" + venta.getTotal());

        boolean paraEnviar = false;

        if (main.datosDAO.map.get("email_automatic_send").equals("Si")) {
            paraEnviar = true;
        } else {
            if (main.datosDAO.map.get("email_question_send").equals("Si")) {
                int inforTipo = JOptionPane.showOptionDialog(null, "¿Desea mandar por correo esta venta?",
                        "Enviar por correo", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                        new Object[]{"SI", "NO"}, "salir");

                try {
                    if (inforTipo == 0) {
                        paraEnviar = true;
                    }
                } catch (Exception e) {
                }
            }
        }

        if (inicio.gAyC.getApertura(fechaTmp) == null) {
            inicio.gAyC.actualizarAyC();
            main.balanceDAO.actualizarCierres();
        } else {
            System.out.println("vista.JcobranzasPago.pagar() hay apertura en ese día");
        }

        inicio.gReportes.boleta(venta, paraEnviar);
        main.jCobranzas.actualizar("Todos", "");
        main.jCobranzas.limpiarFechas();

        this.dispose();

    }

}
