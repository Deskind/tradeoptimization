/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deskind.tradeoptimization.utils;

import com.deskind.tradeoptimization.entities.Sgn;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    
    public static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory(String s){
        if(sessionFactory == null){
        Configuration configuration = new Configuration();
        configuration.configure(s);
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(ssrb.build());
        }
        return sessionFactory;
    }

    public static List<Sgn> queryForMe(String string) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(string);
        List<Sgn> list = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
    
    
}
