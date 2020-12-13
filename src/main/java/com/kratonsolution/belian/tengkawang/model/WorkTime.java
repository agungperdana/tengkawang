package com.kratonsolution.belian.tengkawang.model;

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
@Table(name = "work_time")
public class WorkTime {

	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "start_time")
	private LocalTime start;
	
	@Column(name = "end_time")
	private LocalTime end;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private AttendanceEventType type;
	
	@Version
	private Long version;
}
