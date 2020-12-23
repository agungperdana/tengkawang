package com.kratonsolution.belian.tengkawang.controller.backoffice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.belian.tengkawang.model.Role;
import com.kratonsolution.belian.tengkawang.model.User;
import com.kratonsolution.belian.tengkawang.service.RoleService;
import com.kratonsolution.belian.tengkawang.service.UserService;
import com.kratonsolution.belian.tengkawang.util.Securitys;

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
	public String list(Authentication auth, Model model) {
		
		List<User> users = service.getAll(Securitys.getOrganizations(auth.getPrincipal()));
		users.removeIf(p->!Securitys.isRoot(auth) && p.getRole().equals(Role.ROOT));
		
		model.addAttribute("users", users);
		return "users/table";
	}
	
	@GetMapping("/backoffice/users-pre-edit")
	public String preedit(Authentication auth, @RequestParam String id, Model model) {
		
		Optional<User> opt = service.getById(id);
		if(opt.isPresent()) {
			
			List<Role> roles = roleService.getAll();
			if(!auth.getAuthorities().stream().anyMatch(p->p.getAuthority().equals(Role.ROOT))) {
				roles.removeIf(nm->nm.getName().equals(Role.ROOT));
			}
			
			model.addAttribute("roles", roles);
			model.addAttribute("organizations", Securitys.getOrganizations(auth.getPrincipal()));
			model.addAttribute("user", opt.get());
		}

		return "users/edit";
	}
	
	@PostMapping("/backoffice/users-edit")
	public String edit(@RequestParam("id") String id, 
					   @RequestParam("name") String name,
					   @RequestParam("organization")String organization,
					   @RequestParam("role")String role,
					   @RequestParam("oldPassword") String oldPassword, 
					   @RequestParam("newPassword") String newPassword,
					   Model model) {

		Optional<User> user = service.getById(id);
		if(user.isPresent()) {
			user.get().edit(name, oldPassword, newPassword, organization, role);
			service.edit(user.get());
		}
		
		model.addAttribute("users", service.getAll());
		return "redirect:/backoffice/users";
	}

	@GetMapping("/backoffice/users-pre-add")
	public String preadd(Authentication auth, Model model) {

		List<Role> roles = roleService.getAll();
		if(!auth.getAuthorities().stream().anyMatch(p->p.getAuthority().equals(Role.ROOT))) {
			roles.removeIf(nm->nm.getName().equals(Role.ROOT));
		}
		
		model.addAttribute("roles", roles);
		model.addAttribute("organizations", Securitys.getOrganizations(auth.getPrincipal()));
		
		return "users/add";
	}
	
	@PostMapping("/backoffice/users-add")
	public String add(@RequestParam("name")String name,
					  @RequestParam("organization")String organization,
					  @RequestParam("role")String role,
					  @RequestParam("password1")String password1, 
					  @RequestParam("password2")String password2) {

		if(!password1.equals(password2)) {
			return "redirect:/backoffice/user-pre-add";
		}

		service.create(name, password1, password2, organization, role);
		
		return "redirect:/backoffice/users";
	}
	
	@GetMapping("/backoffice/users-delete")
	public String delete(@RequestParam("id")String id) {

		service.delete(id);
		return "redirect:/backoffice/users";
	}
}
