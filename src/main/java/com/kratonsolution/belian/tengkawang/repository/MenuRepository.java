package com.kratonsolution.belian.tengkawang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kratonsolution.belian.tengkawang.model.Menu;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface MenuRepository extends JpaRepository<Menu, String>{
	
	public Optional<Menu> findOneByName(@NonNull String name);
}
