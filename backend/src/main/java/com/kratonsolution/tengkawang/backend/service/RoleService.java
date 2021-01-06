package com.kratonsolution.tengkawang.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.tengkawang.backend.model.Menu;
import com.kratonsolution.tengkawang.backend.model.MenuEvent;
import com.kratonsolution.tengkawang.backend.model.Role;
import com.kratonsolution.tengkawang.backend.model.RoleAccess;
import com.kratonsolution.tengkawang.backend.repository.MenuRepository;
import com.kratonsolution.tengkawang.backend.repository.RoleRepository;

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
public class RoleService implements ApplicationListener<MenuEvent> {

	@Autowired
	private RoleRepository repo;
	
	@Autowired
	private MenuRepository menuRepo;
	
	public List<Role> getAll(@NonNull List<String> organizations) {
		return repo.findAllByOrganizationIn(organizations);
	}
	
	public List<Role> getAll() {
		return repo.findAll();
	}
	
	public Optional<Role> getById(@NonNull String id) {
		return Optional.ofNullable(repo.getOne(id));
	}
	
	public Optional<Role> getByName(@NonNull String name) {
		return repo.findOneByName(name);
	}
	
	public void create(@NonNull String name, @NonNull String organization, @NonNull String comment, Set<RoleAccess> access) {
		
		Role role = new Role();
		role.setName(name);
		role.setOrganization(organization);
		role.setComment(comment);
		access.stream().forEach(com->role.grantAccessTo(com));
		
		repo.save(role);
		
		log.info("Creating new role {}", role.getName());
	}
	
	public void update(@NonNull Role role) {
		
		repo.save(role);
		log.info("Updating role {}", role.getName());
	}
	
	public void delete(@NonNull String id) {
		
		repo.deleteById(id);
		log.info("Deleting role {}", id);
	}

	@Override
	public void onApplicationEvent(MenuEvent event) {
		
		getAll().stream().forEach(menu -> {
			
			if(event.isDelete()) {
				menu.getGrantedAccess().removeIf(p->p.getMenu().getId().equals(event.getMenuID()));
			}
			else {
				
				Optional<Menu> opt = menuRepo.findById(event.getMenuID());
				if(opt.isPresent()) {
					
					RoleAccess access = new RoleAccess();
					access.setMenu(opt.get());
					
					menu.grantAccessTo(access);
				}
			}
			
			repo.save(menu);
		});
	}
}
