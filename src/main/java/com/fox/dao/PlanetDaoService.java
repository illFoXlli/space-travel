package com.fox.dao;

import com.fox.entity.Planet;

public interface PlanetDaoService {

    void create(Planet planet);

    Planet getById(String id);

    void update(Planet planet);

    void deleteById(String id);
}
