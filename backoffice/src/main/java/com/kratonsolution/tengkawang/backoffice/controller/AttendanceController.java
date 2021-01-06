package com.kratonsolution.tengkawang.backoffice.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.tengkawang.backend.service.AttendanceService;
import com.kratonsolution.tengkawang.util.Securitys;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class AttendanceController {

	@Autowired
	private AttendanceService service;
	
	@GetMapping("/backoffice/attendances")
	public String list(Authentication auth, Model model) {
		
		model.addAttribute("attendances", service.getAll(Securitys.getOrganizations(auth.getPrincipal())));
		return "attendances/table";
	}
	
	@GetMapping(value="/backoffice/attendances", params = "employeeNumber")
	public String allByNumber(@RequestParam("employeeNumber") String employeeNumber,Model model) {
		
		model.addAttribute("attendances", service.getAllByNumber(employeeNumber));
		return "attendances/table";
	}
	
	@GetMapping(value="/backoffice/attendances", params = "employeeName")
	public String allByName(@RequestParam("employeeName") String employeeName,Model model) {
		
		model.addAttribute("attendances", service.getAllByNumber(employeeName));
		return "attendances/table";
	}
	
	@GetMapping(value="/backoffice/attendances", params = {"employeeName", "start", "end"})
	public String allByNameAndDate(@RequestParam("employeeName") String employeeName,
			@RequestParam("start")Instant start,
			@RequestParam("end")Instant end,
			Model model) {
		
		model.addAttribute("attendances", service.getAllByNameAndDate(employeeName, start, end));
		return "attendances/table";
	}
	
	@GetMapping(value="/backoffice/attendances", params = {"employeeNumber", "start", "end"})
	public String allByNumberAndDate(@RequestParam("employeeNumber") String employeeNumber,
			@RequestParam("start")Instant start,
			@RequestParam("end")Instant end,
			Model model) {
		
		model.addAttribute("attendances", service.getAllByNumberAndDate(employeeNumber, start, end));
		return "attendances/table";
	}
}
