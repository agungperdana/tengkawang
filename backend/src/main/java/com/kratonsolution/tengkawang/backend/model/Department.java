package com.kratonsolution.tengkawang.backend.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "department")
public class Department {

	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "organization")
	private String organization;
	
	@JsonIgnore
	@Version
	private Long version;
	
	public Department() {
	}
}
