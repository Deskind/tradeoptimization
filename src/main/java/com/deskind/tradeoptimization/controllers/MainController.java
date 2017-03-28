
package com.deskind.tradeoptimization.controllers;

import com.deskind.tradeoptimization.entities.Sgn;
import com.deskind.tradeoptimization.entities.TradePair;
import com.deskind.tradeoptimization.utils.HibernateUtils;
import com.deskind.tradeoptimization.utils.SqlUtil;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class MainController implements Initializable {
    
    @FXML
    private Button chooseFilesBtn;
    
    @FXML
    private Button makeChartBtn;
    
    @FXML
    void handleChooseFilesBtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        List<File> files = fileChooser.showOpenMultipleDialog(new Popup());
        
        dbInitialization(files);
    }
    
    @FXML
    void handleMakeChartBtn(ActionEvent event) {
        //The most earlies date
        LocalDate iteratedDate = null;
        //The most latest date in Sgn table
        LocalDate latestDate = null;
        //The variable contains profit in percents
        Float balance;
        //Variable contains series of data for every trade pair
        List<XYChart.Series<String, Number>> series = new ArrayList<>();
        /**Hibernate's update session factory*/
        HibernateUtils.getSessionFactory("hibernate_update.cfg.xml");
        
        /**Get the most earliest and most latest date from date column in Sgn table*/
        List l = HibernateUtils.queryForMe("FROM Sgn order by date");
            //Earliest
            Sgn earliestSignal = (Sgn)l.get(0);
            iteratedDate = earliestSignal.getDate().toLocalDate();
            //Latest
            Sgn latestSignal = (Sgn)l.get(l.size()-1);
            latestDate = latestSignal.getDate().toLocalDate();
        //end
        
        /**Getting list of trade pairs*/
        List tradePairs = HibernateUtils.queryForMe("FROM TradePair");
        //End
        
        /**Iterate over trade pairs*/
        for(Object t : tradePairs){
            LocalDate date = iteratedDate;
            TradePair tradePair = (TradePair)t;
            String tradePairName = tradePair.getName();
            /**Iterate over all dates*/
            while(!iteratedDate.equals(latestDate)){
                List dateSignals = HibernateUtils.queryForMe("from Sgn where pair like '%"+tradePairName+"%' and date like '%"+iteratedDate.toString()+"%'");
                
            }
            //End
        }
        //End
        
        
        
        /**Closing hibernate's session factory*/
        HibernateUtils.sessionFactory.close();
        //End
    }

    static void dbInitialization(List<File> files) {
        
        SqlUtil.createSgnTable();
        
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory("hibernate.cfg.xml");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        for(File f : files){
            String s = f.getPath();
            Pattern pattern = Pattern.compile("[A-Z]{6}");
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
