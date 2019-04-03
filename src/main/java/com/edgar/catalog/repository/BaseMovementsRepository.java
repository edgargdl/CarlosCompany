package com.edgar.catalog.repository;

import com.edgar.catalog.models.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base repository class for all the movements types
 *
 * @param <T>
 */
@NoRepositoryBean
public interface BaseMovementsRepository<T extends Movement> extends JpaRepository<T, Long> {

}
