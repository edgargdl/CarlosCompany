package com.edgar.catalog.controllers;

import com.edgar.catalog.models.Account;
import com.edgar.catalog.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/v1/")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("accounts")
    public List<Account> retrieveAllAccounts()
    {
        return this.accountRepository.findAll();
    }


    @GetMapping("accounts/{id}")
    public ResponseEntity<Account> retrieveAccount(@PathVariable long id)
    {
        return this.accountRepository.findById(id)
                .map( account -> ResponseEntity.ok().body(account))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("accounts/{id}")
    public void deleteAccount(@PathVariable long id)
    {
        this.accountRepository.deleteById(id);
    }

    @PostMapping("accounts")
    public ResponseEntity<Object> createAccount(@RequestBody Account account)
    {
        Account savedAccount = this.accountRepository.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAccount.getId())
                .toUri();

        return  ResponseEntity.created(location).build();
    }



    @PutMapping("accounts/{id}")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable long id) {

        Optional<Account> accountOptional = this.accountRepository.findById(id);

        if (!accountOptional.isPresent())
            return ResponseEntity.notFound().build();

        account.setId(id);

        this.accountRepository.save(account);

        return ResponseEntity.noContent().build();
    }



}
