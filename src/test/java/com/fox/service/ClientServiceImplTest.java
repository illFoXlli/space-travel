package com.fox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fox.config.DatabaseInitService;
import com.fox.dao.ClientDaoService;
import com.fox.dao.ClientDaoServiceImpl;
import com.fox.entity.Client;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ClientServiceImplTest {

    private static ClientService clientService;

    @BeforeAll
    static void setUp() throws IOException {
        Files.deleteIfExists(Path.of("db/space_travel.mv.db"));
        Files.deleteIfExists(Path.of("db/space_travel.trace.db"));
        DatabaseInitService.init();

        ClientDaoService clientDaoService = new ClientDaoServiceImpl();
        clientService = new ClientServiceImpl(clientDaoService);
    }

    @Test
    void shouldCreateReadUpdateAndDeleteClient() {
        long clientId = clientService.create("TestClientCrud");

        Client createdClient = clientService.getById(clientId);
        assertNotNull(createdClient);
        assertEquals("TestClientCrud", createdClient.getName());

        clientService.update(clientId, "UpdatedClientCrud");

        Client updatedClient = clientService.getById(clientId);
        assertNotNull(updatedClient);
        assertEquals("UpdatedClientCrud", updatedClient.getName());

        clientService.deleteById(clientId);

        Client deletedClient = clientService.getById(clientId);
        assertNull(deletedClient);
    }

    @Test
    void shouldRejectInvalidClientName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clientService.create("ab")
        );

        assertEquals("Client name length must be between 3 and 200", exception.getMessage());
    }
}
