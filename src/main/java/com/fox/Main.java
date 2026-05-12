package com.fox;

import com.fox.config.DatabaseInitService;
import com.fox.dao.ClientDaoService;
import com.fox.dao.ClientDaoServiceImpl;
import com.fox.dao.PlanetDaoService;
import com.fox.dao.PlanetDaoServiceImpl;
import com.fox.entity.Client;
import com.fox.entity.Planet;
import com.fox.service.ClientService;
import com.fox.service.ClientServiceImpl;
import com.fox.service.PlanetService;
import com.fox.service.PlanetServiceImpl;

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

        // DELETE
        planetService.deleteById("TEST");
    }
}
