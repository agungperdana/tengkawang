package com.kratonsolution.belian.tengkawang.backoffice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.belian.tengkawang.model.Employee;
import com.kratonsolution.belian.tengkawang.model.Privilege;
import com.kratonsolution.belian.tengkawang.service.DepartmentService;
import com.kratonsolution.belian.tengkawang.service.EmployeeService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@Autowired
	private DepartmentService depService;
	
	@GetMapping("/backoffice/employees")
	public String list(Model model) {
		
		model.addAttribute("employees", service.getAllEmployee());
		return "employees/table";
	}
	
	@GetMapping("/backoffice/employees-pre-add")
	public String preadd(Model model) {
		
		model.addAttribute("departments", depService.getAll());
		return "employees/add";
	}
	
	@PostMapping("/backoffice/employees-add")
	public String add(@RequestParam("number")String number,
					  @RequestParam("fullname")Optional<String> fullname,
					  @RequestParam("ondevicename")Optional<String> onDeviceName,
					  @RequestParam("department")Optional<String> department,
					  @RequestParam("privilege")Privilege privilege,
					  @RequestParam("password")Optional<String> password,
					  @RequestParam("card")Optional<String> card) {
		
		Employee employee = new Employee();
		employee.setCard(card.get());
		employee.setDepartment(department.get());
		employee.setFullName(fullname.get());
		employee.setNumber(number);
		employee.setOnDeviceName(onDeviceName.get());
		employee.setPassword(password.get());
		employee.setPrivilege(privilege);
		
		service.add(employee);
		
		return "redirect:/backoffice/employees";
	}
	
	@GetMapping("/backoffice/employees-pre-edit")
	public String preedit(@RequestParam("id")String id, Model model) {
		
		model.addAttribute("employee", service.getOneById(id).get());
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
		
		Optional<Employee> opt = service.getOneById(id);
		if(opt.isPresent()) {
			
			opt.get().setCard(card.get());
			opt.get().setDepartment(department.get());
			opt.get().setFullName(fullname.get());
			opt.get().setNumber(number);
			opt.get().setOnDeviceName(onDeviceName.get());
			opt.get().setPassword(password.get());
			opt.get().setPrivilege(privilege);
			
			service.update(opt.get());
		}
		
		return "redirect:/backoffice/employees";
	}
	
	@GetMapping("/backoffice/employees-delete")
	public String delete(@RequestParam("id")String id) {
	
		service.delete(id);
		return "redirect:/backoffice/employees";
	}
}
