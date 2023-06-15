package com.happydev.client.api.rest.models.services;

import java.util.List;

import com.happydev.client.api.rest.models.entity.Client;

public interface IClientService {

	List<Client> findAll();

	Client findById(Long id);

	Client save(Client client);

	void delete(Long id);

}
