package vista;

import java.awt.Toolkit;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Empleados;

public class Jempleados extends javax.swing.JFrame {

    private Empleados empObj;

    private Integer id = -1, idCuenta = null;
    private String CINRUC, nombres, apellidos, direccion, fecha, celtel, correo, ciudad, fechaT;

    private DefaultTableModel datosClientes;

    int filaSelecionada, estado = 1;

    public Jempleados() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Empleados -" + inicio.version);

        String fechaAc = inicio.gFechas.getFechaActual();
        txtFecha.setText("" + fechaAc);

        datosClientes = (DefaultTableModel) tableClientes.getModel();
        inicio.tools.Busqued(tableClientes, datosClientes, txtBuscador);
    }

    public int comprobarCampos() {
        int sw = 1;
        String message = "", aux;

        String num = txtCelTel.getText();
        char[] ch = new char[num.length() - 2];
        int j = 0;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == 40 || num.charAt(i) == 41) {

            } else {
                ch[j] = num.charAt(i);
                j++;
            }

        }

        num = new String(ch);
        System.out.println(num);

        if (txtCINRUC.getText().equals("")) {
            message = "CIN,  ";
            sw = 0;
        }
        if (txtNombres.getText().equals("")) {
            aux = message;
            message = aux + "Nombres,  ";
            sw = 0;
        }
        if (txtApellidos.getText().equals("")) {
            aux = message;
            message = aux + "Apellidos,  ";
            sw = 0;
        }
        if (num.equals("          ")) {
            aux = message;
            message = aux + "CelTel,  ";
            sw = 0;
        }
        if (txtCiudad.getText().equals("")) {
            aux = message;
            message = aux + "Ciudad,  ";
            sw = 0;
        }

        if (sw == 0) {
            JOptionPane.showMessageDialog(null, message + "*Son campos Obigatorios*", "Faltan campos", JOptionPane.ERROR_MESSAGE);
        }
        return sw;
    }

    public void actualizar() {
        id = -1;
        main.empleadosDAO.actualizarEmpleados("");
        datosClientes = (DefaultTableModel) tableClientes.getModel();
        datosClientes.setRowCount(0);

        Object[] fila = new Object[datosClientes.getColumnCount()];

        for (int i = 0; i < main.empleadosDAO.resultadoEmpleados.size(); i++) {

            fila[0] = main.empleadosDAO.resultadoEmpleados.get(i).getId();
            fila[1] = main.empleadosDAO.resultadoEmpleados.get(i).getCINRUC();
            fila[2] = main.empleadosDAO.resultadoEmpleados.get(i).getApellidos();
            fila[3] = main.empleadosDAO.resultadoEmpleados.get(i).getNombres();
            fila[4] = main.empleadosDAO.resultadoEmpleados.get(i).getCeltel();
            fila[5] = main.empleadosDAO.resultadoEmpleados.get(i).getCorreo();
            datosClientes.addRow(fila);
        }

        inicio.tools.centrar(tableClientes, new int[]{4, 5});
        inicio.tools.izquierda(tableClientes, new int[]{1, 2, 3});

    }

    public void limpiar() {
        txtCINRUC.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDireccion.setText("");
        txtCiudad.setText("");
        txtCorreo.setText("");
        txtCelTel.setText("");
        String fechaAc = inicio.gFechas.getFechaActual();
        txtFecha.setText("" + fechaAc);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtCINRUC = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCelTel = new javax.swing.JFormattedTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtBuscador = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAgregar.setFont(new java.awt.Font("Kalinga", 0, 14)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-cliente-agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Kalinga", 0, 14)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-cliente-modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Kalinga", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-cliente-eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-eliminar.png"))); // NOI18N
        btnLimpiar.setToolTipText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar)
                        .addComponent(btnModificar)
                        .addComponent(btnEliminar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtCINRUC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("CIN/RUC*:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Nombres*:");

        txtNombres.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Apellidos*:");

        txtApellidos.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtDireccion.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Dirección:");

        try {
            txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFecha.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Fecha Nac*:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Cel/Tel*:");

        try {
            txtCelTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(####)######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelTel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtCorreo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Correo E.:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Ciudad*:");

        txtCiudad.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCINRUC, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addGap(334, 334, 334)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83))
                            .addComponent(txtDireccion)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCelTel)
                                    .addComponent(txtNombres))
                                .addGap(74, 74, 74)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                    .addComponent(txtCiudad)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCINRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCelTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador::::"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscador)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(153, 153, 255));

        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CIN/RUC", "Apellidos", "Nombres", "Cel/Tel", "Correo E"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableClientes);
        if (tableClientes.getColumnModel().getColumnCount() > 0) {
            tableClientes.getColumnModel().getColumn(0).setResizable(false);
            tableClientes.getColumnModel().getColumn(1).setResizable(false);
            tableClientes.getColumnModel().getColumn(2).setResizable(false);
            tableClientes.getColumnModel().getColumn(3).setResizable(false);
            tableClientes.getColumnModel().getColumn(4).setResizable(false);
            tableClientes.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        int val = comprobarCampos();
        if (val == 1) {
            agregar();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int val = comprobarCampos();
        if (val == 1) {
            modificar();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientesMouseClicked
        filaSelecionada = tableClientes.getSelectedRow();

        filaSelecionada = (Integer) tableClientes.getValueAt(filaSelecionada, 0);

        for (int i = 0; i < main.empleadosDAO.resultadoEmpleados.size(); i++) {

            if (filaSelecionada == main.empleadosDAO.resultadoEmpleados.get(i).getId()) {
                id = main.empleadosDAO.resultadoEmpleados.get(i).getId();

                System.out.println(main.empleadosDAO.resultadoEmpleados.get(i).toString());

                txtCINRUC.setText("" + main.empleadosDAO.resultadoEmpleados.get(i).getCINRUC());
                txtNombres.setText("" + main.empleadosDAO.resultadoEmpleados.get(i).getNombres());
                txtApellidos.setText("" + main.empleadosDAO.resultadoEmpleados.get(i).getApellidos());
                txtCelTel.setText("" + main.empleadosDAO.resultadoEmpleados.get(i).getCeltel());
                txtDireccion.setText("" + main.empleadosDAO.resultadoEmpleados.get(i).getDireccion());
                txtCorreo.setText("" + main.empleadosDAO.resultadoEmpleados.get(i).getCorreo());
                txtCiudad.setText("" + main.empleadosDAO.resultadoEmpleados.get(i).getCiudad());

                try {
                    fechaT = main.empleadosDAO.resultadoEmpleados.get(i).getFecha();
                    fecha = inicio.gFechas.invertir(fechaT);
                } catch (Exception e) {
                    System.out.println("Erro invertir fecha: " + e.getMessage());
                }

            }
        }

        txtFecha.setText("" + fecha);
    }//GEN-LAST:event_tableClientesMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableClientes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCINRUC;
    private javax.swing.JFormattedTextField txtCelTel;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables

    public void agregar() {
        try {
            CINRUC = txtCINRUC.getText();
            nombres = txtNombres.getText();
            apellidos = txtApellidos.getText();
            direccion = txtDireccion.getText();
            celtel = txtCelTel.getText();
            correo = txtCorreo.getText();
            ciudad = txtCiudad.getText();
            fecha = txtFecha.getText();

            String[] fechasPartes = fecha.split("/");
            String dayTmp = fechasPartes[0];
            String mouthTmp = fechasPartes[1];
            String yearTmp = fechasPartes[2];

            fecha = yearTmp + "/" + mouthTmp + "/" + dayTmp;

            if (inicio.gFechas.validarFecha(fecha)) {

                empObj = new Empleados(null, CINRUC, nombres, apellidos, direccion, fecha, celtel, correo, ciudad, null, inicio.userObj.getId());
                main.empleadosDAO.insertar(empObj);
                actualizar();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Datos Invalidos", "Agregar Empleado", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }

    public void modificar() {
        try {
            CINRUC = txtCINRUC.getText();
            nombres = txtNombres.getText();
            apellidos = txtApellidos.getText();
            direccion = txtDireccion.getText();
            celtel = txtCelTel.getText();
            correo = txtCorreo.getText();
            ciudad = txtCiudad.getText();
            fecha = txtFecha.getText();

            String[] fechasPartes = fecha.split("/");
            String dayTmp = fechasPartes[0];
            String mouthTmp = fechasPartes[1];
            String yearTmp = fechasPartes[2];

            fecha = yearTmp + "/" + mouthTmp + "/" + dayTmp;


            if (inicio.gFechas.validarFecha(fecha)) {
                empObj = new Empleados(id, CINRUC, nombres, apellidos, direccion, fecha, celtel, correo, ciudad, null, inicio.userObj.getId());
                main.empleadosDAO.modificar(empObj);
                actualizar();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Datos Invalidos", "Agregar Empleado", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }

    private boolean eliminar() {

        if (id != -1) {

            empObj = new Empleados(id);
            main.empleadosDAO.eliminar(empObj);
            actualizar();
            limpiar();
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "No se puede borrar el Administrador Principal", "Eliminar Empleado", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

}
