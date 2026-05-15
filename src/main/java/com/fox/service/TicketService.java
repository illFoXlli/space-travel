package com.fox.service;

import com.fox.entity.Ticket;

public interface TicketService {

    long create(Long clientId, String fromPlanetId, String toPlanetId);

    Ticket getById(long id);

    void update(long id, Long clientId, String fromPlanetId, String toPlanetId);

    void deleteById(long id);
}
