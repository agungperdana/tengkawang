package com.kratonsolution.belian.tengkawang.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "finger_info_id")
	private FingerInfo fingerInfo;
	
	@Version
	private Long version;
	
	public Employee() {
	}
	
}
