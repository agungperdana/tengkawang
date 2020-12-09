package com.kratonsolution.belian.tengkawang.backoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.belian.tengkawang.model.User;
import com.kratonsolution.belian.tengkawang.service.UserService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class UsersController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/backoffice/users")
	public String list(Model model) {

		model.addAttribute("users", service.getAllUsers());
		return "users/table";
	}
	
	@GetMapping("/backoffice/users-pre-edit")
	public String preedit(@ModelAttribute String id, Model model) {

		model.addAttribute("user", service.getOneById(id));
		return "users/edit";
	}
	
	@PostMapping("/backoffice/users-edit")
	public String edit(@RequestParam("id") String id, 
					   @RequestParam("name") String name, 
					   @RequestParam("oldPassword") String oldPassword, 
					   @RequestParam("newPassword") String newPassword, 
					   Model model) {

		User user = service.getOneById(id);
		if(user != null) {
			user.edit(name, oldPassword, newPassword);
			service.edit(user);
		}
		
		model.addAttribute("users", service.getAllUsers());
		return "users/table";
	}

	@GetMapping("/backoffice/users-pre-add")
	public String preadd(Model model) {

		model.addAttribute("users", service.getAllUsers());
		return "users";
	}
	
	@GetMapping("/backoffice/users-add")
	public String add(Model model) {

		model.addAttribute("users", service.getAllUsers());
		return "users";
	}
	
	@GetMapping("/backoffice/users-delete")
	public String delete(Model model) {

		model.addAttribute("users", service.getAllUsers());
		return "users";
	}
}
