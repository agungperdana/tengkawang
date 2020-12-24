package com.kratonsolution.belian.tengkawang.integration.command;

import com.kratonsolution.belian.tengkawang.model.Employee;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public class USERCommand extends Command {

	private static final long serialVersionUID = 3838177858113392034L;

	private Employee employee;
	
	public static String UPDATE = "USER ";
	
	public static String DELETE = "DEL_USER ";
	
	USERCommand() {
	}
	
	public USERCommand(@NonNull String deviceSN, @NonNull String code, @NonNull Employee employee, @NonNull String operation) {
		setDeviceSN(deviceSN);
		setCode(code);
		this.employee = employee;
		setOperation(operation);
	}
	
	@Override
	public String getCommandString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("C:");
		builder.append(getCode());

		if(getOperation().equals(UPDATE)) {
			
			builder.append(":DATA ").append(getOperation());
			builder.append("PIN=").append(employee.getNumber()).append("\t");
			builder.append("Name=").append(employee.getOnDeviceName()).append("\t");
			builder.append("Pri=").append(employee.getPrivilege().toString()).append("\t");
			builder.append("Passwd=").append(employee.getPassword()).append("\t");
			builder.append("Card=").append("").append("\t");
			builder.append("Grp=").append(employee.getGroup()).append("\t");
			builder.append("TZ=").append("7").append("\t");
			
			if(employee.getFingerInfo() != null) {
				builder.append("\n\r");
				builder.append("FP PIN=").append(employee.getNumber()).append("\t");
				builder.append("PID=").append(employee.getFingerInfo().getFID()).append("\t");
				builder.append("Size=").append(employee.getFingerInfo().getSize()).append("\t");
				builder.append("Valid=").append(employee.getFingerInfo().getValid()).append("\t");
				builder.append("TMP=").append(employee.getFingerInfo().getTemplate()).append("\t");
			}
			
		}
		else {
			
			builder.append(":DATA ").append(DELETE);
			builder.append("PIN=").append(employee.getNumber()).append("\t");
		}
		
		return builder.toString();
	}

}
