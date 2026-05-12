package com.fox.service;

import com.fox.entity.Client;

public interface ClientService {

    long create(String name);

    Client getById(long id);

    void update(long id, String name);

    void deleteById(long id);
}
