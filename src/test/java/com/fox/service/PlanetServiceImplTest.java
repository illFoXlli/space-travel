package com.fox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fox.config.DatabaseInitService;
import com.fox.dao.PlanetDaoService;
import com.fox.dao.PlanetDaoServiceImpl;
import com.fox.entity.Planet;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PlanetServiceImplTest {

    private static PlanetService planetService;

    @BeforeAll
    static void setUp() throws IOException {
        Files.deleteIfExists(Path.of("db/space_travel.mv.db"));
        Files.deleteIfExists(Path.of("db/space_travel.trace.db"));
        DatabaseInitService.init();

        PlanetDaoService planetDaoService = new PlanetDaoServiceImpl();
        planetService = new PlanetServiceImpl(planetDaoService);
    }

    @Test
    void shouldCreateReadUpdateAndDeletePlanet() {
        planetService.create("TESTPLANET1", "Test Planet");

        Planet createdPlanet = planetService.getById("TESTPLANET1");
        assertNotNull(createdPlanet);
        assertEquals("Test Planet", createdPlanet.getName());

        planetService.update("TESTPLANET1", "Updated Planet");

        Planet updatedPlanet = planetService.getById("TESTPLANET1");
        assertNotNull(updatedPlanet);
        assertEquals("Updated Planet", updatedPlanet.getName());

        planetService.deleteById("TESTPLANET1");

        Planet deletedPlanet = planetService.getById("TESTPLANET1");
        assertNull(deletedPlanet);
    }

    @Test
    void shouldRejectInvalidPlanetId() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> planetService.create("mars", "Mars")
        );

        assertEquals(
                "Planet id must contain only uppercase Latin letters and digits",
                exception.getMessage()
        );
    }
}
