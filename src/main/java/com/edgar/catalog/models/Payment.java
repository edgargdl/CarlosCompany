package com.edgar.catalog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends Movement {

    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
}
