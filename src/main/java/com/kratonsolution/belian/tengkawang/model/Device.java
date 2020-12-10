package com.kratonsolution.belian.tengkawang.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "device")
public class Device implements Serializable{

	private static final long serialVersionUID = 6055408642604681879L;

	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(name = "serial")
	private String serial;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "ip")
	private String ip;

	@Column(name = "organization")
	private String organization;
	
	@Column(name = "option")
	private String option;
	
	@Column(name = "comment")
	private String comment;
	
	@Version
	private Long version;
	
	public Device() {
	}
	
	public Device(@NonNull String serial, @NonNull String name, @NonNull String ipAddress) {
		
		this.serial = serial;
		this.ip = ipAddress;
	}
	
	public String shellCmd(@NonNull String command) {
		return null;
	}
	
	public String refresh() {
		return null;
	}
	
	public String clear() {
		return null;
	}
	
	public String info() {
		return null;
	}
	
	public String setOption(@NonNull String option) {
		return null;
	}
	
	public String reboot() {
		return "C:"+UUID.randomUUID().toString()+":REBOOT";
	}
}
