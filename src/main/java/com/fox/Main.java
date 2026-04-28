package com.fox;

import com.fox.config.DatabaseInitService;
import com.fox.entity.Client;
import com.fox.entity.Planet;
import com.fox.service.ClientCrudService;
import com.fox.service.PlanetCrudService;

public class Main {

    public static void main(String[] args) {
        DatabaseInitService.init();

        ClientCrudService service = new ClientCrudService();

        // CREATE
        Client client = new Client("TestUser");
        service.create(client);

        System.out.println("CREATED ID: " + client.getId());

        // READ
        Client clientFromDb = service.getById(client.getId());
        System.out.println(clientFromDb);

        // UPDATE
        clientFromDb.setName("UpdatedName");
        service.update(clientFromDb);

        // DELETE
        service.deleteById(clientFromDb.getId());

        PlanetCrudService planetService = new PlanetCrudService();

        Planet p = new Planet("TEST", "Test Planet");

        // CREATE
        planetService.create(p);

        // READ
        Planet planetFromDb = planetService.getById("TEST");
        System.out.println(planetFromDb);

        // UPDATE
        planetFromDb.setName("Updated Planet");
        planetService.update(planetFromDb);

        // DELETE
        planetService.deleteById("TEST");
    }
}