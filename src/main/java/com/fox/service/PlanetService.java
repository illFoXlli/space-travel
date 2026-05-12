package com.fox.service;

import com.fox.entity.Planet;

public interface PlanetService {

    void create(String id, String name);

    Planet getById(String id);

    void update(String id, String name);

    void deleteById(String id);
}
