/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static com.sun.java.accessibility.util.EventID.KEY;
import static java.nio.file.StandardOpenOption.CREATE;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class tables {
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        try {
            con = ConnectoinProvider.getCon();
            st = con.createStatement();
            if(!tableExists(st, "userdetails")){
              st.executeUpdate("CREATE TABLE userdetails (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, gender VARCHAR(50) NOT NULL, email VARCHAR(255) NOT NULL, contact VARCHAR(20) NOT NULL, address VARCHAR(500), state VARCHAR(100), country VARCHAR(100), uniqueregid VARCHAR(100) NOT NULL, imagename VARCHAR(100));");

            }
            
            if (!tableExists(st, "userattendance")) {
               st.executeUpdate("CREATE TABLE userattendance (userid INT NOT NULL, date DATE NOT NULL, checkin DATETIME, checkout DATETIME, workduration VARCHAR(100));");
            }

             
             JOptionPane.showMessageDialog(null, "Tables Checked/Created Successfully");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
          try {
                if(con != null){
            
                con.close();
            }
            if(st != null) {
                st.close();
            }
          
          } catch(Exception ex){
              ex.printStackTrace();
          }
        }
    }
    
    
    private static boolean tableExists(Statement st, String tableName) throws Exception {
    ResultSet resultSet = st.executeQuery("SHOW TABLES LIKE '" + tableName + "'");
    return resultSet.next();
}

}
