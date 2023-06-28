package vista;

import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.balance.*;

public class JCajasCierre extends javax.swing.JFrame {

    private Integer cajaT = 0, ivaD = 0, bancoT = 0, tarjetaT = 0;
    private String fechaCerrar;
    private int i, e, sumi, sume;
    private Balance balObj;

    public JCajasCierre(String fecha) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Cierre Caja - " + inicio.version);
        int cierreSize = main.balanceDAO.cierresArray.size() - 1;

        inicio.gAyC.actualizarAyC();
        main.balanceDAO.actualizarCierres();

        fechaCerrar = fecha;

        txtFecha.setText(inicio.gFechas.invertir(fechaCerrar));

        //CIERRE DE CAJA
        i = 0;
        e = 0;
        try {
            main.balanceDAO.actualizarCaja(" where fecha='" + fechaCerrar + "' and idUsuarios='" + inicio.userObj.getId() + "'");

            for (int j = 0; j < main.balanceDAO.resultadoCaja.size(); j++) {
                e = e + main.balanceDAO.resultadoCaja.get(j).getEgreso();
                i = i + main.balanceDAO.resultadoCaja.get(j).getIngreso();
            }

            cajaT = i - e;

        } catch (Exception ex) {
            cajaT = 0;
            sumi = 0;
            sume = 0;
        }

        //CIERRE DE BANCO
        sumi = i;
        sume = e;
        i = 0;
        e = 0;

        try {
            main.balanceDAO.actualizarBanco(" where fecha='" + fechaCerrar + "' and idUsuarios='" + inicio.userObj.getId() + "'");

            for (int j = 0; j < main.balanceDAO.resultadoBanco.size(); j++) {
                e = e + main.balanceDAO.resultadoBanco.get(j).getEgreso();
                i = i + main.balanceDAO.resultadoBanco.get(j).getIngreso();
            }

            bancoT = i - e;

        } catch (Exception ex) {
            bancoT = 0;
            System.out.println("try catch banco");
        }

        //CIERRE DE TARJETA
        i = 0;
        e = 0;
        try {
            main.balanceDAO.actualizarTarjeta(" where fecha='" + fechaCerrar + "' and idUsuarios='" + inicio.userObj.getId() + "'");

            for (int j = 0; j < main.balanceDAO.resultadoTarjeta.size(); j++) {
                e = e + main.balanceDAO.resultadoTarjeta.get(j).getEgreso();
                i = i + main.balanceDAO.resultadoTarjeta.get(j).getIngreso();
            }

            tarjetaT = i - e;

        } catch (Exception ex) {
            tarjetaT = 0;
            System.out.println("try catch tarjeta");
        }

        if (cajaT == null) {
            cajaT = 0;
        }

        if (bancoT == null) {
            bancoT = 0;
        }
        if (tarjetaT == null) {
            tarjetaT = 0;
        }

        try {
            txtCaja.setText(inicio.format.format(cajaT));
            txtBanco.setText(inicio.format.format(bancoT));
            txtTarjeta.setText(inicio.format.format(tarjetaT));
        } catch (Exception es) {
            System.out.println("JCajasCierre error de formato: " + es.getMessage());
        }

        System.out.println("vista.JCajasCierre: caja: " + cajaT + " | banco: " + bancoT + " | Tarjeta: " + tarjetaT);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnCerraCajas = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txtTarjeta = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtBanco = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Caja:");

        txtCaja.setEditable(false);
        txtCaja.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCaja.setBorder(null);
        txtCaja.setEnabled(false);
        txtCaja.setOpaque(false);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        btnCerraCajas.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCerraCajas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-cerrarcajas-cerrar.png"))); // NOI18N
        btnCerraCajas.setText("Cerrar Cajas");
        btnCerraCajas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerraCajasActionPerformed(evt);
            }
        });

        btnCerrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-cerrarcajas-salir.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setText("Fecha:");

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtFecha.setBorder(null);
        txtFecha.setEnabled(false);
        txtFecha.setOpaque(false);

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Tarjeta:");

        txtTarjeta.setEditable(false);
        txtTarjeta.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTarjeta.setBorder(null);
        txtTarjeta.setEnabled(false);
        txtTarjeta.setOpaque(false);

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setText("Banco:");

        txtBanco.setEditable(false);
        txtBanco.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtBanco.setBorder(null);
        txtBanco.setEnabled(false);
        txtBanco.setOpaque(false);

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator5)
                                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator6)
                                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCerraCajas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(31, 31, 31))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCerraCajas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnCerraCajasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerraCajasActionPerformed
        cerrarCajas();
    }//GEN-LAST:event_btnCerraCajasActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerraCajas;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtCaja;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTarjeta;
    // End of variables declaration//GEN-END:variables

    public void cerrarCajas() {

        Balance caj = new Balance(null, "*Cierre del Dia", 0, cajaT, fechaCerrar, inicio.userObj.getId(), "caja");
        main.balanceDAO.insertar(caj);
        main.balanceDAO.actualizarCaja("");

        Balance ban = new Balance(null, "*Cierre del Dia", 0, bancoT, fechaCerrar, inicio.userObj.getId(), "banco");
        main.balanceDAO.insertar(ban);
        main.balanceDAO.actualizarBanco("");

        Balance tar = new Balance(null, "*Cierre del Dia", 0, tarjetaT, fechaCerrar, inicio.userObj.getId(), "tarjeta");
        main.balanceDAO.insertar(tar);
        main.balanceDAO.actualizarTarjeta("");

        this.setVisible(false);

        crearCierre();

        if (!fechaCerrar.equals(inicio.fechaYMD)) {
            inicio.login.jMenu.cierreAnterior = true;
        }

    }

    public void crearCierre() {
        inicio.gReportes.cierreCaja(fechaCerrar, tarjetaT, bancoT, sumi, sume);
    }
}
