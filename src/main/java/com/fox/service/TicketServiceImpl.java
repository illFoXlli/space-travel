package com.fox.service;

import com.fox.dao.ClientDaoService;
import com.fox.dao.PlanetDaoService;
import com.fox.dao.TicketDaoService;
import com.fox.entity.Client;
import com.fox.entity.Planet;
import com.fox.entity.Ticket;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TicketServiceImpl implements TicketService {

    private final TicketDaoService ticketDaoService;
    private final ClientDaoService clientDaoService;
    private final PlanetDaoService planetDaoService;

    public TicketServiceImpl(
            TicketDaoService ticketDaoService,
            ClientDaoService clientDaoService,
            PlanetDaoService planetDaoService
    ) {
        this.ticketDaoService = ticketDaoService;
        this.clientDaoService = clientDaoService;
        this.planetDaoService = planetDaoService;
    }

    @Override
    public long create(Long clientId, String fromPlanetId, String toPlanetId) {
        Client client = getExistingClient(clientId);
        Planet fromPlanet = getExistingPlanet(fromPlanetId, "from");
        Planet toPlanet = getExistingPlanet(toPlanetId, "to");
        validateDifferentPlanets(fromPlanet, toPlanet);

        Ticket ticket = new Ticket(LocalDateTime.now(ZoneOffset.UTC), client, fromPlanet, toPlanet);
        return ticketDaoService.create(ticket);
    }

    @Override
    public Ticket getById(long id) {
        return ticketDaoService.getById(id);
    }

    @Override
    public void update(long id, Long clientId, String fromPlanetId, String toPlanetId) {
        Ticket ticket = ticketDaoService.getById(id);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket with id " + id + " not found");
        }

        Client client = getExistingClient(clientId);
        Planet fromPlanet = getExistingPlanet(fromPlanetId, "from");
        Planet toPlanet = getExistingPlanet(toPlanetId, "to");
        validateDifferentPlanets(fromPlanet, toPlanet);

        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        ticketDaoService.update(ticket);
    }

    @Override
    public void deleteById(long id) {
        ticketDaoService.deleteById(id);
    }

    private Client getExistingClient(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("Client id must not be null");
        }

        Client client = clientDaoService.getById(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Client with id " + clientId + " not found");
        }

        return client;
    }

    private Planet getExistingPlanet(String planetId, String role) {
        if (planetId == null) {
            throw new IllegalArgumentException(role + " planet id must not be null");
        }

        Planet planet = planetDaoService.getById(planetId.trim());
        if (planet == null) {
            throw new IllegalArgumentException(role + " planet with id " + planetId + " not found");
        }

        return planet;
    }

    private void validateDifferentPlanets(Planet fromPlanet, Planet toPlanet) {
        if (fromPlanet.getId().equals(toPlanet.getId())) {
            throw new IllegalArgumentException("From planet and to planet must be different");
        }
    }
}
