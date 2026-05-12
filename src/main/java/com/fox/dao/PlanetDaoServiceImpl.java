package com.fox.dao;

import com.fox.config.HibernateUtil;
import com.fox.entity.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlanetDaoServiceImpl implements PlanetDaoService {

    @Override
    public void create(Planet planet) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.persist(planet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public Planet getById(String id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(Planet.class, id);
        }
    }

    @Override
    public void update(Planet planet) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.merge(planet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void deleteById(String id) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();

            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.remove(planet);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
}
