/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1.GUI;

import java.sql.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.Vector; 
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jdesktop.swingx.table.DatePickerCellEditor;
/**
 *
 * @author duyue_000
 */
@SuppressWarnings("unchecked")
public class Asset extends javax.swing.JFrame {
    /**
     * Creates new form Asset
     */
Connection conn = null;
PreparedStatement pst = null;
Statement stUpdate = null;
Statement stDelete = null;
ResultSet rs = null;    

    public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
    
    public Asset() {
        initComponents();
        conn = MySqlConnect.ConnectDB();
        String Sql = "Select * from [Record] where UserName = ? and AssetType = ? Order by Date DESC ";
        
        try{
            pst = conn.prepareStatement(Sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setString(1,UserName.getUserName());
            pst.setString(2,AssetType.getAssetType());
            rs = pst.executeQuery();  

            if(!rs.first()){
                lblTransaction.setText("No transactions found!");
            }
            else{
                ResultSetMetaData metaData = rs.getMetaData();

                // names of columns
                Vector<String> columnNames = new Vector<String>();
                int columnCount = metaData.getColumnCount();
                for (int column = 1; column <= columnCount; column++) {
                    columnNames.add(metaData.getColumnName(column));
                }

                // data of the table
                Vector<Vector<Object>> data = new Vector<Vector<Object>>();
                Vector<Vector<Object>> dataCopy = new Vector<Vector<Object>>();
                do{
                    Vector<Object> vector = new Vector<Object>();
                    Vector<Object> vectorCopy = new Vector<Object>();
                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                        Object object = rs.getObject(columnIndex);
                        Object objectCopy = DeepClone.copy(object);
                        vector.add(object);
                        vectorCopy.add(objectCopy);
                    }
                    data.add(vector);
                    dataCopy.add(vectorCopy);
                }                           
                
                while (rs.next()) ;
                DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnNames);
                DefaultTableModel defaultTableModelCopy = new DefaultTableModel(dataCopy,columnNames);
                jTable.getTableHeader().setReorderingAllowed(false);
                jTable.setModel(defaultTableModel);
                jTable.getColumnModel().getColumn(0).setWidth(0);
                jTable.getColumnModel().getColumn(0).setMinWidth(0);
                jTable.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable.getColumnModel().getColumn(1).setWidth(0);
                jTable.getColumnModel().getColumn(1).setMinWidth(0);
                jTable.getColumnModel().getColumn(1).setMaxWidth(0);
                jTable.getColumnModel().getColumn(3).setWidth(0);
                jTable.getColumnModel().getColumn(3).setMinWidth(0);
                jTable.getColumnModel().getColumn(3).setMaxWidth(0);
                for(int i=0;i<jTable.getRowCount();i++){
                    if(jTable.getValueAt(i, 2).toString().equals("1"))
                        jTable.setValueAt("Income", i, 2);
                    if(jTable.getValueAt(i, 2).toString().equals("0"))
                        jTable.setValueAt("Expense", i, 2);
                }
                
                TableColumn incomeColumn = jTable.getColumnModel().getColumn(2);;
                comboBox.addItem("Income");
                comboBox.addItem("Expense");
                incomeColumn.setCellEditor(new DefaultCellEditor(comboBox));
                TableColumn expenseTypeColumn = jTable.getColumnModel().getColumn(8);
                comboBoxExpenseType.addItem("Surplus");
                comboBoxExpenseType.addItem("Food");
                comboBoxExpenseType.addItem("Clothes");
                comboBoxExpenseType.addItem("Transportation");
                comboBoxExpenseType.addItem("Groceries");
                comboBoxExpenseType.addItem("Education");
                comboBoxExpenseType.addItem("Health");
                comboBoxExpenseType.addItem("Housing");
                comboBoxExpenseType.addItem("Other Expense Types");
                expenseTypeColumn.setCellEditor(new DefaultCellEditor(comboBoxExpenseType)); 
                TableColumn amountColumn = jTable.getColumnModel().getColumn(5);
                amountColumn.setCellRenderer(new CurrencyTableCellRenderer());
                jScrollPane.setViewportView(jTable);
                
//            JOptionPane.showMessageDialog(null,new JScrollPane(new JTable(ListTableModel.createModelFromResultSet(rs))));        
//            JOptionPane.showMessageDialog(null,new JScrollPane(new JTable(new DefaultTableModel(data, columnNames))));                 
                
                jTable.getModel().addTableModelListener(new TableModelListener(){
                    public void tableChanged(TableModelEvent e){
                        int row = e.getFirstRow();
                        int column = e.getColumn();
                        JTable jTableCopy = new JTable();
                        jTableCopy.setModel(defaultTableModelCopy);
                        for(int i=0;i<jTableCopy.getRowCount();i++){
                            if(jTableCopy.getValueAt(i, 2).toString().equals("1"))
                                jTableCopy.setValueAt("Income", i, 2);
                            if(jTableCopy.getValueAt(i, 2).toString().equals("0"))
                                jTableCopy.setValueAt("Expense", i, 2);
                        }
                        if(((String)jTable.getValueAt(row, 2)).equals("Expense") && ((String)jTable.getValueAt(row, 8)).equals("Surplus")){
                            //JOptionPane.showMessageDialog(null, "Please choose the logical expense type");
                            if(((String)jTableCopy.getValueAt(row, 2)).equals("Expense"))                            
                                jTable.setValueAt(jTableCopy.getValueAt(row, 8), row, 8);
                            else
                                jTable.setValueAt(jTableCopy.getValueAt(row, 2), row, 2);
                        }
                        if(((String)jTable.getValueAt(row, 2)).equals("Income") && !((String)jTable.getValueAt(row, 8)).equals("Surplus")){
                            //JOptionPane.showMessageDialog(null, "Please choose the logical surplus type");
                            if(((String)jTableCopy.getValueAt(row, 2)).equals("Income"))                          
                                jTable.setValueAt(jTableCopy.getValueAt(row, 8), row, 8);
                            else
                                jTable.setValueAt(jTableCopy.getValueAt(row, 2), row, 2);
                        }
                        TableModel tableModelCopy = jTableCopy.getModel();
                        Object oldData = tableModelCopy.getValueAt(row, column);
                        TableModel model = (TableModel)e.getSource();
                        String columnName = model.getColumnName(column);
                        Object data = model.getValueAt(row, column);
                        String id = (String)model.getValueAt(row, 0);
                        if(!oldData.equals(data)){                          
                            int result = JOptionPane.showConfirmDialog(null, "Do you want to update the cell value permanently?");
                            if(result == 0){
                                try {
                                    //int income = Integer.parseInt((String)model.getValueAt(row, 2));
                                    String incomeString = (String)model.getValueAt(row, 2);
                                    short income = 1;
                                    if(incomeString.equals("Income"))
                                        income = 1;
                                    if(incomeString.equals("Expense"))
                                        income = 0;
//                                    if(incomeString.equals("Income")){
//                                        comboBoxExpenseType.removeItemAt(1);
//                                        comboBoxExpenseType.removeItemAt(2);
//                                        comboBoxExpenseType.removeItemAt(3);
//                                        comboBoxExpenseType.removeItemAt(4);
//                                        comboBoxExpenseType.removeItemAt(5);
//                                        comboBoxExpenseType.removeItemAt(6);
//                                        comboBoxExpenseType.removeItemAt(7);
//                                        comboBoxExpenseType.removeItemAt(8);
//                                    }
//                                    if(incomeString.equals("Expense"))
//                                        comboBoxExpenseType.removeItemAt(0);;
                                    String location = (String)model.getValueAt(row, 4);
                                    float amount = Float.parseFloat(model.getValueAt(row, 5).toString());
                                    String comment = (String)model.getValueAt(row, 6);
                                    String date = model.getValueAt(row, 7).toString();
                                    String expenseType = (String)model.getValueAt(row,8);
                                    String SqlUpdate = "UPDATE [Record] SET Income ="+income+" , Location = '"+location+"', Amount = "+amount+", Comment = '"+comment+"',"
                                            + " Date = CONVERT(DATETIME, '"+date+"'), ExpenseType ='"+expenseType+"'  WHERE RecordId = '"+id+"'";
                                    stUpdate = conn.createStatement();
                                    //JOptionPane.showMessageDialog(null, SqlUpdate);
                                    int ret = stUpdate.executeUpdate(SqlUpdate);
                                    //int i= Integer.parseInt((String)model.getValueAt(row, 2));
                                    //JOptionPane.showMessageDialog(null, i);
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(Asset.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if(result == 1 || result == 2 || result == -1){
                                model.setValueAt(oldData, row, column);
                            }
                        }
                    }
                });
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        } 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblAssetType = new javax.swing.JLabel();
        jPanel = new javax.swing.JPanel();
        comboBox = new javax.swing.JComboBox();
        comboBoxExpenseType = new javax.swing.JComboBox();
        lblTransaction = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btBack = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAssetType.setText(AssetType.getAssetType());

        lblTransaction.setText("Recent Transaction Record");

        jScrollPane.setViewportView(jTable);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(lblTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
            .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addComponent(lblTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        btBack.setText("Back");
        btBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBackActionPerformed(evt);
            }
        });

        btDelete.setText("Delete select records");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btBack, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAssetType, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btDelete)
                    .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblAssetType, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btBack, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBackActionPerformed
        this.setVisible(false);
        Build_GUI mainframe = new Build_GUI();
        mainframe.setVisible(true); 
    }//GEN-LAST:event_btBackActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
//        DefaultTableModel model = (DefaultTableModel) this.jTable.getModel();
        int[] rows = this.jTable.getSelectedRows();
//        for(int i=0;i<rows.length;i++){
//            model.removeRow(rows[i]-i);
//        }
        
        ArrayList<String> allIds = new ArrayList<String>();
        for(int i=0;i<rows.length;i++){
            String id = jTable.getValueAt(rows[i], 0).toString();
//            JOptionPane.showMessageDialog(null, id);
            allIds.add(id);            
        }
        
        while(!allIds.isEmpty()){
            String id = allIds.remove(0);
//            JOptionPane.showMessageDialog(null, id);
            String SqlDelete = "DELETE FROM [Record] WHERE RecordId = '"+id+"'";
            try {
                stDelete = conn.createStatement();            
            //JOptionPane.showMessageDialog(null, SqlUpdate);
            int ret = stDelete.executeUpdate(SqlDelete);
            }
            catch (SQLException ex) {
                Logger.getLogger(Asset.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.setVisible(false);
        Asset newAsset = new Asset();
        newAsset.setVisible(true);
    }//GEN-LAST:event_btDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Asset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asset().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBack;
    private javax.swing.JButton btDelete;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAssetType;
    private javax.swing.JLabel lblTransaction;
    private javax.swing.JComboBox comboBox;
    private javax.swing.JComboBox comboBoxExpenseType;
    // End of variables declaration//GEN-END:variables

}
