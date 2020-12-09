package com.kratonsolution.belian.tengkawang.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name = "number")
	private String number;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "privilege")
	@Enumerated(EnumType.STRING)
	private Privilege privilege;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "card")
	private String card;
	
	@Version
	private Long version;
	
	public Employee() {
	}
	
}