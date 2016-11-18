/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1.GUI;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author duyue_000
 */
public class MySqlConnect {
    Connection conn = null;
    public static Connection ConnectDB(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://cs3343test.mssql.somee.com;databaseName=cs3343test;user=Netbeans;password=Cs33433343;");
            //JOptionPane.showMessageDialog(null, "Connect to database");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
