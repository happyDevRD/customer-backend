package com.happydev.client.api.rest.models.services;

import java.util.List;

import com.happydev.client.api.rest.models.entity.Address;

public interface IAddressService {

	List<Address> findAll();

	Address findById(Long id);

	Address saveAddress(Address address);

	void deleteAddress(Long id);
	
	

}
