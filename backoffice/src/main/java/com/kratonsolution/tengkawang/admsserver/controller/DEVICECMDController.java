package com.kratonsolution.tengkawang.admsserver.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.cache.Cache;
import com.kratonsolution.tengkawang.backend.command.Command;
import com.kratonsolution.tengkawang.backend.util.ValueUtil;

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
		
		String[] rows = body.split("\\n");
		Arrays.asList(rows).forEach(row -> {
			
			String code = ValueUtil.getValueWtihAnd("ID", row);
			String rtn = ValueUtil.getValueWtihAnd("Return", row);
			String cmd = ValueUtil.getValueWtihAnd("CMD", row);
			
			Command command = commandCache.getIfPresent(code);
			if(command != null) {
				
				commandCache.invalidate(command.getCode());
				
				log.info("ID {} : TYPE {} : STATUS {} : REMOVED FROM CACHE", code, cmd, rtn);
				
				command.getChilds().forEach(child->commandCache.put(child.getCode(), child));
			}
		});
		
		return "OK";
	}
}
