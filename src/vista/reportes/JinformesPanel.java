package vista.reportes;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Productos;
import modelo.Ventas;
import modelo.VentasDetalles;
import modelo.balance.Balance;
import modelo.balance.BalanceReport;
import vista.Jbuscar;
import vista.inicio;
import vista.main;

public class JinformesPanel extends javax.swing.JFrame {

    ArrayList<Balance> balanceArray;
    ArrayList<Productos> productosArray;
    ArrayList<Ventas> ventasArray;
    private DefaultTableModel datosTableResumen, datosTableMovimientos;
    public JinformesClientes jInformesClientes;

    public JinformesPanel() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Panel de Informes -" + inicio.version);

        panelprincipal();
        panelProveedores.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnClientesHistorial = new javax.swing.JButton();
        btnClientesQuienes = new javax.swing.JButton();
        btnClientesQuienes1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnProductosAyB = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtValorI = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIngresoT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCantidadV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGanancia = new javax.swing.JTextField();
        txtGastoT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtValorP = new javax.swing.JTextField();
        panelOtros = new javax.swing.JPanel();
        btnClientesHistorial1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableResumen = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMovimientos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        panelProveedores = new javax.swing.JPanel();
        btnClientesHistorial3 = new javax.swing.JButton();
        btnClientesQuienes3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        btnClientesHistorial.setText("Historial de ventas");
        btnClientesHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesHistorialActionPerformed(evt);
            }
        });

        btnClientesQuienes.setText("Quienes compran más");
        btnClientesQuienes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesQuienesActionPerformed(evt);
            }
        });

        btnClientesQuienes1.setText("Extracto de Clientes");
        btnClientesQuienes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesQuienes1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClientesHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClientesQuienes, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClientesQuienes1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClientesHistorial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClientesQuienes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClientesQuienes1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        btnProductosAyB.setText("Altas y Bajas");
        btnProductosAyB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosAyBActionPerformed(evt);
            }
        });

        jButton4.setText("Más Vendidos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Quienes Compradores");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProductosAyB, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnProductosAyB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtValorI.setEditable(false);
        txtValorI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtValorI.setText("0");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Valor en Inventario:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingreso Total del Mes:");

        txtIngresoT.setEditable(false);
        txtIngresoT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cantidad de Ventas del Mes:");

        txtCantidadV.setEditable(false);
        txtCantidadV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Gasto Total del Mes:");

        txtGanancia.setEditable(false);
        txtGanancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtGastoT.setEditable(false);
        txtGastoT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ganancia del Mes:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Perdidas en Bajas del mes:");

        txtValorP.setEditable(false);
        txtValorP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtValorP.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtIngresoT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCantidadV, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGastoT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorP, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValorI, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIngresoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtCantidadV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGastoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(txtValorI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtValorP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelOtros.setBackground(new java.awt.Color(153, 153, 153));
        panelOtros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Otros Informes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        btnClientesHistorial1.setText("Acreedores y Creditos");
        btnClientesHistorial1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesHistorial1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOtrosLayout = new javax.swing.GroupLayout(panelOtros);
        panelOtros.setLayout(panelOtrosLayout);
        panelOtrosLayout.setHorizontalGroup(
            panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOtrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClientesHistorial1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelOtrosLayout.setVerticalGroup(
            panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOtrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClientesHistorial1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-tarea-pendiente.png"))); // NOI18N
        jButton1.setText("Informes Cajas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resumen del Mes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        tableResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DETALLE", "INGRESO", "EGRESO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableResumen);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movimientos del Mes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        tableMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
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
        jScrollPane2.setViewportView(tableMovimientos);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-informe-comprobar.png"))); // NOI18N
        jButton2.setText("Aperturas y Cierres");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        panelProveedores.setBackground(new java.awt.Color(153, 153, 153));
        panelProveedores.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        btnClientesHistorial3.setText("Historial de ventas");
        btnClientesHistorial3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesHistorial3ActionPerformed(evt);
            }
        });

        btnClientesQuienes3.setText("Quienes compran más");
        btnClientesQuienes3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesQuienes3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProveedoresLayout = new javax.swing.GroupLayout(panelProveedores);
        panelProveedores.setLayout(panelProveedoresLayout);
        panelProveedoresLayout.setHorizontalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClientesHistorial3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClientesQuienes3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelProveedoresLayout.setVerticalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClientesHistorial3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClientesQuienes3)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelOtros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(7, 7, 7)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelOtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 43, Short.MAX_VALUE)))
                .addContainerGap())
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesQuienesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesQuienesActionPerformed
        inicio.gReportes.clientesCompradores();
    }//GEN-LAST:event_btnClientesQuienesActionPerformed

    private void btnClientesHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesHistorialActionPerformed
        Jbuscar jBancous = new Jbuscar("historialCliente");
        jBancous.setVisible(true);
        jBancous.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnClientesHistorialActionPerformed

    private void btnProductosAyBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosAyBActionPerformed
        Jbuscar jBuscar = new Jbuscar("informeProductoAB");
        jBuscar.setVisible(true);
        jBuscar.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnProductosAyBActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        inicio.gReportes.productosComprados();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Jbuscar jBuscar = new Jbuscar("quienesCompran");
        jBuscar.setVisible(true);
        jBuscar.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnClientesHistorial1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesHistorial1ActionPerformed
        JIacreedorescreditos jiacre = new JIacreedorescreditos();
        jiacre.setVisible(true);
        jiacre.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnClientesHistorial1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            JinformesCajas jinf;
            int inforTipo = JOptionPane.showOptionDialog(null, "Informes",
                    "Elija de donde quiere el informe", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                    new Object[]{"Caja", "Banco", "Tarjeta"}, "salir");

            if (inforTipo == 0) {
                jinf = new JinformesCajas("caja");
                jinf.setVisible(true);
                jinf.setLocationRelativeTo(null);
            } else {
                if (inforTipo == 1) {
                    jinf = new JinformesCajas("banco");
                    jinf.setVisible(true);
                    jinf.setLocationRelativeTo(null);
                } else {
                    if (inforTipo == 2) {
                        jinf = new JinformesCajas("tarjeta");
                        jinf.setVisible(true);
                        jinf.setLocationRelativeTo(null);
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JinformesAyC jiac = new JinformesAyC();
        jiac.setVisible(true);
        jiac.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnClientesHistorial3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesHistorial3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesHistorial3ActionPerformed

    private void btnClientesQuienes3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesQuienes3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesQuienes3ActionPerformed

    private void btnClientesQuienes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesQuienes1ActionPerformed
        jInformesClientes = new JinformesClientes();
        jInformesClientes.setVisible(true);
        jInformesClientes.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnClientesQuienes1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientesHistorial;
    private javax.swing.JButton btnClientesHistorial1;
    private javax.swing.JButton btnClientesHistorial3;
    private javax.swing.JButton btnClientesQuienes;
    private javax.swing.JButton btnClientesQuienes1;
    private javax.swing.JButton btnClientesQuienes3;
    private javax.swing.JButton btnProductosAyB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelOtros;
    private javax.swing.JPanel panelProveedores;
    private javax.swing.JTable tableMovimientos;
    private javax.swing.JTable tableResumen;
    private javax.swing.JTextField txtCantidadV;
    private javax.swing.JTextField txtGanancia;
    private javax.swing.JTextField txtGastoT;
    private javax.swing.JTextField txtIngresoT;
    public static javax.swing.JTextField txtValorI;
    public static javax.swing.JTextField txtValorP;
    // End of variables declaration//GEN-END:variables

    public void panelprincipal() {
        datosTableMovimientos = (DefaultTableModel) tableMovimientos.getModel();
        datosTableMovimientos.setRowCount(0);

        //Datos del mes
        balanceArray = new ArrayList();
        String condicion = " WHERE fecha BETWEEN '" + inicio.gFechas.getAnoActual() + "/" + inicio.gFechas.getMesctual() + "/01' AND '" + inicio.gFechas.getAnoActual() + "/" + inicio.gFechas.getMesctual() + "/31' and idUsuarios='" + inicio.userObj.getId() + "'";
        balanceArray = main.balanceDAO.listar(condicion, "caja");
        Integer ingreso = 0, egreso = 0, ingresoVentas = 0, egresoComprasMerca = 0, egresoInsumos = 0, egresoGastosO = 0, egresoBienesA = 0, egresoSinE = 0;

        for (int i = 0; i < balanceArray.size(); i++) {
            if (!balanceArray.get(i).getConcepto().contains("# Cierre") && !balanceArray.get(i).getConcepto().contains("# Apertura")
                    && !balanceArray.get(i).getConcepto().contains("*Cierre") && !balanceArray.get(i).getConcepto().contains("*Apertura")
                    && !balanceArray.get(i).getConcepto().contains("&Sin")) {

                ingreso = ingreso + balanceArray.get(i).getIngreso();
                egreso = egreso + balanceArray.get(i).getEgreso();

                ingresarTablarMovimientos(inicio.gFechas.invertir(balanceArray.get(i).getFecha()), balanceArray.get(i).getConcepto(), balanceArray.get(i).getIngreso(), balanceArray.get(i).getEgreso());

                if (balanceArray.get(i).getConcepto().contains("Venta en Caja Factura")) {
                    ingresoVentas = ingresoVentas + balanceArray.get(i).getIngreso();
                } else {
                    if (balanceArray.get(i).getConcepto().contains("Compra de Mercaderias")) {
                        egresoComprasMerca = egresoComprasMerca + balanceArray.get(i).getEgreso();
                    } else {
                        if (balanceArray.get(i).getConcepto().contains("Gasto de Sin Especificar")) {
                            egresoSinE = egresoSinE + balanceArray.get(i).getEgreso();
                        } else {
                            if (balanceArray.get(i).getConcepto().contains("Gasto de Insumo")) {
                                egresoInsumos = egresoInsumos + balanceArray.get(i).getEgreso();
                            } else {
                                if (balanceArray.get(i).getConcepto().contains("Gasto de Gasto Operativo")) {
                                    egresoGastosO = egresoGastosO + balanceArray.get(i).getEgreso();
                                } else {
                                    if (balanceArray.get(i).getConcepto().contains("Gasto de Bienes Activos")) {
                                        egresoBienesA = egresoBienesA + balanceArray.get(i).getEgreso();
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        inicio.tools.decimalTable(tableMovimientos, 3);
        inicio.tools.decimalTable(tableMovimientos, 2);

        datosTableResumen = (DefaultTableModel) tableResumen.getModel();
        datosTableResumen.setRowCount(0);

        ingresarTabla("Ventas del Mes", ingresoVentas, 0);
        ingresarTabla("Compra de Mercaderías", 0, egresoComprasMerca);
        ingresarTabla("Gasto en Insumos", 0, egresoInsumos);
        ingresarTabla("Gastos Operativos", 0, egresoGastosO);
        ingresarTabla("Gasto en Bienes Activos", 0, egresoBienesA);
        ingresarTabla("Gasto Sin Especificar", 0, egresoSinE);

        txtIngresoT.setText(inicio.format.format(ingreso));
        txtGastoT.setText(inicio.format.format(egreso));
        txtGanancia.setText(inicio.format.format(ingreso - egreso));

        inicio.tools.decimalTable(tableResumen, 1);
        inicio.tools.decimalTable(tableResumen, 2);

        ventasArray = new ArrayList();
        condicion = " WHERE fechapago BETWEEN '" + inicio.gFechas.getAnoActual() + "/" + inicio.gFechas.getMesctual() + "/01' AND '" + inicio.gFechas.getAnoActual() + "/" + inicio.gFechas.getMesctual() + "/31' and estado='Pagado' and idCobrador='" + inicio.userObj.getId() + "'";
        ventasArray = main.ventasDAO.listar(condicion);
        ArrayList<VentasDetalles> detVenArray = new ArrayList();

        for (int i = 0; i < ventasArray.size(); i++) {

            for (int j = 0; j < main.detVentasDAO.resultado.size(); j++) {
                detVenArray.clear();
                if (ventasArray.get(i).getId() == main.detVentasDAO.resultado.get(j).getIdVentas()) {
                    detVenArray.add(main.detVentasDAO.resultado.get(j));
                }
            }
        }
         
        //Datos de ventas
        condicion = " WHERE fecha BETWEEN '" + inicio.gFechas.getAnoActual() + "/" + inicio.gFechas.getMesctual() + "/01' AND '" + inicio.gFechas.getAnoActual() + "/" + inicio.gFechas.getMesctual() + "/31' and idCobrador='" + inicio.userObj.getId() + "'";

        Integer cantidadVentas = main.ventasDAO.ventasMes(condicion);
        txtCantidadV.setText(inicio.format.format(cantidadVentas));

    }

    public void ingresarTabla(String detalle, Integer ingreso, Integer egreso) {
        Object[] fila = new Object[datosTableResumen.getColumnCount()];
        fila[0] = detalle;
        fila[1] = ingreso;
        fila[2] = egreso;

        datosTableResumen.addRow(fila);
    }

    public void ingresarTablarMovimientos(String fecha, String concepto, Integer ingreso, Integer egreso) {
        Object[] fila = new Object[datosTableMovimientos.getColumnCount()];
        fila[0] = fecha;
        fila[1] = concepto;
        fila[2] = ingreso;
        fila[3] = egreso;

        datosTableMovimientos.addRow(fila);
    }
}
