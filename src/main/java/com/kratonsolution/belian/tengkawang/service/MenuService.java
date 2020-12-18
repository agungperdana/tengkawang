package com.kratonsolution.belian.tengkawang.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.Menu;
import com.kratonsolution.belian.tengkawang.model.MenuEvent;
import com.kratonsolution.belian.tengkawang.repository.MenuRepository;

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
public class MenuService {

	@Autowired
	private MenuRepository repo;
	
	@Autowired
	private ApplicationEventPublisher pub;
	
	public List<Menu> getAll() {
		return repo.findAll();
	}
	
	public Optional<Menu> getById(@NonNull String id) {
		
		log.info("Find menu object with id {} {}", id, repo.getOne(id));
		return Optional.ofNullable(repo.getOne(id));
	}
	
	public Optional<Menu> getByName(@NonNull String name) {
		return repo.findOneByName(name);
	}
	
	public void create(@NonNull String name, String comment) {
		
		Menu menu = new Menu();
		menu.setName(name);
		menu.setComment(comment);
		
		repo.save(menu);
		
		pub.publishEvent(new MenuEvent(getClass().getName(), menu.getId(), false));
		
		log.info("Creating new menu {}", menu.getName());
	}
	
	public void update(@NonNull Menu menu) {
		
		repo.save(menu);
		log.info("Updating menu {}", menu.getName());
	}
	
	public void delete(@NonNull String id) {
		
		repo.deleteById(id);
		pub.publishEvent(new MenuEvent(getClass().getName(), id, true));
		log.info("Deleting menu ID:{}", id);
	}
}
