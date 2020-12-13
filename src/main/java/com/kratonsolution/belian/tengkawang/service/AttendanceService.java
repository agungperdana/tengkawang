package com.kratonsolution.belian.tengkawang.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.Attendance;
import com.kratonsolution.belian.tengkawang.model.AttendanceEventType;
import com.kratonsolution.belian.tengkawang.model.Device;
import com.kratonsolution.belian.tengkawang.model.Employee;
import com.kratonsolution.belian.tengkawang.model.VerificationType;
import com.kratonsolution.belian.tengkawang.repository.AttendanceRepository;

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
public class AttendanceService {

	@Autowired
	private AttendanceRepository repo;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private EmployeeService employeeService;

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
	
	public int onAttandanceEvent(@NonNull String sn, long time, Optional<String> body) {
		
		if(body.isPresent()) {
			
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
			String[] rows = body.get().trim().split("\n");
			for(int idx=0;idx<rows.length;idx++) {
				
				String[] cols = rows[idx].trim().split("\t");
				
				Optional<Device> opt = deviceService.getOneBySerial(sn);
				if(opt.isPresent()) {
					
					Optional<Employee> emp = employeeService.getOneByNumber(cols[0]);
					if(emp.isPresent()) {
						
						TemporalAccessor accessor = format.parse(cols[1]);
						LocalDateTime localDateTime = LocalDateTime.from(accessor);
						ZonedDateTime zoned = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
						
						Attendance attendance = new Attendance();
						attendance.setDevice(opt.get().getSerial());
						attendance.setEmployeeNumber(emp.get().getNumber());
						attendance.setEmployeeName(emp.get().getFullName());
						attendance.setEventLocation(opt.get().getOrganization());
						attendance.setTime(Instant.from(zoned));
						attendance.setType(AttendanceEventType.IN);
						attendance.setVerificationType(cols[3].equals("1")?VerificationType.Fingerprint:VerificationType.Password);
						
						repo.save(attendance);
						log.info("Creating new Attendance data {}", attendance.getEmployeeName());
					}
				}
			}
			
			return rows.length;
		}
		
		return 0;
	}
}
