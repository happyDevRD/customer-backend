package com.happydev.client.api.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happydev.client.api.rest.models.entity.Client;
import com.happydev.client.api.rest.models.services.IClientService;


@CrossOrigin(origins =  {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {

	@Autowired
	private IClientService clientService;
	
	@GetMapping("/clientes")
	public List<Client> index(){
		return clientService.findAll();
	}
}
