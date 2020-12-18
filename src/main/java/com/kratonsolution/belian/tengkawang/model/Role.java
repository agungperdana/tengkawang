package com.kratonsolution.belian.tengkawang.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
	
	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name = "name")
	private String name;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "is_root")
	private boolean root;
	
	@Version
	private Long version;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<RoleAccess> grantedAccess = new HashSet<>();
	
	public void grantAccessTo(@NonNull RoleAccess access) {
		
		access.setRole(this);
		getGrantedAccess().add(access);
	}
}
