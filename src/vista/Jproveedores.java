package vista;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.persona.Proveedores;

public class Jproveedores extends javax.swing.JFrame {

    private Proveedores proObj = null;

    private Integer id = -1;
    private String CINRUC, nombres, apellidos, direccion, fecha, celtel, correo, empresanombre;

    private DefaultTableModel datosProveedores;

    private int filaSelecionada, estado = 1;

    public Jproveedores() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Proveedores -" + inicio.version);

        fecha = inicio.fechaYMD;

        datosProveedores = (DefaultTableModel) tableProveedores.getModel();
        inicio.tools.Busqued(tableProveedores, datosProveedores, txtBuscador);
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
        if (txtEmpresa.getText().equals("")) {
            aux = message;
            message = aux + "txtEmpresa,  ";
            sw = 0;
        }

        if (sw == 0) {
            JOptionPane.showMessageDialog(null, message + "*Son campos Obigatorios*", "Faltan campos", JOptionPane.ERROR_MESSAGE);
        }
        return sw;
    }

    public void actualizar() {

        id = -1;
        main.proveedoresDAO.actualizarLista("");
        datosProveedores = (DefaultTableModel) tableProveedores.getModel();
        datosProveedores.setRowCount(0);

        Object[] fila = new Object[datosProveedores.getColumnCount()];

        for (int i = 0; i < main.proveedoresDAO.resultado.size(); i++) {

            fila[0] = main.proveedoresDAO.resultado.get(i).getId();
            fila[1] = main.proveedoresDAO.resultado.get(i).getCINRUC();
            fila[2] = main.proveedoresDAO.resultado.get(i).getEmpresaNombre();
            fila[3] = main.proveedoresDAO.resultado.get(i).getNombres() +" "+main.proveedoresDAO.resultado.get(i).getApellidos();
            fila[4] = main.proveedoresDAO.resultado.get(i).getCeltel();
            fila[5] = main.proveedoresDAO.resultado.get(i).getCorreo();
            datosProveedores.addRow(fila);
        }

        inicio.tools.centrar(tableProveedores, new int[]{4, 5});
        inicio.tools.izquierda(tableProveedores, new int[]{1, 2, 3});

    }

    public void limpiar() {
        txtCINRUC.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDireccion.setText("");
        txtEmpresa.setText("");
        txtCorreo.setText("");
        txtCelTel.setText("");
        String fechaAc = inicio.gFechas.getFechaActual();
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
        jLabel6 = new javax.swing.JLabel();
        txtCelTel = new javax.swing.JFormattedTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmpresa = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtBuscador = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProveedores = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));
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
        jLabel4.setText("Direccion:");

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
        jLabel8.setText("Empresa*:");

        txtEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCINRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                .addComponent(txtNombres, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(146, 146, 146)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCelTel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(txtDireccion))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCINRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtCelTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));

        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador::::"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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

        jPanel8.setBackground(new java.awt.Color(255, 153, 153));

        tableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CIN/RUC", "Empresa", "Nombre", "Apellido", "Cel/Tel", "Correo E"
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
        tableProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProveedores);
        if (tableProveedores.getColumnModel().getColumnCount() > 0) {
            tableProveedores.getColumnModel().getColumn(0).setResizable(false);
            tableProveedores.getColumnModel().getColumn(1).setResizable(false);
            tableProveedores.getColumnModel().getColumn(2).setResizable(false);
            tableProveedores.getColumnModel().getColumn(3).setResizable(false);
            tableProveedores.getColumnModel().getColumn(4).setResizable(false);
            tableProveedores.getColumnModel().getColumn(5).setResizable(false);
            tableProveedores.getColumnModel().getColumn(6).setResizable(false);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tableProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProveedoresMouseClicked
        filaSelecionada = tableProveedores.getSelectedRow();

        filaSelecionada = (Integer) tableProveedores.getValueAt(filaSelecionada, 0);

        for (int i = 0; i < main.proveedoresDAO.resultado.size(); i++) {

            if (filaSelecionada == main.proveedoresDAO.resultado.get(i).getId()) {
                id = main.proveedoresDAO.resultado.get(i).getId();

                txtCINRUC.setText("" + main.proveedoresDAO.resultado.get(i).getCINRUC());
                txtNombres.setText("" + main.proveedoresDAO.resultado.get(i).getNombres());
                txtApellidos.setText("" + main.proveedoresDAO.resultado.get(i).getApellidos());
                txtCelTel.setText("" + main.proveedoresDAO.resultado.get(i).getCeltel());
                txtDireccion.setText("" + main.proveedoresDAO.resultado.get(i).getDireccion());
                txtCorreo.setText("" + main.proveedoresDAO.resultado.get(i).getCorreo());
                txtEmpresa.setText("" + main.proveedoresDAO.resultado.get(i).getEmpresaNombre());

                fecha = main.proveedoresDAO.resultado.get(i).getFecha();
            }

        }
    }//GEN-LAST:event_tableProveedoresMouseClicked

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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProveedores;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCINRUC;
    private javax.swing.JFormattedTextField txtCelTel;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmpresa;
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
            empresanombre = txtEmpresa.getText();

            proObj = new Proveedores(null, CINRUC, nombres, apellidos, direccion, fecha, celtel, correo, inicio.userObj.getId(), null, empresanombre);
            main.proveedoresDAO.insertar(proObj);
            actualizar();
            limpiar();

        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Campos Vacios o Inválidos", "Agregar Proveedores", JOptionPane.ERROR_MESSAGE);
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
            empresanombre = txtEmpresa.getText();

            proObj = new Proveedores(id, CINRUC, nombres, apellidos, direccion, fecha, celtel, correo, inicio.userObj.getId(), null, empresanombre);
            main.proveedoresDAO.modificar(proObj);
            actualizar();
            limpiar();
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Campos Vacios o Inválidos", "Agregar Proveedores", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void eliminar() {

        if (id > -1) {
            proObj = new Proveedores(id);
            main.proveedoresDAO.eliminar(proObj);
            actualizar();
            limpiar();
        }
    }
}
