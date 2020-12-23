package com.kratonsolution.belian.tengkawang.model;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Getter
public class AttendanceOrganizationChangeEvent extends ApplicationEvent {

	private static final long serialVersionUID = -7824163437652593185L;

	private List<String> employeeNames;
	
	private String organizationName;
	
	public AttendanceOrganizationChangeEvent(Object source, @NonNull List<String> employeeNames, @NonNull String organizationName) {
		
		super(source);
		this.employeeNames = employeeNames;
		this.organizationName = organizationName;
	}
}
