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

import com.happydev.client.api.rest.models.entity.Address;
import com.happydev.client.api.rest.models.entity.Client;
import com.happydev.client.api.rest.models.services.IAddressService;
import com.happydev.client.api.rest.models.services.IClientService;

@CrossOrigin(origins =  {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {

    private final IClientService clientService;
    private final IAddressService addressService;

    public ClientRestController(IClientService clientService, IAddressService addressService) {
        this.clientService = clientService;
        this.addressService = addressService;
    }

    @GetMapping("/clientes")
    public List<Client> index() {
        return clientService.findAll();
    }

    @GetMapping("/clientes/{id}")
    public Client show(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping("/clientes")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        return clientService.save(client);
    }

    @PutMapping("/clientes/{id}")
    public Client update(@RequestBody Client client, @PathVariable Long id) {
        Client currentClient = clientService.findById(id);

        currentClient.setLastName(client.getLastName());
        currentClient.setAddress(client.getAddress());
        currentClient.setFirstName(client.getFirstName());
        currentClient.setMail(client.getMail());
        clientService.save(currentClient);

        return currentClient;
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @GetMapping("/clientes/{clientId}/direcciones")
    public List<Address> getAddressByClientId(@PathVariable Long clientId) {
        Client client = clientService.findById(clientId);
        return client.getAddress();
    }

    @GetMapping("/direcciones/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return addressService.findById(id);
    }

    @PostMapping("/clientes/{clientId}/direcciones")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Address createAddress(@PathVariable Long clientId, @RequestBody Address address) {
        Client client = clientService.findById(clientId);
        address.setClient(client);
        return addressService.saveAddress(address);
    }

    @PutMapping("/direcciones/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address address) {
        Address currentAddress = addressService.findById(id);
        currentAddress.setStreet(address.getStreet());
        currentAddress.setCity(address.getCity());
        addressService.saveAddress(currentAddress);
        return currentAddress;
    }

    @DeleteMapping("/direcciones/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
