package com.kratonsolution.belian.tengkawang.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.Attendance;
import com.kratonsolution.belian.tengkawang.repository.AttendanceRepository;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AttendanceService {

	@Autowired
	private AttendanceRepository repo;

	public List<Attendance> getAll() {
		return repo.findAll();
	}

	public List<Attendance> getAllByNumberAndDate(@NonNull String employeeNumber, 
			@NonNull Instant start, 
			@NonNull Instant end) {

		return repo.findAllByNumberAndDate(employeeNumber, start, end);
	}

	public List<Attendance> getAllByNameAndDate(@NonNull String employeeName, 
			@NonNull Instant start, 
			@NonNull Instant end) {

		return repo.findAllByNameAndDate(employeeName, start, end);
	}
	
	public List<Attendance> getAllByNumber(@NonNull String employeeNumber) {
		return repo.findALlByEmployeeNumber(employeeNumber);
	}

	public List<Attendance> getAllByName(@NonNull String employeeName) {
		return repo.findALlByEmployeeName(employeeName);
	}
}
