package com.fox.config;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {

    public static void init() {
        String dbUrl = EnvConfig.getRequired("DB_URL");
        String dbUser = EnvConfig.getRequired("DB_USER");
        String dbPassword = EnvConfig.getRequired("DB_PASSWORD");

        Flyway flyway = Flyway.configure()
                .dataSource(dbUrl, dbUser, dbPassword)
                .load();

        flyway.migrate();
    }
}
