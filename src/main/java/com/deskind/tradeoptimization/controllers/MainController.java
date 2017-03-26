
package com.deskind.tradeoptimization.controllers;

import com.deskind.tradeoptimization.utils.SqlUtil;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Popup;


public class MainController implements Initializable {

    @FXML
    private Button chooseFilesBtn;

    @FXML
    void handleChooseFilesBtn(ActionEvent event) {
        
        Connection connection = SqlUtil.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**Choose files from file system*/
        FileChooser fileChooser = new FileChooser();
        List<File> files = fileChooser.showOpenMultipleDialog(new Popup());
        System.out.println(files.size());
        //end 
        
        /**Sgn table creation*/
        String createTableQuery = "create table if not exists sgn(\n" +
                                    "id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                    "date TIMESTAMP,\n" +
                                    "result INT,\n" +
                                    "pair varchar(10)\n" +
                                    ");";
        try {
            statement.execute(createTableQuery);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //end
        
        /**Add data to sgn table from choosen fiels*/
        for(File f : files){
            String fillSgnTable = null;
            String path = f.getPath();
            String newPath = path.replace("\\", "\\\\");
            fillSgnTable = "load data local infile\n" + "'" +
                            newPath + "'" +
                            " into table sgn fields terminated by ','\n" +
                            "  enclosed by '\"'\n" +
                            "  lines terminated by '\\n'\n" +
                            "    (date, result, pair);";     
            try {
                statement.execute(fillSgnTable);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //end
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
