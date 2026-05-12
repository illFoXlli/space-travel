package com.fox.dao;

import com.fox.entity.Client;

public interface ClientDaoService {

    long create(Client client);

    Client getById(long id);

    void update(Client client);

    void deleteById(long id);
}
