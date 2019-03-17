package com.edgar.catalog.models;



// default package
// Generated 13-mar-2019 19:13:41 by Hibernate Tools 5.3.6.Final

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
//JPA
@Entity
@Table(name="catalogs")
public class Catalog implements Serializable {

	@Id
	@GeneratedValue
	private long id;
	private String name;


}
