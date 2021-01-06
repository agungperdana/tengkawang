package com.kratonsolution.tengkawang.admsserver.processor;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface PayloadProcessor {
	
	public int execute(@NonNull String deviceSerial, @NonNull String payload);
}
