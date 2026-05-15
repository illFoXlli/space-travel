package com.fox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fox.config.DatabaseInitService;
import com.fox.dao.ClientDaoService;
import com.fox.dao.ClientDaoServiceImpl;
import com.fox.dao.PlanetDaoService;
import com.fox.dao.PlanetDaoServiceImpl;
import com.fox.dao.TicketDaoService;
import com.fox.dao.TicketDaoServiceImpl;
import com.fox.entity.Ticket;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TicketServiceImplTest {

    private static ClientService clientService;
    private static PlanetService planetService;
    private static TicketService ticketService;

    @BeforeAll
    static void setUp() throws IOException {
        Files.deleteIfExists(Path.of("db/space_travel.mv.db"));
        Files.deleteIfExists(Path.of("db/space_travel.trace.db"));
        DatabaseInitService.init();

        ClientDaoService clientDaoService = new ClientDaoServiceImpl();
        PlanetDaoService planetDaoService = new PlanetDaoServiceImpl();
        TicketDaoService ticketDaoService = new TicketDaoServiceImpl();

        clientService = new ClientServiceImpl(clientDaoService);
        planetService = new PlanetServiceImpl(planetDaoService);
        ticketService = new TicketServiceImpl(ticketDaoService, clientDaoService, planetDaoService);
    }

    @Test
    void shouldCreateReadUpdateAndDeleteTicket() {
        long clientId = clientService.create("TicketClient");
        planetService.create("EARTH1", "Earth");
        planetService.create("MARS1", "Mars");
        planetService.create("VENUS1", "Venus");

        long ticketId = ticketService.create(clientId, "EARTH1", "MARS1");

        Ticket createdTicket = ticketService.getById(ticketId);
        assertNotNull(createdTicket);
        assertNotNull(createdTicket.getCreatedAt());
        assertEquals(clientId, createdTicket.getClient().getId());
        assertEquals("EARTH1", createdTicket.getFromPlanet().getId());
        assertEquals("MARS1", createdTicket.getToPlanet().getId());

        ticketService.update(ticketId, clientId, "MARS1", "VENUS1");

        Ticket updatedTicket = ticketService.getById(ticketId);
        assertNotNull(updatedTicket);
        assertEquals("MARS1", updatedTicket.getFromPlanet().getId());
        assertEquals("VENUS1", updatedTicket.getToPlanet().getId());

        ticketService.deleteById(ticketId);

        Ticket deletedTicket = ticketService.getById(ticketId);
        assertNull(deletedTicket);
    }

    @Test
    void shouldRejectNullClient() {
        planetService.create("TICKETP1", "Planet One");
        planetService.create("TICKETP2", "Planet Two");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketService.create(null, "TICKETP1", "TICKETP2")
        );

        assertEquals("Client id must not be null", exception.getMessage());
    }

    @Test
    void shouldRejectNonExistingClient() {
        planetService.create("TICKETP3", "Planet Three");
        planetService.create("TICKETP4", "Planet Four");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketService.create(999999L, "TICKETP3", "TICKETP4")
        );

        assertEquals("Client with id 999999 not found", exception.getMessage());
    }

    @Test
    void shouldRejectNullPlanet() {
        long clientId = clientService.create("PlanetValidationClient");
        planetService.create("TICKETP5", "Planet Five");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketService.create(clientId, null, "TICKETP5")
        );

        assertEquals("from planet id must not be null", exception.getMessage());
    }

    @Test
    void shouldRejectNonExistingPlanet() {
        long clientId = clientService.create("MissingPlanetClient");
        planetService.create("TICKETP6", "Planet Six");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketService.create(clientId, "UNKNOWN1", "TICKETP6")
        );

        assertEquals("from planet with id UNKNOWN1 not found", exception.getMessage());
    }
}
