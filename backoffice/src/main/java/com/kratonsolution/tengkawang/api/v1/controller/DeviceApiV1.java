package com.kratonsolution.tengkawang.api.v1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.cache.Cache;
import com.kratonsolution.tengkawang.backend.command.Command;
import com.kratonsolution.tengkawang.backend.command.REBOOTCommand;
import com.kratonsolution.tengkawang.backend.model.Device;
import com.kratonsolution.tengkawang.backend.service.DeviceService;
import com.kratonsolution.tengkawang.backend.util.CommandCodeGenerator;
import com.kratonsolution.tengkawang.util.Securitys;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@RestController
public class DeviceApiV1 {

	@Autowired
	private DeviceService service;

	@Autowired
	private Cache<String, Command> cache;

	@Autowired
	private CommandCodeGenerator generator;

	@Secured("ROLE_API_V1_DEVICE_READ")
	@GetMapping("/api/v1/devices")
	public List<Device> list(Authentication auth) {

		return service.getAll(Securitys.getOrganizations(auth.getPrincipal()));
	}

	@Secured("ROLE_API_V1_DEVICE_CREATE")
	@PostMapping("/api/v1/devices-add")
	public Device add(@RequestBody Device device) {

		service.add(device);
		return device;
	}

	@Secured("ROLE_API_V1_DEVICE_UPDATE")
	@PostMapping("/api/v1/devices-update")
	public Device edit(@RequestBody Device dev) {

		service.update(dev.getId(), 
				dev.getSerial(), 
				Optional.ofNullable(dev.getName()), 
				Optional.ofNullable(dev.getIp()), 
				Optional.ofNullable(dev.getOrganization()), 
				Optional.ofNullable(dev.getOption()), 
				Optional.ofNullable(dev.getComment()));
		return dev;
	}

	@Secured("ROLE_API_V1_DEVICE_DELETE")
	@DeleteMapping("/api/v1/devices-delete/{id}")
	public Device delete(@PathVariable String id) {

		Optional<Device> opt = service.getById(id);
		service.delete(id);
		return opt.get();
	}

	@Secured("ROLE_API_V1_DEVICE_UPDATE")
	@GetMapping("/api/v1/devices-reboot/{id}")
	public Device reboot(@PathVariable String id) {

		Optional<Device> opt = service.getById(id);
		if(opt.isPresent()) {

			REBOOTCommand command = new REBOOTCommand(opt.get().getSerial(), generator.generate());
			cache.put(command.getCode(), command);
		}

		return opt.get();
	}
}
