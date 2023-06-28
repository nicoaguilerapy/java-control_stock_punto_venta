package ColorTablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorTabla2 extends JTable {

    Integer aux;

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {

        Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);

        component.setBackground(new Color(255, 255, 255));

        try {
            if (!getValueAt(rowIndex, 6).equals("Si")) {
                try {
                    aux = (Integer) getValueAt(rowIndex, 2);
                } catch (Exception e) {
                }

                if (aux > 0) {

                    component.setBackground(new Color(85, 205, 97));

                } else {
                    if (aux < 0) {

                        component.setBackground(new Color(205, 97, 85));

                    } else {
                        if (aux == 0) {

                            component.setBackground(new Color(180, 142, 42));

                        }
                    }

                }

            } else {
                component.setBackground(new Color(180, 142, 42));
            }
        } catch (Exception e) {
            
        }

        return component;
    }

}
