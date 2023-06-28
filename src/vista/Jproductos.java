package vista;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Productos;
import modelo.VentasDetalles;
import org.apache.commons.io.FilenameUtils;

public class Jproductos extends javax.swing.JFrame {

    Productos prodObj = null;
    VentasDetalles detVen = null;
    ArrayList<VentasDetalles> list;

    private DefaultTableModel datosProductos;
    private TableRowSorter<TableModel> sorter;

    FileInputStream fi = null;
    File archivo = null;
    File archivo2 = new File("");
    FileOutputStream fo = null;

    String nombre, codigo, fecha;
    Integer id = -1, cantidad = -1, proveedor = -1, iva = -1, idImagen = -1;
    public String concepto = "";
    Integer valor1 = 0, valor2 = 0, valor3 = 0, costo = -1;

    int filaSelecionada;
    String url = "", extension = "";

    public Jproductos() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Productos -" + inicio.version);
        limpiar();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        datosProductos = (DefaultTableModel) tableProductos.getModel();
        inicio.tools.Busqued(tableProductos, datosProductos, txtBuscador);

        sorter = new TableRowSorter<>(datosProductos);
        tableProductos.setRowSorter(sorter);
        tableProductos.getSelectionModel().setSelectionInterval(0, 0);
    }

    public void nuevoProducto(String codigoaux) {
        actualizar();
        limpiar();
        txtCodigo.setText(codigoaux);
    }

    private void limpiar() {
        txtNombre.setText("");
        txtCodigo.setText("");
        txtURL.setText("Preview");
        txtValor1.setText("0");
        txtValor2.setText("0");
        txtValor3.setText("0");

        lbIconProducto.setIcon(null);
        cbIva.setEnabled(false);

        lbIva.setForeground(new java.awt.Color(153, 153, 153));

        id = -1;
        cantidad = -1;
        proveedor = -1;
        costo = -1;
        iva = -1;

    }

    public void actualizar() {

        id = -1;
        main.productosDAO.actualizarLista("");
        main.lotesDetallesDAO.actualizarSuma();
        main.detVentasDAO.actualizarSuma();
        datosProductos = (DefaultTableModel) tableProductos.getModel();
        datosProductos.setRowCount(0);

        Object[] fila = new Object[datosProductos.getColumnCount()];
        for (int i = 0; i < main.productosDAO.resultado.size(); i++) {

            fila[0] = main.productosDAO.resultado.get(i).getId();
            fila[1] = main.productosDAO.resultado.get(i).getCodigo();
            fila[2] = main.productosDAO.resultado.get(i).getNombre();
            fila[3] = main.productosDAO.resultado.get(i).getValor1();
            fila[4] = main.productosDAO.resultado.get(i).getValor2();
            fila[5] = main.productosDAO.resultado.get(i).getValor3();

            if (main.productosDAO.resultado.get(i).isIlimitado()) {
                fila[6] = "Ilimitado";
            } else {
                fila[6] = cantidadProd(main.productosDAO.resultado.get(i).getId());
            }

            datosProductos.addRow(fila);
        }

        inicio.tools.centrar(tableProductos, new int[]{0, 1});
        inicio.tools.izquierda(tableProductos, new int[]{2});
        inicio.tools.derecha(tableProductos, new int[]{3, 4, 5, 6});
        inicio.tools.decimalTable(tableProductos, 3);
        inicio.tools.decimalTable(tableProductos, 4);
        inicio.tools.decimalTable(tableProductos, 5);

    }

    private int comprobarCampos() {
        int sw = 1;
        String message = "", aux;

        if (txtCodigo.getText().equals("")) {
            message = "Codigo,  ";
            sw = 0;
        }
        if (txtNombre.getText().equals("")) {
            aux = message;
            message = aux + "Nombre,  ";
            sw = 0;
        }
        if (txtValor1.getText().equals("")) {
            aux = message;
            message = aux + "Precio A,  ";
            sw = 0;
        }
        if (txtValor2.getText().equals("")) {
            aux = message;
            message = aux + "Precio B,  ";
            sw = 0;
        }
        if (txtValor3.getText().equals("")) {
            aux = message;
            message = aux + "Precio C,  ";
            sw = 0;
        }

        if (sw == 0) {
            JOptionPane.showMessageDialog(null, message + "*Son campos Obigatorios*", "Faltan campos", JOptionPane.ERROR_MESSAGE);
        }
        return sw;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtValor1 = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txtValor2 = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        txtValor3 = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        btnExaminar = new javax.swing.JButton();
        txtURL = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        lbIconProducto = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lbIva = new javax.swing.JLabel();
        cbIva = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        cbIlimitado = new javax.swing.JCheckBox();
        lbIva1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        txtBuscador = new javax.swing.JTextField();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Codigo:");

        txtCodigo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCodigo.setBorder(null);
        txtCodigo.setOpaque(false);

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        txtNombre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNombre.setBorder(null);
        txtNombre.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Nombre:");

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Precio A:");

        txtValor1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtValor1.setText("0");
        txtValor1.setBorder(null);
        txtValor1.setOpaque(false);
        txtValor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValor1ActionPerformed(evt);
            }
        });
        txtValor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValor1KeyTyped(evt);
            }
        });

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Precio B:");

        txtValor2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtValor2.setText("0");
        txtValor2.setBorder(null);
        txtValor2.setOpaque(false);
        txtValor2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValor2KeyTyped(evt);
            }
        });

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

        txtValor3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtValor3.setText("0");
        txtValor3.setBorder(null);
        txtValor3.setOpaque(false);
        txtValor3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValor3KeyTyped(evt);
            }
        });

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Precio C:");

        btnExaminar.setBackground(new java.awt.Color(255, 255, 255));
        btnExaminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnExaminar.setText("Examinar");
        btnExaminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnExaminarMousePressed(evt);
            }
        });
        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarActionPerformed(evt);
            }
        });

        txtURL.setEditable(false);
        txtURL.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtURL.setText("Preview");
        txtURL.setBorder(null);
        txtURL.setOpaque(false);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Imagen"));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbIconProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lbIconProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAgregar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-producto-agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAgregarMousePressed(evt);
            }
        });
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-producto-modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnModificarMousePressed(evt);
            }
        });
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-producto-eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lbIva.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbIva.setText("Iva %:");

        cbIva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbIva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "5" }));
        cbIva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbIvaMouseClicked(evt);
            }
        });
        cbIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIvaActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-eliminar.png"))); // NOI18N
        btnLimpiar.setToolTipText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        cbIlimitado.setBackground(new java.awt.Color(204, 204, 204));
        cbIlimitado.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        lbIva1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbIva1.setText("Ilimtado:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExaminar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtURL, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(jSeparator1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtCodigo)
                                        .addGap(476, 476, 476))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator7)
                                    .addComponent(txtNombre))
                                .addGap(262, 262, 262))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtValor3, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(129, 129, 129))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtValor2)
                                        .addGap(155, 155, 155)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbIva1)
                                    .addComponent(lbIva))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbIlimitado)
                                    .addComponent(cbIva, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(235, 235, 235)))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbIva1)
                                    .addComponent(cbIlimitado))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbIva)
                                    .addComponent(cbIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtValor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtValor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtValor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExaminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar)
                        .addComponent(btnModificar)
                        .addComponent(btnAgregar)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Codigo", "Nombre", "Precio A", "Precio B", "Precio C", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProductos);
        if (tableProductos.getColumnModel().getColumnCount() > 0) {
            tableProductos.getColumnModel().getColumn(0).setResizable(false);
            tableProductos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableProductos.getColumnModel().getColumn(1).setResizable(false);
            tableProductos.getColumnModel().getColumn(1).setPreferredWidth(50);
            tableProductos.getColumnModel().getColumn(2).setResizable(false);
            tableProductos.getColumnModel().getColumn(2).setPreferredWidth(300);
            tableProductos.getColumnModel().getColumn(3).setResizable(false);
            tableProductos.getColumnModel().getColumn(4).setResizable(false);
            tableProductos.getColumnModel().getColumn(5).setResizable(false);
            tableProductos.getColumnModel().getColumn(6).setResizable(false);
        }

        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador::::"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(txtBuscador))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductosMouseClicked
        //cbProveedor.setEnabled(false);

        filaSelecionada = tableProductos.getSelectedRow();

        filaSelecionada = (Integer) tableProductos.getValueAt(filaSelecionada, 0);

        for (int i = 0; i < main.productosDAO.resultado.size(); i++) {

            if (filaSelecionada == main.productosDAO.resultado.get(i).getId()) {

                System.out.println(main.productosDAO.resultado.get(i).toString());

                id = main.productosDAO.resultado.get(i).getId();
                idImagen = main.productosDAO.resultado.get(i).getId();
                txtCodigo.setText(main.productosDAO.resultado.get(i).getCodigo());
                txtNombre.setText("" + main.productosDAO.resultado.get(i).getNombre());
                txtValor1.setText("" + main.productosDAO.resultado.get(i).getValor1());
                txtValor2.setText("" + main.productosDAO.resultado.get(i).getValor2());
                txtValor3.setText("" + main.productosDAO.resultado.get(i).getValor3());
                cbIva.setSelectedItem("" + main.productosDAO.resultado.get(i).getIva());

                if (main.productosDAO.resultado.get(i).isIlimitado()) {
                    cbIlimitado.setSelected(true);
                } else {
                    cbIlimitado.setSelected(false);
                }

                getImagen();

                lbIva.setForeground(new java.awt.Color(0, 0, 0));
                cbIva.setEnabled(true);

            }
        }

        txtURL.setText("Preview");

    }//GEN-LAST:event_tableProductosMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        actualizar();
    }//GEN-LAST:event_formWindowActivated

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cbIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIvaActionPerformed

    private void cbIvaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbIvaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIvaMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (comprobarCampos() == 1) {
            modificar();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnModificarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMousePressed

    }//GEN-LAST:event_btnModificarMousePressed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (comprobarCampos() == 1) {
            agregar();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnAgregarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMousePressed

    }//GEN-LAST:event_btnAgregarMousePressed

    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Buscar una Imagen o Foto");
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            url = fc.getSelectedFile().toString();
            if (url.endsWith("jpg") || url.endsWith("png") || url.endsWith("gif")) {
                extension = FilenameUtils.getExtension(url);
                txtURL.setText(url);
                archivo = new File(fc.getSelectedFile().toString());
                rsscalelabel.RSScaleLabel.setScaleLabel(lbIconProducto, url);
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una imagen .JPG ; .PNG ; .GIF ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnExaminarActionPerformed

    private void btnExaminarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExaminarMousePressed

    }//GEN-LAST:event_btnExaminarMousePressed

    private void txtValor3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValor3KeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            if (c == '.') {

            } else {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtValor3KeyTyped

    private void txtValor2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValor2KeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            if (c == '.') {

            } else {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtValor2KeyTyped

    private void txtValor1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValor1KeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            if (c == '.') {

            } else {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtValor1KeyTyped

    private void txtValor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValor1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExaminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JCheckBox cbIlimitado;
    private javax.swing.JComboBox<String> cbIva;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbIconProducto;
    private javax.swing.JLabel lbIva;
    private javax.swing.JLabel lbIva1;
    private javax.swing.JTable tableProductos;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtURL;
    private javax.swing.JTextField txtValor1;
    private javax.swing.JTextField txtValor2;
    private javax.swing.JTextField txtValor3;
    // End of variables declaration//GEN-END:variables

    private void agregar() {

        try {
            prodObj = (Productos) main.productosDAO.map.get(txtCodigo.getText());
        } catch (Exception e) {
            prodObj = null;
        }

        if (prodObj == null) {
            try {
                valor1 = Integer.parseInt(txtValor1.getText());
                valor2 = Integer.parseInt(txtValor2.getText());
                valor3 = Integer.parseInt(txtValor3.getText());

                if (cbIva.getSelectedIndex() == 0) {
                    iva = 10;

                } else {
                    iva = 5;
                }

                codigo = txtCodigo.getText();
                nombre = txtNombre.getText();
                url = txtURL.getText();

                prodObj = new Productos(null, codigo, nombre, valor1, valor2, valor3, "Activo", cbIlimitado.isSelected(), iva, inicio.userObj.getId());
                System.out.println(prodObj.toString());
                main.productosDAO.insertar(prodObj);
                actualizar();
                try {
                    setImagen("agregar");
                } catch (Exception e) {
                    System.out.println("Error de Imagen: " + e.getMessage());
                }

                limpiar();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error, código duplicado", "Crear Producto", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificar() {
        Integer idAux, sw = 0;
        try {
            prodObj = (Productos) main.productosDAO.map.get(txtCodigo.getText());
            idAux = prodObj.getId();
        } catch (Exception e) {
            prodObj = null;
            idAux = -1;
        }

        if ((idAux == -1) || (prodObj != null && idAux == id)) {
            try {
                valor1 = Integer.parseInt(txtValor1.getText());
                valor2 = Integer.parseInt(txtValor2.getText());
                valor3 = Integer.parseInt(txtValor3.getText());

                if (cbIva.getSelectedIndex() == 0) {
                    iva = 10;

                } else {
                    iva = 5;
                }

                codigo = txtCodigo.getText();
                nombre = txtNombre.getText();
                url = txtURL.getText();

                prodObj = new Productos(id, codigo, nombre, valor1, valor2, valor3, "Activo", cbIlimitado.isSelected(), iva, inicio.userObj.getId());
                System.out.println(prodObj.toString());

                if (main.productosDAO.modificar(prodObj)) {
                    setImagen("modificar");
                    actualizar();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Modificado con Exito", "Modificar Producto", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error, código duplicado", "Crear Producto", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        try {
            if (id > -1) {
                prodObj = new Productos(id);
                main.productosDAO.eliminar(prodObj);
                actualizar();
                limpiar();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public Integer cantidadProd(Integer id) {
        int a = 0;
        int b = 0;
        int c = 0;
        try {
            a = main.lotesDetallesDAO.mapLotesDetalles.get(id);
        } catch (Exception e) {

        }

        try {
            b = main.detVentasDAO.mapDetVentas.get(id);
        } catch (Exception e) {
        }

        try {
            c = main.bajasProductosDAO.mapBajas.get(id);
        } catch (Exception e) {
        }

        //System.out.println("vista.Jproductos.cantidadProd()");
        //System.out.println("a: "+a+" | b: "+b+" | c: "+c);
        return a - b - c;
    }

    private boolean getImagen() {
        lbIconProducto.setIcon(null);
        String url2;

        try {
            url2 = "img/producto" + id + ".png";
            rsscalelabel.RSScaleLabel.setScaleLabel(lbIconProducto, url2);
            return true;
        } catch (Exception e) {
        }

        try {
            url2 = "img/producto" + id + ".jpg";
            rsscalelabel.RSScaleLabel.setScaleLabel(lbIconProducto, url2);
            return true;
        } catch (Exception e) {
        }

        try {
            url2 = "img/producto" + id + ".gif";
            rsscalelabel.RSScaleLabel.setScaleLabel(lbIconProducto, url2);
            return true;
        } catch (Exception e) {
        }

        return false;

    }

    private void setImagen(String texto) {
        int idtemp = id;
        if (texto.equals("agregar")) {
            try {

                idtemp = (int) tableProductos.getValueAt(0, 0);

                inicio.ac.copiar(url, "img/producto" + idtemp + "." + extension);
            } catch (Exception e) {
                System.out.println("vista.Jproductos.setImagen(agregar) error: " + e.getMessage());
            }
        } else {
            if (texto.equals("modificar") && !txtURL.getText().equals("Preview")) {

                try {
                    inicio.ac.eliminar("img/producto" + idImagen + ".png");
                } catch (Exception e) {
                }
                try {
                    inicio.ac.eliminar("img/producto" + idImagen + ".jpg");
                } catch (Exception e) {
                }

                try {
                    inicio.ac.eliminar("img/producto" + idImagen + ".gif");
                } catch (Exception e) {
                }

                try {
                    inicio.ac.copiar(url, "img/producto" + idImagen + "." + extension);
                    idImagen = -1;
                } catch (Exception e) {
                    System.out.println("vista.Jproductos.setImagen(modificar) error: " + e.getMessage());
                }
            }
        }

    }

}
