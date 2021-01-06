package com.kratonsolution.tengkawang.admsserver.processor;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OPERLOGProcessor implements PayloadProcessor {

	@Autowired
	private FingerPrintExtractor fingerPrintExtractor;

	@Override
	public int execute(@NonNull String serial, @NonNull String payload) {

		Arrays.asList(payload.split("\\n")).forEach(row -> {
			
			if(row.trim().startsWith("FP")) {
				fingerPrintExtractor.extract(serial, row);
			}
		});
				
		return 0;
	}
}
