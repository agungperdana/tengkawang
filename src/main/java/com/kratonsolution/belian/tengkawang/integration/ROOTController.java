package com.kratonsolution.belian.tengkawang.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.common.cache.Cache;
import com.kratonsolution.belian.tengkawang.integration.command.Command;
import com.kratonsolution.belian.tengkawang.integration.command.REBOOTCommand;
import com.kratonsolution.belian.tengkawang.service.DeviceService;
import com.kratonsolution.belian.tengkawang.util.CommandCodeGenerator;

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
}
