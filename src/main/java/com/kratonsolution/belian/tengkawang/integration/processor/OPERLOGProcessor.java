package com.kratonsolution.belian.tengkawang.integration.processor;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class OPERLOGProcessor implements PayloadProcessor {

	@Autowired
	private FingerPrintExtractor fingerPrintExtractor;

	@Override
	public int execute(@NonNull String payload) {

		log.info("Start processing OPERLOG payload");
		
		List<String> list = Arrays.asList(payload.split("\n\r"));
		if(!list.isEmpty()) {
			
			list.stream().forEach(row -> {

				if(row.startsWith("OPLOG")) {
					log.info("OPLOG Event occure, just skip for now, will be handled at next stable release");
				}
				else if(row.startsWith("FP")) {
					fingerPrintExtractor.extract(row);
				}
				else {
					log.info("No known processor for this payload {}", payload);
				}
			});
		}
		
		log.info("Finish processing OPERLOG payload");
		
		return list.size();
	}
}
