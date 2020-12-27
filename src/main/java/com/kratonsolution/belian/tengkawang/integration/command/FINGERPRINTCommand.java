package com.kratonsolution.belian.tengkawang.integration.command;

import com.kratonsolution.belian.tengkawang.model.Employee;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public class FINGERPRINTCommand extends Command {

	private static final long serialVersionUID = 3838177858113392034L;
	
	private Employee emp;
	
	public static String DELETE = "DELETE";
	
	public static String UPDATE = "UPDATE";

	private String action = UPDATE;
	
	FINGERPRINTCommand() {
	}
	
	public FINGERPRINTCommand(@NonNull String deviceSN, @NonNull String code, @NonNull Employee emp, @NonNull String action) {
		setDeviceSN(deviceSN);
		setCode(code);
		this.emp = emp;
		this.action = action;
	}
	
	@Override
	public String getCommandString() {
		
		StringBuilder builder = new StringBuilder();
		
		if(this.emp.getFingerInfo() != null) {
			
			builder.append("C:");
			builder.append(getCode());
			
			if(this.action.equals(UPDATE)) {
				
				builder.append(":DATA FP ");
				builder.append("PIN=").append(emp.getNumber()).append("\t");
				builder.append("FID=").append(this.emp.getFingerInfo().getFID()).append("\t");
				builder.append("Size=").append(this.emp.getFingerInfo().getSize()).append("\t");
				builder.append("Valid=").append(this.emp.getFingerInfo().getValid()).append("\t");
				builder.append("TMP=").append(this.emp.getFingerInfo().getTemplate()).append("\t");
			}
			else {
				
				builder.append(":DATA DEL_FP ");
				builder.append("PIN=").append(emp.getNumber()).append("\t");
				builder.append("FID=").append(this.emp.getFingerInfo().getFID());
			}
			
			builder.append("\n\r");
		}
		
		return builder.toString();
	}

}
