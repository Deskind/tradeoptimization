
package com.deskind.tradeoptimization.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SqlUtil {
    
    public static Connection connection;
    
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost?serverTimezone=UTC","root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    public static String createTradeoptimization = "create table if not exists tradeoptimization";
    
    public static String useTradeoptimization = "use tradeoptimization";
    
    public static String fillTableFromFile(String convertedPath){
        String s = "load data local infile\n" +"'" +
                    convertedPath + "'" +
                    " into table sgn fields terminated by ','\n" +
                    "  enclosed by '\"'\n" +
                    "  lines terminated by '\\n'\n" +
                    "    (date, result, pair);";
        return s;
    }
    
    /**Method for preparing db - create database , use it and create table*/
    public static void prepareDb() {
        try {
            
            Statement st = connection.createStatement();
            st.execute("create database if not exists tradeoptimization");
            st.close();
            
            Statement st1 = connection.createStatement();
            st1.execute("use tradeoptimization");
            st1.close();
            
            Statement st2 = connection.createStatement();
            st2.execute("create table if not exists sgn(\n" +
                    "id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "date TIMESTAMP,\n" +
                    "result INT\n" +
                    ");\n" +
                    "");
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //End
    
    /**Method for filling sgn table*/
    public static void fillSgn(String path) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(fillTableFromFile(path));
        } catch (SQLException ex) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //End
    
    
    
    
}
