package com.fox.dao;

import com.fox.entity.Ticket;

public interface TicketDaoService {

    long create(Ticket ticket);

    Ticket getById(long id);

    void update(Ticket ticket);

    void deleteById(long id);
}
