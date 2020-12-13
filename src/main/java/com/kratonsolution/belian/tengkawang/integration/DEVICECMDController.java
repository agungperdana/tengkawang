package com.kratonsolution.belian.tengkawang.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.cache.Cache;
import com.kratonsolution.belian.tengkawang.integration.command.Command;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@Controller
public class DEVICECMDController {

	@Autowired
	private Cache<String, Command> commandCache;
	
	@PostMapping(value = "/iclock/devicecmd", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String response(@RequestParam("SN")String sn, @RequestBody String body) {
		
		log.info("Device sending reponse");
		
		String rows[] = body.trim().split("\r\n");
		for(int idx=0;idx<rows.length;idx++) {
			log.info("Command response {}", rows[idx]);
		}
		
		return "OK";
	}
}
