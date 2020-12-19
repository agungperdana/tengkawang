package com.kratonsolution.belian.tengkawang.backoffice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Preconditions;
import com.kratonsolution.belian.tengkawang.model.Role;
import com.kratonsolution.belian.tengkawang.model.RoleAccess;
import com.kratonsolution.belian.tengkawang.service.MenuService;
import com.kratonsolution.belian.tengkawang.service.RoleService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class RoleController {

	@Autowired
	private RoleService service;
	
	@Autowired
	private MenuService menuService;
		
	@GetMapping("/backoffice/roles")
	public String list(Authentication auth, Model model) {

		List<Role> roles = service.getAll();
		if(!auth.getAuthorities().stream().anyMatch(p->p.getAuthority().equals(Role.ROOT))) {
			roles.removeIf(m->m.getName().contains(Role.ROOT));
		}
		
		model.addAttribute("roles", roles);
		return "roles/table";
	}
	
	@GetMapping("/backoffice/roles-pre-add")
	public String preadd(Model model) {

		model.addAttribute("menus", menuService.getAll());
		return "roles/add";
	}
	
	@PostMapping("/backoffice/roles-add")
	public String add(@RequestParam("name")String name, 
					  @RequestParam("comment")Optional<String> comment,
					  @RequestParam("menus")Optional<String[]> menus,
					  @RequestParam("create")Optional<String[]> create,
					  @RequestParam("read")Optional<String[]> read,
					  @RequestParam("update")Optional<String[]> update,
					  @RequestParam("delete")Optional<String[]> delete,
					  @RequestParam("print")Optional<String[]> print) {
		
		Preconditions.checkArgument(!name.contains(Role.ROOT), "System Administrator is reserved, please choose another name");
		
		List<String> menusList = menus.isPresent()?Arrays.asList(menus.get()):new ArrayList<>();
		List<String> createList = create.isPresent()?Arrays.asList(create.get()):new ArrayList<>();
		List<String> readList = read.isPresent()?Arrays.asList(read.get()):new ArrayList<>();
		List<String> updateList = update.isPresent()?Arrays.asList(update.get()):new ArrayList<>();
		List<String> deleteList = delete.isPresent()?Arrays.asList(delete.get()):new ArrayList<>();
		List<String> printList = print.isPresent()?Arrays.asList(print.get()):new ArrayList<>();
		
		Set<RoleAccess> granted = new HashSet<>();

		menusList.stream().forEach(mn -> {
			
			RoleAccess roleAccess = new RoleAccess();
			roleAccess.setMenu(menuService.getById(mn).orElse(null));
			roleAccess.setCreate(createList.contains(mn));
			roleAccess.setRead(readList.contains(mn));
			roleAccess.setUpdate(updateList.contains(mn));
			roleAccess.setDelete(deleteList.contains(mn));
			roleAccess.setPrint(printList.contains(mn));
			
			granted.add(roleAccess);
		});

		
		service.create(name, comment.orElse(null), granted);
		
		return "redirect:/backoffice/roles";
	}
	
	@GetMapping("/backoffice/roles-pre-edit")
	public String preedit(@RequestParam("id")String id, Model model) {
	
		model.addAttribute("role", service.getById(id).get());
		return "roles/edit";
	}
	
	@PostMapping("/backoffice/roles-edit")
	public String edit(@RequestParam("id")String id, 
						@RequestParam("name")String name,
						@RequestParam("comment")Optional<String> comment,
						@RequestParam("menus")Optional<String[]> menus,
						@RequestParam("create")Optional<String[]> create,
						@RequestParam("read")Optional<String[]> read,
						@RequestParam("update")Optional<String[]> update,
						@RequestParam("delete")Optional<String[]> delete,
						@RequestParam("print")Optional<String[]> print) {
			
			List<String> menusList = menus.isPresent()?Arrays.asList(create.get()):new ArrayList<>();
			List<String> createList = create.isPresent()?Arrays.asList(create.get()):new ArrayList<>();
			List<String> readList = read.isPresent()?Arrays.asList(read.get()):new ArrayList<>();
			List<String> updateList = update.isPresent()?Arrays.asList(update.get()):new ArrayList<>();
			List<String> deleteList = delete.isPresent()?Arrays.asList(delete.get()):new ArrayList<>();
			List<String> printList = print.isPresent()?Arrays.asList(print.get()):new ArrayList<>();
	
		Optional<Role> opt = service.getById(id);
		if(opt.isPresent()) {
			
			if(!opt.get().getName().equals(name) && name.contains(Role.ROOT)) {
				throw new RuntimeException("System Administrator is reserved, please choose another name.");
			}
			
			opt.get().setComment(comment.get());
			opt.get().setName(name);
			
			menusList.stream().forEach(menu -> {
				
				Optional<RoleAccess> db = opt.get().getGrantedAccess()
											.stream()
											.filter(p->p.getId().equals(menu))
											.findFirst();
				if(db.isPresent()) {
					
					db.get().setCreate(createList.contains(menu));
					db.get().setRead(readList.contains(menu));
					db.get().setUpdate(updateList.contains(menu));
					db.get().setDelete(deleteList.contains(menu));
					db.get().setPrint(printList.contains(menu));
				}
			});
			
			service.update(opt.get());
		}
		
		return "redirect:/backoffice/roles";
	}
	
	@GetMapping("/backoffice/roles-delete")
	public String delete(@RequestParam("id")String id) {
		
		service.delete(id);
		return "redirect:/backoffice/roles";
	}
}
