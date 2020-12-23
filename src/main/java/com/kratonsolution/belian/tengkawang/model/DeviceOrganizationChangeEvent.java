package com.kratonsolution.belian.tengkawang.model;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Getter
public class DeviceOrganizationChangeEvent extends ApplicationEvent {

	private static final long serialVersionUID = -5817187478946607127L;

	private String deviceSerial;
	
	private String organizationName;
	
	public DeviceOrganizationChangeEvent(Object source, @NonNull String deviceSerial, @NonNull String organizationName) {
		
		super(source);
		this.deviceSerial = deviceSerial;
		this.organizationName = organizationName;
	}
}
