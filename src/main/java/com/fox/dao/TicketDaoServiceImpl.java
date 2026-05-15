package com.fox.dao;

import com.fox.config.HibernateUtil;
import com.fox.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TicketDaoServiceImpl implements TicketDaoService {

    @Override
    public long create(Ticket ticket) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.persist(ticket);
            tx.commit();
            return ticket.getId();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public Ticket getById(long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    @Override
    public void update(Ticket ticket) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.merge(ticket);
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

            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.remove(ticket);
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
