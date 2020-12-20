package com.kratonsolution.belian.tengkawang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kratonsolution.belian.tengkawang.model.Employee;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	public Optional<Employee> findOneByNumber(@NonNull String number);

	public List<Employee> findAllByOrganizationIn(@NonNull List<String> organizations);
}
