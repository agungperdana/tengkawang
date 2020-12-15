package com.kratonsolution.belian.tengkawang.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.kratonsolution.belian.tengkawang.model.Attendance;
import com.kratonsolution.belian.tengkawang.model.AttendanceEventType;
import com.kratonsolution.belian.tengkawang.model.Device;
import com.kratonsolution.belian.tengkawang.model.Employee;
import com.kratonsolution.belian.tengkawang.model.VerificationType;
import com.kratonsolution.belian.tengkawang.model.WorkTime;
import com.kratonsolution.belian.tengkawang.model.WorkTimeType;
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
	
	@Autowired
	private WorkTimeService workTimeService;

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
	
	public int onAttandanceEvent(@NonNull String sn, Optional<String> body) {
		
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
						
						log.info("Attendance date {}", localDateTime);
						
						Optional<Attendance> ondb = repo.findOneByDeviceAndEmployeeNumberAndDateAndTime(
														opt.get().getSerial(), 
														emp.get().getNumber(), 
														localDateTime.atZone(ZoneId.systemDefault()).toLocalDate(), 
														localDateTime.atZone(ZoneId.systemDefault()).toLocalTime());
						
						if(ondb.isEmpty()) {
							
							Attendance attendance = new Attendance();
							attendance.setDevice(opt.get().getSerial());
							attendance.setEmployeeNumber(emp.get().getNumber());
							attendance.setEmployeeName(emp.get().getFullName());
							attendance.setEventLocation(opt.get().getOrganization());
							attendance.setDate(localDateTime.atZone(ZoneId.systemDefault()).toLocalDate());
							attendance.setTime(localDateTime.atZone(ZoneId.systemDefault()).toLocalTime());
							attendance.setType(getType(opt.get().getSerial(), emp.get().getNumber(), localDateTime));
							attendance.setVerificationType(cols[3].equals("1")?VerificationType.Fingerprint:VerificationType.Password);
							
							repo.save(attendance);
							log.info("Creating new Attendance data {}", attendance.getEmployeeName());
						}

					}
				}
			}
			
			return rows.length;
		}
		
		return 0;
	}
	
	private AttendanceEventType getType(@NonNull String device, @NonNull String employeeNumber, @NonNull LocalDateTime eventTime) {
		
		LocalDate date = eventTime.atZone(ZoneId.systemDefault()).toLocalDate();
		LocalTime time = eventTime.atZone(ZoneId.systemDefault()).toLocalTime();
				
		//finding all matching worktime config based on date
		List<WorkTime> valid = workTimeService.getAll().stream().filter(wt -> {
			return (date.compareTo(wt.getValidFrom()) > 0 && wt.getValidTo() == null) || 
			(date.compareTo(wt.getValidFrom()) >= 0 && date.compareTo(wt.getValidTo()) <= 0);
		}).collect(Collectors.toList());
		
		//finding first matching worktime config based on time
		Optional<WorkTime> op = valid.stream()
									.filter(wk->time.compareTo(wk.getStart())>=0 && time.compareTo(wk.getEnd())<=0)
									.findFirst();

		Preconditions.checkState(op.isPresent(), "Work Time Config does not exist, please go to Work Time menu first.");
		
		List<Attendance> ondb = repo.findAllByDeviceAndEmployeeNumberAndDate(device, employeeNumber, date);
		
		if(op.get().getType().equals(WorkTimeType.REGULER)) {
			
			if(ondb.size() % 2 > 0) {
				return AttendanceEventType.OUT;
			}
			else {
				return AttendanceEventType.IN;
			}
		}
		else {
			
			if(ondb.size() % 2 > 0) {
				return AttendanceEventType.OVERTIME_IN;
			}
			else {
				return AttendanceEventType.OVERTIME_OUT;
			}
		}
	}
}
