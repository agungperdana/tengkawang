package com.kratonsolution.tengkawang.api.v1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kratonsolution.tengkawang.backend.model.Organization;
import com.kratonsolution.tengkawang.backend.service.OrganizationService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@RestController
public class OrganizationApiV1 {

	@Autowired
	private OrganizationService service;

	@Secured({"ROLE_API_V1_ORGANIZATION_READ"})
	@GetMapping("/api/v1/organizations")
	public List<Organization> list() {
	
		return service.getAll();
	}

	@Secured({"ROLE_API_V1_ORGANIZATION_CREATE"})
	@PostMapping("/api/v1/organizations-add")
	public Organization add(@RequestBody Organization organization) {
		
		service.add(organization);
		return organization;
	}
	
	@Secured({"ROLE_API_V1_ORGANIZATION_UPDATE"})
	@PostMapping("/api/v1/organizations-update")
	public Organization update(@RequestBody Organization organization) {
		
		Optional<Organization> opt = service.getOneById(organization.getId());
		if(opt.isPresent()) {
			
			opt.get().setName(organization.getName());
			opt.get().setParent(organization.getParent());
			opt.get().setComment(organization.getComment());
			
			service.edit(opt.get());
		}
		
		return opt.get();
	}
	
	@Secured({"ROLE_API_V1_ORGANIZATION_DELETE"})
	@DeleteMapping("/api/v1/organizations-delete/{id}")
	public Organization delete(@PathVariable String id) {
		
		Optional<Organization> opt = service.getOneById(id);
		service.delete(id);
		
		return opt.get();
	}
}
