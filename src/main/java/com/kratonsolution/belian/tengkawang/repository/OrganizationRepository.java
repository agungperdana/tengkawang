package com.kratonsolution.belian.tengkawang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kratonsolution.belian.tengkawang.model.Organization;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface OrganizationRepository extends JpaRepository<Organization, String> {

	public Optional<Organization> findOneByName(@NonNull String name);
	
	@Query("SELECT org FROM Organization org WHERE org.name != ?0")
	public List<Organization> findAllExcludeName(@NonNull String name);
}
