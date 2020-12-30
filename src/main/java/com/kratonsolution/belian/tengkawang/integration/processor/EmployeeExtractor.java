package com.kratonsolution.belian.tengkawang.integration.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.kratonsolution.belian.tengkawang.model.Device;
import com.kratonsolution.belian.tengkawang.model.Employee;
import com.kratonsolution.belian.tengkawang.model.Privilege;
import com.kratonsolution.belian.tengkawang.repository.DeviceRepository;
import com.kratonsolution.belian.tengkawang.repository.EmployeeRepository;
import com.kratonsolution.belian.tengkawang.util.ValueUtil;

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
public class EmployeeExtractor implements PayloadRowExtractor {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DeviceRepository deviceRepository;

	@Override
	public void extract(@NonNull String deviceSerial, @NonNull String row) {

		log.info("employee row data {}", row);
		
		Optional<Device> device = deviceRepository.findOneBySerial(deviceSerial);
		if(device.isPresent()) {

			String pin = ValueUtil.getValue("PIN", row);
			String name = ValueUtil.getValue("Name", row);
			String password = ValueUtil.getValue("Passwd", row);
			String card = ValueUtil.getValue("Card", row);
			
			Employee employee = getEmployee(pin);
			employee.setNumber(pin);
			employee.setOnDeviceName(name);
			employee.setPassword(password);
			employee.setCard(card);
			employee.setOrganization(device.get().getOrganization());

			if(Strings.isNullOrEmpty(employee.getFullName())) {
				employee.setFullName(name);
			}

			if(employee.getPrivilege() == null) {
				employee.setPrivilege(Privilege.User);
			}

			employeeRepository.save(employee);

			log.info("Successfuly add/update employee data {}", employee.getFullName());
		}
		else {
			log.info("Unknown device, skipping process of extracting employee information");
		}
	}

	private Employee getEmployee(@NonNull String number) {

		Optional<Employee> opt = employeeRepository.findOneByNumber(number);
		if(opt.isPresent()) {
			return opt.get();
		}

		return new Employee();
	}
}
