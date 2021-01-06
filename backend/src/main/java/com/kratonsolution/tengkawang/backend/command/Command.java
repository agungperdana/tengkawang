package com.kratonsolution.tengkawang.backend.command;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Vector;

import com.google.common.base.MoreObjects;

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
	
//	private boolean executed = false;
	
	@NonNull
	private String deviceSN;
	
	public abstract String getCommandString();
	
	private int sendingCount=0;
	
	@NonNull
	private String code;
	
	private String operation;
	
	private LocalDateTime lastSend;
	
	private LocalDateTime nextSchedule;

	private Vector<Command> childs = new Vector<>();
	
	@Override
		public String toString() {
			return MoreObjects.toStringHelper(getClass())
					.add("code", code)
					.add("child", childs)
					.toString();
		}
}
