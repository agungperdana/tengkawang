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
	
	public static String READ = "READ";
	
	public static String UPDATE = "UPDATE";

	private String action = READ;
	
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
		builder.append("C:");
		builder.append(getCode());
		builder.append(":DATA FP ");
		builder.append("PIN=").append(emp.getNumber()).append("\t");
		
		if(this.action.equals(UPDATE) && this.emp.getFingerInfo() != null) {
			
			builder.append("FID=").append(this.emp.getFingerInfo().getFID()).append("\t");
			builder.append("Size=").append(this.emp.getFingerInfo().getSize()).append("\t");
			builder.append("Valid=").append(this.emp.getFingerInfo().getValid()).append("\t");
			builder.append("TMP=").append(this.emp.getFingerInfo().getTemplate()).append("\t");
		}
		
		builder.append("\n\r");
		
		return builder.toString();
	}

}
