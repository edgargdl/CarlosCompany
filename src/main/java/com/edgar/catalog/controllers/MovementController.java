package com.edgar.catalog.controllers;

import com.edgar.catalog.models.GenericMovement;
import com.edgar.catalog.models.Movement;
import com.edgar.catalog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/v1/")
public class MovementController {

    @Autowired
    private GenericMovementsRepository genericMovementsRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private MovementsRepository movementsRepository;

    //Endpoints to interact with all the movements using the super class

    @GetMapping("movements")
    public ResponseEntity<List<Movement>> retrieveAllMovements() {
        return Optional.ofNullable(this.movementsRepository.findAll())
                .map(movements -> ResponseEntity.ok().body(movements))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("movements/{id}")
    public ResponseEntity<Movement> retrieveMovement(@PathVariable long id) {
        return this.movementsRepository.findById(id)
                .map(movement -> ResponseEntity.ok().body(movement))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("movements/{id}")
    public ResponseEntity deleteMovement(@PathVariable long id) {
        this.genericMovementsRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("movements")
    public ResponseEntity createMovement(@RequestBody Movement account) {
        Movement savedMovement = this.movementsRepository.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMovement.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("movements/{id}")
    public ResponseEntity updateMovement(@RequestBody Movement movement, @PathVariable long id) {

        Optional<Movement> movementOptional = this.movementsRepository.findById(id);

        if (!movementOptional.isPresent())
            return ResponseEntity.notFound().build();

        movement.setId(id);

        this.movementsRepository.save(movement);

        return ResponseEntity.noContent().build();
    }

    //Endpoints to interact with one of the subtypes of movements

    @GetMapping("movements/generic")
    public ResponseEntity<List<GenericMovement>> retrieveAllGenericMovements() {
        return Optional.ofNullable(this.genericMovementsRepository.findAll())
                .map(movements -> ResponseEntity.ok().body(movements))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("movements/generic/{id}")
    public ResponseEntity<GenericMovement> retrieveGenericMovement(@PathVariable long id) {
        return this.genericMovementsRepository.findById(id)
                .map(movement -> ResponseEntity.ok().body(movement))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("movements/generic/account/{id}")
    public ResponseEntity<List<GenericMovement>> retrieveAllMovementsByAccount(@PathVariable long id) {
        return accountRepository.findById(id)
                .map(account -> this.genericMovementsRepository.findByAccount(account))
                .map(movements -> ResponseEntity.ok().body(movements))
                .orElse(ResponseEntity.notFound().build());


    }

    @GetMapping("movements/generic/catalog/{id}")
    public ResponseEntity<List<GenericMovement>> retrieveAllMovementsByCatalog(@PathVariable long id) {
        return catalogRepository.findById(id)
                .map(catalog -> this.genericMovementsRepository.findByCatalog(catalog))
                .map(movements -> ResponseEntity.ok().body(movements))
                .orElse(ResponseEntity.notFound().build());
    }


}
