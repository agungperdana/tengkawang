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
@Table(name = "organization")
public class Organization {

	public static final String DEFAULT = "DEFT ORG";
	
	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name = "name")
	private String name;

	@Column(name = "parent")
	private String parent;

	@Column(name = "comment")
	private String comment;

	@JsonIgnore
	@Version
	private Long version;

	public Organization() {
	}
}
