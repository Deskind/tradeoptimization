
package com.deskind.tradeoptimization.main.AppMain;

import com.deskind.tradeoptimization.entities.Bar;
import com.deskind.tradeoptimization.utils.AppUtils;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class AppMain {
    public static void main(String[] args){
        
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        Session session = sessionFactory.openSession();
        
        
        session.beginTransaction();
        
        
//        List<Bar> list = AppUtils.getBars(AppUtils.getReader("EURUSD5.csv"));
//        
//        for(Bar b : list){
//            session.save(b);
//        }


        Bar b = (Bar)session.get(Bar.class, 2l);
        System.out.println(b.getDate().toString());
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        
    }
}
