
package com.deskind.tradeoptimization.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SqlUtil {
    
    public static String createSgnTableQuery = "create table if not exists sgn(\n" +
                                    "id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                    "date TIMESTAMP,\n" +
                                    "result INT,\n" +
                                    "pair varchar(10)\n" +
                                    ");";
    
    public static String createPairQuery = "create table if not exists pair(\n" +
                                    "id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                    "name VARCHAR(10) \n" +
                                    ");";
    
    public static String fillTableFromFile(String convertedPath){
        String s = "load data local infile\n" +"'" +
                    convertedPath + "'" +
                    " into table sgn fields terminated by ','\n" +
                    "  enclosed by '\"'\n" +
                    "  lines terminated by '\\n'\n" +
                    "    (date, result, pair);";
        return s;
    }
    
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
