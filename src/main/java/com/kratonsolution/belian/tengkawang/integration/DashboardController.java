package com.kratonsolution.belian.tengkawang.integration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/backoffice/home")
	public String home() {
		return "home";
	}
}
