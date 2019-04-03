package com.edgar.catalog.controllers;

import com.edgar.catalog.models.Customer;
import com.edgar.catalog.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/v1/")
public class CustomersController {

    @Autowired
    private CustomersRepository customersRepository;

    @GetMapping("customers")
    public List<Customer> retrieveAllCustomers() {
        return this.customersRepository.findAll();
    }


    @GetMapping("customers/{id}")
    public ResponseEntity<Customer> retrieveCustomer(@PathVariable long id) {
        return this.customersRepository.findById(id)
                .map(customer -> ResponseEntity.ok().body(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("customers/{id}")
    public void deleteCustomer(@PathVariable long id) {
        this.customersRepository.deleteById(id);
    }

    @PostMapping("customers")
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        Customer savedCatalog = this.customersRepository.save(customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCatalog.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping("customers/{id}")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable long id) {

        Optional<Customer> customerOptional = this.customersRepository.findById(id);

        if (!customerOptional.isPresent())
            return ResponseEntity.notFound().build();

        customer.setId(id);

        this.customersRepository.save(customer);

        return ResponseEntity.noContent().build();
    }
}
