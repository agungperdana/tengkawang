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

				String col[] = column.split("=");
				if(col.length > 1)
					return col[1];
				else
					return "";
			}
		}


		return "";
	}

	/**
	 * this will split row content with '&' as separator.
	 * @param key
	 * @param row
	 * @return
	 */
	public static final String getValueWtihAnd(@NonNull String key, @NonNull String row) {

		List<String> list = Arrays.asList(row.split("&"));
		for(String column:list) {

			if(column.toUpperCase().startsWith(key.toUpperCase()) ||
					column.toUpperCase().contains(key.toUpperCase())) {

				String col[] = column.split("=");
				if(col.length > 1)
					return col[1];
				else
					return "";
			}
		}


		return "";
	}
}
