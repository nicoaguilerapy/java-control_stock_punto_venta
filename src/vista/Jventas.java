package vista;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.VentasDetalles;
import modelo.Productos;
import modelo.Ventas;
import modelo.persona.Personas;

public class Jventas extends javax.swing.JFrame {

    Ventas venta = null;
    VentasDetalles detallesVentas = null;
    ArrayList<Productos> ArrayProductos;
    Productos prodObj = null;
    Personas clienteObj;

    Jbuscar jbc;
    String CINCliente = "", CodProducto = "", NombreProducto = "", facturanum = "", clienteDirec = "", clienteCIN = "";
    public Integer IdCliente = -1, idProducto = -1, total = 0, idVentas = -1, cantidad = -1, cantidadProd = -1, costoProd = -1, provProd = -1, descuento = 0;
    public Integer valor1 = -1, valor2 = -1, valor3 = -1, subtotal = -1, precio = -1, iva = -1, iva5 = -1, iva10 = -1, ivaT = 0;
    String fecha = "", estado = "Pendiente", formaPago = "";

    DefaultTableModel datosDatalles;

    Image img;
    ImageIcon img2;

    private void limpiarCliente() {
        txtCIN.setText("");
        txtNombre.setText("");
        CINCliente = "";
        IdCliente = -1;
        txtNombre.setText("");
        clienteCIN = "";
        clienteDirec = "";
    }

    private void limpiarProducto() {
        txtCodProducto.setText("");
        txtCantidad.setText("");
        txtSubTotal.setText("");
        txtSubTotal.setText("");
        cbPrecio.removeAllItems();
        prodObj = null;
        CodProducto = "";
        NombreProducto = "";
        idProducto = -1;
        costoProd = -1;
        provProd = -1;
        cantidad = -1;
        valor1 = -1;
        valor2 = -1;
        valor3 = -1;
        iva = -1;
    }

    public Jventas() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Ventas -" + inicio.version);

        img = new ImageIcon(getClass().getResource("/imagenes/icon-producto-agregar.png")).getImage();
        img2 = new ImageIcon(img.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        btnAgregar.setIcon(img2);

        img = new ImageIcon(getClass().getResource("/imagenes/icon-producto-eliminar.png")).getImage();
        img2 = new ImageIcon(img.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        btnEliminar.setIcon(img2);

        img = new ImageIcon(getClass().getResource("/imagenes/icon-venta-generarventa.png")).getImage();
        img2 = new ImageIcon(img.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        btnGenerarVenta.setIcon(img2);

        datosDatalles = (DefaultTableModel) tableDetallesVentas.getModel();

        fecha = inicio.gFechas.getFechaActual();
        txtFecha.setText("" + fecha);

        cbPrecio.removeAllItems();
        ArrayProductos = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCIN = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtFecha = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetallesVentas = new javax.swing.JTable();
        btnBuscarProd = new javax.swing.JButton();
        txtCodProducto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbPrecio = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnGenerarVenta = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("CIN:");

        txtCIN.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCINKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCINKeyTyped(evt);
            }
        });

