package com.kratonsolution.tengkawang.backend.service;

import java.util.List;
import java.util.Optional;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.kratonsolution.tengkawang.backend.model.User;
import com.kratonsolution.tengkawang.backend.repository.UserRepository;

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
	
	public List<User> getAll(@NonNull List<String> organization) {
		return repo.findAllByOrganizationIn(organization);
	}
	
	public List<User> getAll() {
		return repo.findAll();
	}
	
	public Optional<User> getById(@NonNull String id) {
		
		log.info("Find user object with id {} {}", id, repo.getOne(id));
		return Optional.ofNullable(repo.getOne(id));
	}
	
	public Optional<User> getByName(@NonNull String name) {
		return repo.findOneByName(name);
	}
	
	public void create(@NonNull String name, @NonNull String password1, @NonNull String password2, @NonNull String organization, @NonNull String role) {
		
		Preconditions.checkArgument(password1.equals(password2), "Passowrd 1 not equal Password 2");
		Preconditions.checkState(repo.findOneByName(name).isEmpty(), "User already exist");
		
		User user = new User();
		user.setName(name);
		user.setPassword(new StrongPasswordEncryptor().encryptPassword(password1));
		user.setRole(role);
		user.setOrganization(organization);
		
		repo.save(user);
		log.info("Creating new user {}", user.getName());
	}
	
	public void edit(@NonNull User user) {
		
		repo.save(user);
		log.info("Updating user {}", user.getName());
	}
	
	public void delete(@NonNull String id) {
		
		repo.deleteById(id);
		log.info("Deleting user ID:{}", id);
	}
}
