package com.kratonsolution.belian.tengkawang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kratonsolution.belian.tengkawang.model.Department;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface DepartmentRepository extends JpaRepository<Department, String>{

	public Optional<Department> findOneByName(@NonNull String name);
	
	public List<Department> findAllByOrganizationIn(@NonNull List<String> organizations);
}
