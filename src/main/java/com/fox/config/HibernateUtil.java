package com.fox.config;

import com.fox.entity.Client;
import com.fox.entity.Planet;
import com.fox.entity.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            configuration.setProperty(
                    "hibernate.connection.driver_class",
                    EnvConfig.getRequired("DB_DRIVER")
            );
            configuration.setProperty(
                    "hibernate.connection.url",
                    EnvConfig.getRequired("DB_URL")
            );
            configuration.setProperty(
                    "hibernate.connection.username",
                    EnvConfig.getRequired("DB_USER")
            );
            configuration.setProperty(
                    "hibernate.connection.password",
                    EnvConfig.getRequired("DB_PASSWORD")
            );
            configuration.setProperty(
                    "hibernate.show_sql",
                    EnvConfig.getRequired("HIBERNATE_SHOW_SQL")
            );
            configuration.setProperty(
                    "hibernate.format_sql",
                    EnvConfig.getRequired("HIBERNATE_FORMAT_SQL")
            );
            configuration.setProperty(
                    "hibernate.hbm2ddl.auto",
                    EnvConfig.getRequired("HIBERNATE_HBM2DDL_AUTO")
            );

            return configuration
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Planet.class)
                    .addAnnotatedClass(Ticket.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("SessionFactory creation failed", e);
        }
    }

    public static SessionFactory getInstance() {
        return sessionFactory;
    }
}
