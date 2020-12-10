package com.kratonsolution.belian.tengkawang.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.Department;
import com.kratonsolution.belian.tengkawang.repository.DepartmentRepository;

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
public class DepartmentService {

	@Autowired
	private DepartmentRepository repo;

	public List<Department> getAll() {
		return repo.findAll();
	}
	
	public Optional<Department> getOneById(@NonNull String id) {
		return repo.findById(id);
	}
	
	public Optional<Department> getOneByName(@NonNull String name) {
		return repo.findOneByName(name);
	}
	
	public void add(@NonNull Department department) {
		
		Optional<Department> opt = getOneByName(department.getName());
		if(opt.isEmpty()) {
			
			repo.save(department);
			log.info("Creating new department {}", department.getName());
		}
		else {
			log.info("Creating new department canceled, data already exist {}", department.getName());
		}
	}
	
	public void update(@NonNull Department department) {
		
		Optional<Department> opt = getOneByName(department.getName());
		if(opt.isPresent()) {
			
			repo.save(department);
			log.info("Updating department {}", department.getName());
		}
	}
	
	public void delete(@NonNull String id) {
		repo.deleteById(id);
		log.info("Deleting department {}", id);
	}
}
