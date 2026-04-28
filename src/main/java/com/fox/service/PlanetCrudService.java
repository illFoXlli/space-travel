package com.fox.service;

import com.fox.config.HibernateUtil;
import com.fox.entity.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlanetCrudService {

    public void create(Planet planet) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();

            session.persist(planet);

            tx.commit();
        }
    }

    public Planet getById(String id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(Planet.class, id);
        }
    }

    public void update(Planet planet) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();

            session.merge(planet);

            tx.commit();
        }
    }

    public void deleteById(String id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();

            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.remove(planet);
            }

            tx.commit();
        }
    }
}