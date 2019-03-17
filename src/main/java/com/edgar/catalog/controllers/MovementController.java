package com.edgar.catalog.controllers;

import com.edgar.catalog.models.Catalog;
import com.edgar.catalog.models.Movement;
import com.edgar.catalog.repository.AccountRepository;
import com.edgar.catalog.repository.CatalogRepository;
import com.edgar.catalog.repository.MovementsRepository;
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
    private MovementsRepository movementsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @GetMapping("movements")
    public List<Movement> retrieveAllMovements()
    {
        return this.movementsRepository.findAll();
    }

    @GetMapping("movements/account/{id}")
    public ResponseEntity<List<Movement>> retrieveAllMovementsByAccount(@PathVariable long id)
    {
        return  accountRepository.findById(id)
                .map(account -> this.movementsRepository.findByAccount(account))
                .map(movements -> ResponseEntity.ok().body(movements))
                .orElse(ResponseEntity.notFound().build());


    }

    @GetMapping("movements/catalog/{id}")
    public ResponseEntity<List<Movement>> retrieveAllMovementsByCatalog(@PathVariable long id)
    {
      return catalogRepository.findById(id)
              .map(catalog -> this.movementsRepository.findByCatalog(catalog))
              .map(movements -> ResponseEntity.ok().body(movements))
              .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("movements/{id}")
    public ResponseEntity<Movement> retrieveMovement(@PathVariable long id)
    {
        return this.movementsRepository.findById(id)
                .map(movement -> ResponseEntity.ok().body(movement))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("movements/{id}")
    public void deleteMovement(@PathVariable long id)
    {
        this.movementsRepository.deleteById(id);
    }

    @PostMapping("movements")
    public ResponseEntity<Object> createMovement(@RequestBody Movement account)
    {
        Movement savedMovement = this.movementsRepository.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMovement.getId())
                .toUri();

        return  ResponseEntity.created(location).build();
    }



    @PutMapping("movements/{id}")
    public ResponseEntity<Object> updateMovement(@RequestBody Movement movement, @PathVariable long id) {

        Optional<Movement> movementOptional = this.movementsRepository.findById(id);

        if (!movementOptional.isPresent())
            return ResponseEntity.notFound().build();

        movement.setId(id);

        this.movementsRepository.save(movement);

        return ResponseEntity.noContent().build();
    }
}
