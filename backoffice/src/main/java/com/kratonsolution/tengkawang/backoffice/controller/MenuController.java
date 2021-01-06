package com.kratonsolution.tengkawang.backoffice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.tengkawang.backend.model.Menu;
import com.kratonsolution.tengkawang.backend.service.MenuService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class MenuController {

	@Autowired
	private MenuService service;
		
	@GetMapping("/backoffice/menus")
	public String list(Model model) {
		model.addAttribute("menus", service.getAll());
		return "menus/table";
	}
	
	@GetMapping("/backoffice/menus-pre-add")
	public String preadd() {
		
		return "menus/add";
	}
	
	@PostMapping("/backoffice/menus-add")
	public String add(@RequestParam("name")String name, @RequestParam("comment")Optional<String> comment) {
		
		service.create(name, comment.orElse(null));
		
		return "redirect:/backoffice/menus";
	}
	
	@GetMapping("/backoffice/menus-pre-edit")
	public String preedit(@RequestParam("id")String id, Model model) {
	
		model.addAttribute("menu", service.getById(id).get());
		return "menus/edit";
	}
	
	@PostMapping("/backoffice/menus-edit")
	public String edit(@RequestParam("id")String id, 
						@RequestParam("name")String name,
						@RequestParam("comment")Optional<String> comment,
						Model model) {
	
		Optional<Menu> opt = service.getById(id);
		if(opt.isPresent()) {
			
			opt.get().setComment(comment.get());
			opt.get().setName(name);
			
			service.update(opt.get());
		}
		
		return "redirect:/backoffice/menus";
	}
	
	@GetMapping("/backoffice/menus-delete")
	public String delete(@RequestParam("id")String id) {
		
		service.delete(id);
		return "redirect:/backoffice/menus";
	}
}
