package com.fox.service;

import com.fox.config.DatabaseInitService;
import com.fox.entity.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientCrudServiceTest {

    private static ClientCrudService clientCrudService;

    @BeforeAll
    static void setUp() throws IOException {
        Files.deleteIfExists(Path.of("db/space_travel.mv.db"));
        Files.deleteIfExists(Path.of("db/space_travel.trace.db"));
        DatabaseInitService.init();
        clientCrudService = new ClientCrudService();
    }

    @Test
    void shouldCreateReadUpdateAndDeleteClient() {
        Client client = new Client("TestClientCrud");
        clientCrudService.create(client);

        assertNotNull(client.getId());

        Client createdClient = clientCrudService.getById(client.getId());
        assertNotNull(createdClient);
        assertEquals("TestClientCrud", createdClient.getName());

        createdClient.setName("UpdatedClientCrud");
        clientCrudService.update(createdClient);

        Client updatedClient = clientCrudService.getById(client.getId());
        assertNotNull(updatedClient);
        assertEquals("UpdatedClientCrud", updatedClient.getName());

        clientCrudService.deleteById(client.getId());

        Client deletedClient = clientCrudService.getById(client.getId());
        assertNull(deletedClient);
    }
}
