package com.kratonsolution.tengkawang.backend.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.jasypt.util.password.StrongPasswordEncryptor;

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
@Table(name = "user")
public class User {
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "organization")
	private String organization;
	
	@Version
	private Long version;
	
	public void edit(@NonNull String name, @NonNull String oldPassword, @NonNull String newPassword, @NonNull String organization, @NonNull String role) {
		
		if(!this.name.equals(name)) {
			this.name = name;
		}
		
		if(!this.role.equals(role)) {
			this.role = role;
		}

		this.organization = organization;
		
		if(!oldPassword.equals(newPassword)) {
			
			StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
			if(encryptor.checkPassword(oldPassword, this.password)) {
				this.password = encryptor.encryptPassword(newPassword);
			}
		}
	}
}
