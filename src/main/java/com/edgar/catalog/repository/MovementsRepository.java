package com.edgar.catalog.repository;

import com.edgar.catalog.models.Account;
import com.edgar.catalog.models.Catalog;
import com.edgar.catalog.models.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementsRepository extends JpaRepository<Movement,Long> {


    List<Movement> findByCatalog(@Param("catalog") final Catalog catalog);

    List<Movement> findByAccount(@Param("account") final Account account);

}
