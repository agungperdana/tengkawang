package com.kratonsolution.tengkawang.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kratonsolution.tengkawang.backend.model.Invoice;
import com.kratonsolution.tengkawang.backend.service.InvoiceService;
import com.kratonsolution.tengkawang.util.Securitys;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@RestController
public class InvoiceApiV1 {

	@Autowired
	private InvoiceService service;
		
	@Secured("ROLE_API_V1_INVOICE_READ")
	@GetMapping("/api/v1/invoices")
	public List<Invoice> list(Authentication auth, Model model) {

		return service.getAll(Securitys.getOrganizations(auth.getPrincipal()));
	}
}
