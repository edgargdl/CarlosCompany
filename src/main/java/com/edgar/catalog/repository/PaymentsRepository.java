package com.edgar.catalog.repository;

import com.edgar.catalog.models.Customer;
import com.edgar.catalog.models.Payment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends BaseMovementsRepository<Payment> {

    List<Payment> findByCustomer(@Param("customer") final Customer customer);

}
