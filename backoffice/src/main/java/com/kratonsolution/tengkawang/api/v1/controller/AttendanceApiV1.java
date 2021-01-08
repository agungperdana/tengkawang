package com.kratonsolution.tengkawang.api.v1.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.tengkawang.backend.model.Attendance;
import com.kratonsolution.tengkawang.backend.service.AttendanceService;
import com.kratonsolution.tengkawang.util.Securitys;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class AttendanceApiV1 {

	@Autowired
	private AttendanceService service;
	
	@GetMapping("/api/v1/attendances")
	public List<Attendance> list(Authentication auth) {
		return service.getAll(Securitys.getOrganizations(auth.getPrincipal()));
	}
	
	@GetMapping("/api/v1/attendances-allByNumber/{employeeNumber}")
	public List<Attendance> allByNumber(@PathVariable String employeeNumber) {
		
		return service.getAllByNumber(employeeNumber);
	}
	
	@GetMapping("/api/v1/attendances/{employeeName}")
	public List<Attendance> allByName(@PathVariable String employeeName) {
		
		return service.getAllByNumber(employeeName);
	}
	
	@GetMapping(value="/api/v1/attendances", params = {"employeeName", "start", "end"})
	public String allByNameAndDate(@RequestParam("employeeName") String employeeName,
			@RequestParam("start")Instant start,
			@RequestParam("end")Instant end,
			Model model) {
		
		model.addAttribute("attendances", service.getAllByNameAndDate(employeeName, start, end));
		return "attendances/table";
	}
	
	@GetMapping("/api/v1/attendances-allByNumberAndDate/{employeeNumber}/{start}/{end}")
	public List<Attendance> allByNumberAndDate(@PathVariable("employeeNumber") String employeeNumber,
			@PathVariable("start")Instant start, @PathVariable("end")Instant end) {
		
		return service.getAllByNumberAndDate(employeeNumber, start, end);
	}
}
