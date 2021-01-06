package com.kratonsolution.tengkawang.backend.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.common.base.Strings;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public class DateTimeHelper {
	
	public static LocalDate toLocalDate(String date) {
		
		if(!Strings.isNullOrEmpty(date)) {
			return LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
		
		return null;
	}
}
