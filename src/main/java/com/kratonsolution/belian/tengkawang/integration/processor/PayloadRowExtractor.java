package com.kratonsolution.belian.tengkawang.integration.processor;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface PayloadRowExtractor {
	
	public void extract(@NonNull String deviceSerial, @NonNull String row);
}
