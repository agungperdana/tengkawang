package com.kratonsolution.tengkawang.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.tengkawang.backend.service.InvoiceService;
import com.kratonsolution.tengkawang.util.Securitys;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class InvoiceController {

	@Autowired
	private InvoiceService service;
		
	@GetMapping("/backoffice/invoices")
	public String list(Authentication auth, Model model) {

		model.addAttribute("invoices", service.getAll(Securitys.getOrganizations(auth.getPrincipal())));
		return "invoices/table";
	}
	
	@GetMapping("/backoffice/invoices-pre-edit")
	public String preedit(@RequestParam("id")String id, Model model) {
	
		model.addAttribute("invoice", service.getById(id).get());
		return "invoices/edit";
	}
}
