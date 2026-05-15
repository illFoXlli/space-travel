package com.fox.config;

import io.github.cdimascio.dotenv.Dotenv;

public final class EnvConfig {

    private static final Dotenv DOTENV = Dotenv.configure()
            .directory(".")
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    private EnvConfig() {
    }

    public static String getRequired(String key) {
        String systemValue = System.getenv(key);
        if (systemValue != null) {
            return systemValue.trim();
        }

        String dotenvValue = DOTENV.get(key);
        if (dotenvValue != null) {
            return dotenvValue.trim();
        }

        throw new IllegalStateException("Required environment variable is missing: " + key);
    }
}
