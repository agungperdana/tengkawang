package com.kratonsolution.tengkawang.backend.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "employee")
public class Employee {

	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name = "number")
	private String number;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "on_device_name")
	private String onDeviceName;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "organization")
	private String organization;
	
	@Column(name = "privilege")
	@Enumerated(EnumType.STRING)
	private Privilege privilege = Privilege.User;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "card")
	private String card;
	
	@Column(name = "employee_group")
	private EmployeeGroup group = EmployeeGroup.UserTimeZone;
		
	@JsonIgnore
	@Version
	private Long version;
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<FingerInfo> fingerInfos = new HashSet<>();
	
	public Employee() {
	}
	
	public void addFingerInfo(@NonNull FingerInfo info) {
		
		if(fingerInfos.stream().anyMatch(p->p.getFID().equals(info.getFID()))) {
			
			Optional<FingerInfo> opt = fingerInfos.stream().filter(p->p.getFID().equals(info.getFID())).findFirst();
			if(opt.isPresent()) {
				
				opt.get().setSize(info.getSize());
				opt.get().setTemplate(info.getTemplate());
				opt.get().setValid(info.getValid());
			}

		}
		else {
			
			info.setEmployee(this);
			fingerInfos.add(info);
		}
	}
}
