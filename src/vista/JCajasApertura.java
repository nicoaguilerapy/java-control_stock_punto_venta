package vista;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import modelo.balance.Balance;

public class JCajasApertura extends javax.swing.JFrame {

    private Integer cajaT, ivaD, bancoT, tarjetaT;
    private int i;
    public int cerradoCorrectamente = 0, bal = 0;
    public String fechaUlt;
    private int ingresoT, egresoT;
    private Balance balObj;

    public JCajasApertura() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Apertura de Cajas - " + inicio.version);
        iniciar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnAbrirCajas = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTarjeta = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txtBanco = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Caja:");

        txtCaja.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCaja.setBorder(null);
        txtCaja.setEnabled(false);
        txtCaja.setOpaque(false);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        btnAbrirCajas.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAbrirCajas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-cerrarcajas-cerrar.png"))); // NOI18N
        btnAbrirCajas.setText("Abrir Cajas");
        btnAbrirCajas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCajasActionPerformed(evt);
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
        jLabel4.setText("Tarjeta:");

        txtTarjeta.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTarjeta.setBorder(null);
        txtTarjeta.setEnabled(false);
        txtTarjeta.setOpaque(false);

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Banco:");

        txtBanco.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtBanco.setBorder(null);
        txtBanco.setEnabled(false);
        txtBanco.setOpaque(false);

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAbrirCajas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator4)
                            .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator5)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAbrirCajas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnAbrirCajasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCajasActionPerformed
        abrirCajas();
    }//GEN-LAST:event_btnAbrirCajasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirCajas;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtCaja;
    private javax.swing.JTextField txtTarjeta;
    // End of variables declaration//GEN-END:variables

    public void iniciar() {
        int aperturaSize = main.balanceDAO.aperturasArray.size() - 1;
        int cierreSize = main.balanceDAO.cierresArray.size() - 1;

        try {
            if (main.balanceDAO.aperturasArray.get(aperturaSize).getFecha().equals(inicio.fechaYMD)) {
                JOptionPane.showMessageDialog(null, "Caja Abierta", "Apertura de Cajas", JOptionPane.INFORMATION_MESSAGE);
                inicio.login.jMenu.aperturaCaja = true;
                this.dispose();
            } else {
                this.setVisible(true);
                this.setLocationRelativeTo(null);
            }
        } catch (Exception e) {
            this.setVisible(true);
            this.setLocationRelativeTo(null);
        }

        //CAJA- CAPTURA EL CIERRE ANTERIOR
        try {
            balObj = main.balanceDAO.cierresArray.get(cierreSize);
            cajaT = balObj.getEgreso();

        } catch (Exception e) {
            cajaT = 0;
        }

        //BANCO - CAPTURA EL CIERRE ANTERIOR
        try {
            for (int j = 0; j < main.balanceDAO.resultadoBanco.size(); j++) {
                if (main.balanceDAO.resultadoBanco.get(j).getConcepto().contains("*Cierre") && main.balanceDAO.resultadoBanco.get(j).getFecha().equals(balObj.getFecha())) {
                    bancoT = main.balanceDAO.resultadoBanco.get(j).getEgreso();
                }
            }

        } catch (Exception e) {
            bancoT = 0;
        }

        //TARJETA - CAPTURA EL CIERRE ANTERIOR
        try {
            for (int j = 0; j < main.balanceDAO.resultadoTarjeta.size(); j++) {
                if (main.balanceDAO.resultadoTarjeta.get(j).getConcepto().contains("*Cierre") && main.balanceDAO.resultadoTarjeta.get(j).getFecha().equals(balObj.getFecha())) {
                    tarjetaT = main.balanceDAO.resultadoTarjeta.get(j).getEgreso();
                }
            }

        } catch (Exception e) {
            bancoT = 0;
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
        if (ivaD == null) {
            ivaD = 0;
        }
        if (ivaD == null) {
            ivaD = 0;
        }
        try {
            txtCaja.setText(inicio.format.format(cajaT));
            txtBanco.setText(inicio.format.format(bancoT));
            txtTarjeta.setText(inicio.format.format(tarjetaT));
        } catch (Exception e) {
            System.out.println("JCajasApertura error de formato: " + e.getMessage());
        }

        main.jCaja.actualizar();
    }

    public void abrirCajas() {

        Balance balObj = null;

        balObj = new Balance(null, "*Apertura del Dia", cajaT, 0, inicio.fechaYMD, inicio.userObj.getId(), "caja");
        main.balanceDAO.insertar(balObj);
        balObj = new Balance(null, "&Sincronizacion", 0, 0, inicio.fechaYMD, inicio.userObj.getId(), "caja");
        main.balanceDAO.insertar(balObj);

        main.balanceDAO.actualizarCaja("");
        balObj = new Balance(null, "*Apertura del Dia", bancoT, 0, inicio.fechaYMD, inicio.userObj.getId(), "banco");

        main.balanceDAO.insertar(balObj);
        main.balanceDAO.actualizarBanco("");

        balObj = new Balance(null, "*Apertura del Dia", tarjetaT, 0, inicio.fechaYMD, inicio.userObj.getId(), "tarjeta");

        main.balanceDAO.insertar(balObj);
        main.balanceDAO.actualizarTarjeta("");

        this.setVisible(false);
        main.balanceDAO.actualizarCierres();
        inicio.login.jMenu.aperturaCaja = true;
    }
}
