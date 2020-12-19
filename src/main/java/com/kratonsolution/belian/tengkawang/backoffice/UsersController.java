package com.kratonsolution.belian.tengkawang.backoffice;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.belian.tengkawang.model.Role;
import com.kratonsolution.belian.tengkawang.model.User;
import com.kratonsolution.belian.tengkawang.service.RoleService;
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
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/backoffice/users")
	public String list(Model model) {

		model.addAttribute("users", service.getAllUsers());
		return "users/table";
	}
	
	@GetMapping("/backoffice/users-pre-edit")
	public String preedit(Principal principal, @RequestParam String id, Model model) {

		
		Optional<User> opt = service.getById(id);
		if(opt.isPresent()) {
			
			List<Role> roles = roleService.getAll();
			if(!principal.getName().equals("System Administrator")) {
				roles.removeIf(p->p.getName().equals("System Administrator"));
			}
			
			model.addAttribute("roles", roles);
			model.addAttribute("user", opt.get());
		}

		return "users/edit";
	}
	
	@PostMapping("/backoffice/users-edit")
	public String edit(@RequestParam("id") String id, 
					   @RequestParam("name") String name, 
					   @RequestParam("oldPassword") String oldPassword, 
					   @RequestParam("newPassword") String newPassword,
					   @RequestParam("role") String role,
					   Model model) {

		Optional<User> user = service.getById(id);
		if(user.isPresent()) {
			user.get().edit(name, oldPassword, newPassword, role);
			service.edit(user.get());
		}
		
		model.addAttribute("users", service.getAllUsers());
		return "redirect:/backoffice/users";
	}

	@GetMapping("/backoffice/users-pre-add")
	public String preadd(Principal principal, Model model) {

		List<Role> roles = roleService.getAll();
		if(!principal.getName().equals("System Administrator")) {
			roles.removeIf(p->p.getName().equals("System Administrator"));
		}
		
		return "users/add";
	}
	
	@PostMapping("/backoffice/users-add")
	public String add(@RequestParam("name")String name, 
					  @RequestParam("password1")String password1, 
					  @RequestParam("password2")String password2,
					  @RequestParam("role")String role) {

		if(!password1.equals(password2)) {
			return "redirect:/backoffice/user-pre-add";
		}

		service.create(name, password1, password2, role);
		
		return "redirect:/backoffice/users";
	}
	
	@GetMapping("/backoffice/users-delete")
	public String delete(@RequestParam("id")String id) {

		service.delete(id);
		return "redirect:/backoffice/users";
	}
}
