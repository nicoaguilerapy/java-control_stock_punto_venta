package vista;

import vista.reportes.JinformesPanel;
import vista.reportes.JinformesCajas;
import Gestores.GestorBackups;
import Gestores.GestorScripts;
import herramientas.HiloPanel;
import herramientas.ScriptInit;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import javafx.beans.binding.Bindings;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Empleados;
import modelo.balance.Balance;

public class Jmenu extends javax.swing.JFrame {

    JinformesCajas jinf;
    JproductosBajas jbp;

    public boolean cierreAnterior = false, aperturaCaja = false, cierreCaja = false;

    private Color rojoClaro = new Color(246, 89, 102);
    private Color rojoNormal = new Color(180, 42, 42);
    private Color gris = new Color(43, 44, 67);

    public Jmenu() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Menu -" + inicio.version);

        Image img = new ImageIcon(getClass().getResource("/imagenes/logo.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(90, 90, Image.SCALE_SMOOTH));

        lbLogo.setIcon(img2);

        productsPanel31.setVisible(false);
        productsPanel32.setVisible(false);
        productsPanel33.setVisible(false);

        if (inicio.userObj.getRango().equals("Operador")) {
            mnUsuarios.setEnabled(false);
        }

        cargarCampos();

        //Asignar datos en pantalla        
        lbNombre.setText("Nombre: " + inicio.userObj.getNombre());
        lbRango.setText("Rango: " + inicio.userObj.getRango());

        //GestorScripts gs = new GestorScripts();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        panelCaja = new javax.swing.JPanel();
        lbCaja = new javax.swing.JLabel();
        panelTarjeta = new javax.swing.JPanel();
        lbTarjeta = new javax.swing.JLabel();
        panelBanco = new javax.swing.JPanel();
        lbBanco = new javax.swing.JLabel();
        panelAcreedores = new javax.swing.JPanel();
        lbAcreedores = new javax.swing.JLabel();
        panelCreditos = new javax.swing.JPanel();
        lbCreditos = new javax.swing.JLabel();
        panelInformes = new javax.swing.JPanel();
        lbInformes = new javax.swing.JLabel();
        panelInventario = new javax.swing.JPanel();
        lbInventario = new javax.swing.JLabel();
        panelVentas = new javax.swing.JPanel();
        lbVentas = new javax.swing.JLabel();
        panelClientes = new javax.swing.JPanel();
        lbClientes = new javax.swing.JLabel();
        panelPedidos = new javax.swing.JPanel();
        lbPedidos = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbLogo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbNombre = new javax.swing.JLabel();
        lbRango = new javax.swing.JLabel();
        dashBoardPanel = new javax.swing.JPanel();
        productsPanel11 = new javax.swing.JPanel();
        panel11cabecera = new javax.swing.JPanel();
        lb11cabecera = new javax.swing.JLabel();
        panel11campo = new javax.swing.JPanel();
        lb11campo = new javax.swing.JLabel();
        productsPanel12 = new javax.swing.JPanel();
        panel12cabecera = new javax.swing.JPanel();
        lb12cabecera = new javax.swing.JLabel();
        panel12campo = new javax.swing.JPanel();
        lb12campo = new javax.swing.JLabel();
        productsPanel13 = new javax.swing.JPanel();
        panel13cabecera = new javax.swing.JPanel();
        lb13cabecera = new javax.swing.JLabel();
        panel13campo = new javax.swing.JPanel();
        lb13campo = new javax.swing.JLabel();
        productsPanel21 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lb21campo = new javax.swing.JLabel();
        productsPanel22 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        lb22campo = new javax.swing.JLabel();
        productsPanel23 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        lb23campo = new javax.swing.JLabel();
        productsPanel31 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        productsPanel32 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        productsPanel33 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnUsuarios = new javax.swing.JMenu();
        mnEmpleados = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnCredenciales = new javax.swing.JMenuItem();
        mnHistorial = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnCajasIndividuales = new javax.swing.JMenuItem();
        menuConfiguracion = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        mnCaja = new javax.swing.JMenuItem();
        mnCajasTarjeta = new javax.swing.JMenuItem();
        mnCajasBanco = new javax.swing.JMenuItem();
        mnCajasIVAdebito = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        mnAperturaCaja = new javax.swing.JMenuItem();
        mnCierre = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnCliente = new javax.swing.JMenuItem();
        mnCobranza = new javax.swing.JMenuItem();
        mnVentas = new javax.swing.JMenuItem();
        mnAnularFacturas = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        mnInventario = new javax.swing.JMenu();
        mnControldeProveedores = new javax.swing.JMenuItem();
        mnControldeProductos = new javax.swing.JMenuItem();
        menuIngresoMercaderias = new javax.swing.JMenuItem();
        menuBajas = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        mnInformeCliente = new javax.swing.JMenuItem();
        mnDetallesCliente = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        mnInformeProductos = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        mnQuienesCompraron = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mnSalir = new javax.swing.JMenu();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(227, 254, 224));

        jPanel5.setBackground(new java.awt.Color(227, 254, 224));
        jPanel5.setLayout(new java.awt.GridLayout(0, 1, 0, 5));

        panelCaja.setBackground(new java.awt.Color(180, 42, 42));
        panelCaja.setPreferredSize(new java.awt.Dimension(300, 40));

        lbCaja.setBackground(new java.awt.Color(204, 204, 0));
        lbCaja.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbCaja.setForeground(new java.awt.Color(255, 255, 255));
        lbCaja.setText("CAJA");
        lbCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCajaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCajaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbCajaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbCajaMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panelCajaLayout = new javax.swing.GroupLayout(panelCaja);
        panelCaja.setLayout(panelCajaLayout);
        panelCajaLayout.setHorizontalGroup(
            panelCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCajaLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(lbCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelCajaLayout.setVerticalGroup(
            panelCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbCaja, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(panelCaja);

        panelTarjeta.setBackground(new java.awt.Color(180, 42, 42));
        panelTarjeta.setPreferredSize(new java.awt.Dimension(300, 40));

        lbTarjeta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTarjeta.setForeground(new java.awt.Color(255, 255, 255));
        lbTarjeta.setText("TARJETA");
        lbTarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbTarjetaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbTarjetaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbTarjetaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbTarjetaMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panelTarjetaLayout = new javax.swing.GroupLayout(panelTarjeta);
        panelTarjeta.setLayout(panelTarjetaLayout);
        panelTarjetaLayout.setHorizontalGroup(
            panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTarjetaLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(lbTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelTarjetaLayout.setVerticalGroup(
            panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(panelTarjeta);

        panelBanco.setBackground(new java.awt.Color(180, 42, 42));
        panelBanco.setPreferredSize(new java.awt.Dimension(300, 40));

        lbBanco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbBanco.setForeground(new java.awt.Color(255, 255, 255));
        lbBanco.setText("BANCO");
        lbBanco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbBancoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbBancoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbBancoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbBancoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panelBancoLayout = new javax.swing.GroupLayout(panelBanco);
        panelBanco.setLayout(panelBancoLayout);
        panelBancoLayout.setHorizontalGroup(
            panelBancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBancoLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(lbBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBancoLayout.setVerticalGroup(
            panelBancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbBanco, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(panelBanco);

        panelAcreedores.setBackground(new java.awt.Color(180, 42, 42));
        panelAcreedores.setPreferredSize(new java.awt.Dimension(300, 40));

        lbAcreedores.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbAcreedores.setForeground(new java.awt.Color(255, 255, 255));
        lbAcreedores.setText("ACREEDORES");
        lbAcreedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbAcreedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbAcreedoresMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbAcreedoresMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbAcreedoresMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panelAcreedoresLayout = new javax.swing.GroupLayout(panelAcreedores);
        panelAcreedores.setLayout(panelAcreedoresLayout);
        panelAcreedoresLayout.setHorizontalGroup(
            panelAcreedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAcreedoresLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(lbAcreedores, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelAcreedoresLayout.setVerticalGroup(
            panelAcreedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAcreedores, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(panelAcreedores);

        panelCreditos.setBackground(new java.awt.Color(180, 42, 42));
        panelCreditos.setPreferredSize(new java.awt.Dimension(300, 40));

        lbCreditos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbCreditos.setForeground(new java.awt.Color(255, 255, 255));
        lbCreditos.setText("CREDITOS");
        lbCreditos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCreditosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCreditosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbCreditosMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelCreditosLayout = new javax.swing.GroupLayout(panelCreditos);
        panelCreditos.setLayout(panelCreditosLayout);
        panelCreditosLayout.setHorizontalGroup(
            panelCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCreditosLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(lbCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelCreditosLayout.setVerticalGroup(
            panelCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbCreditos, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(panelCreditos);

        panelInformes.setBackground(new java.awt.Color(180, 42, 42));
        panelInformes.setPreferredSize(new java.awt.Dimension(300, 40));

        lbInformes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbInformes.setForeground(new java.awt.Color(255, 255, 255));
        lbInformes.setText("INFORMES");
        lbInformes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbInformesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbInformesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbInformesMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelInformesLayout = new javax.swing.GroupLayout(panelInformes);
        panelInformes.setLayout(panelInformesLayout);
        panelInformesLayout.setHorizontalGroup(
            panelInformesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformesLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(lbInformes, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelInformesLayout.setVerticalGroup(
            panelInformesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbInformes, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(panelInformes);

        panelInventario.setBackground(new java.awt.Color(180, 42, 42));
        panelInventario.setPreferredSize(new java.awt.Dimension(300, 40));

        lbInventario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbInventario.setForeground(new java.awt.Color(255, 255, 255));
        lbInventario.setText("PRODUCTOS");
        lbInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbInventarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbInventarioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbInventarioMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelInventarioLayout = new javax.swing.GroupLayout(panelInventario);
        panelInventario.setLayout(panelInventarioLayout);
        panelInventarioLayout.setHorizontalGroup(
            panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInventarioLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(lbInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelInventarioLayout.setVerticalGroup(
            panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbInventario, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(panelInventario);

        panelVentas.setBackground(new java.awt.Color(180, 42, 42));
        panelVentas.setPreferredSize(new java.awt.Dimension(300, 40));

        lbVentas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbVentas.setForeground(new java.awt.Color(255, 255, 255));
        lbVentas.setText("VENTAS");
        lbVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbVentasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbVentasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbVentasMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelVentasLayout = new javax.swing.GroupLayout(panelVentas);
        panelVentas.setLayout(panelVentasLayout);
        panelVentasLayout.setHorizontalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentasLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(lbVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelVentasLayout.setVerticalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(panelVentas);

        panelClientes.setBackground(new java.awt.Color(180, 42, 42));
        panelClientes.setPreferredSize(new java.awt.Dimension(300, 40));

        lbClientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbClientes.setForeground(new java.awt.Color(255, 255, 255));
        lbClientes.setText("CLIENTES");
        lbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbClientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbClientesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbClientesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbClientesMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClientesLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(lbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(panelClientes);

        panelPedidos.setBackground(new java.awt.Color(180, 42, 42));
        panelPedidos.setPreferredSize(new java.awt.Dimension(300, 40));

        lbPedidos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbPedidos.setForeground(new java.awt.Color(255, 255, 255));
        lbPedidos.setText("PEDIDOS");
        lbPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPedidosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbPedidosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbPedidosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbPedidosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbPedidosMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panelPedidosLayout = new javax.swing.GroupLayout(panelPedidos);
        panelPedidos.setLayout(panelPedidosLayout);
        panelPedidosLayout.setHorizontalGroup(
            panelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPedidosLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(lbPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelPedidosLayout.setVerticalGroup(
            panelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(236, 77, 77));

        jLabel3.setFont(new java.awt.Font("Perpetua Titling MT", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("MiEMPRE");

        jPanel4.setBackground(new java.awt.Color(236, 77, 77));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbNombre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbNombre.setText("Nombre:");

        lbRango.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbRango.setText("Rango:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRango, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbRango)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 435, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dashBoardPanel.setBackground(new java.awt.Color(227, 254, 224));
        dashBoardPanel.setLayout(new java.awt.GridLayout(3, 3, 5, 5));

        productsPanel11.setBackground(new java.awt.Color(153, 0, 0));
        productsPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        panel11cabecera.setBackground(new java.awt.Color(12, 75, 96));

        lb11cabecera.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb11cabecera.setForeground(new java.awt.Color(255, 255, 255));
        lb11cabecera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb11cabecera.setText("CAJA");
        lb11cabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb11cabeceraMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb11cabeceraMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panel11cabeceraLayout = new javax.swing.GroupLayout(panel11cabecera);
        panel11cabecera.setLayout(panel11cabeceraLayout);
        panel11cabeceraLayout.setHorizontalGroup(
            panel11cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11cabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb11cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel11cabeceraLayout.setVerticalGroup(
            panel11cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11cabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb11cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel11campo.setBackground(new java.awt.Color(153, 0, 0));

        lb11campo.setBackground(new java.awt.Color(255, 153, 204));
        lb11campo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb11campo.setForeground(new java.awt.Color(204, 204, 204));
        lb11campo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb11campo.setText("0");
        lb11campo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb11campoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panel11campoLayout = new javax.swing.GroupLayout(panel11campo);
        panel11campo.setLayout(panel11campoLayout);
        panel11campoLayout.setHorizontalGroup(
            panel11campoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11campoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb11campo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel11campoLayout.setVerticalGroup(
            panel11campoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11campoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb11campo, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout productsPanel11Layout = new javax.swing.GroupLayout(productsPanel11);
        productsPanel11.setLayout(productsPanel11Layout);
        productsPanel11Layout.setHorizontalGroup(
            productsPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel11cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel11campo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        productsPanel11Layout.setVerticalGroup(
            productsPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanel11Layout.createSequentialGroup()
                .addComponent(panel11cabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panel11campo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        dashBoardPanel.add(productsPanel11);

        productsPanel12.setBackground(new java.awt.Color(204, 204, 204));
        productsPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        panel12cabecera.setBackground(new java.awt.Color(12, 75, 96));

        lb12cabecera.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb12cabecera.setForeground(new java.awt.Color(255, 255, 255));
        lb12cabecera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb12cabecera.setText("BANCO");
        lb12cabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb12cabeceraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel12cabeceraLayout = new javax.swing.GroupLayout(panel12cabecera);
        panel12cabecera.setLayout(panel12cabeceraLayout);
        panel12cabeceraLayout.setHorizontalGroup(
            panel12cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12cabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb12cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel12cabeceraLayout.setVerticalGroup(
            panel12cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12cabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb12cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel12campo.setBackground(new java.awt.Color(153, 153, 153));

        lb12campo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb12campo.setForeground(new java.awt.Color(255, 255, 255));
        lb12campo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb12campo.setText("0");
        lb12campo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb12campoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel12campoLayout = new javax.swing.GroupLayout(panel12campo);
        panel12campo.setLayout(panel12campoLayout);
        panel12campoLayout.setHorizontalGroup(
            panel12campoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12campoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb12campo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel12campoLayout.setVerticalGroup(
            panel12campoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12campoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb12campo, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout productsPanel12Layout = new javax.swing.GroupLayout(productsPanel12);
        productsPanel12.setLayout(productsPanel12Layout);
        productsPanel12Layout.setHorizontalGroup(
            productsPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel12cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel12campo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        productsPanel12Layout.setVerticalGroup(
            productsPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanel12Layout.createSequentialGroup()
                .addComponent(panel12cabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panel12campo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        dashBoardPanel.add(productsPanel12);

        productsPanel13.setBackground(new java.awt.Color(153, 153, 0));
        productsPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        panel13cabecera.setBackground(new java.awt.Color(12, 75, 96));

        lb13cabecera.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb13cabecera.setForeground(new java.awt.Color(255, 255, 255));
        lb13cabecera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb13cabecera.setText("TARJETA");
        lb13cabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb13cabeceraMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panel13cabeceraLayout = new javax.swing.GroupLayout(panel13cabecera);
        panel13cabecera.setLayout(panel13cabeceraLayout);
        panel13cabeceraLayout.setHorizontalGroup(
            panel13cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel13cabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb13cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel13cabeceraLayout.setVerticalGroup(
            panel13cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel13cabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb13cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel13campo.setBackground(new java.awt.Color(153, 153, 0));

        lb13campo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb13campo.setForeground(new java.awt.Color(255, 255, 255));
        lb13campo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb13campo.setText("0");
        lb13campo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb13campoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panel13campoLayout = new javax.swing.GroupLayout(panel13campo);
        panel13campo.setLayout(panel13campoLayout);
        panel13campoLayout.setHorizontalGroup(
            panel13campoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel13campoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb13campo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel13campoLayout.setVerticalGroup(
            panel13campoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel13campoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb13campo, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout productsPanel13Layout = new javax.swing.GroupLayout(productsPanel13);
        productsPanel13.setLayout(productsPanel13Layout);
        productsPanel13Layout.setHorizontalGroup(
            productsPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel13cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel13campo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        productsPanel13Layout.setVerticalGroup(
            productsPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanel13Layout.createSequentialGroup()
                .addComponent(panel13cabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panel13campo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        dashBoardPanel.add(productsPanel13);

        productsPanel21.setBackground(new java.awt.Color(0, 51, 255));
        productsPanel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel12.setBackground(new java.awt.Color(12, 75, 96));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("IVA CREDITO");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(0, 51, 255));

        lb21campo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb21campo.setForeground(new java.awt.Color(255, 255, 255));
        lb21campo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb21campo.setText("0");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb21campo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb21campo, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout productsPanel21Layout = new javax.swing.GroupLayout(productsPanel21);
        productsPanel21.setLayout(productsPanel21Layout);
        productsPanel21Layout.setHorizontalGroup(
            productsPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        productsPanel21Layout.setVerticalGroup(
            productsPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanel21Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        dashBoardPanel.add(productsPanel21);

        productsPanel22.setBackground(new java.awt.Color(153, 153, 153));
        productsPanel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel14.setBackground(new java.awt.Color(12, 75, 96));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("IVA DEBITO");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(102, 102, 102));

        lb22campo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb22campo.setForeground(new java.awt.Color(255, 255, 255));
        lb22campo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb22campo.setText("0");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb22campo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb22campo, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout productsPanel22Layout = new javax.swing.GroupLayout(productsPanel22);
        productsPanel22.setLayout(productsPanel22Layout);
        productsPanel22Layout.setHorizontalGroup(
            productsPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        productsPanel22Layout.setVerticalGroup(
            productsPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanel22Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        dashBoardPanel.add(productsPanel22);

        productsPanel23.setBackground(new java.awt.Color(246, 89, 102));
        productsPanel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel16.setBackground(new java.awt.Color(12, 75, 96));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("IVA a Pagar");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(0, 153, 51));

        lb23campo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb23campo.setForeground(new java.awt.Color(255, 255, 255));
        lb23campo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb23campo.setText("0");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb23campo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb23campo, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout productsPanel23Layout = new javax.swing.GroupLayout(productsPanel23);
        productsPanel23.setLayout(productsPanel23Layout);
        productsPanel23Layout.setHorizontalGroup(
            productsPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        productsPanel23Layout.setVerticalGroup(
            productsPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanel23Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        dashBoardPanel.add(productsPanel23);

        productsPanel31.setBackground(new java.awt.Color(246, 89, 102));
        productsPanel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel18.setBackground(new java.awt.Color(12, 75, 96));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("CUSTOMERS");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(246, 89, 102));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("15451");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout productsPanel31Layout = new javax.swing.GroupLayout(productsPanel31);
        productsPanel31.setLayout(productsPanel31Layout);
        productsPanel31Layout.setHorizontalGroup(
            productsPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        productsPanel31Layout.setVerticalGroup(
            productsPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanel31Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        dashBoardPanel.add(productsPanel31);

        productsPanel32.setBackground(new java.awt.Color(246, 89, 102));
        productsPanel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel20.setBackground(new java.awt.Color(12, 75, 96));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("CUSTOMERS");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(246, 89, 102));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("15451");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout productsPanel32Layout = new javax.swing.GroupLayout(productsPanel32);
        productsPanel32.setLayout(productsPanel32Layout);
        productsPanel32Layout.setHorizontalGroup(
            productsPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(productsPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        productsPanel32Layout.setVerticalGroup(
            productsPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanel32Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        dashBoardPanel.add(productsPanel32);

        productsPanel33.setBackground(new java.awt.Color(246, 89, 102));
        productsPanel33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel22.setBackground(new java.awt.Color(12, 75, 96));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("CUSTOMERS");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(246, 89, 102));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("15451");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout productsPanel33Layout = new javax.swing.GroupLayout(productsPanel33);
        productsPanel33.setLayout(productsPanel33Layout);
        productsPanel33Layout.setHorizontalGroup(
            productsPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        productsPanel33Layout.setVerticalGroup(
            productsPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanel33Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        dashBoardPanel.add(productsPanel33);

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-menu-correo.png"))); // NOI18N
        jLabel1.setText("Correo: uverodevpy@gmail.com");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-menu-whatsapp.png"))); // NOI18N
        jLabel2.setText("Celular: (0993)326313");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mnUsuarios.setText("Administrar");

        mnEmpleados.setText("Empleados");
        mnEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnEmpleadosActionPerformed(evt);
            }
        });
        mnUsuarios.add(mnEmpleados);

        jMenu3.setText("Usuarios");

        mnCredenciales.setText("Credenciales");
        mnCredenciales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCredencialesActionPerformed(evt);
            }
        });
        jMenu3.add(mnCredenciales);

        mnHistorial.setText("Historial");
        mnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnHistorialActionPerformed(evt);
            }
        });
        jMenu3.add(mnHistorial);

        mnUsuarios.add(jMenu3);

        jMenu1.setText("Caja");

        mnCajasIndividuales.setText("Cajas Individuales");
        mnCajasIndividuales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCajasIndividualesActionPerformed(evt);
            }
        });
        jMenu1.add(mnCajasIndividuales);

        mnUsuarios.add(jMenu1);

        menuConfiguracion.setText("Configuración");
        menuConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConfiguracionActionPerformed(evt);
            }
        });
        mnUsuarios.add(menuConfiguracion);

        jMenuBar1.add(mnUsuarios);

        jMenu6.setText("Cajas");

        mnCaja.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mnCaja.setText("Caja Individual");
        mnCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCajaActionPerformed(evt);
            }
        });
        jMenu6.add(mnCaja);

        mnCajasTarjeta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        mnCajasTarjeta.setText("Tarjeta");
        mnCajasTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCajasTarjetaActionPerformed(evt);
            }
        });
        jMenu6.add(mnCajasTarjeta);

        mnCajasBanco.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        mnCajasBanco.setText("Banco");
        mnCajasBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCajasBancoActionPerformed(evt);
            }
        });
        jMenu6.add(mnCajasBanco);

        mnCajasIVAdebito.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        mnCajasIVAdebito.setText("IVA");
        mnCajasIVAdebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCajasIVAdebitoActionPerformed(evt);
            }
        });
        jMenu6.add(mnCajasIVAdebito);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem1.setText("Transferir a Administración");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem1);

        mnAperturaCaja.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        mnAperturaCaja.setText("Apertura de Cajas");
        mnAperturaCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAperturaCajaActionPerformed(evt);
            }
        });
        jMenu6.add(mnAperturaCaja);

        mnCierre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        mnCierre.setText("Cierre de Cajas");
        mnCierre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCierreActionPerformed(evt);
            }
        });
        jMenu6.add(mnCierre);

        jMenuBar1.add(jMenu6);

        jMenu2.setText("Facturación");

        mnCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mnCliente.setText("Clientes");
        mnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnClienteActionPerformed(evt);
            }
        });
        jMenu2.add(mnCliente);

        mnCobranza.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mnCobranza.setText("Cobranzas");
        mnCobranza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCobranzaActionPerformed(evt);
            }
        });
        jMenu2.add(mnCobranza);

        mnVentas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        mnVentas.setText("Ventas");
        mnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnVentasActionPerformed(evt);
            }
        });
        jMenu2.add(mnVentas);

        mnAnularFacturas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        mnAnularFacturas.setText("Facturas");
        mnAnularFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAnularFacturasActionPerformed(evt);
            }
        });
        jMenu2.add(mnAnularFacturas);

        jMenuItem6.setText("Compras");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        mnInventario.setText("Inventario");

        mnControldeProveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mnControldeProveedores.setText("Proveedores");
        mnControldeProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnControldeProveedoresActionPerformed(evt);
            }
        });
        mnInventario.add(mnControldeProveedores);

        mnControldeProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mnControldeProductos.setText("Productos");
        mnControldeProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnControldeProductosActionPerformed(evt);
            }
        });
        mnInventario.add(mnControldeProductos);

        menuIngresoMercaderias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        menuIngresoMercaderias.setText("Ingreso de Mercaderias");
        menuIngresoMercaderias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIngresoMercaderiasActionPerformed(evt);
            }
        });
        mnInventario.add(menuIngresoMercaderias);

        menuBajas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        menuBajas.setText("Baja de Productos");
        menuBajas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBajasActionPerformed(evt);
            }
        });
        mnInventario.add(menuBajas);

        jMenuBar1.add(mnInventario);

        jMenu4.setText("Informe");

        jMenu7.setText("Clientes");

        mnInformeCliente.setText("Quienes compran más");
        mnInformeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnInformeClienteActionPerformed(evt);
            }
        });
        jMenu7.add(mnInformeCliente);

        mnDetallesCliente.setText("Detalles de Cliente");
        mnDetallesCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDetallesClienteActionPerformed(evt);
            }
        });
        jMenu7.add(mnDetallesCliente);

        jMenu4.add(jMenu7);

        jMenu8.setText("Productos");

        mnInformeProductos.setText("Productos Vendidos");
        mnInformeProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnInformeProductosActionPerformed(evt);
            }
        });
        jMenu8.add(mnInformeProductos);

        jMenuItem3.setText("Altas y Bajas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem3);

        mnQuienesCompraron.setText("Quienes compraron");
        mnQuienesCompraron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnQuienesCompraronActionPerformed(evt);
            }
        });
        jMenu8.add(mnQuienesCompraron);

        jMenu4.add(jMenu8);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Reiniciar");
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        mnSalir.setText("Salir");
        mnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mnSalirMousePressed(evt);
            }
        });
        mnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSalirActionPerformed(evt);
            }
        });
        jMenuBar1.add(mnSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(dashBoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dashBoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbCajaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCajaMouseEntered
        panelCaja.setBackground(rojoClaro);
    }//GEN-LAST:event_lbCajaMouseEntered

    private void lbCajaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCajaMouseExited
        panelCaja.setBackground(rojoNormal);
    }//GEN-LAST:event_lbCajaMouseExited

    private void lbCajaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCajaMousePressed
        panelCaja.setBackground(gris);
        iniciarCaja();
    }//GEN-LAST:event_lbCajaMousePressed

    private void lbCajaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCajaMouseReleased
        panelCaja.setBackground(rojoClaro);
    }//GEN-LAST:event_lbCajaMouseReleased

    private void lbBancoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBancoMouseEntered
        panelBanco.setBackground(rojoClaro);
    }//GEN-LAST:event_lbBancoMouseEntered

    private void lbBancoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBancoMouseExited
        panelBanco.setBackground(rojoNormal);
    }//GEN-LAST:event_lbBancoMouseExited

    private void lbBancoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBancoMousePressed
        iniciarBanco();
    }//GEN-LAST:event_lbBancoMousePressed

    private void lbBancoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBancoMouseReleased
        if (inicio.userObj.getRango().equals("Administrador")) {
            panelBanco.setBackground(rojoClaro);
        }
    }//GEN-LAST:event_lbBancoMouseReleased

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            cargarCampos();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowActivated

    private void lbAcreedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAcreedoresMouseEntered
        panelAcreedores.setBackground(rojoClaro);
    }//GEN-LAST:event_lbAcreedoresMouseEntered

    private void lbAcreedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAcreedoresMouseExited
        panelAcreedores.setBackground(rojoNormal);
    }//GEN-LAST:event_lbAcreedoresMouseExited

    private void lbAcreedoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAcreedoresMousePressed
        panelAcreedores.setBackground(gris);
        main.jAcreedores.setVisible(true);
        main.jAcreedores.setLocationRelativeTo(null);
    }//GEN-LAST:event_lbAcreedoresMousePressed

    private void lbAcreedoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAcreedoresMouseReleased
        panelAcreedores.setBackground(rojoClaro);
    }//GEN-LAST:event_lbAcreedoresMouseReleased

    private void lbCreditosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCreditosMouseEntered
        panelCreditos.setBackground(rojoClaro);
    }//GEN-LAST:event_lbCreditosMouseEntered

    private void lbCreditosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCreditosMouseExited
        panelCreditos.setBackground(rojoNormal);
    }//GEN-LAST:event_lbCreditosMouseExited

    private void lbCreditosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCreditosMousePressed
        panelCreditos.setBackground(gris);
        iniciarDeudas();
    }//GEN-LAST:event_lbCreditosMousePressed

    private void lbPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPedidosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbPedidosMouseClicked

    private void lbPedidosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPedidosMouseEntered
        panelPedidos.setBackground(rojoClaro);
    }//GEN-LAST:event_lbPedidosMouseEntered

    private void lbPedidosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPedidosMouseExited
        panelPedidos.setBackground(rojoNormal);
    }//GEN-LAST:event_lbPedidosMouseExited

    private void lbPedidosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPedidosMousePressed
        iniciarPedidos();
        panelPedidos.setBackground(gris);
    }//GEN-LAST:event_lbPedidosMousePressed

    private void lbPedidosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPedidosMouseReleased
        panelPedidos.setBackground(rojoClaro);
    }//GEN-LAST:event_lbPedidosMouseReleased

    private void lbInventarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbInventarioMouseEntered
        panelInventario.setBackground(rojoClaro);
    }//GEN-LAST:event_lbInventarioMouseEntered

    private void lbInventarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbInventarioMouseExited
        panelInventario.setBackground(rojoNormal);
    }//GEN-LAST:event_lbInventarioMouseExited

    private void lbInventarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbInventarioMousePressed
        iniciarControldeProductos();
    }//GEN-LAST:event_lbInventarioMousePressed

    private void lb13cabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb13cabeceraMousePressed

    }//GEN-LAST:event_lb13cabeceraMousePressed

    private void lb13campoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb13campoMousePressed

    }//GEN-LAST:event_lb13campoMousePressed

    private void lb11cabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb11cabeceraMousePressed

    }//GEN-LAST:event_lb11cabeceraMousePressed

    private void lb11campoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb11campoMousePressed

    }//GEN-LAST:event_lb11campoMousePressed

    private void lb12cabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb12cabeceraMouseClicked

    }//GEN-LAST:event_lb12cabeceraMouseClicked

    private void lb12campoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb12campoMouseClicked

    }//GEN-LAST:event_lb12campoMouseClicked

    private void lbInformesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbInformesMouseEntered
        panelInformes.setBackground(rojoClaro);
    }//GEN-LAST:event_lbInformesMouseEntered

    private void lbInformesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbInformesMouseExited
        panelInformes.setBackground(rojoNormal);
    }//GEN-LAST:event_lbInformesMouseExited

    private void lbInformesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbInformesMousePressed
        panelInformes.setBackground(gris);
        try {
            iniciarPanelInformes();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_lbInformesMousePressed

    private void lb11cabeceraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb11cabeceraMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb11cabeceraMouseExited

    private void mnCredencialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCredencialesActionPerformed
        iniciarControldeUsuarios();
    }//GEN-LAST:event_mnCredencialesActionPerformed

    private void mnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnHistorialActionPerformed
        iniciarHistorialdeUsuarios();
    }//GEN-LAST:event_mnHistorialActionPerformed

    private void mnControldeProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnControldeProductosActionPerformed
        iniciarControldeProductos();
    }//GEN-LAST:event_mnControldeProductosActionPerformed

    private void mnControldeProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnControldeProveedoresActionPerformed
        iniciarControldeProveedores();
    }//GEN-LAST:event_mnControldeProveedoresActionPerformed

    private void lbVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbVentasMouseEntered
        panelVentas.setBackground(rojoClaro);
    }//GEN-LAST:event_lbVentasMouseEntered

    private void lbVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbVentasMouseExited
        panelVentas.setBackground(rojoNormal);
    }//GEN-LAST:event_lbVentasMouseExited

    private void lbVentasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbVentasMousePressed
        iniciarVentas();
    }//GEN-LAST:event_lbVentasMousePressed

    private void mnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnClienteActionPerformed
        iniciarClientes();
    }//GEN-LAST:event_mnClienteActionPerformed

    private void mnCobranzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCobranzaActionPerformed
        iniciarCobranzas();
    }//GEN-LAST:event_mnCobranzaActionPerformed

    private void lbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbClientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbClientesMouseClicked

    private void lbClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbClientesMouseEntered
        panelClientes.setBackground(rojoClaro);
    }//GEN-LAST:event_lbClientesMouseEntered

    private void lbClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbClientesMouseExited
        panelClientes.setBackground(rojoNormal);
    }//GEN-LAST:event_lbClientesMouseExited

    private void lbClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbClientesMousePressed
        iniciarClientes();
    }//GEN-LAST:event_lbClientesMousePressed

    private void lbClientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbClientesMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbClientesMouseReleased

    private void mnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnVentasActionPerformed
        iniciarVentas();
    }//GEN-LAST:event_mnVentasActionPerformed

    private void mnInformeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnInformeClienteActionPerformed
        inicio.gReportes.clientesCompradores();
    }//GEN-LAST:event_mnInformeClienteActionPerformed

    private void mnInformeProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnInformeProductosActionPerformed
        inicio.gReportes.productosComprados();
    }//GEN-LAST:event_mnInformeProductosActionPerformed

    private void mnCajasIVAdebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCajasIVAdebitoActionPerformed
        try {
            iniciariIvaDebito();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_mnCajasIVAdebitoActionPerformed

    private void mnCajasIndividualesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCajasIndividualesActionPerformed
        inicio.gReportes.cajasIndividuales();
    }//GEN-LAST:event_mnCajasIndividualesActionPerformed

    private void mnCierreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCierreActionPerformed
        iniciarCierreCaja();
    }//GEN-LAST:event_mnCierreActionPerformed

    private void mnAperturaCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAperturaCajaActionPerformed
        iniciarAperturaCaja();
    }//GEN-LAST:event_mnAperturaCajaActionPerformed

    private void mnCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCajaActionPerformed
        iniciarCaja();
    }//GEN-LAST:event_mnCajaActionPerformed

    private void mnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnSalirActionPerformed

    private void mnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnSalirMousePressed

    }//GEN-LAST:event_mnSalirMousePressed

    private void mnDetallesClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDetallesClienteActionPerformed
        Jbuscar jBancous = new Jbuscar("historialCliente");
        jBancous.setVisible(true);
        jBancous.setLocationRelativeTo(null);
    }//GEN-LAST:event_mnDetallesClienteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        iniciarTranferir();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void lbTarjetaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTarjetaMouseEntered
        panelTarjeta.setBackground(rojoClaro);
    }//GEN-LAST:event_lbTarjetaMouseEntered

    private void lbTarjetaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTarjetaMouseExited
        panelTarjeta.setBackground(rojoNormal);
    }//GEN-LAST:event_lbTarjetaMouseExited

    private void lbTarjetaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTarjetaMousePressed
        iniciarTarjeta();
    }//GEN-LAST:event_lbTarjetaMousePressed

    private void lbTarjetaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTarjetaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbTarjetaMouseReleased

    private void mnCajasBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCajasBancoActionPerformed
        iniciarBanco();
    }//GEN-LAST:event_mnCajasBancoActionPerformed

    private void mnCajasTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCajasTarjetaActionPerformed
        iniciarTarjeta();
    }//GEN-LAST:event_mnCajasTarjetaActionPerformed

    private void mnEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnEmpleadosActionPerformed
        iniciarEmpleados();
    }//GEN-LAST:event_mnEmpleadosActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Jbuscar jBuscar = new Jbuscar("informeProductoAB");
        jBuscar.setVisible(true);
        jBuscar.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void mnQuienesCompraronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnQuienesCompraronActionPerformed
        Jbuscar jBuscar = new Jbuscar("quienesCompran");
        jBuscar.setVisible(true);
        jBuscar.setLocationRelativeTo(null);
    }//GEN-LAST:event_mnQuienesCompraronActionPerformed

    private void mnAnularFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAnularFacturasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnAnularFacturasActionPerformed

    private void menuIngresoMercaderiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIngresoMercaderiasActionPerformed
        iniciarLoteProductos();
    }//GEN-LAST:event_menuIngresoMercaderiasActionPerformed

    private void menuBajasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBajasActionPerformed
        iniciarBajasProductos();
    }//GEN-LAST:event_menuBajasActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        iniciarCompras();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void menuConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConfiguracionActionPerformed
        iniciarConfig();
    }//GEN-LAST:event_menuConfiguracionActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        try {
            inicio.restartApplication();
        } catch (IOException | URISyntaxException e) {
        }
    }//GEN-LAST:event_jMenu5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel dashBoardPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lb11cabecera;
    private javax.swing.JLabel lb11campo;
    private javax.swing.JLabel lb12cabecera;
    private javax.swing.JLabel lb12campo;
    private javax.swing.JLabel lb13cabecera;
    private javax.swing.JLabel lb13campo;
    private javax.swing.JLabel lb21campo;
    private javax.swing.JLabel lb22campo;
    private javax.swing.JLabel lb23campo;
    private javax.swing.JLabel lbAcreedores;
    private javax.swing.JLabel lbBanco;
    private javax.swing.JLabel lbCaja;
    private javax.swing.JLabel lbClientes;
    private javax.swing.JLabel lbCreditos;
    private javax.swing.JLabel lbInformes;
    private javax.swing.JLabel lbInventario;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbPedidos;
    private javax.swing.JLabel lbRango;
    private javax.swing.JLabel lbTarjeta;
    private javax.swing.JLabel lbVentas;
    private javax.swing.JMenuItem menuBajas;
    private javax.swing.JMenuItem menuConfiguracion;
    private javax.swing.JMenuItem menuIngresoMercaderias;
    private javax.swing.JMenuItem mnAnularFacturas;
    private javax.swing.JMenuItem mnAperturaCaja;
    private javax.swing.JMenuItem mnCaja;
    private javax.swing.JMenuItem mnCajasBanco;
    private javax.swing.JMenuItem mnCajasIVAdebito;
    private javax.swing.JMenuItem mnCajasIndividuales;
    private javax.swing.JMenuItem mnCajasTarjeta;
    private javax.swing.JMenuItem mnCierre;
    private javax.swing.JMenuItem mnCliente;
    private javax.swing.JMenuItem mnCobranza;
    private javax.swing.JMenuItem mnControldeProductos;
    private javax.swing.JMenuItem mnControldeProveedores;
    private javax.swing.JMenuItem mnCredenciales;
    private javax.swing.JMenuItem mnDetallesCliente;
    private javax.swing.JMenuItem mnEmpleados;
    private javax.swing.JMenuItem mnHistorial;
    private javax.swing.JMenuItem mnInformeCliente;
    private javax.swing.JMenuItem mnInformeProductos;
    private javax.swing.JMenu mnInventario;
    private javax.swing.JMenuItem mnQuienesCompraron;
    private javax.swing.JMenu mnSalir;
    private javax.swing.JMenu mnUsuarios;
    private javax.swing.JMenuItem mnVentas;
    private javax.swing.JPanel panel11cabecera;
    private javax.swing.JPanel panel11campo;
    private javax.swing.JPanel panel12cabecera;
    private javax.swing.JPanel panel12campo;
    private javax.swing.JPanel panel13cabecera;
    private javax.swing.JPanel panel13campo;
    private javax.swing.JPanel panelAcreedores;
    private javax.swing.JPanel panelBanco;
    private javax.swing.JPanel panelCaja;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelCreditos;
    private javax.swing.JPanel panelInformes;
    private javax.swing.JPanel panelInventario;
    private javax.swing.JPanel panelPedidos;
    private javax.swing.JPanel panelTarjeta;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JPanel productsPanel11;
    private javax.swing.JPanel productsPanel12;
    private javax.swing.JPanel productsPanel13;
    private javax.swing.JPanel productsPanel21;
    private javax.swing.JPanel productsPanel22;
    private javax.swing.JPanel productsPanel23;
    private javax.swing.JPanel productsPanel31;
    private javax.swing.JPanel productsPanel32;
    private javax.swing.JPanel productsPanel33;
    // End of variables declaration//GEN-END:variables

    public void iniciarCierreCaja() {
        if (inicio.userObj.getRango().equals("Operador")) {
            if (cierreAnterior) {
                if (!cierreCaja && aperturaCaja) {
                    JCajasCierre jcc = new JCajasCierre(inicio.fechaYMD);
                    jcc.setVisible(true);
                    jcc.setLocationRelativeTo(null);
                }
            } else {
                comprobarAperturaCierre();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Un administrador no puede abrir ni cerrar cajas", "Apertura y Cierre", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void iniciarAperturaCaja() {
        if (inicio.userObj.getRango().equals("Operador")) {
            if (cierreAnterior && !cierreCaja && !aperturaCaja) {
                JCajasApertura jca = new JCajasApertura();
                jca.setVisible(true);
                jca.setLocationRelativeTo(null);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Un administrador no puede abrir ni cerrar cajas", "Apertura y Cierre", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void comprobarAperturaCierre() {
        if (inicio.userObj.getRango().equals("Operador")) {
            int lastc = main.balanceDAO.cierresArray.size();
            int lasta = main.balanceDAO.aperturasArray.size();

            Balance cierreA = main.balanceDAO.cierresArray.get(lastc - 1);
            Balance aperturaA = main.balanceDAO.aperturasArray.get(lasta - 1);

            //Cierre anterior
            if (cierreA.getFecha().equals(aperturaA.getFecha())) {
                cierreAnterior = true;
            } else {
                JCajasCierre jcc = new JCajasCierre(aperturaA.getFecha());
                jcc.setVisible(true);
                jcc.setLocationRelativeTo(null);
            }

            for (int i = 0; i < main.balanceDAO.aperturasArray.size(); i++) {
                if (main.balanceDAO.aperturasArray.get(i).getFecha().equals(inicio.fechaYMD)) {
                    aperturaCaja = true;
                }
            }

            for (int i = 0; i < main.balanceDAO.cierresArray.size(); i++) {
                if (main.balanceDAO.cierresArray.get(i).getFecha().equals(inicio.fechaYMD)) {
                    cierreCaja = true;
                }
            }

            if (!aperturaCaja && !cierreCaja && cierreAnterior) {
                int inforTipo = JOptionPane.showOptionDialog(null, "¿Desea realizar la apertura de Cajas del Día de hoy?",
                        "CAJA", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                        new Object[]{"SI", "NO"}, "salir");

                try {
                    if (inforTipo == 0) {
                        iniciarAperturaCaja();
                    }
                } catch (Exception e) {
                }
            } else {
                if (cierreAnterior && !cierreCaja && !aperturaCaja) {
                    JOptionPane.showMessageDialog(null, "Apertura de Caja ya realizada el día de hoy", "CAJAS", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void cargarCampos() {
        try {
            main.jCaja.actualizar();
            lb11campo.setText(inicio.format.format(main.jCaja.bal));
            main.jBanco.actualizar();
            lb12campo.setText(inicio.format.format(main.jBanco.bal));
            main.jTarjeta.actualizar();
            lb13campo.setText(inicio.format.format(main.jTarjeta.bal));

            main.jIvaDebito.actualizar();
            lb21campo.setText(inicio.format.format(main.jIvaDebito.ingresoT));
            lb22campo.setText(inicio.format.format(main.jIvaDebito.egresoT));
            lb23campo.setText(inicio.format.format(main.jIvaDebito.bal));
        } catch (Exception e) {
            System.out.println("vista.Jmenu.cargarCampos() error: "+e.getMessage());
        }
    }

    private void iniciarDeudas() {
        main.jDeudas.setVisible(true);
        main.jDeudas.setLocationRelativeTo(null);
    }

    private void iniciarBajasProductos() {
        jbp = new JproductosBajas();
        jbp.setVisible(true);
        jbp.setLocationRelativeTo(null);
    }

    private void iniciarCaja() {
        main.jCaja.actualizar();
        main.jCaja.setVisible(true);
        main.jCaja.setLocationRelativeTo(null);
    }

    private void iniciarBanco() {
        main.jBanco.actualizar();
        main.jBanco.setVisible(true);
        main.jBanco.setLocationRelativeTo(null);
    }

    private void iniciarTarjeta() {
        main.jTarjeta.actualizar();
        main.jTarjeta.setVisible(true);
        main.jTarjeta.setLocationRelativeTo(null);
    }

    private void iniciarControldeUsuarios() {
        main.jUsuarios.setVisible(true);
        main.jUsuarios.setLocationRelativeTo(null);
    }

    public void iniciarControldeProductos() {
        main.jProductos.setVisible(true);
        main.jProductos.setLocationRelativeTo(null);
    }

    private void iniciarHistorialdeUsuarios() {
        main.jUHistorial.setVisible(true);
        main.jUHistorial.setLocationRelativeTo(null);
    }

    private void iniciarControldeProveedores() {
        main.jProveedores.setVisible(true);
        main.jProveedores.setLocationRelativeTo(null);
    }

    private void iniciarVentas() {
        main.jVentas.setVisible(true);
        main.jVentas.setLocationRelativeTo(null);
    }

    public void iniciarClientes() {
        main.jClientes.setVisible(true);
        main.jClientes.setLocationRelativeTo(null);
    }

    public void iniciarEmpleados() {
        main.jEmpleados.setVisible(true);
        main.jEmpleados.setLocationRelativeTo(null);
    }

    private void iniciarPedidos() {
        main.jPedidos.setVisible(true);
        main.jPedidos.setLocationRelativeTo(null);
    }

    private void iniciarCobranzas() {
        main.jCobranzas.setVisible(true);
        main.jCobranzas.actualizar("Todos", "");
        main.jCobranzas.setLocationRelativeTo(null);
    }

    private void iniciariIvaDebito() {
        main.jIvaDebito.actualizar();
        main.jIvaDebito.setVisible(true);
        main.jIvaDebito.setLocationRelativeTo(null);
    }

    private void iniciarLoteProductos() {
        main.jLoteProductos.actualizar();
        main.jLoteProductos.setVisible(true);
        main.jLoteProductos.setLocationRelativeTo(null);
    }

    private void iniciarCompras() {
        main.jCompras.actualizar();
        main.jCompras.setVisible(true);
        main.jCompras.setLocationRelativeTo(null);
    }

    private void iniciarConfig() {
        main.Jconfig = new Jconfiguracion();
        main.Jconfig.setVisible(true);
        main.Jconfig.setLocationRelativeTo(null);
    }

    private void iniciarTranferir() {
        main.jCaja.actualizar();
        main.jIvaDebito.actualizar();

        if (main.jCaja.bal != 0) {
            Jtransferir jTarjetarans = new Jtransferir();
            jTarjetarans.setVisible(true);
            jTarjetarans.setLocationRelativeTo(null);
        } else {
            JOptionPane.showMessageDialog(null, "El balance debe ser distinto de 0", "Error al Transferir", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void iniciarPanelInformes() {
        main.JpanelInformes = new JinformesPanel();
        main.JpanelInformes.setVisible(true);
        main.JpanelInformes.setLocationRelativeTo(null);
        HiloPanel hp = new HiloPanel();
        hp.start();
    }

    public void iniciarRespaldo() {
        GestorBackups gBackups = new GestorBackups();
        gBackups.start();
    }

}
