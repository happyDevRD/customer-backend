package com.happydev.client.api.rest.models.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happydev.client.api.rest.models.dao.IAddressDao;
import com.happydev.client.api.rest.models.entity.Address;

@Service
public class AddressServiceImplement implements IAddressService {

	private final IAddressDao repositoryAddress;

	public AddressServiceImplement(IAddressDao repositoryAddress) {
		this.repositoryAddress = repositoryAddress;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Address> findAll() {
		return (List<Address>) repositoryAddress.findAll();
	}

	@Override
	public Address saveAddress(Address address) {
		return repositoryAddress.save(address);
	}

	@Override
	public void deleteAddress(Long id) {
		repositoryAddress.deleteById(id);
	}

	@Override
	public Address findById(Long id) {
		return repositoryAddress.findById(id)
				.orElseThrow(() -> new NoSuchElementException("la direccion no existe"));
	}

}
