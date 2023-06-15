package com.happydev.client.api.rest.models.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happydev.client.api.rest.models.dao.IClienteDao;
import com.happydev.client.api.rest.models.entity.Client;

@Service
public class ClientServiceImplement implements IClientService {

	private final IClienteDao clienteDao;
	
	public ClientServiceImplement(IClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Client findById(Long id) {
		return clienteDao.findById(id).orElseThrow(() -> new NoSuchElementException("El Cliente no existe"));
	}

	@Override
	@Transactional
	public Client save(Client client) {
		return clienteDao.save(client);
	}

	@Override
	@Transactional
	public void delete(Long id) {		
	}



}
