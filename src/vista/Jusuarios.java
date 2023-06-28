package vista;

import java.awt.Toolkit;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Empleados;
import modelo.Usuarios;
import modelo.persona.Personas;

public class Jusuarios extends javax.swing.JFrame {

    private Usuarios usuarios;
    private String usuario, pass, rango, nombre;

    private Empleados emp = null;

    private DefaultTableModel datosUsuarios;

    private int filaSelecionada, estado = 1;
    private Integer id = -1, idCuenta = -1;

    public Jusuarios() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Usuarios -" + inicio.version);

        datosUsuarios = (DefaultTableModel) tableUsuarios.getModel();
    }

    public void actualizar() {
        btnAgregar.setEnabled(false);
        main.usuariosDAO.actualizarLista("");

        datosUsuarios.setRowCount(0);

        Object[] fila = new Object[datosUsuarios.getColumnCount()];

        for (int i = 0; i < main.usuariosDAO.resultadoUsuarios.size(); i++) {

            fila[0] = main.usuariosDAO.resultadoUsuarios.get(i).getNombre();
            fila[1] = main.usuariosDAO.resultadoUsuarios.get(i).getUsuario();
            fila[2] = main.usuariosDAO.resultadoUsuarios.get(i).getPass();
            fila[3] = main.usuariosDAO.resultadoUsuarios.get(i).getRango();
            datosUsuarios.addRow(fila);
        }
        inicio.tools.centrar(tableUsuarios, new int[]{0, 1, 2, 3});
    }

    private void limpiar() {
        txtUsuario.setText("");
        txtPass.setText("");
        txtEmpleado.setText("");
        id = -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbxRango = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        btnBuscarEmpleado = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Usuario", "Contraseña", "Rango"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableUsuarios);
        if (tableUsuarios.getColumnModel().getColumnCount() > 0) {
            tableUsuarios.getColumnModel().getColumn(0).setResizable(false);
            tableUsuarios.getColumnModel().getColumn(1).setResizable(false);
            tableUsuarios.getColumnModel().getColumn(2).setResizable(false);
            tableUsuarios.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
        );

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contraseña:");

        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassKeyTyped(evt);
            }
        });

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-usuario-agregar.png"))); // NOI18N
        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-usuario-modificar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-usuario-eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Rango:");

        cbxRango.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Operador" }));
        cbxRango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRangoActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Empleado:");

        txtEmpleado.setEditable(false);
        txtEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleadoActionPerformed(evt);
            }
        });
        txtEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmpleadoKeyTyped(evt);
            }
        });

        btnBuscarEmpleado.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnBuscarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-historial-buscar.png"))); // NOI18N
        btnBuscarEmpleado.setPreferredSize(new java.awt.Dimension(95, 28));
        btnBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario)
                    .addComponent(txtPass)
                    .addComponent(cbxRango, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEmpleado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxRango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtEmpleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUsuariosMouseClicked
        filaSelecionada = tableUsuarios.getSelectedRow();
        id = main.usuariosDAO.resultadoUsuarios.get(filaSelecionada).getId();

        txtUsuario.setText("" + main.usuariosDAO.resultadoUsuarios.get(filaSelecionada).getUsuario());
        txtPass.setText("" + main.usuariosDAO.resultadoUsuarios.get(filaSelecionada).getPass());
        cbxRango.setSelectedItem("" + main.usuariosDAO.resultadoUsuarios.get(filaSelecionada).getRango());

        idCuenta = (Integer) main.empleadosDAO.mapEmpleadosCuenta.get(id);
        
        recibirEmpleado(idCuenta);


    }//GEN-LAST:event_tableUsuariosMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed

    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void cbxRangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRangoActionPerformed

    }//GEN-LAST:event_cbxRangoActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        int c = txtUsuario.getText().length();
        if (c > 16) {
            evt.consume();
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyTyped
        int c = txtPass.getText().length();
        if (c > 16) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPassKeyTyped

    private void txtEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoActionPerformed

    private void txtEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoKeyTyped

    private void btnBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpleadoActionPerformed
        Jbuscar jbc = new Jbuscar("empleados");
        jbc.setVisible(true);
        jbc.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnBuscarEmpleadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarEmpleado;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbxRango;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUsuarios;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    public void recibirEmpleado(Integer idEmpleado) {
        emp = (Empleados) main.empleadosDAO.mapEmpleados.get(idEmpleado);

        txtEmpleado.setText(emp.getNombres() + " " + emp.getApellidos());
        btnAgregar.setEnabled(true);
    }

    private void agregar() {
        if (txtUsuario.getText().equals("") || txtPass.getText().equals("") || txtEmpleado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Rellene los campos", "Agregar Usuario", JOptionPane.ERROR_MESSAGE);
        } else {
            if (emp != null) {
                usuario = txtUsuario.getText();
                pass = txtPass.getText();
                rango = (String) cbxRango.getSelectedItem();
                nombre = txtEmpleado.getText();

                usuarios = new Usuarios(null, nombre, usuario, pass, rango);

                if (main.usuariosDAO.insertar(usuarios)) {
                    limpiar();
                    actualizar();
                    int i = main.usuariosDAO.resultadoUsuarios.size();
                    idCuenta = main.usuariosDAO.resultadoUsuarios.get(i - 1).getId();
                    main.empleadosDAO.asignarCuenta(emp, idCuenta);

                    emp = null;
                    idCuenta = -1;
                    btnAgregar.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccionar Empleado", "Agregar Usuario", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void modificar() {
        if (txtUsuario.getText().equals("") || txtPass.getText().equals("") || txtEmpleado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Rellene los campos", "Modificar Usuario", JOptionPane.ERROR_MESSAGE);
        } else {
            if (emp != null) {
                usuario = txtUsuario.getText();
                pass = txtPass.getText();
                rango = (String) cbxRango.getSelectedItem();
                nombre = txtEmpleado.getText();

                usuarios = new Usuarios(id, nombre, usuario, pass, rango);

                if (main.usuariosDAO.modificar(usuarios)) {
                    limpiar();
                    actualizar();
                    emp = null;
                    id = -1;
                    btnAgregar.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccionar Empleado", "Agregar Usuario", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminar() {
        if (id != 1) {
            try {
                usuarios = new Usuarios(id);
                if (main.usuariosDAO.eliminar(usuarios)) {
                    limpiar();
                    actualizar();
                }
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se puede Eliminar el usuario Administrador Principal", "Eliminar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }
}
