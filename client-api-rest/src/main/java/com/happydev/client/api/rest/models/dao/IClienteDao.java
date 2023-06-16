package com.happydev.client.api.rest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.happydev.client.api.rest.models.entity.Client;

public interface IClienteDao extends CrudRepository<Client, Long>{
	

}
