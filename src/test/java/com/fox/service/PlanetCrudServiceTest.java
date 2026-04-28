package com.fox.service;

import com.fox.config.DatabaseInitService;
import com.fox.entity.Planet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlanetCrudServiceTest {

    private static PlanetCrudService planetCrudService;

    @BeforeAll
    static void setUp() throws IOException {
        Files.deleteIfExists(Path.of("db/space_travel.mv.db"));
        Files.deleteIfExists(Path.of("db/space_travel.trace.db"));
        DatabaseInitService.init();
        planetCrudService = new PlanetCrudService();
    }

    @Test
    void shouldCreateReadUpdateAndDeletePlanet() {
        Planet planet = new Planet("TESTPLANET1", "Test Planet");
        planetCrudService.create(planet);

        Planet createdPlanet = planetCrudService.getById("TESTPLANET1");
        assertNotNull(createdPlanet);
        assertEquals("Test Planet", createdPlanet.getName());

        createdPlanet.setName("Updated Planet");
        planetCrudService.update(createdPlanet);

        Planet updatedPlanet = planetCrudService.getById("TESTPLANET1");
        assertNotNull(updatedPlanet);
        assertEquals("Updated Planet", updatedPlanet.getName());

        planetCrudService.deleteById("TESTPLANET1");

        Planet deletedPlanet = planetCrudService.getById("TESTPLANET1");
        assertNull(deletedPlanet);
    }
}
