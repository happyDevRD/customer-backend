package com.happydev.client.api.rest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.happydev.client.api.rest.models.entity.Address;

public interface IAddressDao extends CrudRepository<Address, Long>{

}
