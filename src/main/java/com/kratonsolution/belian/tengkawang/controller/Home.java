package com.kratonsolution.belian.tengkawang.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class Home {
	
	@RequestMapping("/")
	public String welcome(){
		return "welcome users";
	}
}
