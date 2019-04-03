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
@Table(name = "movements")
public class GenericMovement extends Movement {

    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "catalog_id", referencedColumnName = "id")
    private Catalog catalog;
    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

}
