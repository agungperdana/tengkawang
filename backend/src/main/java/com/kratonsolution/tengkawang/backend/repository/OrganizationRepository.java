package com.kratonsolution.tengkawang.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kratonsolution.tengkawang.backend.model.Organization;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface OrganizationRepository extends JpaRepository<Organization, String> {

	public Optional<Organization> findOneByName(@NonNull String name);
	
	@Query("SELECT org FROM Organization org WHERE org.name != ?1")
	public List<Organization> findAllExcludeName(@NonNull String name);
	
	@Query("SELECT org FROM Organization org WHERE org.id != ?1")
	public List<Organization> findAllExcludeId(@NonNull String id);
	
	public List<Organization> findAllByParent(@NonNull String parent);
}
