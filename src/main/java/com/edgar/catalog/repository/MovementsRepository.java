package com.edgar.catalog.repository;

import com.edgar.catalog.models.Movement;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementsRepository extends BaseMovementsRepository<Movement> {
}
