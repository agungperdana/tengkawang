package com.kratonsolution.belian.tengkawang.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.Attendance;
import com.kratonsolution.belian.tengkawang.model.AttendanceEventType;
import com.kratonsolution.belian.tengkawang.model.AttendanceOrganizationChangeEvent;
import com.kratonsolution.belian.tengkawang.model.Device;
import com.kratonsolution.belian.tengkawang.model.DeviceOrganizationChangeEvent;
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

	@Autowired
	private ApplicationEventPublisher pub;

	public List<Attendance> getAll() {
		return repo.findAll();
	}

	public List<Attendance> getAll(@NonNull List<String> organizations) {
		return repo.findAllByOrganizationIn(organizations);
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

	public void add(@NonNull Attendance attendance) {
		repo.save(attendance);
	}
	
	public int onAttandanceEvent(@NonNull String sn, Optional<String> body) {

		if(body.isPresent()) {

			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			Arrays.asList(body.get().split("\\n")).forEach(row -> {

				String[] cols = row.trim().split("\t");

				Optional<Device> opt = deviceService.getBySerial(sn);
				if(opt.isPresent()) {

					Optional<Employee> emp = employeeService.getByNumber(cols[0]);
					if(emp.isEmpty()) {
						emp = employeeService.createDefaultEmployee(cols[0], opt.get().getOrganization());
					}

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
						attendance.setOrganization(emp.get().getOrganization());

						repo.save(attendance);
						log.info("Creating new Attendance data {}", attendance.getEmployeeName());
					}
					else {
						log.info("Data already exist, skipping....");
					}
				}
			});
		}

		return 0;
	}

	private AttendanceEventType getType(@NonNull String device, @NonNull String employeeNumber, @NonNull LocalDateTime eventTime) {

		LocalDate date = eventTime.atZone(ZoneId.systemDefault()).toLocalDate();
		LocalTime time = eventTime.atZone(ZoneId.systemDefault()).toLocalTime().truncatedTo(ChronoUnit.MINUTES);

		//finding all matching worktime config based on date
		List<WorkTime> valid = workTimeService.getAll().stream().filter(wt -> {
			return (wt.getValidFrom().compareTo(date) <= 0 && wt.getValidTo() == null) || 
					(wt.getValidFrom().compareTo(date) <=0 && wt.getValidTo().compareTo(date) >= 0);
		}).collect(Collectors.toList());

		log.info("Valid WorkTime {}", valid);

		//finding first matching worktime config based on time
		Optional<WorkTime> matchOp = valid.stream()
				.filter(wk->{
					return ((wk.getStart().compareTo(time) < 1) && (wk.getEnd().compareTo(time) > -1));
				})
				.findAny();

		if(matchOp.isEmpty()) {
			return AttendanceEventType.UNKNOWN;
		}

		List<Attendance> ondb = repo.findAllByDeviceAndEmployeeNumberAndDate(device, employeeNumber, date);

		if(matchOp.get().getType().equals(WorkTimeType.REGULER)) {

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

	@EventListener(classes = DeviceOrganizationChangeEvent.class)
	public void onDeviceOrganizationChange(@NonNull DeviceOrganizationChangeEvent event) {

		List<String> pins = new ArrayList<>();

		repo.findAllByDevice(event.getDeviceSerial()).forEach(att -> {

			att.setOrganization(event.getOrganizationName());
			repo.save(att);

			pins.add(att.getEmployeeNumber());
		});

		if(!pins.isEmpty()) {

			pub.publishEvent(new AttendanceOrganizationChangeEvent(getClass().getName(), pins, event.getOrganizationName()));
		}

		log.info("Organizaation Name changed, updating attendance data with {}", event.getOrganizationName());
	}
}
