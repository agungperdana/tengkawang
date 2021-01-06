package com.kratonsolution.tengkawang.backend.command;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public class GenericCommand extends Command {

	private static final long serialVersionUID = 3838177858113392034L;

	private String command;
	
	GenericCommand() {
	}
	
	public GenericCommand(@NonNull String deviceSN, @NonNull String code, @NonNull String command) {
		setDeviceSN(deviceSN);
		setCode(code);
		this.command = command;
	}
	
	@Override
	public String getCommandString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("C:");
		builder.append(getCode());
		builder.append(":").append(this.command);
		
		return builder.toString();
	}

}
