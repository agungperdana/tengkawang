package com.kratonsolution.belian.tengkawang.backoffice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.cache.Cache;
import com.kratonsolution.belian.tengkawang.integration.command.Command;
import com.kratonsolution.belian.tengkawang.integration.command.REBOOTCommand;
import com.kratonsolution.belian.tengkawang.model.Device;
import com.kratonsolution.belian.tengkawang.service.DeviceService;
import com.kratonsolution.belian.tengkawang.service.OrganizationService;
import com.kratonsolution.belian.tengkawang.util.CommandCodeGenerator;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class DeviceController {

	@Autowired
	private DeviceService service;
	
	@Autowired
	private OrganizationService orgService;
	
	@Autowired
	private Cache<String, Command> cache;
	
	@Autowired
	private CommandCodeGenerator generator;
	
	@GetMapping("/backoffice/devices")
	public String list(Model model) {
		model.addAttribute("devices", service.getAll());
		return "devices/table";
	}
	
	@GetMapping("/backoffice/devices-pre-add")
	public String preadd(Model model) {
		
		model.addAttribute("companys", orgService.getAll());
		return "devices/add";
	}
	
	@PostMapping("/backoffice/devices-add")
	public String add(@RequestParam("serial")String serial,
					@RequestParam("name")Optional<String> name,
					@RequestParam("ip")Optional<String> ip,
					@RequestParam("company")Optional<String> company,
					@RequestParam("option")Optional<String> option,
					@RequestParam("comment")Optional<String> comment) {
		
		Device device = new Device();
		device.setComment(comment.get());
		device.setIp(ip.get());
		device.setName(name.get());
		device.setOption(option.get());
		device.setOrganization(company.get());
		device.setSerial(serial);
		
		service.add(device);
		
		return "redirect:/backoffice/devices";
	}
	
	@GetMapping("/backoffice/devices-pre-edit")
	public String preedit(@RequestParam("id")String id, Model model) {
	
		model.addAttribute("device", service.getOneById(id).get());
		model.addAttribute("companys", orgService.getAll());
		
		return "devices/edit";
	}
	
	@PostMapping("/backoffice/devices-edit")
	public String edit(@RequestParam("id")String id, 
						@RequestParam("serial")String serial,
						@RequestParam("name")Optional<String> name,
						@RequestParam("ip")Optional<String> ip,
						@RequestParam("company")Optional<String> company,
						@RequestParam("option")Optional<String> option,
						@RequestParam("comment")Optional<String> comment,
						Model model) {
	
		Optional<Device> opt = service.getOneById(id);
		if(opt.isPresent()) {
			
			opt.get().setComment(comment.get());
			opt.get().setIp(ip.get());
			opt.get().setName(name.get());
			opt.get().setOption(option.get());
			opt.get().setOrganization(company.get());
			opt.get().setSerial(serial);
			opt.get().setOption(option.get());
			
			service.update(opt.get());
		}
		
		return "redirect:/backoffice/devices";
	}
	
	@GetMapping("/backoffice/devices-delete")
	public String delete(@RequestParam("id")String id) {
		
		service.delete(id);
		return "redirect:/backoffice/devices";
	}
	
	@GetMapping("/backoffice/devices-reboot")
	public String reboot(@RequestParam("id")String id) {
		
		Optional<Device> opt = service.getOneById(id);
		if(opt.isPresent()) {
			
			REBOOTCommand command = new REBOOTCommand(opt.get().getSerial(), generator.generate());
			cache.put(command.getCode(), command);
		}
		
		return "redirect:/backoffice/devices";
	}
}
