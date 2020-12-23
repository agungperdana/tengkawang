package com.kratonsolution.belian.tengkawang.controller.integration;

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
import com.kratonsolution.belian.tengkawang.integration.config.DeviceOption;
import com.kratonsolution.belian.tengkawang.integration.processor.OPERLOGProcessor;
import com.kratonsolution.belian.tengkawang.integration.processor.USERINFOProcessor;
import com.kratonsolution.belian.tengkawang.model.Device;
import com.kratonsolution.belian.tengkawang.service.AttendanceService;
import com.kratonsolution.belian.tengkawang.service.DeviceService;

import lombok.NonNull;
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
	private Cache<String, DeviceOption> deviceOptionCache;
	
	@Autowired
	private USERINFOProcessor userInfoProcessor;
	
	@Autowired
	private OPERLOGProcessor operLogProcessor;
	
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
			
			service.adhocRegistration(serial.get(), request.getRemoteAddr().toString());
		}
		else if(!opt.get().isOnline()){
			
			service.online(serial.get(), Optional.ofNullable(request.getRemoteAddr().toString()));
		}
		
		DeviceOption option = getOption(serial.get());

		StringBuilder response = new StringBuilder();
		response.append("GET OPTION FROM : "+serial.get()+"\n\r");
		response.append("Stamp="+option.getStamp().orElse("")+"\n\r");
		response.append("OpStamp="+option.getOpStamp().orElse("")+"\n\r");
		response.append("ErrorDelay="+option.getErrorDelay().orElse("")+"\n\r");
		response.append("Delay="+option.getDelay().orElse("")+"\n\r");
		response.append("TransTimes="+option.getTransTimes().orElse("")+"\n\r");
		response.append("TransInterval="+option.getTransInterval().orElse("")+"\n\r");
		response.append("TransFlag="+option.getTransFlag().orElse("")+"\n\r");
		response.append("Realtime="+option.getRealtime().orElse("")+"\n\r");
		response.append("Encrypt="+option.getEncrypt().orElse("")+"\n\r");

		return response.toString();
	}
	
	@PostMapping(value="/iclock/cdata", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String receive(@RequestParam("SN")String serial, 
					@RequestParam("table")String table, 
					@RequestParam("Stamp")Optional<String> stamp,
					@RequestBody Optional<String> body) {

		log.info("Receiving data from device{} with type {}", serial, table);
		
		if(body.isPresent()) {
			
			if(table.equalsIgnoreCase("USERINFO")) {
				userInfoProcessor.execute(serial, body.get());
			}
			else if(table.equals("ATTLOG")) {
				
				getOption(serial).setStamp(Optional.ofNullable(stamp.orElse("")));
				attService.onAttandanceEvent(serial, body);
			}
			else if(table.equals("OPERLOG")) {
				
				getOption(serial).setOpStamp(Optional.ofNullable(stamp.orElse("")));
				operLogProcessor.execute(serial, body.get());
			}
		}
		
		return "OK";
	}
	
	private DeviceOption getOption(@NonNull String serial) {
		
		DeviceOption option = deviceOptionCache.getIfPresent(serial);
		if(option == null) {
			option = new DeviceOption(serial);
		}
		
		return option;
	}
}
