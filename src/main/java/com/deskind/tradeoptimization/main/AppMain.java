
package com.deskind.tradeoptimization.main;

import com.deskind.tradeoptimization.entities.Sgn;
import com.deskind.tradeoptimization.entities.TradePair;
import com.deskind.tradeoptimization.utils.HibernateUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class AppMain extends Application{
    
        
    @Override
    
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**Test method for getting list of signals (Sgn)*/
    public static ArrayList<Sgn> getList(){
        ArrayList<Sgn> list = new ArrayList<Sgn>();
        list.add(new Sgn(LocalDateTime.now(), 1));
        list.add(new Sgn(LocalDateTime.now(), 0));
        return list;
    }
    //end
    
    public static void main(String[] args) {
//        launch(args);
        
        SessionFactory sessionFactory = HibernateUtils.getHibernateSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Sgn> list = getList();
        TradePair tp = new TradePair();
        session.save(new TradePair("USDBLR", list));
        session.getTransaction().commit();
        session.close();
        HibernateUtils.sessionFactory.close();
    }
}
