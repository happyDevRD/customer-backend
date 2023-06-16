package com.happydev.client.api.rest.initializer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.happydev.client.api.rest.models.entity.Address;
import com.happydev.client.api.rest.models.entity.Client;
import com.happydev.client.api.rest.models.services.IAddressService;
import com.happydev.client.api.rest.models.services.IClientService;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {
	
	private final IClientService clientService;
	private final IAddressService addressService;
	
	public DataInitializer(IClientService clientService, IAddressService addressService) {
		this.addressService = addressService;
		this.clientService = clientService;
	}

	@PostConstruct
	public void init() {
		Client client1 = new Client();
		client1.setFirstName("John");
		client1.setLastName("Doe");
		client1.setMail("john.doe@example.com");

		List<Address> addresses1 = new ArrayList<>();
	        
		Address address1 = new Address();
		address1.setStreet("123 Main St");
		address1.setCity("City 1");
		address1.setClient(client1);
		addresses1.add(address1);

		Address address2 = new Address();
		address2.setStreet("456 Elm St");
		address2.setCity("City 2");
		address2.setClient(client1);
		addresses1.add(address2);

		client1.setAddress(addresses1);
		clientService.save(client1);
	}
}