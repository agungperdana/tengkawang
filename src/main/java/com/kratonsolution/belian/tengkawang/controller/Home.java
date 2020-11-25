package com.kratonsolution.belian.tengkawang.controller;

import com.kratonsolution.belian.tengkawang.EntryPoint;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class Home {
	
	@GetMapping("/")
	public String welcome(HttpServletRequest req){
		
		writeIntoMemory(req,"get-request");
		
		return "welcome users";
	}
	
	@GetMapping("/{text}")
	public String req(HttpServletRequest req) {
		
		writeIntoMemory(req, "get-request");
		return "get-req()";
	}
	
	@PostMapping("/{text}")
	public String post(HttpServletRequest req, @RequestBody String body) {
		
		writeIntoMemory(req, body);
		return "Post-req()";
	}
	
	@GetMapping("/shows")
	public String shows() {
		
		return EntryPoint.memory.toString();
	}
	
	private void writeIntoMemory(HttpServletRequest req, String body) {
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("type", req.getContentType());
		map.put("param",req.getParameterMap().toString());
		map.put("body", body);
		
		EntryPoint.memory.add(map);
	}
}