        btnBuscarCliente.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-historial-buscar.png"))); // NOI18N
        btnBuscarCliente.setPreferredSize(new java.awt.Dimension(95, 28));
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Nombre:");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        try {
            txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFecha.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Fecha:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 463, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCIN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))
                    .addComponent(txtNombre)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Control"));

        btnLimpiar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-eliminar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-eliminar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles de Venta"));

        tableDetallesVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "COD", "NOMBRE", "PRECIO", "CANTIDAD", "SUB-TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDetallesVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetallesVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDetallesVentas);
        if (tableDetallesVentas.getColumnModel().getColumnCount() > 0) {
            tableDetallesVentas.getColumnModel().getColumn(0).setResizable(false);
            tableDetallesVentas.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableDetallesVentas.getColumnModel().getColumn(1).setResizable(false);
            tableDetallesVentas.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableDetallesVentas.getColumnModel().getColumn(2).setResizable(false);
            tableDetallesVentas.getColumnModel().getColumn(2).setPreferredWidth(200);
            tableDetallesVentas.getColumnModel().getColumn(3).setResizable(false);
            tableDetallesVentas.getColumnModel().getColumn(4).setResizable(false);
            tableDetallesVentas.getColumnModel().getColumn(4).setPreferredWidth(30);
            tableDetallesVentas.getColumnModel().getColumn(5).setResizable(false);
        }

        btnBuscarProd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnBuscarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-historial-buscar.png"))); // NOI18N
        btnBuscarProd.setPreferredSize(new java.awt.Dimension(95, 28));
        btnBuscarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProdActionPerformed(evt);
            }
        });

        txtCodProducto.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCodProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Cod. Producto:");

        cbPrecio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbPrecio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbPrecioMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cbPrecioMouseExited(evt);
            }
        });
        cbPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPrecioActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Precio:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Cantidad:");

        txtCantidad.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Sub-Total:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Total:");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtTotal.setText("0");

        btnGenerarVenta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-eliminar.png"))); // NOI18N
        btnGenerarVenta.setText("Generar Presupuesto");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Descuento");

        txtDescuento.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtDescuento.setText("0");
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGenerarVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(btnGenerarVenta))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableDetallesVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetallesVentasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDetallesVentasMouseClicked

    private void txtCINKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCINKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCINKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        if (txtCIN.getText().equals("")) {
            jbc = new Jbuscar("cliente");
            jbc.setVisible(true);
            jbc.setLocationRelativeTo(null);
        } else {
            buscarCliente();
        }
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtCINKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCINKeyPressed
        char c = (char) evt.getKeyCode();
        if (c == 10) {
            buscarCliente();
        }
    }//GEN-LAST:event_txtCINKeyPressed

    private void txtCodProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyTyped

    }//GEN-LAST:event_txtCodProductoKeyTyped

    private void btnBuscarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdActionPerformed
        if (txtCodProducto.getText().equals("")) {
            jbc = new Jbuscar("producto");
            jbc.setVisible(true);
            jbc.setLocationRelativeTo(null);
        } else {
            buscarProductoMap();
        }
    }//GEN-LAST:event_btnBuscarProdActionPerformed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        try {
            char c = (char) evt.getKeyCode();

            if (c == 10) {
                buscarProductoMap();
            } else {
                if (c == 155) {
                    comprobarAgregarProductos();
                }
            }
        } catch (Exception e) {
            System.out.println("Error producto");
        }

    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        calcularSubtotal();
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void cbPrecioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbPrecioMouseExited

    }//GEN-LAST:event_cbPrecioMouseExited

    private void cbPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbPrecioMouseClicked
        calcularSubtotal();
    }//GEN-LAST:event_cbPrecioMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        comprobarAgregarProductos();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int row = tableDetallesVentas.getSelectedRow();
        int aux = Integer.parseInt(tableDetallesVentas.getValueAt(row, 5).toString());
        ArrayProductos.remove(row);

        total = total - aux;
        datosDatalles.removeRow(row);
        txtTotal.setText(inicio.format.format(total));
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased

    }//GEN-LAST:event_txtCodProductoKeyReleased

    private void cbPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPrecioActionPerformed
        calcularSubtotal();
    }//GEN-LAST:event_cbPrecioActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        if (total != 0) {
            if (IdCliente > 0) {
                generarPresupuesto();
                limpiarCliente();
                limpiarProducto();
                datosDatalles.setRowCount(0);
                ArrayProductos.clear();
                total = 0;
                txtTotal.setText("");
                txtDescuento.setText("");
                main.datosDAO.actualizarLista("");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccionar un Cliente", "Error al Generar Venta", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El monto total tiene que ser mayor a 0", "Error al Generar Venta", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCliente();
        limpiarProducto();
        total = 0;
        ArrayProductos.clear();
        txtTotal.setText("" + total);
        datosDatalles.setRowCount(0);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtCodProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyPressed
        try {
            char c = (char) evt.getKeyCode();

            if (c == 10) {
                buscarProductoMap();
            } else {
                if (c == 155) {
                    comprobarAgregarProductos();
                }
            }
        } catch (Exception e) {
            System.out.println("Error producto");
        }
    }//GEN-LAST:event_txtCodProductoKeyPressed

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtDescuentoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarProd;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cbPrecio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDetallesVentas;
    public static javax.swing.JTextField txtCIN;
    private javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JFormattedTextField txtFecha;
    public static javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private void buscarCliente() {
        int sw = 0;
        Integer idT = -1;
        CINCliente = txtCIN.getText();
        try {
            idT = Integer.parseInt(CINCliente);
        } catch (NumberFormatException e) {
        }
        for (int i = 0; i < main.personasDAO.resultadoClientes.size(); i++) {

            if (CINCliente.equals(main.personasDAO.resultadoClientes.get(i).getCINRUC()) || idT == main.personasDAO.resultadoClientes.get(i).getId()) {
                IdCliente = main.personasDAO.resultadoClientes.get(i).getId();
                txtNombre.setText(main.personasDAO.resultadoClientes.get(i).getNombres() + " " + main.personasDAO.resultadoClientes.get(i).getApellidos());
                clienteDirec = main.personasDAO.resultadoClientes.get(i).getDireccion() + clienteDirec;
                clienteCIN = main.personasDAO.resultadoClientes.get(i).getCINRUC() + clienteCIN;
                System.out.println("buscarCliente() " + main.personasDAO.resultadoClientes.get(i).getCINRUC());
                sw = 1;
            }
        }

        if (sw == 0) {
            CINCliente = "";
            IdCliente = -1;
            txtNombre.setText("");
            clienteCIN = "";
            clienteDirec = "";
        }

    }

    public void buscarProductoMap() {

        try {
            CodProducto = txtCodProducto.getText();
            prodObj = (Productos) main.productosDAO.map.get(CodProducto);

            cbPrecio.removeAllItems();
            cbPrecio.addItem(inicio.format.format(prodObj.getValor1()));
            cbPrecio.addItem(inicio.format.format(prodObj.getValor2()));
            cbPrecio.addItem(inicio.format.format(prodObj.getValor3()));

            txtCantidad.setText("1");

            calcularSubtotal();
        } catch (Exception e) {
            prodObj = null;
            CodProducto = "";
            NombreProducto = "";
            idProducto = -1;
            costoProd = -1;
            provProd = -1;
            cantidad = -1;
            valor1 = -1;
            valor2 = -1;
            valor3 = -1;
            iva = -1;
        }
    }

    private void agregarProducto() {
        int sw = 0;
        for (int i = 0; i < ArrayProductos.size(); i++) {
            if (prodObj.getId() == ArrayProductos.get(i).getId()) {
                JOptionPane.showMessageDialog(null, "producto Ya agregado", "Error al Agregar Producto", JOptionPane.ERROR_MESSAGE);
                sw = 1;
            }
        }

        if (sw == 0) {
            if (prodObj.isIlimitado()) {
                calcularSubtotal();
                generarDetallesVenta();
                ArrayProductos.add(prodObj);
            } else {
                if (main.jProductos.cantidadProd(prodObj.getId()) >= Integer.parseInt(txtCantidad.getText())) {
                    calcularSubtotal();
                    generarDetallesVenta();
                    ArrayProductos.add(prodObj);
                } else {
                    JOptionPane.showMessageDialog(null, "No existe esa cantidad en Stock", "Error al Agregar Producto", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void calcularSubtotal() {
        try {
            subtotal = (Integer.parseInt(txtCantidad.getText()));

            int sw = cbPrecio.getSelectedIndex();

            switch (sw) {
                case 0:
                    subtotal = subtotal * prodObj.getValor1();
                    break;
                case 1:
                    subtotal = subtotal * prodObj.getValor2();
                    break;
                case 2:
                    subtotal = subtotal * prodObj.getValor3();
                    break;
            }

            txtSubTotal.setText(inicio.format.format(subtotal));
        } catch (Exception e) {
            txtSubTotal.setText("");
        }
    }

    private void generarDetallesVenta() {
        // calcularSubtotal();

        Object[] fila = new Object[datosDatalles.getColumnCount()];

        int sw = cbPrecio.getSelectedIndex();
        fila[0] = prodObj.getId();
        fila[1] = prodObj.getCodigo();
        fila[2] = prodObj.getNombre();

        switch (sw) {
            case 0:
                fila[3] = prodObj.getValor1();
                break;
            case 1:
                fila[3] = prodObj.getValor2();
                break;
            case 2:
                fila[3] = prodObj.getValor3();
                break;
        }

        fila[4] = txtCantidad.getText();
        fila[5] = subtotal;
        datosDatalles.addRow(fila);

        total = total + subtotal;
        txtTotal.setText(inicio.format.format(total));

    }

    private void generarPresupuesto() {
        fecha = txtFecha.getText();
        fecha = inicio.gFechas.invertir(fecha);
        try {
            descuento = Integer.parseInt(txtDescuento.getText());
        } catch (Exception e) {
            descuento = 0;
        }
        venta = new Ventas(null, facturanum, fecha, "", IdCliente, total, estado, formaPago, descuento, inicio.userObj.getId(), 1);
        main.ventasDAO.insertar(venta);
        main.ventasDAO.actualizarLista("");
        idVentas = main.ventasDAO.ventasArray.get(0).getId();
        procesarDetallesVentas();
    }

    private void procesarDetallesVentas() {
        ivaT = 0;
        for (int i = 0; i < datosDatalles.getRowCount(); i++) {

            //insertar Detalles
            idProducto = Integer.parseInt(tableDetallesVentas.getValueAt(i, 0).toString());
            cantidad = Integer.parseInt(tableDetallesVentas.getValueAt(i, 4).toString());
            precio = Integer.parseInt(tableDetallesVentas.getValueAt(i, 3).toString());
            subtotal = precio * cantidad;

            //Calcular Iva
            if (ArrayProductos.get(i).getIva() == 10) {

                iva = precio * cantidad;
                iva10 = iva * 100;
                iva10 = iva - (iva10 / 110);
                iva5 = 0;
            } else {

                iva = precio * cantidad;
                iva5 = iva * 100;
                iva5 = iva - (iva5 / 105);
                iva10 = 0;
            }

            ivaT = ivaT + (iva5 + iva10);

            detallesVentas = new VentasDetalles(null, idVentas, idProducto, cantidad, iva5, iva10, precio, subtotal);
            main.detVentasDAO.insertar(detallesVentas);
        }

    }

    private void comprobarAgregarProductos() {
        if (!txtSubTotal.getText().equals("")) {
            if (ArrayProductos.size() < 9) {
                agregarProducto();
                limpiarProducto();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes ingresar una cantidad", "Error de Cantidad", JOptionPane.ERROR_MESSAGE);
        }
    }

}
