package ColorTablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import vista.reportes.JinformesAyC;

public class ColorTablaAyC extends JTable {

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
        Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);

        component.setForeground(new Color(0, 0, 0));
        component.setBackground(new Color(255, 255, 255));

        return component;
    }
}
