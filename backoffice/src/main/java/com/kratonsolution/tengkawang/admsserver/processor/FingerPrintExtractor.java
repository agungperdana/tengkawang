package com.kratonsolution.tengkawang.admsserver.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.tengkawang.backend.model.Employee;
import com.kratonsolution.tengkawang.backend.model.FingerInfo;
import com.kratonsolution.tengkawang.backend.repository.EmployeeRepository;
import com.kratonsolution.tengkawang.backend.util.ValueUtil;

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
public class FingerPrintExtractor implements PayloadRowExtractor {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void extract(@NonNull String deviceSerial, @NonNull String row) {

		String[] cols = row.split("\t");
		if(cols != null) {
			
			String pin = ValueUtil.getValue("PIN", row);
			String fid = ValueUtil.getValue("FID", row);
			String valid = ValueUtil.getValue("Valid", row);
			String tmp = ValueUtil.getValue("TMP", row);
			String size = ValueUtil.getValue("Size", row);
			
			Optional<Employee> opt = employeeRepository.findOneByNumber(pin);
			if(opt.isPresent()) {

				FingerInfo info = new FingerInfo();
				info.setFID(fid);
				info.setValid(valid);
				info.setTemplate(tmp);
				info.setSize(size);
				
				opt.get().addFingerInfo(info);
				
				employeeRepository.save(opt.get());

				log.info("Updating employee {} finger print data", opt.get().getFullName());
			}
		}
	}
}
