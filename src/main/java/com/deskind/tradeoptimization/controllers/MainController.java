
package com.deskind.tradeoptimization.controllers;

import com.deskind.tradeoptimization.entities.TradePair;
import com.deskind.tradeoptimization.utils.HibernateUtils;
import com.deskind.tradeoptimization.utils.SqlUtil;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class MainController implements Initializable {

    @FXML
    private Button chooseFilesBtn;
    
    @FXML
    void handleChooseFilesBtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        List<File> files = fileChooser.showOpenMultipleDialog(new Popup());
        
        dbInitialization(files);
    }

    static void dbInitialization(List<File> files) {
        
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        HashMap<String, File> map =  new HashMap<>();
        Connection connection = SqlUtil.getConnection();
        Statement statement = null;
        
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**Choose files from file system*/
        
            /**Initialize a map*/
            for(File f : files){
                String path = f.getPath();
                String forMap = path.substring(0, 5);
                map.put(forMap, f);
            }
            //end
        //end 

        try {
            statement.execute(SqlUtil.createSgnTableQuery);
            statement.execute(SqlUtil.createPairQuery);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**Iterate through map and fill sgn table*/
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String pairName = (String)entry.getKey();
            session.save(new TradePair(pairName));
            
            File f = (File)entry.getValue();
            String path = f.getPath();
            String convertedPath = path.replace("\\", "\\\\");
            
            try {
                statement.execute(SqlUtil.fillTableFromFile(convertedPath));
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
