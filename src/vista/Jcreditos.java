package vista;

import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Creditos;
import modelo.CreditosPagos;
import modelo.CreditosVentas;
import modelo.Ventas;
import modelo.balance.Balance;
import modelo.persona.Clientes;
import modelo.persona.Personas;
import static vista.inicio.licencia;

public class Jcreditos extends javax.swing.JFrame {

    DefaultTableModel tableModelClientes, tableModelDetalles, tableModelVentas;
    private TableRowSorter<TableModel> tableSorter;
    ArrayList<Ventas> arrayVentas;
    ArrayList<CreditosVentas> arrayDeudasVentas;
    ArrayList<CreditosPagos> arrayDeudasPagos;

    String dataTable[][] = {};
    String columnTable[] = {"ID", "CIN", "APELLIDOS", "NOMBRES", "ESTADO", "DEUDA TOTAL"};
    String columnTable1[] = {"ID", "FECHA", "PAGO", "DEUDA"};
    String columnTable2[] = {"ID", "FECHA", "MONTO"};
    boolean bolBotones[] = {false, false, false, false};

    Integer filaSelecVentas = -1, filaSelecDetalles = -1, filaSelecDeudas = -1, sumPagos = 0, sumVentas = 0;
    ;
    Integer id = -1;

    Creditos credObj;
    CreditosVentas deuVenObj;
    CreditosPagos deuPagObj;
    Personas cliObj;
    Ventas venObj;

    public Jcreditos() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logo.png")));
        this.setTitle("Creditos -" + inicio.version);

        txtFecha.setText("" + inicio.gFechas.getFechaActual());

