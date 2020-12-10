package com.kratonsolution.belian.tengkawang.backoffice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.belian.tengkawang.model.Organization;
import com.kratonsolution.belian.tengkawang.service.OrganizationService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class OrganizationController {

	@Autowired
	private OrganizationService service;

	@GetMapping("/backoffice/organizations")
	public String list(Model model) {
		model.addAttribute("organizations", service.getAll());
		return "organizations/table";
	}
	
	@GetMapping("/backoffice/organizations-pre-add")
	public String preadd(Model model) {
		
		model.addAttribute("parents", service.getAll());
		return "organizations/add";
	}
	
	@PostMapping("/backoffice/organizations-add")
	public String add(@RequestParam("name")String name,
			@RequestParam("parent")Optional<String> parent,
			@RequestParam("comment")Optional<String> comment) {
		
		Organization organization = new Organization();
		organization.setName(name);
		organization.setParent(parent.orElse(null));
		organization.setComment(comment.orElse(null));
		
		service.add(organization);
		
		return "redirect:/backoffice/organizations";
	}
	
	@GetMapping("/backoffice/organizations-pre-edit")
	public String preedit(@RequestParam("id")String id, Model model) {
		
		model.addAttribute("organization", service.getOneById(id).orElse(new Organization()));
		model.addAttribute("parents", service.findAllExcludeId(id));
		
		return "organizations/edit";
	}
	
	@PostMapping("/backoffice/organizations-update")
	public String update(@RequestParam("id")String id,
			@RequestParam("name")String name,
			@RequestParam("parent")Optional<String> parent,
			@RequestParam("comment")Optional<String> comment) {
		
		Optional<Organization> opt = service.getOneById(id);
		if(opt.isPresent()) {
			
			opt.get().setName(name);
			opt.get().setParent(parent.orElse(null));
			opt.get().setComment(comment.orElse(null));
			
			service.edit(opt.get());
		}
		
		return "redirect:/backoffice/organizations";
	}
	
	@GetMapping("/backoffice/organizations-delete")
	public String delete(@RequestParam("id")String id) {
		service.delete(id);
		return "redirect:/backoffice/organizations";
	}
}
