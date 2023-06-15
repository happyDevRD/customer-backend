package com.happydev.client.api.rest.models.services;

import java.util.List;

import com.happydev.client.api.rest.models.entity.Client;

public interface IClientService {

	public List<Client> findAll();
}
