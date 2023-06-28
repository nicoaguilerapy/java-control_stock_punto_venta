package ColorTablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorTablaInformes extends JTable {

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {

        Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
        component.setBackground(new Color(255, 255, 255));
        component.setForeground(new Color(0, 0, 0));
        String concepto = (String) getValueAt(rowIndex, 1);
        if (concepto.contains("Sumas Totales de ")||concepto.contains("Sumatoria")) {
            component.setBackground(new Color(204, 204, 204));
        } else {
            if (concepto.contains("Diferencia de Sumas Totales")) {
                if (getValueAt(rowIndex, 2).equals("0")) {
                    component.setBackground(new Color(255, 102, 102));
                }
                if (getValueAt(rowIndex, 3).equals("0")) {
                    component.setBackground(new Color(204, 255, 204));
                }
            }
        }

        return component;
    }

}
