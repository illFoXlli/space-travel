package com.fox.dao;

import com.fox.config.HibernateUtil;
import com.fox.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDaoServiceImpl implements ClientDaoService {

    @Override
    public long create(Client client) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.persist(client);
            tx.commit();
            return client.getId();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public Client getById(long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(Client.class, id);
        }
    }

    @Override
    public void update(Client client) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.merge(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void deleteById(long id) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();

            Client client = session.get(Client.class, id);
            if (client != null) {
                session.remove(client);
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
