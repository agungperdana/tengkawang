package com.kratonsolution.belian.tengkawang.integration.command;

import java.io.Serializable;
import java.time.Instant;
import java.util.Vector;

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
public abstract class Command implements Serializable {
	
	private static final long serialVersionUID = -3512903707332424198L;

	private Instant issued = Instant.now();
	
	private boolean executed = false;
	
	@NonNull
	private String deviceSN;
	
	private Vector<Vector<String>> content = new Vector<>();
	
	public abstract String getCommandString();
	
	private int sendingCount=0;
	
	@NonNull
	private String code;
	
	private String operation;
}
