package ColorTablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorTablaCobranzas extends JTable {

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {

        Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);

        component.setBackground(new Color(171, 162, 162));

        try {
            if (getValueAt(rowIndex, 6).equals("Anulado") || getValueAt(rowIndex, 6).equals("Cancelado")) {

                component.setBackground(new Color(247, 56, 56));

            } else {

                if (getValueAt(rowIndex, 6).equals("Pagado")|| getValueAt(rowIndex, 6).equals("Pagado Financiado")) {

                    component.setBackground(new Color(113, 194, 61));

                } else {
                    if (getValueAt(rowIndex, 6).equals("Financiado")) {

                        component.setBackground(new Color(0, 102, 204));

                    }
                }
            }
        } catch (Exception e) {
        }

        return component;
    }

}
