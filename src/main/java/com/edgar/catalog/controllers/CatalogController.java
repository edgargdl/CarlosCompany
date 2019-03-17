package com.edgar.catalog.controllers;

import com.edgar.catalog.models.Catalog;
import com.edgar.catalog.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/v1/")
public class CatalogController {

    @Autowired
    private CatalogRepository catalogRepository;

    @GetMapping("catalogs")
    public List<Catalog> retrieveAllCatalogs()
    {
        return this.catalogRepository.findAll();
    }


    @GetMapping("catalogs/{id}")
    public ResponseEntity<Catalog> retrieveCatalog(@PathVariable long id)
    {
        return this.catalogRepository.findById(id)
                .map( catalog -> ResponseEntity.ok().body(catalog))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("catalogs/{id}")
    public void deleteCatalog(@PathVariable long id)
    {
        this.catalogRepository.deleteById(id);
    }

    @PostMapping("catalogs")
    public ResponseEntity<Object> createCatalog(@RequestBody Catalog account)
    {
        Catalog savedCatalog = this.catalogRepository.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCatalog.getId())
                .toUri();

        return  ResponseEntity.created(location).build();
    }



    @PutMapping("catalogs/{id}")
    public ResponseEntity<Object> updateCatalog(@RequestBody Catalog account, @PathVariable long id) {

        Optional<Catalog> catalogOptional = this.catalogRepository.findById(id);

        if (!catalogOptional.isPresent())
            return ResponseEntity.notFound().build();

        account.setId(id);

        this.catalogRepository.save(account);

        return ResponseEntity.noContent().build();
    }
}
