package com.kratonsolution.belian.tengkawang.model;

import java.time.LocalDate;
import java.time.LocalTime;
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
	private String id = UUID.randomUUID().toString();
	
	@Column(name = "device")
	private String device;
	
	@Column(name = "event_date")
	private LocalDate date;
	
	@Column(name = "event_time")
	private LocalTime time;
	
	@Column(name = "employee_number")
	private String employeeNumber;
	
	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "event_type")
	@Enumerated(EnumType.STRING)
	private AttendanceEventType type;
	
	@Column(name = "verification_type")
	@Enumerated(EnumType.STRING)
	private VerificationType verificationType = VerificationType.Password;
	
	@Column(name = "event_location")
	private String eventLocation;
	
	@Version
	private Long version;
	
	public Attendance() {
	}
}
