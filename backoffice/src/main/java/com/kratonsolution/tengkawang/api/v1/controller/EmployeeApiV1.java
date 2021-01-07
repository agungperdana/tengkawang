package com.kratonsolution.tengkawang.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kratonsolution.tengkawang.backend.model.Employee;
import com.kratonsolution.tengkawang.backend.service.EmployeeService;
import com.kratonsolution.tengkawang.util.Securitys;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@RestController
public class EmployeeApiV1 {
	
	@Autowired
	private EmployeeService service;

	@GetMapping("/api/v1/employees")
	public List<Employee> list(Authentication auth) {

		return service.getAll(Securitys.getOrganizations(auth.getPrincipal()));
	}
	
	@PostMapping("/api/v1/employees-add")
	public Employee add(Authentication auth, @RequestBody Employee employee) {

		service.add(employee);
		return employee;
	}
}
