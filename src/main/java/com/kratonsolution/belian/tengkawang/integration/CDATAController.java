package com.kratonsolution.belian.tengkawang.integration;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.cache.Cache;
import com.kratonsolution.belian.tengkawang.model.Device;
import com.kratonsolution.belian.tengkawang.model.DeviceStatus;
import com.kratonsolution.belian.tengkawang.service.AttendanceService;
import com.kratonsolution.belian.tengkawang.service.DeviceService;
import com.kratonsolution.belian.tengkawang.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@Controller
public class CDATAController {

	@Autowired
	private DeviceService service;
	
	@Autowired
	private AttendanceService attService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Cache<String, Long> configCache;
	
	@GetMapping(value =  "/iclock/cdata", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String handshake(@RequestParam("SN")Optional<String> serial, 
						  @RequestParam("options")Optional<String> options, 
						  @RequestParam("pushver")Optional<String> pushVer,
						  @RequestParam("language")Optional<String> lang,
						  HttpServletRequest request) {

		log.info("Handshake event from device with ip {} and sn {}", request.getRemoteAddr(), serial.orElse("Empty"));
		
		Optional<Device> opt = service.getOneBySerial(serial.orElse(null));
		if(!opt.isPresent()) {
			
			Device device = new Device();
			device.setComment("Auto Generated on first handshake");
			device.setIp(request.getRemoteAddr().toString());
			device.setName(serial.orElse(""));
			device.setSerial(serial.orElse(null));
			device.setOrganization("DEFT ORG");
			device.setStatus(DeviceStatus.Online);
			
			service.add(device);
		}
		else if(!opt.get().isOnline()){
			
			opt.get().setIp(request.getRemoteAddr().toString());
			opt.get().setStatus(DeviceStatus.Online);
			service.update(opt.get());
		}
		
		Long stamp = configCache.getIfPresent(serial.get());
				
		StringBuilder response = new StringBuilder();
		response.append("GET OPTION FROM : "+serial.get()+"\n\r");
		response.append("Stamp="+(stamp!=null?stamp:"")+"\n\r");
		response.append("OpStamp="+(stamp!=null?stamp:"")+"\n\r");
		response.append("ErrorDelay=10\n\r");
		response.append("Delay=10\n\r");
		response.append("TransTimes=00:00;00:00\n\r");
		response.append("TransInterval=1");
		response.append("TransFlag=1111000000\n\r");
		response.append("Timezone=7\n\r");
		response.append("Realtime=1\n\r");
		response.append("Encrypt=0\n\r");
		
		return response.toString();
	}
	
	@PostMapping(value="/iclock/cdata", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String onAttendanceEvent(@RequestParam("SN")String serial, 
					@RequestParam("table")String table, 
					@RequestParam("Stamp")Optional<Long> stamp,
					@RequestBody Optional<String> body) {

		int row = 0;
		
		if(body.isPresent()) {
			
			if(table.equalsIgnoreCase("USERINFO")) {
				row = employeeService.extractAndSave(body.get());
			}
			else if(table.equals("ATTLOG")) {
				row = attService.onAttandanceEvent(serial, body);
			}
		}
		
		log.info("DATA FROM DEVICE {} WITH TYPE {} CONTAIN {} ENTRYS", serial, table, row);
		
		return "OK "+(row>0?row:"");
	}
}
