package com.kratonsolution.belian.tengkawang.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.Organization;
import com.kratonsolution.belian.tengkawang.repository.OrganizationRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationService {

	@Autowired
	private OrganizationRepository repo;
	
	public List<Organization> getAll() {
		return repo.findAll();
	}
	
	public Optional<Organization> getOneById(@NonNull String id) {
		return repo.findById(id);
	}
	
	public Optional<Organization> getOneByName(@NonNull String name) {
		return repo.findOneByName(name);
	}
	
	public List<Organization> findAllExcludeName(@NonNull String name) {
		return repo.findAllExcludeName(name);
	}
	
	public List<Organization> findAllExcludeId(@NonNull String id) {
		return repo.findAllExcludeId(id);
	}
	
	public void add(@NonNull Organization organization) {
		
		repo.save(organization);
		log.info("Creating new Organization {}", organization.getName());
	}
	
	public void edit(@NonNull Organization organization) {
		repo.save(organization);
		log.info("Updating organization {}", organization.getName());
	}
	
	public void delete(@NonNull String id) {
		repo.deleteById(id);
	}
	
	public void getAllTree(@NonNull String parent, @NonNull List<String> list) {
		
		List<Organization> childs = repo.findAllByParent(parent);
		childs.forEach(org->{
			list.add(org.getName());
			getAllTree(org.getName(), list);
		});
	}
}
