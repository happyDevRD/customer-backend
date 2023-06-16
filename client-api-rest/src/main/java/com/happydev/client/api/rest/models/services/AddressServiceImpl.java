package com.happydev.client.api.rest.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happydev.client.api.rest.models.dao.IAddressDao;
import com.happydev.client.api.rest.models.entity.Address;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AddressServiceImpl implements IAddressService {

    private final IAddressDao addressDao;

    @Autowired
    public AddressServiceImpl(IAddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return (List<Address>) addressDao.findAll();
    }

    @Override
    public Address saveAddress(Address address) {
        return addressDao.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressDao.deleteById(id);
    }

    @Override
    public Address findById(Long id) {
        return addressDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La dirección no existe"));
    }
}