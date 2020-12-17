package com.kratonsolution.belian.tengkawang.integration.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.kratonsolution.belian.tengkawang.model.Employee;
import com.kratonsolution.belian.tengkawang.model.Privilege;
import com.kratonsolution.belian.tengkawang.repository.EmployeeRepository;

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

	@Override
	public void extract(@NonNull String row) {

		String[] cols = row.split("\t");

		String pin = cols[0].split("=")[1];
		String name = cols[1].split("=")[1];
		String password = cols[2].split("=")[1];
		String card = cols[3].split("=")[1];
		//String group = cols[4].split("=")[1];
		//String tz = cols[5].split("=")[1];

		Employee employee = getEmployee(pin);
		employee.setNumber(pin);
		employee.setOnDeviceName(name);
		employee.setPassword(password);
		employee.setCard(card);

		if(Strings.isNullOrEmpty(employee.getFullName())) {
			employee.setFullName(name);
		}

		if(employee.getPrivilege() == null) {
			employee.setPrivilege(Privilege.User);
		}

		employeeRepository.save(employee);

		log.info("Successfuly add/update employee data {}", employee.getFullName());
	}

	private Employee getEmployee(@NonNull String number) {

		Optional<Employee> opt = employeeRepository.findOneByNumber(number);
		if(opt.isPresent()) {
			return opt.get();
		}

		return new Employee();
	}
}
