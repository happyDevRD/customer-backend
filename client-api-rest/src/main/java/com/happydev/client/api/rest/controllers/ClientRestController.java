package com.happydev.client.api.rest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.happydev.client.api.rest.models.entity.Client;
import com.happydev.client.api.rest.models.services.IClientService;


@CrossOrigin(origins =  {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController  {
	
	private final IClientService clientService;

	public ClientRestController(IClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping("/clientes")
	public List<Client> index(){
		return clientService.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public Client show(@PathVariable Long id ) {
		return this.clientService.findById(id);
	}

	@PostMapping("/clientes")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Client create(@RequestBody Client client ) {
		return this.clientService.save(client);
	}
	
	@PutMapping("/clientes/{id}")
	public Client update(@RequestBody Client client , @PathVariable Long id) {
		Client currentClient = clientService.findById(id);
		
		currentClient.setLastName(client.getLastName());
		currentClient.setAddress(client.getAddress());
		currentClient.setFirstName(client.getFirstName());
		currentClient.setMail(client.getMail());	
		this.clientService.save(currentClient);
		
		return currentClient;
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id ) {
		clientService.delete(id);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
	    return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
