package com.kratonsolution.tengkawang.admsserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.cache.Cache;
import com.kratonsolution.tengkawang.backend.command.Command;
import com.kratonsolution.tengkawang.backend.command.GenericCommand;
import com.kratonsolution.tengkawang.backend.command.REBOOTCommand;
import com.kratonsolution.tengkawang.backend.service.DeviceService;
import com.kratonsolution.tengkawang.backend.util.CommandCodeGenerator;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class ROOTController {
	
	@Autowired
	private Cache<String, Command> cache;
	
	@Autowired
	private CommandCodeGenerator generator;
	
	@Autowired
	private DeviceService deviceService;
	
	@GetMapping("/iclock/reboot")
	public String reboot() {
		
		deviceService.getAll().stream().forEach(dev ->{
			
			REBOOTCommand command = new REBOOTCommand(dev.getSerial(), generator.generate());
			cache.put(command.getCode(), command);
		});
		
		return "redirect:/backoffice/devices";
	}
	
	@GetMapping("/iclock/check")
	public String check() {
		
		deviceService.getAll().stream().forEach(dev ->{
			
			GenericCommand command = new GenericCommand(dev.getSerial(), generator.generate(), "CHECK");
			cache.put(command.getCode(), command);
		});
		
		return "redirect:/backoffice/home";
	}
	
	@GetMapping("/iclock/commandtester")
	public String commandTester(@RequestParam("command")String command) {
		
		deviceService.getAll().stream().forEach(dev ->{
			
			Command obj = new GenericCommand(dev.getSerial(), generator.generate(), command);
			cache.put(obj.getCode(), obj);
		});
		
		return "redirect:/backoffice/home";
	}
}
