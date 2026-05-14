package com.fox.service;

import com.fox.dao.ClientDaoService;
import com.fox.entity.Client;

public class ClientServiceImpl implements ClientService {

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 200;

    private final ClientDaoService daoService;

    public ClientServiceImpl(ClientDaoService daoService) {
        this.daoService = daoService;
    }

    @Override
    public long create(String name) {
        validateName(name);
        return daoService.create(new Client(name.trim()));
    }

    @Override
    public Client getById(long id) {
        return daoService.getById(id);
    }

    @Override
    public void update(long id, String name) {
        validateName(name);

        Client client = daoService.getById(id);
        if (client == null) {
            throw new IllegalArgumentException("Client with id " + id + " not found");
        }

        client.setName(name.trim());
        daoService.update(client);
    }

    @Override
    public void deleteById(long id) {
        daoService.deleteById(id);
    }

    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Client name must not be null");
        }

        String normalizedName = name.trim();
        if (normalizedName.length() < MIN_NAME_LENGTH
                || normalizedName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "Client name length must be between "
                            + MIN_NAME_LENGTH
                            + " and "
                            + MAX_NAME_LENGTH
            );
        }
    }
}
