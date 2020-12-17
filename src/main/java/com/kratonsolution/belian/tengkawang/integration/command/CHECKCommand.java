package com.kratonsolution.belian.tengkawang.integration.command;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public class CHECKCommand extends Command {

	private static final long serialVersionUID = 3838177858113392034L;

	CHECKCommand() {
	}
	
	public CHECKCommand(@NonNull String deviceSN, @NonNull String code) {
		setDeviceSN(deviceSN);
		setCode(code);
	}
	
	@Override
	public String getCommandString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("C:");
		builder.append(getCode());
		builder.append(":CHECK");
		
		return builder.toString();
	}

}
