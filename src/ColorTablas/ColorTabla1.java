package ColorTablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorTabla1 extends JTable {

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {

        Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);

        component.setBackground(new Color(180, 142, 42));

        if (getValueAt(rowIndex, 3).equals("Pendiente")) {

            component.setBackground(new Color(180, 142, 42));

        } else {
            if (getValueAt(rowIndex, 3).equals("Cancelado")) {

                component.setBackground(new Color(196, 194, 194));

            } else {
                if (getValueAt(rowIndex, 3).equals("Finalizado")) {

                    component.setBackground(new Color(113, 194, 61));

                }
            }

        }

        return component;
    }

}
