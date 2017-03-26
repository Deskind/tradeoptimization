
package com.deskind.tradeoptimization.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SqlUtil {
    
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/tradeoptimization?serverTimezone=UTC","root", "");
        } catch (SQLException ex) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    
}
