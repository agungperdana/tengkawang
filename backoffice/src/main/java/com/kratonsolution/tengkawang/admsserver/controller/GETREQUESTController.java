package com.kratonsolution.tengkawang.admsserver.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.cache.Cache;
import com.kratonsolution.tengkawang.backend.command.Command;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@Controller
public class GETREQUESTController {

	@Autowired
	private Cache<String, Command> commandCache;
	
	@GetMapping(value = "/iclock/getrequest", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String onDeviceReconnect(@RequestParam("SN")String sn) {

		StringBuilder builder = new StringBuilder();
		commandCache.asMap().values().forEach(command -> {
			
			if(command.getDeviceSN().equals(sn) && (command.getLastSend() == null || command.getNextSchedule().compareTo(LocalDateTime.now()) <= 0)) {

				builder.append(command.getCommandString()).append("\r\n");

				command.setSendingCount(command.getSendingCount()+1);
				command.setLastSend(LocalDateTime.now());
				command.setNextSchedule(command.getLastSend().plus(3, ChronoUnit.MINUTES));
				
				log.info("ID {} : TYPE {} : CMD {}", command.getCode(), command.getClass().getSimpleName(), command.getCommandString());
			}
		});
		
		if(builder.toString().length() == 0) {
			builder.append("OK");
		}
		
		return builder.toString();
	}
	
	//SN=3397002440486&INFO=Ver%206.60%20Oct%2012%202011,5,3,71,192.168.1.201,10
}
