package vista;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ComprasDetalles;
import modelo.Compras;
import modelo.Productos;
import modelo.balance.Balance;

public class JcomprasDetalles extends javax.swing.JFrame {

    Image img;
    ImageIcon img2;

    Compras compra;
    ComprasDetalles comprasDet;

    DefaultTableModel datosModel;
    ArrayList<ComprasDetalles> comprasDetArray;

    Integer id = -1, cantidad = 0, costo = 0, subtotal = 0, subiva = 0, filaSeleccionada;
    Integer auxcosto, auxiva, totalauxcosto = 0, totalauxiva = 0;
    String fecha = "";

    public JcomprasDetalles(Compras compr, boolean editar) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Compras -" + inicio.version);

        comprasDetArray = new ArrayList();
        compra = compr;
        System.out.println("vista.Jlotedetalles.<init>()" + compra.toString());

        img = new ImageIcon(getClass().getResource("/imagenes/icon-categorias-agregar.png")).getImage();
        img2 = new ImageIcon(img.getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        btnAgregar.setIcon(img2);

        img = new ImageIcon(getClass().getResource("/imagenes/icon-categorias-editar.png")).getImage();
        img2 = new ImageIcon(img.getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        btnModificar.setIcon(img2);

        img = new ImageIcon(getClass().getResource("/imagenes/icon-categorias-eliminar.png")).getImage();
        img2 = new ImageIcon(img.getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        btnEliminar.setIcon(img2);

        img = new ImageIcon(getClass().getResource("/imagenes/icon-usuario-agregar.png")).getImage();
        img2 = new ImageIcon(img.getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        btnIngresar.setIcon(img2);

        img = new ImageIcon(getClass().getResource("/imagenes/icon-producto-agregar.png")).getImage();
        img2 = new ImageIcon(img.getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        btnGuardar.setIcon(img2);

        img = new ImageIcon(getClass().getResource("/imagenes/icon-deudores-eliminar.png")).getImage();
        img2 = new ImageIcon(img.getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        btnLimpiar.setIcon(img2);

        actualizarCB();
        actualizarComprasDet();
        actualizar();

        hacerEditable(editar);
        setIdProveedor(compra.getIdProveedor());
        cbTipo.setSelectedItem(compra.getTipo());

    }

    public void hacerEditable(boolean editar) {
        btnIngresar.setEnabled(editar);
        btnGuardar.setEnabled(editar);

        btnAgregar.setEnabled(editar);
        btnModificar.setEnabled(editar);
        btnEliminar.setEnabled(editar);
        btnLimpiar.setEnabled(editar);

        txtConcepto.setEnabled(editar);
        txtCosto.setEnabled(editar);
        txtCantidad.setEnabled(editar);

        txtFecha.setEnabled(editar);
        txtFacturanum.setEnabled(editar);

        cbProveedor.setEnabled(editar);
    }

    public void limpiarDetalles() {
        txtCantidad.setText("");
        txtCosto.setText("");
        txtSubTotal.setText("");
        txtConcepto.setText("");
        cantidad = -1;
        costo = -1;
        id = -1;
    }

    public void actualizarComprasDet() {
        txtID.setText("" + compra.getId());
        txtFacturanum.setText("" + compra.getFacturanum());

        fecha = inicio.gFechas.invertir(compra.getFecha());
        txtFecha.setText("" + fecha);
    }

    public void actualizar() {
        totalauxcosto = 0;
        totalauxiva = 0;
        id = -1;
        datosModel = (DefaultTableModel) table.getModel();
        datosModel.setRowCount(0);
        comprasDetArray = main.comprasDetallesDAO.getLista(" where idCompras='" + compra.getId() + "'");

        Object[] fila = new Object[datosModel.getColumnCount()];

        for (int i = 0; i < comprasDetArray.size(); i++) {

            auxcosto = comprasDetArray.get(i).getCosto() * comprasDetArray.get(i).getCantidad();
            auxiva = (comprasDetArray.get(i).getCosto() * comprasDetArray.get(i).getCantidad()) / 11;

            fila[0] = comprasDetArray.get(i).getId();
            fila[1] = comprasDetArray.get(i).getConcepto();
            fila[2] = comprasDetArray.get(i).getCantidad();
            fila[3] = comprasDetArray.get(i).getCosto();
            fila[4] = auxcosto;

            totalauxcosto = totalauxcosto + auxcosto;
            totalauxiva = totalauxiva + auxiva;

            datosModel.addRow(fila);

        }

        txtCostoTotal.setText(inicio.format.format(totalauxcosto));
        txtIVAtotal.setText(inicio.format.format(totalauxiva));

        inicio.tools.centrar(table, new int[]{0, 1});
        inicio.tools.derecha(table, new int[]{2, 3, 4});
        inicio.tools.decimalTable(table, 2);
        inicio.tools.decimalTable(table, 3);
        inicio.tools.decimalTable(table, 4);
    }

    private void actualizarCB() {
        cbProveedor.removeAllItems();

        try {
            for (int i = 0; i < main.proveedoresDAO.resultado.size(); i++) {
                cbProveedor.addItem(main.proveedoresDAO.resultado.get(i).getEmpresaNombre());
            }
        } catch (Exception e) {
            System.out.println("Proveedores vacio");
        }
    }

    private int getIdProveedor() {

        for (int i = 0; i < main.proveedoresDAO.resultado.size(); i++) {
            String aux = main.proveedoresDAO.resultado.get(i).getEmpresaNombre();
            String aux2 = (String) cbProveedor.getSelectedItem();
            if (aux.equals(aux2)) {
                System.out.println(main.proveedoresDAO.resultado.get(i).getId());
                return main.proveedoresDAO.resultado.get(i).getId();
            }
        }
        return -1;
    }

    private void setIdProveedor(int id) {
        String aux = "";
        for (int i = 0; i < main.proveedoresDAO.resultado.size(); i++) {

            if (main.proveedoresDAO.resultado.get(i).getId() == id) {
                aux = main.proveedoresDAO.resultado.get(i).getEmpresaNombre();
                System.out.println("vista.Jlotedetalles.setIdProveedor() aux: " + aux + " Proveedor" + main.proveedoresDAO.resultado.get(i).toString());
            }

            cbProveedor.setSelectedItem(aux);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtCantidad = new javax.swing.JTextField();
        txtCosto = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        txtConcepto = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFacturanum = new javax.swing.JTextField();
        txtFecha = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbProveedor = new javax.swing.JComboBox<>();
        cbTipo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtIVAtotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCostoTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos"));

        txtCantidad.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        txtCosto.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostoActionPerformed(evt);
            }
        });
        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoKeyTyped(evt);
            }
        });

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSubTotal.setText("0");

        jLabel2.setText("Cantidad:");

        jLabel3.setText("Costo:");

        jLabel4.setText("SubTotal:");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        txtConcepto.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConceptoActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel8.setText("Concepto:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar)
                    .addComponent(btnModificar)
                    .addComponent(btnLimpiar)
                    .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Factura"));

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel9.setText("ID:");

        jLabel10.setText("Factura Nº:");

        txtFacturanum.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        try {
            txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setText("Fecha:");

        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel12.setText("Proveedor:");

        cbProveedor.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbTipo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin Especificar", "Insumos", "Gastos Operativos", "Bienes Activos" }));
        cbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoItemStateChanged(evt);
            }
        });

