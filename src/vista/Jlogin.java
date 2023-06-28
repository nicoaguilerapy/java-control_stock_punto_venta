package vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.*;
import modelo.persona.Personas;

public class Jlogin extends javax.swing.JFrame {

    Jmenu jMenu;
    UsuariosHistorial userMH;
    String usuario = "No iniciado";
    String rango = "";
    String pass = "";
    int x, y;

    public Jlogin() {
        initComponents();

        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        lbBarra.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14));
        lbBarra.setForeground(new java.awt.Color(255, 255, 255));
        lbBarra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBarra.setText("Login -" + inicio.version);
        this.setTitle("Login -" + inicio.version);

        Image img = new ImageIcon(getClass().getResource("/imagenes/logo.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_SMOOTH));

        lbLogo.setIcon(img2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lbCerrar = new javax.swing.JLabel();
        lbBarra = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnEntrar = new javax.swing.JLabel();
        lbLogo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(102, 0, 0));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel5MousePressed(evt);
            }
        });

        lbCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventana-cerrar.png"))); // NOI18N
        lbCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCerrarMouseClicked(evt);
            }
        });

        lbBarra.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        lbBarra.setForeground(new java.awt.Color(255, 255, 255));
        lbBarra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBarra.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        lbBarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lbBarraMouseDragged(evt);
            }
        });
        lbBarra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbBarraMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(lbBarra, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lbCerrar))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
            .addComponent(lbBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Usuario:");

        txtUsuario.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtUsuario.setText("caja");
        txtUsuario.setBorder(null);
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Contraseña:");

        txtPass.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtPass.setText("lili427");
        txtPass.setBorder(null);
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        btnEntrar.setBackground(new java.awt.Color(204, 204, 0));
        btnEntrar.setFont(new java.awt.Font("Square721 BT", 1, 18)); // NOI18N
        btnEntrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEntrar.setText("Entrar");
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrar.setOpaque(true);
        btnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEntrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEntrarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEntrarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEntrarMouseReleased(evt);
            }
        });

        lbLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(172, 172, 172))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 37, -1, 380));

        jPanel2.setBackground(new java.awt.Color(153, 153, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 810, 120));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbEstado.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbEstado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbEstado.setText("Esperando respuesta del servidor...");
        jPanel3.add(lbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 290, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 810, 340));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntrarMouseEntered
        btnEntrar.setBackground(new Color(102, 102, 0));
    }//GEN-LAST:event_btnEntrarMouseEntered

    private void btnEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntrarMouseClicked
        acceder();
    }//GEN-LAST:event_btnEntrarMouseClicked

    private void btnEntrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntrarMousePressed
        btnEntrar.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnEntrarMousePressed

    private void btnEntrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntrarMouseReleased
        btnEntrar.setBackground(new Color(255, 51, 51));
    }//GEN-LAST:event_btnEntrarMouseReleased

    private void btnEntrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntrarMouseExited
        btnEntrar.setBackground(new Color(204, 204, 0));
    }//GEN-LAST:event_btnEntrarMouseExited

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed

    }//GEN-LAST:event_txtPassActionPerformed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        int c = evt.getKeyCode();
        if (c == 10) {
            try {
                acceder();
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed

    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void lbCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lbCerrarMouseClicked

    private void jPanel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MousePressed

    }//GEN-LAST:event_jPanel5MousePressed

    private void lbBarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBarraMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_lbBarraMousePressed

    private void lbBarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBarraMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_lbBarraMouseDragged

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        txtUsuario.transferFocus();
    }//GEN-LAST:event_txtUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbBarra;
    private javax.swing.JLabel lbCerrar;
    public static javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    public void acceder() {
        usuario = txtUsuario.getText();
        pass = new String(txtPass.getPassword());

        if (login()) {

            historial();
            JOptionPane.showMessageDialog(null, "Bienvenido " + inicio.userObj.getNombre() + "(" + inicio.userObj.getUsuario() + "): " + inicio.userObj.getRango());
            this.setVisible(false);

            jMenu = new Jmenu();
            jMenu.setLocationRelativeTo(null);
            jMenu.setVisible(true);

            try {
                main.balanceDAO.actualizarCierres();
                main.jCaja.actualizar();
                main.jBanco.actualizar();
                main.jTarjeta.actualizar();
            } catch (Exception e) {
                System.out.println("Error vista.Jlogin.acceder() balances");
            }

            try {
                main.jUsuarios.actualizar();
                main.jUHistorial.actualizar();
            } catch (Exception e) {
            }

            try {
                main.jAcreedores.actualizar();
            } catch (Exception e) {
            }

            try {
                main.jClientes.actualizar();
            } catch (Exception e) {
            }

            try {
                main.jProductos.actualizar();
                main.jProveedores.actualizar();
            } catch (Exception e) {
            }

            try {
                main.jPedidos.actualizar();
            } catch (Exception e) {
            }

            try {
                main.jCobranzas.actualizar("Todos", "");
            } catch (Exception e) {
            }

            try {
                main.jEmpleados.actualizar();
            } catch (Exception e) {
            }
            try {
                main.jLoteProductos.actualizar();
            } catch (Exception e) {
            }
            try {
                main.jDeudas = new Jcreditos();
            } catch (Exception e) {
            }

            main.jCobranzas = new Jcobranzas();
            inicio.gAyC.actualizarAyC();
            jMenu.comprobarAperturaCierre();
        }
    }

    public boolean login() {
        try {
            inicio.userObj = (Usuarios) main.usuariosDAO.listar(" where usuario='" + usuario + "' and pass='" + pass + "'").get(0);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión " + e.getMessage(), "Iniciar Sesión", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean historial() {
        try {
            InetAddress lh = InetAddress.getLocalHost();
            String fecha = inicio.gFechas.getFechaActualBarras();
            fecha = inicio.gFechas.invertir(fecha);

            userMH = new UsuariosHistorial(
                    null,
                    inicio.userObj.getId(),
                    fecha,
                    inicio.gFechas.getHoraActualPuntos(),
                    lh.getHostAddress(),
                    lh.getHostName()
            );

            main.usuariosHistorialDAO.insertar(userMH);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
