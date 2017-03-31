
package com.deskind.tradeoptimization.controllers;

import com.deskind.tradeoptimization.entities.Sgn;
import com.deskind.tradeoptimization.entities.TradePair;
import com.deskind.tradeoptimization.utils.HibernateUtils;
import com.deskind.tradeoptimization.utils.SqlUtil;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import org.hibernate.Session;


public class MainController implements Initializable {
    
    @FXML
    private Button chooseFilesBtn;
    
    @FXML
    private Button makeChartBtn;
    
    @FXML
    private LineChart<String, Number> totalChart;
    
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
        Float totalBalance = 0f;
        //Variable contains series of data for every trade pair
        XYChart.Series series = new XYChart.Series();
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
        
        /**Iterate all days*/
        while(!iteratedDate.equals(latestDate)){
            //Temp var for storing balance of one day
            Float balance = 0f;
            //Temp var for one signal in days list 
            Sgn signal = null;
            //Get signals for iterated date
            List daySignals = HibernateUtils.queryForMe("from Sgn where date like '%"+iteratedDate.toString()+"%'");
            /**Is list empty or not*/
            if(daySignals.size()==0){
                iteratedDate = iteratedDate.plusDays(1);
                continue;
            }else{
                /**If list of signals is not empty calculate one day balance*/
                for(Object o : daySignals){
                    signal = (Sgn)o;
                    if(signal.getResult()==1){
                        balance+=0.75f;
                    }else{
                        balance-=1;
                    }
                }
            }
            //Change total balance, add new object to series and icrease iterated date on one day
            totalBalance+=balance;
            series.getData().add(new XYChart.Data<>(iteratedDate.toString(), totalBalance));
            iteratedDate = iteratedDate.plusDays(1);
            //End
        }
        //End
        
        totalChart.getData().add(series);
        totalChart.setVisible(true);
        /**Closing hibernate's session factory*/
        HibernateUtils.sessionFactory = null;
        //End
    }

    static void dbInitialization(List<File> files) {
        
        SqlUtil.prepareDb();
        
        HibernateUtils.getSessionFactory("hibernate_create.cfg.xml");
        Session session = HibernateUtils.sessionFactory.openSession();
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
        HibernateUtils.sessionFactory = null;
        
        for(File f : files){
            String path = f.getPath();
            String convertedPath = path.replace("\\", "\\\\");
            SqlUtil.fillSgn(convertedPath);
        }        
        
        /**Closing jdbc connection*/
        try {
            SqlUtil.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //End
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        totalChart.setVisible(false);
    }    
    
}
