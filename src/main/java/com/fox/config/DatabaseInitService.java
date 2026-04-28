package com.fox.config;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {

    public static void init() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:./db/space_travel", "fox", "")
                .load();

        flyway.migrate();
    }
}