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
public class USERINFOProcessor implements PayloadProcessor {

	@Autowired
	private EmployeeExtractor employeeExtractor;
	
	@Autowired
	private FingerPrintExtractor fingerPrintExtractor;

	@Override
	public int execute(@NonNull String payload) {

		log.info("Start processing USERINFO payload");
		
		List<String> list = Arrays.asList(payload.split("\n\r"));
		if(!list.isEmpty()) {
			
			list.stream().forEach(row -> {

				if(row.startsWith("USER")) {
					employeeExtractor.extract(row);
				}
				else if(row.startsWith("FP")) {
					fingerPrintExtractor.extract(row);
				}
				else {
					log.info("No known processor for this payload {}", payload);
				}
			});
		}
		
		log.info("Finish processing USERINFO payload");
		
		return list.size();
	}
}
