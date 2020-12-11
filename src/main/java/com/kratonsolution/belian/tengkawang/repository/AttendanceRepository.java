package com.kratonsolution.belian.tengkawang.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kratonsolution.belian.tengkawang.model.Attendance;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface AttendanceRepository extends JpaRepository<Attendance, String>{

	public List<Attendance> findALlByEmployeeNumber(@NonNull String employeeNumber);
	
	public List<Attendance> findALlByEmployeeName(@NonNull String employeeName);
	
	@Query("FROM Attendance att WHERE att.employeeName = ?1 AND att.time BETWEEN ?2 AND ?3 ORDER BY att.time ASC")
	public List<Attendance> findAllByNameAndDate(@NonNull String employeeName, @NonNull Instant start, @NonNull Instant end);
	
	@Query("FROM Attendance att WHERE att.employeeName = ?1 AND att.time BETWEEN ?2 AND ?3 ORDER BY att.time ASC")
	public List<Attendance> findAllByNumberAndDate(@NonNull String employeeNumber, @NonNull Instant start, @NonNull Instant end);
}
