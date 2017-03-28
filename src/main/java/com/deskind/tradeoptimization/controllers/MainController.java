
package com.deskind.tradeoptimization.controllers;

import com.deskind.tradeoptimization.entities.Sgn;
import com.deskind.tradeoptimization.entities.TradePair;
import com.deskind.tradeoptimization.utils.HibernateUtils;
import com.deskind.tradeoptimization.utils.SqlUtil;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        
        HibernateUtils.getSessionFactory("hibernate_update");
        
        /**Get the most earliest and most latest date from date column in Sgn table
         and set time to 00:00:00*/
        List l = HibernateUtils.queryForMe("FROM Sgn order by date");
            //Earliest
            Sgn earliestSignal = (Sgn)l.get(0);
            iteratedDate = earliestSignal.getDate().toLocalDate();
            //Latest
            Sgn latestSignal = (Sgn)l.get(l.size()-1);
            latestDate = latestSignal.getDate().toLocalDate();
        //end
        
        
        //The variable contains profit in percents
        Float f;
        //Variable contains series of data for chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        
        
        /**Iterate through all days*/
        while(!iteratedDate.equals(latestDate)){
            String earliestDateAsString = String.valueOf(iteratedDate.toString());
            List<Sgn> oneDaySignals = HibernateUtils.queryForMe("FROM Sgn WHERE date LIKE '%"+earliestDateAsString+"%'");
            String dateForSeries;
            Float accountForSeries = 0f;
                /**Is list contains any objects???
                 If it is empty just go to next day
                 else Iterate over list and generate XYChart.Data*/
                if(oneDaySignals.isEmpty()){
                    latestDate = latestDate.plusDays(1);
                }else{
                    
                }
                //end
        }
        //end
        
        List daySgn = HibernateUtils.queryForMe("FROM Sgn WHERE date BETWEEN '2017-03-01 00:00:00' AND '2017-03-01 23:59:59'");
        
        HibernateUtils.sessionFactory.close();
    }

    static void dbInitialization(List<File> files) {
        
        SqlUtil.createSgnTable();
        
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory("hibernate_create");
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
