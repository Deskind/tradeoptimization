
package com.deskind.tradeoptimization.controllers;

import com.deskind.tradeoptimization.entities.TradePair;
import com.deskind.tradeoptimization.utils.HibernateUtils;
import com.deskind.tradeoptimization.utils.SqlUtil;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


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
        
        SqlUtil.createSgnTable();
        
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        for(File f : files){
            String s = f.getPath();
            Pattern pattern = Pattern.compile("[A-Z]*-\\d");
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
               session.save(new TradePair(matcher.group()));
            }else System.out.println("CAN'T GET PAIR NAME");
        }
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        sessionFactory.close();
        
        for(File f : files){
//            File f = files.get(0);
            String path = f.getPath();
            String convertedPath = path.replace("\\", "\\\\");
            SqlUtil.fillSgn(convertedPath);
        }
        
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
