package com.kratonsolution.belian.tengkawang.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.Employee;
import com.kratonsolution.belian.tengkawang.model.EmployeeGroup;
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
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	public List<Employee> getAllEmployee() {
		return repo.findAll();
	}

	public Optional<Employee> getOneById(@NonNull String id) {
		return repo.findById(id);
	}

	public Optional<Employee> getOneByNumber(@NonNull String number) {
		return repo.findOneByNumber(number);
	}

	public void add(@NonNull Employee employee) {

		Optional<Employee> opt = getOneByNumber(employee.getNumber());
		if(opt.isEmpty()) {
			repo.save(employee);
			log.info("Creating new employe {}", employee.getFullName());
		}
		else {
			log.info("Creating new employee canceled, data already exist");
		}
	}

	public void update(@NonNull Employee employee) {
		repo.save(employee);
		log.info("Updating employee data {}", employee.getFullName());
	}

	public void delete(@NonNull String id) {
		repo.deleteById(id);
		log.info("Deleting new employee");
	}

	public int extractAndSave(@NonNull String content) {

		String[] rows = content.split("\n");
		if(rows != null) {
			
			for(int idx=0;idx<rows.length;idx++) {

				String[] cols = rows[idx].split("\t");

				String col0[] = cols[0].split("=");
				String col1[] = cols[1].split("=");
				String col2[] = cols[2].split("=");
				String col3[] = cols[3].split("=");
				String col4[] = cols[4].split("=");
				String col5[] = cols[5].split("=");
				String col6[] = cols[6].split("=");
				
				Employee employee = null;
				
				if(col0.length == 2 && col0[0].toUpperCase().contains("USER")) {
					
					if(Strings.isNotEmpty(col0[1])) {
						Optional<Employee> emOp = getOneByNumber(col0[1]);
						if(emOp.isPresent()) {
							employee = emOp.get();
						}
						else {
							employee = new Employee();
							employee.setNumber(col0[1]);
						}
					}
				}

				if(col1.length == 2 && col1[0].toUpperCase().contains("NAME")) {
					
					employee.setOnDeviceName(col1[1]);
				}

				if(col2.length == 2 && col2[0].toUpperCase().contains("PRI")) {

					int pri = Integer.parseInt(col2[1]);
					switch (pri) {
					case 14:
						employee.setPrivilege(Privilege.Super_Administrator);
						break;
					default:
						employee.setPrivilege(Privilege.User);
						break;
					}
				}

				if(col3.length == 2 && col3[0].contains("PASSWORD")) {
					
					employee.setPassword(col3[1]);
				}

				if(col4.length == 2 && col4[0].contains("CARD")) {
					employee.setCard(col4[1]);
				}
				
				if(col5.length == 2 && col5[0].toUpperCase().contains("GRP")) {
					employee.setGroup(EmployeeGroup.User);
				}
				
				if(col6.length == 2 && col6[0].toUpperCase().contains("TZ")) {
					
				}
			
				if(Strings.isEmpty(employee.getFullName()) && Strings.isNotEmpty(employee.getOnDeviceName())) {
					employee.setFullName(employee.getOnDeviceName());
				}
				
				repo.save(employee);
				log.info("Successfuly add/update employee data {}", employee.getFullName());
			}
		}


		return 0;
	}
}
