package com.kratonsolution.belian.tengkawang.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.Employee;
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
}