        tableModelClientes = new DefaultTableModel(dataTable, columnTable) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModelDetalles = new DefaultTableModel(dataTable, columnTable1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModelVentas = new DefaultTableModel(dataTable, columnTable2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableClientes.setModel(tableModelClientes);
        tableDetalles.setModel(tableModelDetalles);
        tableVentas.setModel(tableModelVentas);

        inicio.tools.Busqued(tableClientes, tableModelClientes, txtBuscador);
    }

    private void limpiar() {
        filaSelecDetalles = -1;
        filaSelecVentas = -1;
        filaSelecDeudas = -1;
        venObj = null;
        id = -1;
        cliObj = null;
        deuPagObj = null;

        txtFecha.setText("" + inicio.gFechas.getFechaActual());
        txtMonto.setText("0");
    }

    private void actualizar() {
        main.creditosDAO.actualizarLista(" where estado != 'Oculto' ");
        tableModelClientes.setRowCount(0);
        Object[] fila = new Object[tableModelClientes.getColumnCount()];

        for (int i = 0; i < main.creditosDAO.resultadoDeuda.size(); i++) {

            cliObj = (Personas) main.personasDAO.mapClientes.get(main.creditosDAO.resultadoDeuda.get(i).getIdClientes());

            fila[0] = main.creditosDAO.resultadoDeuda.get(i).getId();
            fila[1] = cliObj.getCINRUC();
            fila[2] = cliObj.getApellidos();
            fila[3] = cliObj.getNombres();
            fila[4] = main.creditosDAO.resultadoDeuda.get(i).getEstado();
            fila[5] = 0;
            tableModelClientes.addRow(fila);

        }

        inicio.tools.centrar(tableClientes, new int[]{0, 1, 5});
        inicio.tools.derecha(tableClientes, new int[]{4});
        inicio.tools.izquierda(tableClientes, new int[]{2, 3});
        inicio.tools.decimalTable(tableClientes, 5);

        bolBotones[0] = false;
        bolBotones[1] = false;
        bolBotones[2] = false;
        bolBotones[3] = false;
        botonesEnable(bolBotones);

        cliObj = null;
    }

    private void actualizarDetalles() {
        System.out.println("vista.Jcreditos.actualizarDetalles()");
        sumPagos = 0;
        sumVentas = 0;
        main.creditosVentasDAO.actualizarLista("");
        main.creditosPagosDAO.actualizarLista("");

        tableModelDetalles.setRowCount(0);
        Object[] fila2 = new Object[tableModelDetalles.getColumnCount()];
        arrayDeudasVentas = new ArrayList();
        arrayDeudasPagos = new ArrayList();

        arrayDeudasVentas = main.creditosVentasDAO.getDeudaVentasList(credObj.getId());
        arrayDeudasPagos = main.creditosPagosDAO.getDeudaPagosList(credObj.getId());

        for (int i = 0; i < arrayDeudasVentas.size(); i++) {

            Ventas venta = main.ventasDAO.getVenta(arrayDeudasVentas.get(i).getIdVentas());

            fila2[0] = arrayDeudasVentas.get(i).getId();
            fila2[1] = arrayDeudasVentas.get(i).getFecha();
            fila2[2] = 0;
            fila2[3] = venta.getTotalACobrar();
            sumVentas = sumVentas + venta.getTotalACobrar();
            tableModelDetalles.addRow(fila2);
        }

        for (int i = 0; i < arrayDeudasPagos.size(); i++) {

            fila2[0] = arrayDeudasPagos.get(i).getId();
            fila2[1] = arrayDeudasPagos.get(i).getFecha();
            fila2[2] = arrayDeudasPagos.get(i).getPago();
            fila2[3] = 0;
            sumPagos = sumPagos + arrayDeudasPagos.get(i).getPago();

            tableModelDetalles.addRow(fila2);
        }

        if (arrayDeudasVentas.size() == 0) {
            btnPagar.setEnabled(false);
        } else {
            btnPagar.setEnabled(true);
        }

        inicio.tools.decimalTable(tableDetalles, 2);
        inicio.tools.decimalTable(tableDetalles, 3);

        txtPagado.setText(inicio.format.format(sumPagos));
        txtPendiente.setText(inicio.format.format(sumVentas - sumPagos));
    }

    private void actualizarVentas() {

        tableModelVentas.setRowCount(0);
        Object[] fila3 = new Object[tableModelVentas.getColumnCount()];
        arrayVentas = new ArrayList();

        arrayVentas = main.ventasDAO.listar(" where idclientes='" + cliObj.getId() + "' and estado='Pendiente' ");
        System.out.println("vista.Jdeudas.actualizarVentas() cantidad: " + arrayVentas.size());

        if (arrayVentas.size() == 0) {
            tableVentas.setVisible(false);
        } else {
            tableVentas.setVisible(true);
        }

        for (int i = 0; i < arrayVentas.size(); i++) {
            fila3[0] = arrayVentas.get(i).getId();
            fila3[1] = inicio.gFechas.invertir(arrayVentas.get(i).getFecha());
            fila3[2] = arrayVentas.get(i).getTotalACobrar();

            tableModelVentas.addRow(fila3);
        }

        inicio.tools.centrar(tableVentas, new int[]{1});
        inicio.tools.decimalTable(tableVentas, 2);
    }

    private void botonesEnable(boolean b[]) {
        btnAgregarDeuda.setEnabled(b[0]);
        btnEliminarDeuda.setEnabled(b[1]);
        btnPagar.setEnabled(b[2]);
        btnEliminarPago.setEnabled(b[3]);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        btnOcultarDeuda = new javax.swing.JButton();
        btnNuevaDeuda = new javax.swing.JButton();
        txtBuscador = new javax.swing.JTextField();
        btnCreditoPagado = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        panelDetalles = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDetalles = new javax.swing.JTable();
        txtFecha = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnPagar = new javax.swing.JButton();
        txtPagado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPendiente = new javax.swing.JTextField();
        panelVentas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableVentas = new javax.swing.JTable();
        btnAgregarDeuda = new javax.swing.JButton();
        btnEliminarDeuda = new javax.swing.JButton();
        btnEliminarPago = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes con Deudas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jScrollPane1.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane1.setBorder(null);

        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableClientes);

        btnOcultarDeuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-anulado.png"))); // NOI18N
        btnOcultarDeuda.setText("Ocultar Credito");
        btnOcultarDeuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarDeudaActionPerformed(evt);
            }
        });

        btnNuevaDeuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-agregar.png"))); // NOI18N
        btnNuevaDeuda.setText("Nuevo Credito");
        btnNuevaDeuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaDeudaActionPerformed(evt);
            }
        });

        txtBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscador::::"));

        btnCreditoPagado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-ventas-seleccion.png"))); // NOI18N
        btnCreditoPagado.setText("Credito Pagado");
        btnCreditoPagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditoPagadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnNuevaDeuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreditoPagado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOcultarDeuda))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
            .addComponent(txtBuscador)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreditoPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOcultarDeuda)
                        .addComponent(btnNuevaDeuda)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        panelDetalles.setBackground(new java.awt.Color(153, 153, 153));

        tableDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetallesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableDetalles);

        try {
            txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha:");

        txtMonto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMonto.setText("0");
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Monto");

        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-deudores-pagado2.png"))); // NOI18N
        btnPagar.setText("Pagar");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDetallesLayout = new javax.swing.GroupLayout(panelDetalles);
        panelDetalles.setLayout(panelDetallesLayout);
        panelDetallesLayout.setHorizontalGroup(
            panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelDetallesLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetallesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPagar)))
                .addContainerGap())
        );
        panelDetallesLayout.setVerticalGroup(
            panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtPagado.setEditable(false);
        txtPagado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPagado.setText("0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total Pagado:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Pendiente:");

        txtPendiente.setEditable(false);
        txtPendiente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPendiente.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(panelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        panelVentas.setBackground(new java.awt.Color(153, 153, 153));
        panelVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        tableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVentasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableVentas);

        btnAgregarDeuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-producto-agregar.png"))); // NOI18N
        btnAgregarDeuda.setText("Agregar Deuda");
        btnAgregarDeuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDeudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelVentasLayout = new javax.swing.GroupLayout(panelVentas);
        panelVentas.setLayout(panelVentasLayout);
        panelVentasLayout.setHorizontalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAgregarDeuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelVentasLayout.setVerticalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarDeuda)
                .addContainerGap())
        );

        btnEliminarDeuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon-producto-eliminar.png"))); // NOI18N
        btnEliminarDeuda.setText("Eliminar Credito");
        btnEliminarDeuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDeudaActionPerformed(evt);
            }
        });

        btnEliminarPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventana-cerrar.png"))); // NOI18N
        btnEliminarPago.setText("Eliminar Pago");
        btnEliminarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnEliminarDeuda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarDeuda)
                    .addComponent(btnEliminarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevaDeudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaDeudaActionPerformed
        nuevaDeuda();
    }//GEN-LAST:event_btnNuevaDeudaActionPerformed

    private void btnOcultarDeudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarDeudaActionPerformed
        ocultarCredito();
    }//GEN-LAST:event_btnOcultarDeudaActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        actualizar();
    }//GEN-LAST:event_formWindowActivated

    private void tableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientesMouseClicked
        filaSelecDeudas = tableClientes.getSelectedRow();

        filaSelecDeudas = (Integer) tableClientes.getValueAt(filaSelecDeudas, 0);

        credObj = (Creditos) main.creditosDAO.mapDeudas.get(filaSelecDeudas);
        cliObj = (Personas) main.personasDAO.mapClientes.get(credObj.getIdClientes());
        System.out.println("vista.Jdeudas.tableClientesMouseClicked() deuda: " + credObj.toString());
        actualizarDetalles();
        actualizarVentas();

        if (credObj.getEstado().equals("Pendiente")) {
            bolBotones[0] = true;
            bolBotones[1] = false;
            bolBotones[2] = true;
            bolBotones[3] = false;
            botonesEnable(bolBotones);

            txtFecha.setEnabled(true);
            txtMonto.setEnabled(true);

        } else {
            bolBotones[0] = false;
            bolBotones[1] = false;
            bolBotones[2] = false;
            bolBotones[3] = false;

            txtFecha.setEnabled(false);
            txtMonto.setEnabled(false);

            botonesEnable(bolBotones);
        }

    }//GEN-LAST:event_tableClientesMouseClicked

    private void btnAgregarDeudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDeudaActionPerformed
        agregarDeuda();
    }//GEN-LAST:event_btnAgregarDeudaActionPerformed

    private void tableVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVentasMouseClicked
        if (credObj.getEstado().equals("Pendiente")) {
            filaSelecVentas = tableVentas.getSelectedRow();
            filaSelecVentas = (Integer) tableVentas.getValueAt(filaSelecVentas, 0);

            for (int i = 0; i < arrayVentas.size(); i++) {
                if (filaSelecVentas == arrayVentas.get(i).getId()) {
                    venObj = arrayVentas.get(i);
                }
            }
        } else {
            venObj = null;
            bolBotones[0] = false;
            bolBotones[1] = false;
            bolBotones[2] = false;
            bolBotones[3] = false;

            txtFecha.setEnabled(false);
            txtMonto.setEnabled(false);

            botonesEnable(bolBotones);
        }
    }//GEN-LAST:event_tableVentasMouseClicked

    private void tableDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetallesMouseClicked
        if (credObj.getEstado().equals("Pendiente")) {
            filaSelecDetalles = tableDetalles.getSelectedRow();
            String pagoAux = "0", deudaAux = "0";
            try {
                pagoAux = (String) tableDetalles.getValueAt(filaSelecDetalles, 2);
            } catch (Exception e) {
                pagoAux = "-1";
            }
            try {
                deudaAux = (String) tableDetalles.getValueAt(filaSelecDetalles, 3);
            } catch (Exception e) {
                deudaAux = "-1";
            }
            filaSelecDetalles = (Integer) tableDetalles.getValueAt(filaSelecDetalles, 0);

            if (deudaAux.equals("0")) {

                for (int i = 0; i < arrayDeudasPagos.size(); i++) {
                    if (filaSelecDetalles == arrayDeudasPagos.get(i).getId()) {
                        deuPagObj = arrayDeudasPagos.get(i);
                        System.out.println("vista.JcredObjitos.tableDetallesMouseClicked()" + deuPagObj.toString());
                    }
                }

                deuVenObj = null;

            } else {
                for (int i = 0; i < arrayDeudasVentas.size(); i++) {
                    if (filaSelecDetalles == arrayDeudasVentas.get(i).getId()) {
                        deuVenObj = arrayDeudasVentas.get(i);
                        System.out.println("vista.JcredObjitos.tableDetallesMouseClicked() " + deuVenObj.toString());
                    }
                }

                deuPagObj = null;

            }

            if (deuVenObj != null) {
                btnEliminarDeuda.setEnabled(true);
            } else {
                btnEliminarDeuda.setEnabled(false);
            }

            if (deuPagObj != null) {
                btnEliminarPago.setEnabled(true);
            } else {
                btnEliminarPago.setEnabled(false);
            }
        } else {
            deuPagObj = null;
            deuVenObj = null;
            bolBotones[0] = false;
            bolBotones[1] = false;
            bolBotones[2] = false;
            bolBotones[3] = false;

            txtFecha.setEnabled(false);
            txtMonto.setEnabled(false);

            botonesEnable(bolBotones);
        }
    }//GEN-LAST:event_tableDetallesMouseClicked

    private void btnEliminarDeudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDeudaActionPerformed
        eliminarDeuda();
    }//GEN-LAST:event_btnEliminarDeudaActionPerformed

    private void txtMontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyPressed
        try {
            if (!txtMonto.getText().equals("") || !txtMonto.getText().equals("0")) {
                btnPagar.setEnabled(true);
            } else {
                btnPagar.setEnabled(false);
            }

            int c = evt.getKeyCode();

            if (c == 10) {
                agregarPago();
            }
        } catch (Exception e) {
            btnPagar.setEnabled(false);
        }
    }//GEN-LAST:event_txtMontoKeyPressed

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        agregarPago();
    }//GEN-LAST:event_btnPagarActionPerformed

    private void btnEliminarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPagoActionPerformed
        eliminarPago();
    }//GEN-LAST:event_btnEliminarPagoActionPerformed

    private void btnCreditoPagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditoPagadoActionPerformed
        if (sumPagos >= sumVentas) {
            int inforTipo = JOptionPane.showOptionDialog(null, "¿Desea marcar todas las ventas a Credito como Pagado de ID: " + credObj.getId() + "? ",
                    "Cambiar Ventas", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                    new Object[]{"SI", "NO"}, "salir");

            try {
                if (inforTipo == 0) {
                    credObj.setEstado("Finalizado");

                    if (main.creditosDAO.modificar(credObj)) {

                        for (int i = 0; i < arrayDeudasVentas.size(); i++) {
                            Ventas venta = main.ventasDAO.getVenta(arrayDeudasVentas.get(i).getIdVentas());
                            venta.setEstado("Pagado Financiado");
                            main.ventasDAO.modificar(venta);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnCreditoPagadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarDeuda;
    private javax.swing.JButton btnCreditoPagado;
    private javax.swing.JButton btnEliminarDeuda;
    private javax.swing.JButton btnEliminarPago;
    private javax.swing.JButton btnNuevaDeuda;
    private javax.swing.JButton btnOcultarDeuda;
    private javax.swing.JButton btnPagar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelDetalles;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JTable tableClientes;
    private javax.swing.JTable tableDetalles;
    private javax.swing.JTable tableVentas;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtPagado;
    private javax.swing.JTextField txtPendiente;
    // End of variables declaration//GEN-END:variables

    private void nuevaDeuda() {
        Jbuscar jb = new Jbuscar("deudas");
        jb.setLocationRelativeTo(null);
        jb.setVisible(true);
    }

    private void agregarDeuda() {
        try {
            deuVenObj = new CreditosVentas(null, inicio.fechaYMD, credObj.getId(), venObj.getId(), inicio.userObj.getId());
            if (main.creditosVentasDAO.insertar(deuVenObj)) {
                venObj.setEstado("Financiado");
                venObj.setFechaPago(inicio.fechaYMD);
                main.ventasDAO.modificar(venObj);

                limpiar();
                actualizarDetalles();
                tableVentas.setVisible(false);

                bolBotones[0] = true;
                bolBotones[1] = false;
                bolBotones[2] = true;
                bolBotones[3] = false;
                botonesEnable(bolBotones);
                btnAgregarDeuda.setEnabled(false);
            }
        } catch (Exception e) {
        }
    }

    private void ocultarCredito() {
        try {
            credObj.setEstado("Oculto");
            if (main.creditosDAO.modificar(credObj)) {
                actualizar();
                limpiar();

                bolBotones[0] = true;
                bolBotones[1] = false;
                bolBotones[2] = true;
                bolBotones[3] = false;
                botonesEnable(bolBotones);
                btnAgregarDeuda.setEnabled(false);
            }
        } catch (Exception e) {
        }
    }

    private void eliminarDeuda() {
        int inforTipo = JOptionPane.showOptionDialog(null, "¿Desea anular esta Venta Credito?",
                "Eliminar Credito", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                new Object[]{"SI", "NO"}, "salir");

        try {
            if (inforTipo == 0) {
                if (main.creditosVentasDAO.eliminar(deuVenObj)) {
                    venObj = main.ventasDAO.getVenta(deuVenObj.getIdVentas());
                    venObj.setEstado("Pendiente");
                    main.ventasDAO.modificar(venObj);

                    limpiar();
                    actualizarDetalles();
                    tableVentas.setVisible(false);
                }
            }
        } catch (Exception e) {
        }
    }

    private void agregarPago() {

        if (inicio.gAyC.getApertura(inicio.gFechas.invertir(txtFecha.getText())) != null) {
            if (inicio.login.jMenu.aperturaCaja) {
                if (!txtMonto.getText().equals("") || !txtMonto.getText().equals("0")) {
                    Integer monto = 0;
                    try {
                        monto = Integer.parseInt(txtMonto.getText());

                        if (monto > 0) {
                            deuPagObj = new CreditosPagos(null, inicio.gFechas.invertir(txtFecha.getText()), credObj.getId(), monto, inicio.userObj.getId());
                            if (main.creditosPagosDAO.insertar(deuPagObj)) {
                                actualizarDetalles();
                                Balance balObj = new Balance(null, "#(" + credObj.getId() + ")Venta Financiada ID: " + main.creditosPagosDAO.resultadoDeudaPagos.get(0).getId(), Integer.parseInt(txtMonto.getText()), 0, inicio.gFechas.invertir(txtFecha.getText()), inicio.userObj.getId(), "caja");
                                Balance ivaObj = new Balance(null, "#(" + credObj.getId() + ")Venta Financiada ID: " + main.creditosPagosDAO.resultadoDeudaPagos.get(0).getId(), Integer.parseInt(txtMonto.getText()) / 11, 0, inicio.gFechas.invertir(txtFecha.getText()), inicio.userObj.getId(), "ivadebito");
                                main.balanceDAO.insertar(balObj);
                                main.balanceDAO.insertar(ivaObj);

                                limpiar();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El monto debe ser positivo", "Pagar Credito", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El monto debe ser distinto de 0 o vacio", "Pagar Credito", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Realizar apertura de Caja antes", "Pagar Credito", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese una fecha con Apertura de Caja", "Pagar Credito", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPago() {

        int inforTipo = JOptionPane.showOptionDialog(null, "¿Desea anular este Pago?",
                "Eliminar Pago", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                new Object[]{"SI", "NO"}, "salir");

        try {
            if (inforTipo == 0) {
                System.out.println("vista.Jcreditos.eliminarPago() " + deuPagObj.toString());
                if (main.creditosPagosDAO.eliminar(deuPagObj)) {
                    actualizarDetalles();
                    Balance balObj = (Balance) main.balanceDAO.listar(" where concepto='#(" + credObj.getId() + ")Venta Financiada ID: " + deuPagObj.getId() + "'", "caja").get(0);
                    Balance ivaObj = (Balance) main.balanceDAO.listar(" where concepto='#(" + credObj.getId() + ")Venta Financiada ID: " + deuPagObj.getId() + "'", "ivadebito").get(0);
                    main.balanceDAO.eliminar(balObj, "caja");
                    main.balanceDAO.eliminar(ivaObj, "ivadebito");

                    limpiar();
                }
            }
        } catch (Exception e) {
        }
    }

}
