package com.kratonsolution.belian.tengkawang.server;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@Controller
public class DeviceCommunicationController {

	@GetMapping(value = "/iclock/getrequest", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String onDeviceReconnect(
			@RequestParam("SN")Optional<String> sn,
			@RequestParam("OpStamp")Optional<Instant> onStamp) {
		
		log.info("SN -> {}", sn.orElse("Empty"));
		log.info("Op Stamp -> {}", onStamp.orElse(Instant.now()));
		
		StringBuilder builder = new StringBuilder();
		builder.append("C:"+UUID.randomUUID().toString()+":INFO");
		
		return builder.toString();
	}
	
	@PostMapping(value = "/iclock/devicecmd", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String onDeviceCommandResponse(@RequestParam("SN") Optional<String> sn) {
	
		return "ok";
	}
	
	@PostMapping(value = "/iclock/cdata", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String onDeviceSendData(
			@RequestParam("SN")Optional<String> sn,
			@RequestParam("OpStamp")Optional<Instant> onStamp,
			@RequestBody Object object) {
		
		log.info("Body {}", object.getClass().getTypeName());
		return "ok";
	}
}
