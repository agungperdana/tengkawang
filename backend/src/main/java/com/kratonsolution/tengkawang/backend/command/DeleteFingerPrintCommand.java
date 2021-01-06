package com.kratonsolution.tengkawang.backend.command;

import com.kratonsolution.tengkawang.backend.model.Employee;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public class DeleteFingerPrintCommand extends Command {

	private static final long serialVersionUID = 3838177858113392034L;
	
	private Employee emp;
	
	DeleteFingerPrintCommand() {
	}
	
	public DeleteFingerPrintCommand(@NonNull String deviceSN, @NonNull String code, @NonNull Employee emp) {

		setDeviceSN(deviceSN);
		setCode(code);
		this.emp = emp;
	}
	
	@Override
	public String getCommandString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("C:");
		builder.append(getCode());
		builder.append(":DATA DEL_FP ");

		this.emp.getFingerInfos().forEach(info -> {
			
			builder.append("PIN=").append(emp.getNumber()).append("\t");
			builder.append("FID=").append(info.getFID());
			builder.append("\n\r");
		});
		
		return builder.toString();
	}

}
