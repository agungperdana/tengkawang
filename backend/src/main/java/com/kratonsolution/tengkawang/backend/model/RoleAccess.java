package com.kratonsolution.tengkawang.backend.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "role_access")
public class RoleAccess {
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	@ManyToOne
	@JoinColumn(name = "fk_role")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "fk_menu")
	private Menu menu;
	
	@Column(name="can_create")
	private boolean create;
	
	@Column(name="can_read")
	private boolean read;
	
	@Column(name="can_update")
	private boolean update;
	
	@Column(name="can_delete")
	private boolean delete;
	
	@Column(name="can_print")
	private boolean print;
	
	@Version
	private Long version;
}
