package com.edgar.catalog.repository;

import com.edgar.catalog.models.Account;
import com.edgar.catalog.models.Catalog;
import com.edgar.catalog.models.GenericMovement;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenericMovementsRepository extends BaseMovementsRepository<GenericMovement> {

    List<GenericMovement> findByCatalog(@Param("catalog") final Catalog catalog);

    List<GenericMovement> findByAccount(@Param("account") final Account account);
}
