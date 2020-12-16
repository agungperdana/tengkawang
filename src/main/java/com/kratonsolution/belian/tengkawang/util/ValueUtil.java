package com.kratonsolution.belian.tengkawang.util;

import java.util.Arrays;
import java.util.List;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public class ValueUtil {
	
	public static final String getValue(@NonNull String key, @NonNull String row) {
		
		List<String> list = Arrays.asList(row.split("\t"));
		for(String column:list) {
			
			if(column.toUpperCase().startsWith(key.toUpperCase()) ||
					column.toUpperCase().contains(key.toUpperCase())) {
				
				return column.split("=")[1];
			}
		}
		
		
		return "";
	}
}
