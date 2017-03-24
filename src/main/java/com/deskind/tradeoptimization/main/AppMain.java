
package com.deskind.tradeoptimization.main;

import com.deskind.tradeoptimization.entities.Sgn;
import java.time.LocalDateTime;
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
    
    public static void main(String[] args) {
        launch(args);
    }
        
//        Configuration configuration = new Configuration();
//        configuration.configure("hibernate.cfg.xml");
//        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
//        Session session = sessionFactory.openSession();
//        
//        
//        session.beginTransaction();
        
        
//        List<Bar> list = AppUtils.getBars(AppUtils.getReader("EURUSD5.csv"));
//        
//        for(Bar b : list){
//            session.save(b);
//        }

//        session.save(new Sgn(LocalDateTime.now(), 3));

//        Sgn s = (Sgn)session.get(Sgn.class, 15l);
//        
//        if(s!=null){
//            System.out.println("The s variable is ok");
//            System.out.println(s.getDate().toString());
//        }else System.out.println("Something wrong!!!");

//        Bar b = (Bar)session.get(Bar.class, 2l);
//        System.out.println(b.getDate().toString());
        
//        session.getTransaction().commit();
//        session.close();
//        sessionFactory.close();
        
    
}
