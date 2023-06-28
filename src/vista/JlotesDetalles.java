package vista;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.LotesDetalles;
import modelo.LotesProductos;
import modelo.Productos;
import modelo.balance.Balance;

public class JlotesDetalles extends javax.swing.JFrame {

    Image img;
    ImageIcon img2;

    LotesProductos loteProducto;
    LotesDetalles loteDetalle;
    public Productos prodObj;

    DefaultTableModel datosModel;
    ArrayList<LotesDetalles> loteDetalleArray;

    Integer id = -1, cantidad = 0, costo = 0, subtotal = 0, subiva = 0, filaSeleccionada;
    Integer auxcosto, auxiva, totalauxcosto = 0, totalauxiva = 0;
    String fecha = "";

    public JlotesDetalles(LotesProductos lotProd, boolean editar) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Lotes Detalles -" + inicio.version);

        loteDetalleArray = new ArrayList();
        loteProducto = lotProd;
        System.out.println("vista.Jlotedetalles.<init>()" + loteProducto.toString());

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
        actualizarLoteProducto();
        actualizar();

        hacerEditable(editar);
    }

    public void hacerEditable(boolean editar) {
        btnIngresar.setEnabled(editar);
        btnGuardar.setEnabled(editar);

        btnAgregar.setEnabled(editar);
        btnModificar.setEnabled(editar);
        btnEliminar.setEnabled(editar);
        btnLimpiar.setEnabled(editar);

        btnBuscarProd.setEnabled(editar);
        txtCodigo.setEnabled(editar);
        txtCosto.setEnabled(editar);
        txtCantidad.setEnabled(editar);

        txtFecha.setEnabled(editar);
        txtFacturanum.setEnabled(editar);
        cbProveedor.setEnabled(editar);
    }

    public void limpiarDetalles() {
        txtCodigo.setText("");
        txtCantidad.setText("");
        txtCosto.setText("");
        txtSubIva.setText("");
        txtSubTotal.setText("");
        txtProducto.setText("");
    }

    public void actualizarLoteProducto() {
        txtID.setText("" + loteProducto.getId());
        txtFacturanum.setText("" + loteProducto.getFacturanum());

        fecha = inicio.gFechas.invertir(loteProducto.getFecha());
        txtFecha.setText("" + fecha);
        setIdProveedor(loteProducto.getIdProveedor());
    }

    public void actualizar() {
        totalauxcosto = 0;
        totalauxiva = 0;
        id = -1;
        datosModel = (DefaultTableModel) table.getModel();
        datosModel.setRowCount(0);
        loteDetalleArray = main.lotesDetallesDAO.getLista(" where idLote='" + loteProducto.getId() + "'");

        Object[] fila = new Object[datosModel.getColumnCount()];

        for (int i = 0; i < loteDetalleArray.size(); i++) {

            Productos obj_aux = (Productos) main.productosDAO.mapId.get(loteDetalleArray.get(i).getIdProducto());

            id = loteDetalleArray.get(i).getCosto() * loteDetalleArray.get(i).getId();

            auxcosto = loteDetalleArray.get(i).getCosto() * loteDetalleArray.get(i).getCantidad();
            auxiva = (loteDetalleArray.get(i).getCosto() * loteDetalleArray.get(i).getCantidad()) / 11;

            fila[0] = loteDetalleArray.get(i).getId();
            fila[1] = obj_aux.getCodigo();
            fila[2] = loteDetalleArray.get(i).getCosto();
            fila[3] = loteDetalleArray.get(i).getCantidad();
            fila[4] = auxiva;
            fila[5] = auxcosto;

            totalauxcosto = totalauxcosto + auxcosto;
            totalauxiva = totalauxiva + auxiva;

            datosModel.addRow(fila);
        }

        txtCostoTotal.setText(inicio.format.format(totalauxcosto));
        txtIVAtotal.setText(inicio.format.format(totalauxiva));

        inicio.tools.centrar(table, new int[]{0, 1});
        inicio.tools.derecha(table, new int[]{2, 3, 4, 5});
        inicio.tools.decimalTable(table, 2);
        inicio.tools.decimalTable(table, 4);
        inicio.tools.decimalTable(table, 5);
    }

    private void nuevoProducto() {
        int inforTipo = JOptionPane.showOptionDialog(null, "Codigo no encontrado, ¿desea crear un nuevo producto?",
                "Agregar Lote", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                new Object[]{"SI", "NO"}, "salir");
        try {
            if (inforTipo == 0) {
                main.jProductos.nuevoProducto("" + txtCodigo.getText());
                main.jProductos.setVisible(true);
                main.jProductos.setLocationRelativeTo(null);
            }
        } catch (Exception e) {
        }
    }

    public void buscarProducto() {
        try {
            prodObj = (Productos) main.productosDAO.map.get(txtCodigo.getText());
            if (prodObj.isIlimitado()) {
                JOptionPane.showMessageDialog(null, "No se puede agregar un producto ilimitado", "Agregar Lote", JOptionPane.ERROR_MESSAGE);
            } else {
                txtProducto.setText("" + prodObj.getNombre());
            }
        } catch (Exception e) {
            txtProducto.setText("No Encontrado");
            prodObj = null;
            cantidad = -1;
            costo = -1;
            id = -1;
        }
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtCosto = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSubIva = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        txtProducto = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        btnBuscarProd = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFacturanum = new javax.swing.JTextField();
        txtFecha = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        cbProveedor = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtIVAtotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCostoTotal = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos"));

        txtCodigo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtCantidad.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
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

        jLabel1.setText("Codigo:");

        jLabel2.setText("Cantidad:");

        jLabel3.setText("Costo:");

        jLabel4.setText("SubTotal:");

        txtSubIva.setEditable(false);
        txtSubIva.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSubIva.setText("0");

        jLabel5.setText("SubIva:");

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

        txtProducto.setEditable(false);
        txtProducto.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductoActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBuscarProd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnBuscarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-historial-buscar.png"))); // NOI18N
        btnBuscarProd.setPreferredSize(new java.awt.Dimension(95, 28));
        btnBuscarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtProducto, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(34, 34, 34))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSubIva, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSubIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAgregar)
                        .addComponent(btnEliminar)
                        .addComponent(btnModificar)
                        .addComponent(btnLimpiar))
                    .addComponent(btnBuscarProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
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

        cbProveedor.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Proveedor:");

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
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIngresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                            .addComponent(cbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(30, 30, 30)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Codigo", "Costo", "Cantidad", "IVA Credito", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            table.getColumnModel().getColumn(5).setResizable(false);
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
            .addComponent(jScrollPane1)
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
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
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
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        agregarDetalleLote();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        buscarProducto();
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        txtCodigo.transferFocus();
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

        for (int i = 0; i < loteDetalleArray.size(); i++) {

            if (loteDetalleArray.get(i).getId() == filaSeleccionada) {

                loteDetalle = loteDetalleArray.get(i);
                System.out.println(loteDetalle.toString());

                id = loteDetalle.getId();
                prodObj = (Productos) main.productosDAO.mapId.get(loteDetalle.getIdProducto());

                txtCodigo.setText("" + prodObj.getCodigo());
                txtCosto.setText("" + loteDetalle.getCosto());
                txtCantidad.setText("" + loteDetalle.getCantidad());
                txtProducto.setText("" + prodObj.getNombre());

                subtotal = loteDetalle.getCosto() * loteDetalle.getCantidad();
                subiva = subtotal / 11;

                buscarProducto();

                txtSubTotal.setText(inicio.format.format(subtotal));
                txtSubIva.setText(inicio.format.format(subiva));
            }
        }
    }//GEN-LAST:event_tableMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarDetalleLote();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificarDetalleLote();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarDetalles();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdActionPerformed
        main.jProductos.setVisible(true);
        main.jProductos.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnBuscarProdActionPerformed

    private void txtCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyReleased
        char c = (char) evt.getKeyCode();
        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
            costo = Integer.parseInt(txtCosto.getText());
            subtotal = cantidad * costo;
            subiva = subtotal / 11;

            txtSubTotal.setText("" + (inicio.format.format(subtotal)));
            txtSubIva.setText("" + (inicio.format.format(subiva)));

        } catch (Exception e) {
        }

        if (c == 155) {
            agregarDetalleLote();
        }

    }//GEN-LAST:event_txtCostoKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        modificarLoteProducto("Pendiente");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        modificarLoteProducto("Ingresado");
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        char c = (char) evt.getKeyCode();
        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
            costo = Integer.parseInt(txtCosto.getText());
            subtotal = cantidad * costo;
            subiva = subtotal / 11;

            txtSubTotal.setText("" + (inicio.format.format(subtotal)));
            txtSubIva.setText("" + (inicio.format.format(subiva)));

        } catch (Exception e) {
        }

        if (c == 155) {
            agregarDetalleLote();
        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        buscarProducto();
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        if (loteProducto.getEstado().equals("Oculto")) {
            int inforTipo = JOptionPane.showOptionDialog(null, "¿Qué desea hacer con esta INGRESO MERCADERÍA?",
                    "INGRESO MERCADERÍA?", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                    new Object[]{"INGRESAR", "GUARDAR", "CANCELAR"}, "salir");
            try {
                if (inforTipo == 0) {
                    modificarLoteProducto("Ingresado");
                } else {
                    if (inforTipo == 1) {
                        modificarLoteProducto("Pendiente");
                    } else {
                        if (inforTipo == 3) {
                            modificarLoteProducto("Oculto");
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarProd;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JTextField txtFacturanum;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIVAtotal;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtSubIva;
    private javax.swing.JTextField txtSubTotal;
    // End of variables declaration//GEN-END:variables

    public void agregarDetalleLote() {
        if (prodObj != null && cantidad > 0 && costo > 0) {
            loteDetalle = new LotesDetalles(null, cantidad, costo, loteProducto.getId(), prodObj.getId(), inicio.userObj.getId());
            System.out.println("vista.Jlotedetalles.agregarDetalleLote() " + loteDetalle.toString());
            if (main.lotesDetallesDAO.insertar(loteDetalle)) {
                actualizar();
                limpiarDetalles();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar un Producto", "Agregar Detalle Lote", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificarDetalleLote() {
        if (prodObj != null && cantidad > 0 && costo > 0) {
            loteDetalle = new LotesDetalles(id, cantidad, costo, loteProducto.getId(), prodObj.getId(), inicio.userObj.getId());
            if (main.lotesDetallesDAO.modificar(loteDetalle)) {
                actualizar();
                limpiarDetalles();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar un Producto", "Modificar Detalle Lote", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarDetalleLote() {
        if (id > 0) {
            loteDetalle = new LotesDetalles(id);
            if (main.lotesDetallesDAO.eliminar(loteDetalle)) {
                actualizar();
                limpiarDetalles();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar un Producto", "Agregar Detalle Lote", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificarLoteProducto(String estado) {
        fecha = inicio.gFechas.invertir(txtFecha.getText());

        loteProducto.setEstado(estado);
        loteProducto.setFecha(fecha);
        loteProducto.setFacturanum(txtFacturanum.getText());
        loteProducto.setIdProveedor(getIdProveedor());
        loteProducto.setGastototal(totalauxcosto);
        loteProducto.setIvacredito(totalauxiva);

        if (main.lotesProductosDAO.modificar(loteProducto)) {
            JOptionPane.showMessageDialog(null, "Cambio de Estado a " + estado, "Modicar Lote", JOptionPane.INFORMATION_MESSAGE);
            if (estado.equals("Ingresado")) {
                Balance ivaObj = new Balance(null, "#(" + loteProducto.getId() + ")Compra de Mercaderias Nº: " + loteProducto.getFacturanum(), 0, totalauxiva, fecha, inicio.userObj.getId(), "ivadebito");
                main.balanceDAO.insertar(ivaObj);

                int inforTipo = JOptionPane.showOptionDialog(null, "¿Desea descontar de CAJA este Ingreso?",
                        "Agregar Lote", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                        new Object[]{"SI", "NO"}, "salir");

                try {
                    if (inforTipo == 0) {
                        Balance caja = new Balance(null, "#(" + loteProducto.getId() + ")Compra de Mercaderias Nº: " + loteProducto.getFacturanum(), 0, totalauxcosto, fecha, inicio.userObj.getId(), "caja");
                        main.balanceDAO.insertar(caja);
                    }
                } catch (Exception e) {
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
