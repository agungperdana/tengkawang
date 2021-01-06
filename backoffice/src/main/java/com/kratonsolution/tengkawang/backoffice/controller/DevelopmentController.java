package com.kratonsolution.tengkawang.backoffice.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.tengkawang.backend.model.Attendance;
import com.kratonsolution.tengkawang.backend.model.AttendanceEventType;
import com.kratonsolution.tengkawang.backend.model.Device;
import com.kratonsolution.tengkawang.backend.model.Employee;
import com.kratonsolution.tengkawang.backend.model.EmployeeGroup;
import com.kratonsolution.tengkawang.backend.model.Organization;
import com.kratonsolution.tengkawang.backend.model.Privilege;
import com.kratonsolution.tengkawang.backend.model.VerificationType;
import com.kratonsolution.tengkawang.backend.service.AttendanceService;
import com.kratonsolution.tengkawang.backend.service.EmployeeService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class DevelopmentController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AttendanceService attendanceService;
	
	@GetMapping("/backoffice/developments")
	public String developments() {
		return "developments/table";
	}
	
	@GetMapping("/backoffice/developments-dummy-employee")
	public String employee(@RequestParam("row") int row) {
		
		for(int idx=0;idx<row;idx++) {
			
			Employee employee = new Employee();
			employee.setCard("CARD "+idx);
			employee.setDepartment("DEPARTMENT "+idx);
			employee.setFullName("Employee "+idx);
			employee.setGroup(EmployeeGroup.UserTimeZone);
			employee.setNumber(""+idx);
			employee.setOnDeviceName("EMP"+idx);
			employee.setOrganization(Organization.DEFAULT);
			employee.setPrivilege(Privilege.User);
			
			employeeService.add(employee);
		}
		
		return "redirect:/backoffice/developments";
	}
	
	@GetMapping("/backoffice/developments-dummy-attendance")
	public String attendance(@RequestParam("row") int row) {
		
		for(int idx=0;idx<row;idx++) {
			
			Attendance attendance = new Attendance();
			attendance.setDate(LocalDate.now());
			attendance.setDevice(Device.DUMMY);
			attendance.setEmployeeName("EMP"+idx);
			attendance.setEmployeeNumber(""+idx);
			attendance.setEventLocation(Organization.DEFAULT);
			attendance.setOrganization(Organization.DEFAULT);
			attendance.setTime(LocalTime.now());
			attendance.setType(row%2==0?AttendanceEventType.OUT:AttendanceEventType.IN);
			attendance.setVerificationType(VerificationType.Fingerprint);
			
			attendanceService.add(attendance);
		}
		
		return "redirect:/backoffice/developments";
	}
}
