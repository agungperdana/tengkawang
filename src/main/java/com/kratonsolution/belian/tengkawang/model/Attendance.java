package com.kratonsolution.belian.tengkawang.model;

import java.time.Instant;
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
@Table(name = "attendance")
public class Attendance {
	
	@Id
	public String id = UUID.randomUUID().toString();
	
	@Column(name = "device")
	public String device;
	
	@Column(name = "event_time")
	public Instant time;
	
	@Column(name = "employee_number")
	public String employeeNumber;
	
	@Column(name = "employee_name")
	public String employeeName;

	@Column(name = "event_type")
	@Enumerated(EnumType.STRING)
	public AttendanceEventType type;
	
	@Column(name = "verification_type")
	@Enumerated(EnumType.STRING)
	public VerificationType verificationType = VerificationType.Password;
	
	@Column(name = "event_location")
	public String eventLocation;
	
	@Version
	public Long version;
	
	public Attendance() {
	}
}
