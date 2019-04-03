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
@Table(name = "transactions")
public class Transaction extends Movement {

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    private Account destination;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private Account source;
}
