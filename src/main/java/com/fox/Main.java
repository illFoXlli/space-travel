package com.fox;

import com.fox.config.DatabaseInitService;
import com.fox.dao.ClientDaoService;
import com.fox.dao.ClientDaoServiceImpl;
import com.fox.dao.PlanetDaoService;
import com.fox.dao.PlanetDaoServiceImpl;
import com.fox.dao.TicketDaoService;
import com.fox.dao.TicketDaoServiceImpl;
import com.fox.entity.Client;
import com.fox.entity.Planet;
import com.fox.entity.Ticket;
import com.fox.service.ClientService;
import com.fox.service.ClientServiceImpl;
import com.fox.service.PlanetService;
import com.fox.service.PlanetServiceImpl;
import com.fox.service.TicketService;
import com.fox.service.TicketServiceImpl;

public class Main {

    public static void main(String[] args) {
        DatabaseInitService.init();

        ClientDaoService clientDaoService = new ClientDaoServiceImpl();
        ClientService clientService = new ClientServiceImpl(clientDaoService);

        // CREATE
        long clientId = clientService.create("TestUser");

        System.out.println("CREATED ID: " + clientId);

        // READ
        Client clientFromDb = clientService.getById(clientId);
        System.out.println(clientFromDb);

        // UPDATE
        clientService.update(clientId, "UpdatedName");

        // DELETE
        clientService.deleteById(clientId);

        PlanetDaoService planetDaoService = new PlanetDaoServiceImpl();
        PlanetService planetService = new PlanetServiceImpl(planetDaoService);

        // CREATE
        planetService.create("TEST", "Test Planet");

        // READ
        Planet planetFromDb = planetService.getById("TEST");
        System.out.println(planetFromDb);

        // UPDATE
        planetService.update("TEST", "Updated Planet");

        planetService.create("MARSX", "Mars X");
        planetService.create("VENUSX", "Venus X");

        // DELETE
        planetService.deleteById("TEST");

        TicketDaoService ticketDaoService = new TicketDaoServiceImpl();
        TicketService ticketService = new TicketServiceImpl(
                ticketDaoService,
                clientDaoService,
                planetDaoService
        );

        long ticketClientId = clientService.create("TicketOwner");
        long ticketId = ticketService.create(ticketClientId, "MARSX", "VENUSX");

        Ticket ticketFromDb = ticketService.getById(ticketId);
        System.out.println(ticketFromDb);

        ticketService.update(ticketId, ticketClientId, "VENUSX", "MARSX");
        ticketService.deleteById(ticketId);
        clientService.deleteById(ticketClientId);
        planetService.deleteById("MARSX");
        planetService.deleteById("VENUSX");
    }
}
