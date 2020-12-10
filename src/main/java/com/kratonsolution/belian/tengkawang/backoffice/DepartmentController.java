package com.kratonsolution.belian.tengkawang.backoffice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.belian.tengkawang.model.Department;
import com.kratonsolution.belian.tengkawang.service.DepartmentService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService service;
		
	@GetMapping("/backoffice/departments")
	public String list(Model model) {
		model.addAttribute("departments", service.getAll());
		return "departments/table";
	}
	
	@GetMapping("/backoffice/departments-pre-add")
	public String preadd() {
		
		return "departments/add";
	}
	
	@PostMapping("/backoffice/departments-add")
	public String add(@RequestParam("name")String name, @RequestParam("comment")Optional<String> comment) {
		
		Department department = new Department();
		department.setComment(comment.get());
		department.setName(name);
		
		service.add(department);
		
		return "redirect:/backoffice/departments";
	}
	
	@GetMapping("/backoffice/departments-pre-edit")
	public String preedit(@RequestParam("id")String id, Model model) {
	
		model.addAttribute("department", service.getOneById(id).get());
		return "departments/edit";
	}
	
	@PostMapping("/backoffice/departments-edit")
	public String edit(@RequestParam("id")String id, 
						@RequestParam("name")String name,
						@RequestParam("comment")Optional<String> comment,
						Model model) {
	
		Optional<Department> opt = service.getOneById(id);
		if(opt.isPresent()) {
			
			opt.get().setComment(comment.get());
			opt.get().setName(name);
			
			service.update(opt.get());
		}
		
		return "redirect:/backoffice/departments";
	}
	
	@GetMapping("/backoffice/departments-delete")
	public String delete(@RequestParam("id")String id) {
		
		service.delete(id);
		return "redirect:/backoffice/departments";
	}
}
