package com.fox.service;

import com.fox.config.HibernateUtil;
import com.fox.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientCrudService {

    // CREATE
    public void create(Client client) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();

            session.persist(client);

            tx.commit();
        }
    }

    // READ
    public Client getById(Long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(Client.class, id);
        }
    }

    // UPDATE
    public void update(Client client) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();

            session.merge(client);

            tx.commit();
        }
    }

    // DELETE
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();

            Client client = session.get(Client.class, id);
            if (client != null) {
                session.remove(client);
            }

            tx.commit();
        }
    }
}