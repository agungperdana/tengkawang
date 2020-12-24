package com.kratonsolution.belian.tengkawang.integration.processor;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Service
public class FingerProcessor implements PayloadProcessor {

	@Autowired
	private FingerPrintExtractor extractor;
	
	@Override
	public int execute(@NonNull String deviceSerial, @NonNull String payload) {

		Arrays.asList(payload.split("\n\r")).forEach(rw -> extractor.extract(deviceSerial, rw));
		return 0;
	}

}