        jLabel13.setText("Tipo de Compra:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFacturanum, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(cbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIngresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFacturanum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnIngresar)
                                .addComponent(btnGuardar)
                                .addComponent(cbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Concepto", "Cantidad", "Costo", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Total IVA Credito:");

        txtIVAtotal.setEditable(false);
        txtIVAtotal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtIVAtotal.setText("0");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Total Costo:");

        txtCostoTotal.setEditable(false);
        txtCostoTotal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCostoTotal.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIVAtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIVAtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarComprasDetalle();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConceptoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConceptoActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed

    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCostoKeyTyped

    private void txtCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostoActionPerformed

    }//GEN-LAST:event_txtCostoActionPerformed

    private void txtCostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyPressed


    }//GEN-LAST:event_txtCostoKeyPressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        filaSeleccionada = table.getSelectedRow();
        filaSeleccionada = (Integer) table.getValueAt(filaSeleccionada, 0);

        for (int i = 0; i < comprasDetArray.size(); i++) {

            if (comprasDetArray.get(i).getId() == filaSeleccionada) {

                comprasDet = comprasDetArray.get(i);
                System.out.println(comprasDet.toString());

                id = comprasDet.getId();

                txtConcepto.setText("" + comprasDet.getConcepto());
                txtCosto.setText("" + comprasDet.getCosto());
                costo = comprasDet.getCosto();
                txtCantidad.setText("" + comprasDet.getCantidad());
                cantidad = comprasDet.getCantidad();

                subtotal = comprasDet.getCosto() * comprasDet.getCantidad();

                txtSubTotal.setText(inicio.format.format(subtotal));

                setIdProveedor(id);
            }
        }
    }//GEN-LAST:event_tableMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarComprasDetalle();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificarComprasDetalle();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarDetalles();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyReleased
        char c = (char) evt.getKeyCode();
        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
            costo = Integer.parseInt(txtCosto.getText());
            subtotal = cantidad * costo;
            subiva = subtotal / 11;

            txtSubTotal.setText("" + (inicio.format.format(subtotal)));
        } catch (Exception e) {
        }
        if (c == 155) {
            agregarComprasDetalle();
        }
    }//GEN-LAST:event_txtCostoKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (compra.getEstado().equals("Ingresado")) {
            modificarCompra("Ingresado", true);
        } else {
            JOptionPane.showMessageDialog(null, "No se puede guardar una compra ANULADA", "Modicar Compra", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        modificarCompra("Ingresado", false);
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        char c = (char) evt.getKeyCode();
        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
            costo = Integer.parseInt(txtCosto.getText());
            subtotal = cantidad * costo;
            subiva = subtotal / 11;

            txtSubTotal.setText("" + (inicio.format.format(subtotal)));
        } catch (Exception e) {
        }
        if (c == 155) {
            agregarComprasDetalle();
        }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (compra.getEstado().equals("Oculto")) {
            int inforTipo = JOptionPane.showOptionDialog(null, "¿Qué desea hacer con esta COMPRA?",
                    "Compras", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                    new Object[]{"INGRESAR", "GUARDAR", "CANCELAR"}, "salir");
            try {
                if (inforTipo == 0) {
                    modificarCompra("Ingresado", false);
                } else {
                    if (inforTipo == 1) {
                        modificarCompra("Pendiente", false);
                    } else {
                        if (inforTipo == 3) {
                            modificarCompra("Oculto", false);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void cbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoItemStateChanged
        btnGuardar.setEnabled(true);
    }//GEN-LAST:event_cbTipoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbProveedor;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtConcepto;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JTextField txtFacturanum;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIVAtotal;
    private javax.swing.JTextField txtSubTotal;
    // End of variables declaration//GEN-END:variables

    private void agregarComprasDetalle() {
        if (!txtConcepto.getText().equals("") && cantidad > 0 && costo > 0) {
            comprasDet = new ComprasDetalles(null, txtConcepto.getText(), cantidad, costo, compra.getId());
            System.out.println("vista.Jlotedetalles.agregarComprasDetalle() " + comprasDet.toString());
            if (main.comprasDetallesDAO.insertar(comprasDet)) {
                actualizar();
                limpiarDetalles();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar un Concepto", "Agregar Detalle Compra", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarComprasDetalle() {
        if (!txtConcepto.getText().equals("") && cantidad > 0 && costo > 0) {
            comprasDet = new ComprasDetalles(id, txtConcepto.getText(), cantidad, costo, compra.getId());
            if (main.comprasDetallesDAO.modificar(comprasDet)) {
                actualizar();
                limpiarDetalles();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar un Concepto", "Modificar Detalle Compra", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarComprasDetalle() {
        if (id > 0) {
            comprasDet = new ComprasDetalles(id);
            if (main.comprasDetallesDAO.eliminar(comprasDet)) {
                actualizar();
                limpiarDetalles();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una Compra", "Eliminar Detalle Lote", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarCompra(String estado, boolean update) {
        fecha = inicio.gFechas.invertir(txtFecha.getText());

        compra.setEstado(estado);
        compra.setFecha(fecha);
        compra.setFacturanum(txtFacturanum.getText());
        compra.setTotal(totalauxcosto);
        compra.setIva(totalauxiva);
        compra.setIdProveedor(getIdProveedor());
        compra.setTipo((String) cbTipo.getSelectedItem());

        if (main.comprasDAO.modificar(compra)) {
            JOptionPane.showMessageDialog(null, "Cambio de Estado a " + estado, "Modicar Compra", JOptionPane.INFORMATION_MESSAGE);
            if (estado.equals("Ingresado")) {
                if (!update) {
                    Balance ivaObj = new Balance(null, "#(" + compra.getId() + ")Gasto en " + compra.getTipo() + " Nº: " + compra.getFacturanum(), 0, totalauxiva, fecha, inicio.userObj.getId(), "ivadebito");
                    main.balanceDAO.insertar(ivaObj);

                    int inforTipo = JOptionPane.showOptionDialog(null, "¿Desea descontar de CAJA esta compra?",
                            "Agregar Compra", JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                            new Object[]{"SI", "NO"}, "salir");
                    try {
                        if (inforTipo == 0) {
                            Balance caja = new Balance(null, "#(" + compra.getId() + ")Gasto en " + compra.getTipo() + " Nº: " + compra.getFacturanum(), 0, totalauxcosto, fecha, inicio.userObj.getId(), "caja");
                            main.balanceDAO.insertar(caja);
                        }
                    } catch (Exception e) {
                    }
                } else {
                    Balance balObj = (Balance) main.balanceDAO.listar(" where concepto like '%#(" + compra.getId() + ")Gasto %'", "caja").get(0);
                    main.balanceDAO.eliminar(balObj, "caja");

                    Balance caja = new Balance(null, "#(" + compra.getId() + ")Gasto en " + compra.getTipo() + " Nº: " + compra.getFacturanum(), 0, totalauxcosto, fecha, inicio.userObj.getId(), "caja");
                    main.balanceDAO.insertar(caja);
                }

                hacerEditable(false);
                this.dispose();
            }
        }

        if (inicio.gAyC.getApertura(fecha) == null) {
            inicio.gAyC.actualizarAyC();
            main.balanceDAO.actualizarCierres();
        }
    }

}
