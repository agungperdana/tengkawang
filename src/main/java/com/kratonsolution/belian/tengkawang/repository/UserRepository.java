package com.kratonsolution.belian.tengkawang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kratonsolution.belian.tengkawang.model.User;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface UserRepository extends JpaRepository<User, String> {

	public Optional<User> findOneByName(@NonNull String name);
	
	public List<User> findAllByOrganizationIn(@NonNull List<String> organization);
}
