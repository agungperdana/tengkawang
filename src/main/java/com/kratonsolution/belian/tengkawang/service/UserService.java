package com.kratonsolution.belian.tengkawang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.User;
import com.kratonsolution.belian.tengkawang.repository.UserRepository;

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
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> getAllUsers() {
		return repo.findAll();
	}
	
	public User getOneById(@NonNull String id) {
		
		log.info("Find user object with id {} {}", id, repo.getOne(id));
		return repo.getOne(id);
	}
	
	public void edit(@NonNull User user) {
		
	}
}
