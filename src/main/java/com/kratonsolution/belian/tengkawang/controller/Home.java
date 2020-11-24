package com.kratonsolution.belian.tengkawang.controller;

import com.kratonsolution.belian.tengkawang.EntryPoint;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	
	@GetMapping("/")
	public String welcome(){
		return "welcome users";
	}
	
	@GetMapping("/{text}")
	public String req(@PathVariable String text) {
		
		EntryPoint.memory.add(text);
		return text;
	}
	
	@PostMapping("/{text}")
	public void post(@PathVariable String text) {
		EntryPoint.memory.add(text);
	}
	
	@GetMapping("/shows")
	public String shows() {
		
		return EntryPoint.memory.toString();
	}
}
