package vista;

import Gestores.GestorCorreos;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Datos;

/**
 *
 * @author MBAPPE
 */
public class Jconfiguracion extends javax.swing.JFrame {

    String empresa, direccion1, direccion2, celtel, correo;
    String tuCorreo, contrasena, asunto, envioAutomatico, preguntarAntes;
    Image img;
    ImageIcon img2;

    public Jconfiguracion() {
        initComponents();

        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Boleta -" + inicio.version);

        img = new ImageIcon(getClass().getResource("/imagenes/logo-youtube.png")).getImage();
        img2 = new ImageIcon(img);
        lbYoutube.setIcon(img2);

        cargarBoleta();
        cargarCorreo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtEmpresa = new javax.swing.JTextField();
        txtDireccion1 = new javax.swing.JTextField();
        txtDireccion2 = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        Aceptar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txt2Correo = new javax.swing.JTextField();
        txt2Contrasena = new javax.swing.JTextField();
        txt2Asunto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        lbYoutube = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Aceptar1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        cbEnvioAutomatico = new javax.swing.JCheckBox();
        cbPreguntarAntes = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtEmpresa.setBorder(null);

        txtDireccion1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDireccion1.setBorder(null);

        txtDireccion2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDireccion2.setBorder(null);

        txtNumero.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNumero.setBorder(null);

        txtCorreo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCorreo.setBorder(null);

        jLabel1.setText("Nombre de la Empresa:");

        jLabel2.setText("Dirección Linea 1:");

        jLabel3.setText("Dirección Linea 2:");

        jLabel4.setText("Celular / Telefono:");

        jLabel5.setText("Correo Electrónico:");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDireccion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(1, 1, 1)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        Aceptar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-aceptar.png"))); // NOI18N
        Aceptar.setText("Aceptar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 421, Short.MAX_VALUE)
                .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Boleta de Venta", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txt2Correo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt2Correo.setBorder(null);

        txt2Contrasena.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt2Contrasena.setBorder(null);

        txt2Asunto.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt2Asunto.setText("Recibo de Pago ID: {idventa}");
        txt2Asunto.setBorder(null);

        jLabel6.setText("Tu Correo Electrónico:");

        jLabel7.setText("Contraseña de Aplicación:");

        jLabel8.setText("Asunto:");

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        lbYoutube.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbYoutube.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbYoutube.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbYoutubeMouseClicked(evt);
            }
        });

        jLabel10.setText("Tutorial de como Crear una contraseña de aplicación de Google");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator7)
                    .addComponent(jSeparator8)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt2Asunto, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt2Contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt2Correo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbYoutube, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt2Correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt2Contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt2Asunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbYoutube, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(177, Short.MAX_VALUE))
        );

        Aceptar1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Aceptar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-aceptar.png"))); // NOI18N
        Aceptar1.setText("Aceptar");
        Aceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Aceptar1ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        cbEnvioAutomatico.setBackground(new java.awt.Color(255, 255, 255));
        cbEnvioAutomatico.setText("Envío Automatico de Boleta");

        cbPreguntarAntes.setBackground(new java.awt.Color(255, 255, 255));
        cbPreguntarAntes.setText("Preguntar por cada Boleta");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEnvioAutomatico)
                    .addComponent(cbPreguntarAntes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbEnvioAutomatico)
                .addGap(26, 26, 26)
                .addComponent(cbPreguntarAntes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(Aceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Aceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Envío de Correos", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        aceptarBoleta();
    }//GEN-LAST:event_AceptarActionPerformed

    private void Aceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Aceptar1ActionPerformed
        aceptarCorreo();
    }//GEN-LAST:event_Aceptar1ActionPerformed

    private void lbYoutubeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbYoutubeMouseClicked
        abrirTutorial();
    }//GEN-LAST:event_lbYoutubeMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        abrirTutorial();
    }//GEN-LAST:event_jLabel10MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Aceptar1;
    private javax.swing.JCheckBox cbEnvioAutomatico;
    private javax.swing.JCheckBox cbPreguntarAntes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbYoutube;
    private javax.swing.JTextField txt2Asunto;
    private javax.swing.JTextField txt2Contrasena;
    private javax.swing.JTextField txt2Correo;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion1;
    private javax.swing.JTextField txtDireccion2;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables

    private void aceptarBoleta() {
        empresa = txtEmpresa.getText();
        direccion1 = txtDireccion1.getText();
        direccion2 = txtDireccion2.getText();
        celtel = txtNumero.getText();
        correo = txtCorreo.getText();

        Datos dat = null;
        dat = new Datos(null, "empresa", empresa);
        main.datosDAO.modificar(dat);
        dat = new Datos(null, "direccion1", direccion1);
        main.datosDAO.modificar(dat);
        dat = new Datos(null, "direccion2", direccion2);
        main.datosDAO.modificar(dat);
        dat = new Datos(null, "celtel", celtel);
        main.datosDAO.modificar(dat);
        dat = new Datos(null, "correo", correo);
        main.datosDAO.modificar(dat);
        main.datosDAO.actualizarLista("");
    }

    private void aceptarCorreo() {
        JOptionPane.showMessageDialog(null, "Espere mientras se configura el correo", "Configuración de Correo", JOptionPane.INFORMATION_MESSAGE);
        Datos dat = null;
        dat = new Datos(null, "email_email", txt2Correo.getText());
        main.datosDAO.modificar(dat);
        dat = new Datos(null, "email_password", txt2Contrasena.getText());
        main.datosDAO.modificar(dat);
        dat = new Datos(null, "email_asunto", txt2Asunto.getText());
        main.datosDAO.modificar(dat);

        if (cbEnvioAutomatico.isSelected()) {
            dat = new Datos(null, "email_automatic_send", "Si");
        } else {
            dat = new Datos(null, "email_automatic_send", "No");
        }
        main.datosDAO.modificar(dat);

        if (cbPreguntarAntes.isSelected()) {
            dat = new Datos(null, "email_question_send", "Si");
        } else {
            dat = new Datos(null, "email_question_send", "No");
        }

        main.datosDAO.modificar(dat);
        main.datosDAO.actualizarLista("");

        GestorCorreos gCorreos = new GestorCorreos();
        try {
            gCorreos.setEmail(txt2Correo.getText());
            gCorreos.setPassword(txt2Contrasena.getText());

            if (gCorreos.sendEmail(txt2Correo.getText(), "Email de Prueba")) {
                JOptionPane.showMessageDialog(null, "Verifique su Correo, configuración correcta", "Configuración de Correo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Correo y/o contraseña inválidas", "Configuración de Correo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Correo y/o contraseña inválidas", "Configuración de Correo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarBoleta() {
        empresa = (String) main.datosDAO.map.get("empresa");
        direccion1 = (String) main.datosDAO.map.get("direccion1");
        direccion2 = (String) main.datosDAO.map.get("direccion2");
        celtel = (String) main.datosDAO.map.get("celtel");
        correo = (String) main.datosDAO.map.get("correo");

        txtEmpresa.setText(empresa);
        txtDireccion1.setText(direccion1);
        txtDireccion2.setText(direccion2);
        txtNumero.setText(celtel);
        txtCorreo.setText(correo);
    }

    private void cargarCorreo() {
        tuCorreo = (String) main.datosDAO.map.get("email_email");
        contrasena = (String) main.datosDAO.map.get("email_password");
        asunto = (String) main.datosDAO.map.get("email_asunto");
        envioAutomatico = (String) main.datosDAO.map.get("email_automatic_send");
        preguntarAntes = (String) main.datosDAO.map.get("email_question_send");

        txt2Correo.setText(tuCorreo);
        txt2Contrasena.setText(contrasena);
        txt2Asunto.setText(asunto);

        if (envioAutomatico.equals("Si")) {
            cbEnvioAutomatico.setSelected(true);
        } else {
            cbEnvioAutomatico.setSelected(false);
        }

        if (envioAutomatico.equals("Si")) {
            cbPreguntarAntes.setSelected(true);
        } else {
            cbPreguntarAntes.setSelected(false);
        }
    }

    private void abrirTutorial() {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=ziBy8j2TbF0"));
            } catch (URISyntaxException ex) {
                Logger.getLogger(Jconfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Jconfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
