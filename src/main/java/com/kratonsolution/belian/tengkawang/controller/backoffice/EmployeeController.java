package com.kratonsolution.belian.tengkawang.controller.backoffice;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.cache.Cache;
import com.kratonsolution.belian.tengkawang.integration.command.Command;
import com.kratonsolution.belian.tengkawang.integration.command.FINGERPRINTCommand;
import com.kratonsolution.belian.tengkawang.integration.command.USERCommand;
import com.kratonsolution.belian.tengkawang.model.Employee;
import com.kratonsolution.belian.tengkawang.model.Privilege;
import com.kratonsolution.belian.tengkawang.service.DepartmentService;
import com.kratonsolution.belian.tengkawang.service.DeviceService;
import com.kratonsolution.belian.tengkawang.service.EmployeeService;
import com.kratonsolution.belian.tengkawang.util.CommandCodeGenerator;
import com.kratonsolution.belian.tengkawang.util.Securitys;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@Controller
public class EmployeeController {

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

	@GetMapping("/backoffice/employees")
	public String list(Authentication auth, Model model) {

		model.addAttribute("employees", service.getAll(Securitys.getOrganizations(auth.getPrincipal())));
		return "employees/table";
	}

	@GetMapping("/backoffice/employees-pre-add")
	public String preadd(Authentication auth, Model model) {

		model.addAttribute("companys", Securitys.getOrganizations(auth.getPrincipal()));
		model.addAttribute("departments", depService.getAll(Securitys.getOrganizations(auth.getPrincipal())));
		return "employees/add";
	}

	@PostMapping("/backoffice/employees-add")
	public String add(Authentication auth, @RequestParam("number")String number,
			@RequestParam("fullname")Optional<String> fullname,
			@RequestParam("ondevicename")Optional<String> onDeviceName,
			@RequestParam("department")Optional<String> department,
			@RequestParam("privilege")Privilege privilege,
			@RequestParam("password")Optional<String> password,
			@RequestParam("card")Optional<String> card,
			@RequestParam("company")Optional<String> organization) {

		Employee employee = new Employee();
		employee.setCard(card.orElse(null));
		employee.setDepartment(department.orElse(null));
		employee.setFullName(fullname.orElse(null));
		employee.setNumber(number);
		employee.setOnDeviceName(onDeviceName.orElse(null));
		employee.setPassword(password.orElse(null));
		employee.setPrivilege(privilege);
		employee.setOrganization(organization.orElse(Securitys.getOrganization(auth.getPrincipal())));

		service.add(employee);

		return "redirect:/backoffice/employees";
	}

	@GetMapping("/backoffice/employees-pre-edit")
	public String preedit(@RequestParam("id")String id, Model model) {

		Optional<Employee> emp = service.getById(id);

		model.addAttribute("employee", emp.orElse(new Employee()));
		model.addAttribute("departments", depService.getAll());

		return "employees/edit";
	}

	@PostMapping("/backoffice/employees-edit")
	public String edit(@RequestParam("id")String id,
			@RequestParam("number")String number,
			@RequestParam("fullname")Optional<String> fullname,
			@RequestParam("ondevicename")Optional<String> onDeviceName,
			@RequestParam("department")Optional<String> department,
			@RequestParam("privilege")Privilege privilege,
			@RequestParam("password")Optional<String> password,
			@RequestParam("card")Optional<String> card) {

		Optional<Employee> opt = service.getById(id);
		if(opt.isPresent()) {

			opt.get().setCard(card.get());
			opt.get().setDepartment(department.orElse(null));
			opt.get().setFullName(fullname.orElse(null));
			opt.get().setNumber(number);
			opt.get().setOnDeviceName(onDeviceName.get());
			opt.get().setPassword(password.get());
			opt.get().setPrivilege(privilege);

			service.update(opt.get());
		}

		return "redirect:/backoffice/employees";
	}
	
	@GetMapping("/backoffice/employees-pre-copy")
	public String precopy(Authentication auth, @RequestParam("id")Optional<String> id, @RequestParam("mode")String mode, Model model) {

		
		if(mode.equalsIgnoreCase("all")) {
			
			model.addAttribute("mode","all");
		}
		else {
			
			model.addAttribute("id", id.orElse(""));
			model.addAttribute("mode","selected");

			Optional<Employee> opt = service.getById(id.get());
			
			model.addAttribute("employee", opt.isPresent()?opt.get().getFullName():"Unknown Employee");
		}

		model.addAttribute("devices", deviceService.getAll(Securitys.getOrganizations(auth.getPrincipal())));

		return "employees/copy";
	}
	
	@PostMapping("/backoffice/employees-copy")
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
						command.getChilds().add(new FINGERPRINTCommand(serial, codeGen.generate(), em, FINGERPRINTCommand.UPDATE));
						
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
							command.getChilds().add(new FINGERPRINTCommand(serial, codeGen.generate(), emp.get(), FINGERPRINTCommand.UPDATE));
							
							cache.put(command.getCode(), command);
						});
					}
				}

			}
		}

		return "redirect:/backoffice/employees";
	}

	@GetMapping("/backoffice/employees-delete")
	public String delete(@RequestParam("id")String id) {

		service.delete(id);

		return "redirect:/backoffice/employees";
	}

	@GetMapping("/backoffice/employees-pre-edit-finger")
	public String fingerpreedit(@RequestParam("id")String id, Model model) {

		Optional<Employee> emp = service.getById(id);

		model.addAttribute("employee", emp.orElse(new Employee()));
		model.addAttribute("departments", depService.getAll());

		deviceService.getAll().forEach(dev -> {
			
			FINGERPRINTCommand comm = new FINGERPRINTCommand(dev.getSerial(), codeGen.generate(), emp.get(), FINGERPRINTCommand.DELETE);
			cache.put(comm.getCode(), comm);
		});

		return "employees/edit-finger";
	}
}
