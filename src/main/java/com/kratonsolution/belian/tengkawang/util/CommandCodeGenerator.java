package com.kratonsolution.belian.tengkawang.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Service
public class CommandCodeGenerator {

	@Autowired
	private Cache<LocalDate, Integer> codeCache;
	
	public String generate() {
		
		LocalDate date = LocalDate.now();
		Integer seq = codeCache.getIfPresent(date);
		if(seq != null) {
			
			int number = seq+1;
			codeCache.put(date, Integer.valueOf(number));
			return DateTimeFormatter.ofPattern("ddMMyy").format(date)+number;
		}
		else {
			codeCache.put(date, Integer.valueOf(0));
			return DateTimeFormatter.ofPattern("ddMMyy").format(date)+"0";
		}
	}
}
