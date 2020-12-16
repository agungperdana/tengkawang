package com.kratonsolution.belian.tengkawang.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "finger_info")
public class FingerInfo {
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(name = "FID")
	private String FID;
	
	@Column(name = "FTMP")
	private String template;
	
	@Column(name = "size")
	private String size;
	
	@Column(name = "valid")
	private String valid;
	
	@Version
	private Long version;
}
