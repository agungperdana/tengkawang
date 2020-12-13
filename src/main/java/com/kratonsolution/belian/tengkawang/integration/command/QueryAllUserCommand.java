package com.kratonsolution.belian.tengkawang.integration.command;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public class QueryAllUserCommand extends Command {

	private static final long serialVersionUID = 3838177858113392034L;

	QueryAllUserCommand() {
	}
	
	public QueryAllUserCommand(@NonNull String deviceSN) {
		setDeviceSN(deviceSN);
	}
	
	@Override
	public String getCommandString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("C:");
		builder.append(getId());
		builder.append(":INFO");
		
		return builder.toString();
	}

}
