/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1.GUI;

import java.util.Locale;
import java.awt.Component;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author duyue_000
 */
public class CurrencyTableCellRenderer extends DefaultTableCellRenderer{
    private static final NumberFormat FORMAT = NumberFormat.getCurrencyInstance(Locale.US);

    @Override
    public final Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        final Component result = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        if (value instanceof Number) {
            setHorizontalAlignment(JLabel.CENTER);
            setText(FORMAT.format(value));
        } else {
            setText("");
        }
        return result;
    }
}
