package com.fox.config;

import com.fox.entity.Client;
import com.fox.entity.Planet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure()
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Planet.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("SessionFactory creation failed", e);
        }
    }

    public static SessionFactory getInstance() {
        return sessionFactory;
    }
}