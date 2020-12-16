package com.kratonsolution.belian.tengkawang.backoffice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String preedit(@RequestParam String id, Model model) {

		model.addAttribute("user", service.getOneById(id).get());
		return "users/edit";
	}
	
	@PostMapping("/backoffice/users-edit")
	public String edit(@RequestParam("id") String id, 
					   @RequestParam("name") String name, 
					   @RequestParam("oldPassword") String oldPassword, 
					   @RequestParam("newPassword") String newPassword, 
					   Model model) {

		Optional<User> user = service.getOneById(id);
		if(user.isPresent()) {
			user.get().edit(name, oldPassword, newPassword);
			service.edit(user.get());
		}
		
		model.addAttribute("users", service.getAllUsers());
		return "redirect:/backoffice/users";
	}

	@GetMapping("/backoffice/users-pre-add")
	public String preadd() {

		return "users/add";
	}
	
	@PostMapping("/backoffice/users-add")
	public String add(@RequestParam("name")String name, 
					  @RequestParam("password1")String password1, 
					  @RequestParam("password2")String password2) {

		if(!password1.equals(password2)) {
			return "redirect:/backoffice/user-pre-add";
		}

		service.create(name, password1, password2);
		
		return "redirect:/backoffice/users";
	}
	
	@GetMapping("/backoffice/users-delete")
	public String delete(@RequestParam("id")String id) {

		service.delete(id);
		return "redirect:/backoffice/users";
	}
}
