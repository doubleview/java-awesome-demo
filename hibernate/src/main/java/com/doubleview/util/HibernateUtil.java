package com.doubleview.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        Configuration conf = new Configuration();
        conf.configure("/hibernate.cfg.xml");
        sessionFactory = conf.buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void main(String[] args) {
        System.out.println(getSession());
    }
}
