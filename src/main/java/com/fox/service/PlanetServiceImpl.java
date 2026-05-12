package com.fox.service;

import com.fox.dao.PlanetDaoService;
import com.fox.entity.Planet;

public class PlanetServiceImpl implements PlanetService {

    private static final int MAX_ID_LENGTH = 20;
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 500;
    private static final String PLANET_ID_PATTERN = "^[A-Z0-9]+$";

    private final PlanetDaoService daoService;

    public PlanetServiceImpl(PlanetDaoService daoService) {
        this.daoService = daoService;
    }

    @Override
    public void create(String id, String name) {
        String normalizedId = validateAndNormalizeId(id);
        String normalizedName = validateAndNormalizeName(name);
        daoService.create(new Planet(normalizedId, normalizedName));
    }

    @Override
    public Planet getById(String id) {
        String normalizedId = validateAndNormalizeId(id);
        return daoService.getById(normalizedId);
    }

    @Override
    public void update(String id, String name) {
        String normalizedId = validateAndNormalizeId(id);
        String normalizedName = validateAndNormalizeName(name);

        Planet planet = daoService.getById(normalizedId);
        if (planet == null) {
            throw new IllegalArgumentException("Planet with id " + normalizedId + " not found");
        }

        planet.setName(normalizedName);
        daoService.update(planet);
    }

    @Override
    public void deleteById(String id) {
        String normalizedId = validateAndNormalizeId(id);
        daoService.deleteById(normalizedId);
    }

    private String validateAndNormalizeId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Planet id must not be null");
        }

        String normalizedId = id.trim();
        if (normalizedId.isEmpty() || normalizedId.length() > MAX_ID_LENGTH) {
            throw new IllegalArgumentException("Planet id length must be between 1 and " + MAX_ID_LENGTH);
        }

        if (!normalizedId.matches(PLANET_ID_PATTERN)) {
            throw new IllegalArgumentException("Planet id must contain only uppercase Latin letters and digits");
        }

        return normalizedId;
    }

    private String validateAndNormalizeName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Planet name must not be null");
        }

        String normalizedName = name.trim();
        if (normalizedName.length() < MIN_NAME_LENGTH || normalizedName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "Planet name length must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH
            );
        }

        return normalizedName;
    }
}
