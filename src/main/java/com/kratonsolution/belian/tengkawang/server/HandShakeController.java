package com.kratonsolution.belian.tengkawang.server;

import java.time.Instant;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kratonsolution.belian.tengkawang.repository.DeviceService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@Controller
public class HandShakeController {

	@Autowired
	private DeviceService service;
	
	@GetMapping(value =  "/iclock/cdata", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String handshake(@RequestParam("SN")Optional<String> serial, 
						  @RequestParam("options")Optional<String> options, HttpServletRequest request) {

		log.info("Handshake event from device with ip {} and sn {}", request.getRemoteAddr(), serial.orElse("Empty"));
		
		service.register(serial.orElse(null), request.getRemoteAddr().toString());
		
		StringBuilder response = new StringBuilder();
		response.append("GET OPTION FROM:"+serial.orElse(""));
		response.append("Stamp="+Instant.now().toString()+"\r\n");
		response.append("OpStamp="+Instant.now().toString()+"\r\n");
		response.append("ErrorDelay=60\r\n");
		response.append("Delay=30\r\n");
		response.append("TransTimes=00:00;14:05"+"\r\n");
		response.append("TransInterval=1"+"\r\n");
		response.append("TransFlag=1111000000\r\n");
		response.append("Realtime=1\r\n");
		response.append("Encrypt=0\r\n");
		
		return response.toString();
	}
}
