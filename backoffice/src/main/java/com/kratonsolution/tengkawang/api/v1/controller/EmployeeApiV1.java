package com.kratonsolution.tengkawang.api.v1.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.kratonsolution.tengkawang.backend.command.Command;
import com.kratonsolution.tengkawang.backend.command.DeleteFingerPrintCommand;
import com.kratonsolution.tengkawang.backend.command.USERCommand;
import com.kratonsolution.tengkawang.backend.command.UpdateFingerPrintCommand;
import com.kratonsolution.tengkawang.backend.model.Employee;
import com.kratonsolution.tengkawang.backend.service.DepartmentService;
import com.kratonsolution.tengkawang.backend.service.DeviceService;
import com.kratonsolution.tengkawang.backend.service.EmployeeService;
import com.kratonsolution.tengkawang.backend.util.CommandCodeGenerator;
import com.kratonsolution.tengkawang.util.Securitys;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@RestController
public class EmployeeApiV1 {

	@Autowired
	private EmployeeService service;

	@Autowired
	private DepartmentService depService;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private CommandCodeGenerator codeGen;

	@Autowired
	private Cache<String, Command> cache;

	@Secured("ROLE_API_V1_EMPLOYEE_READ")
	@GetMapping("/api/v1//employees")
	public List<Employee> list(Authentication auth) {

		return service.getAll(Securitys.getOrganizations(auth.getPrincipal()));
	}

	@Secured("ROLE_API_V1_EMPLOYEE_CREATE")
	@PostMapping("/api/v1//employees-add")
	public Employee add(Authentication auth, @RequestBody Employee employee) {

		if(Strings.isNullOrEmpty(employee.getOrganization())) {
			employee.setOrganization(Securitys.getOrganization(auth.getPrincipal()));
		}

		service.add(employee);
		return employee;
	}

	@Secured("ROLE_API_V1_EMPLOYEE_UPDATE")
	@PostMapping("/api/v1//employees-update")
	public Employee edit(@RequestBody Employee emp) {

		Optional<Employee> opt = service.getById(emp.getId());
		if(opt.isPresent()) {

			opt.get().setCard(emp.getCard());
			opt.get().setDepartment(emp.getDepartment());
			opt.get().setFullName(emp.getFullName());
			opt.get().setNumber(emp.getNumber());
			opt.get().setOnDeviceName(emp.getOnDeviceName());
			opt.get().setPassword(emp.getPassword());
			opt.get().setPrivilege(emp.getPrivilege());

			service.update(opt.get());
		}

		return emp;
	}
	
	@Secured("ROLE_API_V1_EMPLOYEE_CREATE")
	@PostMapping("/api/v1//employees-copy")
	public String copy(Authentication auth, 
						@RequestParam("id")Optional<String> id, 
						@RequestParam("mode")String mode, 
						@RequestParam("serials")Optional<String[]> serials, 
						Model model) {
		
		log.info("mode {} & id {} & Serials {}", mode, id, serials);
		
		if(serials.isPresent()) {
			
			log.info("preparring command");
			
			if(mode.equalsIgnoreCase("all")) {
				
				service.getAll(Securitys.getOrganizations(auth.getPrincipal())).forEach(em -> {

					Arrays.asList(serials.get()).forEach(serial -> {
						
						USERCommand command = new USERCommand(serial, codeGen.generate(), em, USERCommand.UPDATE);
						command.getChilds().add(new UpdateFingerPrintCommand(serial, codeGen.generate(), em));
						
						cache.put(command.getCode(), command);
					});
				});
			}
			else {
				
				if(id.isPresent()) {
					
					Optional<Employee> emp = service.getById(id.get());
					if(emp.isPresent()) {
						
						log.info("Found target employee {}", emp.get().getFullName());
						
						Arrays.asList(serials.get()).forEach(serial -> {
							
							USERCommand command = new USERCommand(serial, codeGen.generate(), emp.get(), USERCommand.UPDATE);
							command.getChilds().add(new UpdateFingerPrintCommand(serial, codeGen.generate(), emp.get()));
							
							cache.put(command.getCode(), command);
						});
					}
				}

			}
		}

		return mode;
	}

	@Secured("ROLE_API_V1_EMPLOYEE_DELETE")
	@DeleteMapping("/api/v1//employees-delete/{id}")
	public Employee delete(@PathVariable String id) {

		Optional<Employee> opt = service.getById(id);
		if(opt.isPresent()) {
			service.delete(id);
		}

		return opt.get();
	}

	@Secured("ROLE_API_V1_EMPLOYEE_UPDATE")
	@GetMapping("/api/v1//employees-pre-edit-finger")
	public String fingerpreedit(@RequestParam("id")String id, Model model) {

		Optional<Employee> emp = service.getById(id);

		model.addAttribute("employee", emp.orElse(new Employee()));
		model.addAttribute("departments", depService.getAll());

		deviceService.getAll().forEach(dev -> {
			
			DeleteFingerPrintCommand comm = new DeleteFingerPrintCommand(dev.getSerial(), codeGen.generate(), emp.get());
			cache.put(comm.getCode(), comm);
		});

		return "employees/edit-finger";
	}
}