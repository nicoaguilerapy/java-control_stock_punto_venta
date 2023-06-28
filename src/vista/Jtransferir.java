package vista;

import java.awt.Toolkit;
import java.util.HashMap;
import modelo.balance.*;

public class Jtransferir extends javax.swing.JFrame {

    private Integer cajaTransferir = -1;
    private Integer cajaPropia;

    public Jtransferir() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Transferir -" + inicio.version);

        try {
            main.jCaja.actualizar();
            cajaPropia = main.jCaja.bal;

        } catch (Exception ex) {
            cajaPropia = 0;
        }

        try {
            txtCaja.setText(inicio.format.format(cajaPropia));
        } catch (Exception es) {

            System.out.println("JCajasCierre error de formato: " + es.getMessage());
        }

        actualizarCB();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCajaTransferir = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        cbUsuarios = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnTotal = new javax.swing.JButton();
        btnTransferir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Caja Propia"));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Caja:");

        txtCaja.setEditable(false);
        txtCaja.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCaja.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCaja)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Transferir"));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Caja:");

        txtCajaTransferir.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCajaTransferir.setText("0");
        txtCajaTransferir.setBorder(null);
        txtCajaTransferir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCajaTransferirKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCajaTransferirKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCajaTransferirKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Transferir a:");

        cbUsuarios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCajaTransferir, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(jSeparator7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbUsuarios, 0, 129, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCajaTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnTotal.setText(">>>");
        btnTotal.setToolTipText("Transferir El Total");
        btnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnTotal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTransferir.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnTransferir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-transferir-transferir.png"))); // NOI18N
        btnTransferir.setText("Transferir");
        btnTransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(btnTransferir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTransferir)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCajaTransferirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCajaTransferirKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }

    }//GEN-LAST:event_txtCajaTransferirKeyTyped

    private void btnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalActionPerformed
        txtCajaTransferir.setText("" + cajaPropia);
        comprobarTransferencia();
    }//GEN-LAST:event_btnTotalActionPerformed

    private void txtCajaTransferirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCajaTransferirKeyPressed

    }//GEN-LAST:event_txtCajaTransferirKeyPressed

    private void txtCajaTransferirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCajaTransferirKeyReleased
        comprobarTransferencia();
    }//GEN-LAST:event_txtCajaTransferirKeyReleased

    private void btnTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferirActionPerformed
        transferir();
    }//GEN-LAST:event_btnTransferirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTotal;
    private javax.swing.JButton btnTransferir;
    private javax.swing.JComboBox<String> cbUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField txtCaja;
    private javax.swing.JTextField txtCajaTransferir;
    // End of variables declaration//GEN-END:variables

    private void comprobarTransferencia() {
        try {
            cajaTransferir = Integer.parseInt(txtCajaTransferir.getText());
            if (cajaPropia <= cajaTransferir) {
                cajaTransferir = cajaPropia;
                txtCajaTransferir.setText("" + cajaTransferir);
            }
        } catch (Exception e) {
            cajaTransferir = -1;
        }
    }

    private void actualizarCB() {
        cbUsuarios.removeAllItems();
        try {
            for (int i = 0; i < main.usuariosDAO.resultadoUsuarios.size(); i++) {
                if (inicio.userObj.getId() == main.usuariosDAO.resultadoUsuarios.get(i).getId()) {

                } else {
                    cbUsuarios.addItem(main.usuariosDAO.resultadoUsuarios.get(i).getUsuario());
                }
            }
        } catch (Exception e) {
            System.out.println("Proveedores vacio");
        }
    }

    private int getIdUsurio() {

        for (int i = 0; i < main.usuariosDAO.resultadoUsuarios.size(); i++) {
            String aux = main.usuariosDAO.resultadoUsuarios.get(i).getUsuario();
            String aux2 = (String) cbUsuarios.getSelectedItem();
            if (aux.equals(aux2)) {
                System.out.println(main.usuariosDAO.resultadoUsuarios.get(i).getId());
                return main.usuariosDAO.resultadoUsuarios.get(i).getId();
            }
        }
        return -1;
    }

    private void setIdUsurio(int id) {
        String aux = "";
        for (int i = 0; i < main.usuariosDAO.resultadoUsuarios.size(); i++) {

            if (main.usuariosDAO.resultadoUsuarios.get(i).getId() == id) {
                aux = main.usuariosDAO.resultadoUsuarios.get(i).getUsuario();
            }
            cbUsuarios.setSelectedItem(aux);
        }
    }

    private void transferir() {

        if (cajaTransferir != -1) {

            Balance caja = new Balance(null, "# Transferencia de Usuario ID: " + inicio.userObj.getId(), cajaTransferir, 0, inicio.fechaYMD, getIdUsurio(), "caja");
            main.balanceDAO.insertar(caja);
            System.out.println(caja.toString());

            caja = new Balance(null, "# Transferencia a Usuario ID: " + getIdUsurio(), 0, cajaTransferir, inicio.fechaYMD, inicio.userObj.getId(), "caja");
            main.balanceDAO.insertar(caja);
            System.out.println(caja.toString());

            inicio.gReportes.transferir(cajaTransferir, getIdUsurio());

            this.dispose();
        }

    }

}
