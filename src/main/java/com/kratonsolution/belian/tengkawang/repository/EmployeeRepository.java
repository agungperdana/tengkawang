package com.kratonsolution.belian.tengkawang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kratonsolution.belian.tengkawang.model.Employee;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
