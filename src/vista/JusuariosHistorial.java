package vista;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.UsuariosHistorial;


public class JusuariosHistorial extends javax.swing.JFrame {

    UsuariosHistorial UHobj;

    DefaultTableModel datosUsuariosH;

    int estado = 1;

    public JusuariosHistorial() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Historial de Usuarios -" + inicio.version);

        datosUsuariosH = (DefaultTableModel) tableHistorial.getModel();
        inicio.tools.Busqued(tableHistorial, datosUsuariosH, txtBuscador);

    }

    public void actualizar() {

        datosUsuariosH.setRowCount(0);

        Object[] fila = new Object[datosUsuariosH.getColumnCount()];

        for (int i = 0; i < main.usuariosHistorialDAO.resultadoUHistorial.size(); i++) {
            String fecha = main.usuariosHistorialDAO.resultadoUHistorial.get(i).getFecha();
            fecha = inicio.gFechas.invertir(fecha);

            fila[0] = fecha;
            fila[1] = main.usuariosHistorialDAO.resultadoUHistorial.get(i).getHora();
            fila[2] = main.usuariosHistorialDAO.resultadoUHistorial.get(i).getIdUsuarios();
            fila[3] = main.usuariosHistorialDAO.resultadoUHistorial.get(i).getIp();
            fila[4] = main.usuariosHistorialDAO.resultadoUHistorial.get(i).getHost();
            datosUsuariosH.addRow(fila);
        }

        inicio.tools.centrar(tableHistorial, new int[]{0, 1, 2, 3, 4});
        sustituirUsuario();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtBuscador = new javax.swing.JTextField();
        txtFechaF = new javax.swing.JFormattedTextField();
        txtFechaI = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHistorial = new javax.swing.JTable();

        txtBuscador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscadorActionPerformed(evt);
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

        jLabel1.setText("Inicio:");

        jLabel2.setText("Fin:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-historial-buscar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Buscador:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtBuscador))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(98, 98, 98)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tableHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "FECHA", "HORA", "USUARIO", "IP", "PC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableHistorial);
        if (tableHistorial.getColumnModel().getColumnCount() > 0) {
            tableHistorial.getColumnModel().getColumn(0).setResizable(false);
            tableHistorial.getColumnModel().getColumn(1).setResizable(false);
            tableHistorial.getColumnModel().getColumn(2).setResizable(false);
            tableHistorial.getColumnModel().getColumn(3).setResizable(false);
            tableHistorial.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
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
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String fecha1 = txtFechaI.getText();
        String fecha = inicio.gFechas.invertir(fecha1);
        fecha1 = fecha;
        String fecha2 = txtFechaF.getText();
        fecha = inicio.gFechas.invertir(fecha2);
        fecha2 = fecha;

        if (inicio.gFechas.validarFecha(fecha1) && inicio.gFechas.validarFecha(fecha2)) {
             main.usuariosHistorialDAO.actualizarLista(" WHERE fecha BETWEEN '" + fecha1 + "' AND '" + fecha2 + "'");
        } else {
            JOptionPane.showMessageDialog(null, "Fechas vacias o incorrectas", "Comprobar Informe", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscadorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableHistorial;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JFormattedTextField txtFechaF;
    private javax.swing.JFormattedTextField txtFechaI;
    // End of variables declaration//GEN-END:variables

    private void sustituirUsuario() {

        int j, i;
        for (i = 0; i < datosUsuariosH.getRowCount(); i++) {

            int idT = (int) tableHistorial.getValueAt(i, 2);

            for (j = 0; j < main.usuariosDAO.resultadoUsuarios.size(); j++) {

                int idU = main.usuariosDAO.resultadoUsuarios.get(j).getId();
                if (idT == idU) {

                    String usuario = main.usuariosDAO.resultadoUsuarios.get(j).getUsuario();

                    tableHistorial.setValueAt(usuario, i, 2);
                }
            }

        }

    }

}
