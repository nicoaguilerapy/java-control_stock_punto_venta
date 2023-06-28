/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorAcreedores extends JTable {

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {

        Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);

        component.setBackground(new Color(204, 204, 204));

        if (getValueAt(rowIndex, 5).equals("Pendiente")) {

            component.setBackground(new Color(180, 142, 42));

        } else {
            if (getValueAt(rowIndex, 5).equals("Anulado")) {

                component.setBackground(new Color(196, 194, 194));

            } else {
                if (getValueAt(rowIndex, 5).equals("Cancelado")) {

                    component.setBackground(new Color(113, 194, 61));

                }
            }

        }

        return component;
    }

}
