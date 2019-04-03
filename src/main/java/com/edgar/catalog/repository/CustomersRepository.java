package com.edgar.catalog.repository;

import com.edgar.catalog.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Long> {

}
