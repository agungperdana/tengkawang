package com.kratonsolution.tengkawang.api.v1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kratonsolution.tengkawang.backend.model.Department;
import com.kratonsolution.tengkawang.backend.service.DepartmentService;
import com.kratonsolution.tengkawang.util.Securitys;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@RestController
public class DepartmentApiV1 {

	@Autowired
	private DepartmentService service;
		
	@GetMapping("/api/v1/departments")
	public List<Department> list(Authentication auth) {

		return service.getAll(Securitys.getOrganizations(auth.getPrincipal()));
	}
	
	@PostMapping("/api/v1/departments-add")
	public Department add(Authentication auth, @RequestBody Department department) {
		
		department.setOrganization(Securitys.getOrganization(auth.getPrincipal()));
		service.add(department);
		
		return department;
	}
	
	@PostMapping("/api/v1/departments-update")
	public Department edit(@RequestBody Department department) {
	
		Optional<Department> opt = service.getOneById(department.getId());
		if(opt.isPresent()) {
			
			opt.get().setComment(department.getComment());
			opt.get().setName(department.getName());
			
			service.update(opt.get());
		}
		
		return department;
	}
	
	@DeleteMapping("/api/v1/departments-delete/{id}")
	public Department delete(@PathVariable String id) {
		
		Optional<Department> opt = service.getOneById(id);
		service.delete(id);
		return opt.orElse(new Department());
	}
}
