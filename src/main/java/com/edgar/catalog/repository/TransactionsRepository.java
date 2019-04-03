package com.edgar.catalog.repository;

import com.edgar.catalog.models.Account;
import com.edgar.catalog.models.Transaction;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends BaseMovementsRepository<Transaction> {

    List<Transaction> findByDestination(@Param("destination") final Account account);

    List<Transaction> findBySource(@Param("source") final Account account);
}
