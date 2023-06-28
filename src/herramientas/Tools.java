package herramientas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Tools {

    //buscador de la tabla
    private TableRowSorter<TableModel> sorter;

    public void Busqued(JTable tabla, DefaultTableModel modelo, JTextField cuadro) {

        cuadro.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                sorter = new TableRowSorter<>(modelo);
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + "^" + cuadro.getText()));
                tabla.setRowSorter(sorter);
                tabla.getSelectionModel().setSelectionInterval(0, 0);
            }
        });

    }

    public void LimpiarBusqueda(JTextField busqueda, JTable table) {
        if (!busqueda.getText().isEmpty()) {
            sorter.setRowFilter(null);
        }
    }

    public void centrar(JTable tabla, int columna[]) {
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        for (int columna1 : columna) {
            tabla.getColumnModel().getColumn(columna1).setCellRenderer(modelocentrar);
        }
    }

    public void izquierda(JTable tabla, int columna[]) {
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.LEFT);
        for (int columna1 : columna) {
            tabla.getColumnModel().getColumn(columna1).setCellRenderer(modelocentrar);
        }
    }

    public void derecha(JTable tabla, int columna[]) {
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int columna1 : columna) {
            tabla.getColumnModel().getColumn(columna1).setCellRenderer(modelocentrar);
        }
    }

    public void decimalTable(JTable tabla, int columna) {
        DecimalFormat format = new DecimalFormat("###,###.##");
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if (!tabla.getValueAt(i, columna).equals("")) {
                try {
                    Double valor = Double.parseDouble(tabla.getValueAt(i, columna).toString().replace(".", "").replace(",", "."));
                    tabla.setValueAt(format.format(valor), i, columna);
                } catch (Exception e) {
                    tabla.setValueAt("", i, columna);
                }
            }
        }
    }

}
